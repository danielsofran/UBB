#include "myQueue.h"

// sistem de wait pt coada
void locking_system::wait() 
{
    mutex.lock();
    is_locked = true;
    mutex.unlock();

    for (bool running = true; running;) 
    {
        mutex.lock();
        Sleep(1);
        running = is_locked;
        mutex.unlock();
    }
}

// sistem notify pt coada
void locking_system::notify() 
{
    mutex.lock();
    is_locked = false;
    mutex.unlock();
}

// fa coada pentru modul paralel daca is_parallel e true
myQueue::myQueue(bool is_parallel) : is_parallel(is_parallel) {}


void myQueue::enqueue(const RowData& item)
{
    mutex.lock();
    Node* n = new Node{ item, nullptr };
    if (head == nullptr) // adauga prim element
        head = last = n;
    else // adauga in capat
    {
        last->next = n;
        last = n;
    }
    mutex.unlock();
}

bool myQueue::dequeue(RowData& result) {
    mutex.lock();
    if (head == nullptr) 
    { // daca e vida
        mutex.unlock();
        return false;
    }

    result = head->item; // baga in result elementul din cap
    head = head->next; // treci la urm elem
    if (head == nullptr) last = nullptr; // verifica daca e ultimu elem

    mutex.unlock();
    return true;
}

// verifica daca coada e vida
bool myQueue::is_empty() {
    if (is_parallel) mutex.lock();
    bool result = head == nullptr;
    if (is_parallel) mutex.unlock();
    return result;
}
