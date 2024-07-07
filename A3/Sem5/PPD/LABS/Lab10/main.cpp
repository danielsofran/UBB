#include <iostream>
#include <filesystem>
#include <fstream>
#include <string>
#include <chrono>
#include <map>
#include <mutex>
#include <functional>
#include <atomic>
#include <condition_variable>
#include <thread>

using namespace std;

template<typename T>
class SortedLinkedList
{
private:
    struct Node
    {
        T item;
        Node* next;
        std::mutex mutex;

        explicit Node(const T& item, Node* next=nullptr) : item(item), next(next), mutex() { }
    };

    Node* lock_node(Node* n) {
        if (n) {
            n->mutex.lock();
        }
        return n;
    }

    void unlock_node(Node* n) {
        if (n) {
            n->mutex.unlock();
        }
    }

    template<typename... Args>
    void unlock_node(Node* n, Args... args)
    {
        unlock_node(n);
        unlock_node(args...);
    }

    Node* start;
    Node* end;

public:
    SortedLinkedList()
    {
        end = new Node(T());
        start = new Node(T(), end);
    }

    virtual bool is_sorted(const T& x, const T& y) = 0;

    bool find(std::function<bool(const T&)> pred)
    {
        Node* n0 = lock_node(start);
        Node* n1 = lock_node(n0->next);

        if (n1 == end) // empty list
        {
            unlock_node(n0, n1);
            return false;
        }

        Node* n2 = lock_node(n1->next);

        while (n2 != nullptr)
        {
            if (pred(n1->item))
            {
                unlock_node(n0, n1, n2);
                return true;
            }

            unlock_node(n0);
            n0 = n1;
            n1 = n2;
            n2 = lock_node(n2->next);
        }

        unlock_node(n0, n1, n2);
        return false;
    }

    bool find_and_remove(std::function<bool(const T&)> pred, T& result)
    {
        Node* n0 = lock_node(start);
        Node* n1 = lock_node(n0->next);

        if (n1 == end) // empty list
        {
            unlock_node(n0, n1);
            return false;
        }

        Node* n2 = lock_node(n1->next);

        while (n2 != nullptr)
        {
            if (pred(n1->item))
            {
                result = n1->item;
                n0->next = n2;
                unlock_node(n0, n1, n2);
                delete n1;
                return true;
            }

            unlock_node(n0);
            n0 = n1;
            n1 = n2;
            n2 = lock_node(n2->next);
        }

        unlock_node(n0, n1, n2);

        return false;
    }

    void insert(const T& item)
    {
        Node* target = new Node(item);

        Node* n0 = lock_node(start);
        Node* n1 = lock_node(n0->next);

        if (n1 == end) // empty list
        {
            n0->next = target;
            target->next = n1;
            unlock_node(n0, n1);
            return;
        }

        if (is_sorted(target->item, n1->item))
        {
            // insert before
            n0->next = target;
            target->next = n1;
            unlock_node(n0, n1);
            return;
        }

        unlock_node(n0), n0 = n1, n1 = lock_node(n0->next);


        while (n1 != end)
        {
            if (is_sorted(n0->item, target->item) && is_sorted(target->item, n1->item))
            {
                n0->next = target;
                target->next = n1;
                unlock_node(n0, n1);
                return;
            }

            unlock_node(n0), n0 = n1, n1 = lock_node(n0->next);
        }

        // insert at end
        n0->next = target;
        target->next = n1;
        unlock_node(n0, n1);
    }

    ~SortedLinkedList()
    {
        for (Node* n = start, *tmp; n != nullptr; )
        {
            tmp = n;
            n = n->next;
            delete tmp;
        }
    }

    void iterate_sync(std::function<void(const T&)> callback) const
    {
        for (Node* n = start->next; n != end; n = n->next)
            callback(n->item);
    }


};

template<typename T>
class ThreadSafeQueue
{
private:
    int capacity;

    T* items;
    int start = 0, end = 0;
    int size = 0;

    bool is_active=true;

    mutable std::mutex mx;
    std::condition_variable is_empty;
    std::condition_variable is_full;

    std::atomic<int> dequeueing = 0;

public:
    explicit ThreadSafeQueue(int capacity = 50) : capacity(capacity)
    {
        items = new T[capacity]();
    }

    void enqueue(const T& item)
    {
        std::unique_lock<std::mutex> lock(mx);

        while (size == capacity)
        {
            is_full.wait(lock);
        }

        items[end] = item;
        end = (end + 1) % capacity;
        size++;

        is_empty.notify_one();

    }

    bool dequeue(T& item)
    {
        std::unique_lock<std::mutex> lock(mx);
        dequeueing++;

        while (is_active && size == 0)
        {
            is_empty.wait(lock);
        }
        dequeueing--;

        if (!is_active)
            if (size == 0) return false;

        item = items[start];
        start = (start + 1) % capacity;
        size--;

        is_full.notify_one();

        return true;
    }

    void close()
    {
        std::unique_lock<std::mutex> lock(mx);
        is_active = false;
        lock.unlock();


        while (dequeueing.load() != 0)
        {
            lock.lock();
            is_empty.notify_all();
            lock.unlock();
        }
    }

    ~ThreadSafeQueue()
    {
        delete[] items;
    }
};

template<typename T>
class ProducerConsumer
{
private:
    ThreadSafeQueue<T> queue;
    std::atomic<int> cnt_put = 0;
public:
    void put(const T& item)
    {
        queue.enqueue(item);
        cnt_put++;
    }

    virtual void process(const T& item) = 0;

    void run()
    {
        T item=T();
        while (queue.dequeue(item))
        {
            process(item);
        }
    }

