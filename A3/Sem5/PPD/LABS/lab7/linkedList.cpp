#include "linkedList.h"

LinkedList::LinkedList(bool is_parallel) {
    this->is_parallel = is_parallel;
}

LinkedList::Node* LinkedList::get_node(int participant) 
{
    Node* n = head, * prev = nullptr;
    while (n != nullptr) {
        if (n->part_id == participant) {
            if (prev != nullptr)
                prev->next = n->next;
            else
                head = n->next;
            n->next = nullptr;
            return n;
        }
        prev = n;
        n = n->next;
    }
    return nullptr;
}

LinkedList::Node* LinkedList::create_node(int participant)
{
    return new Node{ participant, 0, nullptr };
}

bool LinkedList::compare(Node* n1, Node* n2) 
{ // compara 2 noduri dupa scor si dupa id daca scorurile sunt egale
    if (n1->score > n2->score)
        return true;
    if (n1->score == n2->score && n1->part_id <= n2->part_id)
        return true;
    return false;
}

void LinkedList::insert_node(Node* new_node) 
{
    if (head == nullptr) // daca head ul e null adaugam head
    {
        head = new_node;
    }
    else if (compare(new_node, head)) // daca noul nod are scor mai mare, adaugam in fata
    {
        new_node->next = head;
        head = new_node;
    }
    else
    { // daca nu, adaugam in spate
        Node* n = head;
        while (n->next != nullptr) 
        {
            if (compare(n, new_node) && compare(new_node, n->next)) 
            {
                new_node->next = n->next;
                n->next = new_node;
                return;
            }
            n = n->next;
        }
        n->next = new_node;
    }
}

void LinkedList::add_score(int participant, int score)
{
    if (is_parallel) // folosim mutex pt paralel
        mutex.lock();
    ([&]() 
    { // adaugam scor
        if (disqualified.count(participant)) 
            return;
        Node* n = get_node(participant);
        if (n == nullptr) // daca nu a gasit participantul, il adaugam
            n = create_node(participant);
        if (score == -1) 
        {
            delete n;
            disqualified.insert(participant);
            return;
        }
        n->score += score;
        insert_node(n);
    })();
    if (is_parallel)
        mutex.unlock();
}

void LinkedList::print(std::string fileName) // cu .txt
{
    std::ofstream fout(fileName);
    Node* n = head;
    fout << "Punctaje:\n";
    while (n != nullptr)
    {
        fout << n->part_id << " " << n->score << "\n";
        n = n->next;
    }
    fout << "Descalificati:\n";
    for (const auto& participant : disqualified) 
        fout << participant << " " << "-1\n";

    fout.close();
}