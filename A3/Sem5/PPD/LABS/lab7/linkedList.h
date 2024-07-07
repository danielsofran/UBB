#include <set>
#include <chrono>
#include <fstream>
#include <mutex>

class LinkedList 
{
private:
    struct Node 
    {
        int part_id;
        int score = 0;
        Node* next;
    };

    Node* head = nullptr;
    std::set<int> disqualified;
    std::mutex mutex;
    bool is_parallel;

    bool compare(Node* n1, Node* n2);
    void insert_node(Node* target);
    Node* get_node(int participant);
    Node* create_node(int participant);

public:
    LinkedList(bool is_parallel = false);
    void add_score(int participant, int score);
    void print(std::string fileName);
};