    void stop()
    {
        printf("Stopping...\n");
        queue.close();
    }

    virtual ~ProducerConsumer()
    {
        printf("Total put = %i\n", cnt_put.load());
    }
};

struct RowData
{
    int id;
    int score;
    int country;
};

class ThreadSafeLinkedList : SortedLinkedList<RowData>
{
private:
    class RemovedPartsLinkedList : public SortedLinkedList<int>
    {
        bool is_sorted(const int& x, const int& y) override { return x < y; }
    } removed_parts;

    std::map<int, std::mutex> part_id_mx;
    std::mutex mx;

public:
    bool is_sorted(const RowData& x, const RowData& y) override
    {
        return x.score > y.score || (x.score == y.score && x.id <= y.id);
    }

    void update_or_insert(const RowData& x)
    {
        mx.lock();
        std::lock_guard<mutex> lk(part_id_mx[x.id]); // mutex for each id, prevent duplicates
        mx.unlock();

        if (removed_parts.find([&x](const int& r) {return r == x.id; }))
            return;

        RowData tmp = {x.id, 0, x.country };
        find_and_remove([&x](const RowData& r) {return r.id == x.id; }, tmp);

        if (x.score < 0)
        {
            removed_parts.insert(x.id);
            return;
        }
        else
        {
            tmp.score += x.score;
            insert(tmp);
        }
    }

    friend ostream& operator <<(ostream& out, const ThreadSafeLinkedList& l)
    {
        l.iterate_sync([&out](const RowData& r) { out << r.id << " " << r.score << " " << r.country << "\n"; });
        out << "Eliminati:\n";
        l.removed_parts.iterate_sync([&out](const int& x) { out << x << "\n"; });
        return out;
    }
};


class PartPipe : public ProducerConsumer<RowData>
{
private:
    ThreadSafeLinkedList* parts_list;
    atomic<int> k = 0;
public:
    explicit PartPipe(ThreadSafeLinkedList* parts_list) : parts_list(parts_list) { }

    void process(const RowData& x) override
    {
        parts_list->update_or_insert(x);
        k++;
    }

    ~PartPipe() override
    {
        printf("Total processed = %i\n", k.load());
    }
};

void producer(PartPipe* pipe, int pr, int pid)
{
    int q = 50 / pr, r = 50 % pr;
    int start = pid * q + (r <= pid ? r : pid);
    int end = (pid + 1) * q + (r <= (pid + 1) ? r : pid + 1);
    printf("Started producer %i\n", pid);
    for (int k = start; k < end; k++)
    {
        int country = k / 10 + 1, part = k % 10 + 1;
        ifstream f("data\\C" + to_string(country) + "_P" + to_string(part) + ".txt");


        for (int part, score, k = 0; k < 50000 && (f >> part >> score); k++)
        {
            pipe->put({ part, score, country });
        }
        f.close();

    }
    printf("Producer %i finished.\n", pid);
}

void consumer(PartPipe* pipe, int pid)
{
    printf("Consumer %i started\n", pid);
    pipe->run();
    printf("Consumer %i finished\n", pid);
}

void parallel(int pr, int pw)
{
    ThreadSafeLinkedList parts_list;
    PartPipe pipe(&parts_list);

    auto* consumers = new thread[pw]{};
    for (int p = 0; p < pw; p++) consumers[p] = thread(consumer, &pipe, p);

    auto* producers = new thread[pr]{};
    for (int p = 0; p < pr; p++) producers[p] = thread(producer, &pipe, pr, p);

    for (int p = 0; p < pr; p++) producers[p].join();

    delete[] producers;

    pipe.stop();

    for (int p = 0; p < pw; p++) consumers[p].join();

    ofstream g("result.txt");
    g << parts_list;
    g.close();
}

void generate()
{
    filesystem::create_directory("data");
    for (int c = 1; c <= 5; c++)
    {
        int count = 80 + rand() % 20;
        for (int p = 1; p <= 10; p++)
        {
            ofstream out("data\\C" + to_string(c) + "_P" + to_string(p) + ".txt");
            for (int k = 1; k <= count; k++)
            {
                int score = rand() % 12 - 1;
                out << 100 * c + k << " " << score << "\n";
            }
            out.close();
        }
    }
}

void compare_files(const std::string& file1, const std::string& file2) {
    std::ifstream input1(file1, std::ios::binary);
    if (!input1.is_open()) {
        throw std::runtime_error("Error opening file: " + file1);
    }
    std::ifstream input2(file2, std::ios::binary);
    if (!input2.is_open()) {
        throw std::runtime_error("Error opening file: " + file2);
    }
    try {
        while (true) {
            char byte1, byte2;
            input1.get(byte1);
            input2.get(byte2);
            if (input1.eof() && input2.eof()) {
                std::cout << "Files are identical.\n";
                return;
            }
            if (input1.eof() || input2.eof()) {
                throw std::runtime_error("Files have different sizes.");
            }
            if (byte1 != byte2) {
                throw std::runtime_error("Files are not identical.");
            }
        }
    } catch (...) {
        input1.close();
        input2.close();
        throw;
    }
}

int main(int argc, char** argv)
{
    //generate();

    auto t_start = std::chrono::high_resolution_clock::now();

    int pw = argc > 2 ? atoi(argv[2]) : 12;
    int pr = argc > 3 ? atoi(argv[3]) : 4;

    parallel(pr, pw);
    auto t_end = std::chrono::high_resolution_clock::now();
    double elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();
    printf(">>Measured time = %f\n", elapsed_time_ms);

    compare_files("result.txt", "result_seq.txt");

    return 0;
}