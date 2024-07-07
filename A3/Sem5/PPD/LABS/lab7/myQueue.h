#include <mutex>
#include <exception>
#include <windows.h>

struct locking_system {
private:
    bool is_locked = false;
    std::mutex mutex;

public:
    void wait();
    void notify();
};

struct RowData {
    int part_id;
    int score;
};

class myQueue {
private:
    struct Node {
        RowData item;
        Node* next;
    };
    Node* head = nullptr;
    Node* last = nullptr;
 
    std::mutex mutex;
    locking_system lock;
    bool is_parallel;

public:
    myQueue(bool is_parallel = false);

    void enqueue(const RowData& item);
    bool dequeue(RowData& result);
    bool is_empty();
};