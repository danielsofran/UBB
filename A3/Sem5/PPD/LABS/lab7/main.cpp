#include <iostream>
#include <filesystem>
#include <string>
#include "linkedList.h"
#include <ctime>
#include <windows.h>
#include <vector>
#include <atomic>
#include "myQueue.h"
#include <bits/stdc++.h>

using namespace std;

bool compareFiles(const std::string& file1, const std::string& file2) {
    std::ifstream input1(file1);
    std::ifstream input2(file2);

    if (!input1.is_open() || !input2.is_open()) {
        std::cout << "Unable to open one or both files." << std::endl;
        return false;
    }

    std::vector<std::string> lines1;
    std::vector<std::string> lines2;

    std::string line;
    bool firstFileScoruri = true;
    bool secondFileScoruri = true;

    while (std::getline(input1, line)) {
        if (line == "Scoruri:") {
            firstFileScoruri = false;
            continue;
        }
        if (line == "Descalificati:") {
            break;
        }
        if (!firstFileScoruri) {
            lines1.push_back(line);
        }
    }

    while (std::getline(input2, line)) {
        if (line == "Scoruri:") {
            secondFileScoruri = false;
            continue;
        }
        if (line == "Descalificati:") {
            break;
        }
        if (!secondFileScoruri) {
            lines2.push_back(line);
        }
    }

    input1.close();
    input2.close();

    // Check if both files have the same number of lines and content
    if (lines1.size() != lines2.size()) {
        return false;
    }

    for (size_t i = 0; i < lines1.size(); ++i) {
        if (lines1[i] != lines2[i]) {
            return false;
        }
    }

    return true;
}

void generate()
{
    srand(time(NULL));
    std::filesystem::create_directory("data");
    for (int tara = 1; tara <= 5; tara++)
    {
        int nr_scoruri = 101; // 100 participanti
        for (int fisier = 1; fisier <= 10; fisier++) // 10 fisiere
        {
            ofstream fout("data\\RezultateC" + to_string(tara) + "_P" + to_string(fisier) + ".txt");

            for (int scor = 1; scor < nr_scoruri; scor++)
            { // scoruri random
                int rezultat = rand() % 50;
                if (rezultat == 5)
                    rezultat = -1;
                fout << 100* tara + scor << " " << rezultat << "\n";
            }
        }
    }
}

void producer(myQueue* my_queue, int prod, int pid)
{
    // impartim fisierele la nr de produceri
    int q = 50 / prod, r = 50 % prod;
    int start = pid * q + (r <= pid ? r : pid);
    int end = (pid + 1) * q + (r <= (pid + 1) ? r : pid + 1);
    printf("Started producer %i %i\n", start, end);

    for (int k = start; k < end; k++)
    {
        int country = k / 10 + 1;
        int participant = k % 10 + 1;
        ifstream fin("data\\RezultateC" + to_string(country) + "_P" + to_string(participant) + ".txt");

        int part, score;
        // citim scorurile si le bagam in coada
        while (fin >> part >> score)
            my_queue->enqueue({ part, score });
        fin.close();
    }
    printf("Producer %i finished.\n", pid);
}

void consumer(myQueue* my_queue, std::atomic<bool>* work_needed, LinkedList* linked_list, int pid)
{
    printf("Consumer %i started.\n", pid);
    RowData x;
    while (work_needed->load())
    {
        while (!my_queue->is_empty())
        {
            if (my_queue->dequeue(x)) {
                linked_list->add_score(x.part_id, x.score);
                //printf("Consumer %i: added %i\n", pid, x.part_id);
            }
            else
                Sleep(1);
        }
    }
    printf("Consumer %i finished.\n", pid);
}

void sequencial()
{
    LinkedList linked_list;

    for (const auto& fisier : std::filesystem::directory_iterator("data"))
    {
        ifstream fin(fisier.path().string());
        int id, score;
        while (fin >> id >> score)
            linked_list.add_score(id, score);
        fin.close();
    }
    linked_list.print("ClasamentSecvential.txt");
}

void parallel(int prod, int work)
{
    LinkedList linked_list(true);
    myQueue q(true);

    std::atomic<bool> work_needed(true);

    thread* consumers = new thread[work]{};
    for (int p = 0; p < work; p++)
        consumers[p] = thread(consumer, &q, &work_needed, &linked_list, p);

    thread* producers = new thread[prod]{};
    for (int p = 0; p < prod; p++) 
        producers[p] = thread(producer, &q, prod, p);

    for (int p = 0; p < prod; p++) 
        producers[p].join();

    work_needed.store(false);
    for (int p = 0; p < work; p++) 
        consumers[p].join();

    delete[] producers;

    linked_list.print("ClasamentParalel.txt");
}

int main(int argc, char** argv)
{
    //generate();
    auto t_start = std::chrono::high_resolution_clock::now();

    //sequencial();
    parallel(1, 5);

    auto t_end = std::chrono::high_resolution_clock::now();
    double elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();
    printf(">>Measured time = %f\n", elapsed_time_ms);

    if (compareFiles("ClasamentSecvential.txt", "ClasamentParalel.txt"))
        cout << "Fisierele sunt identice";
    else
        cout << "Fisierele nu sunt identice";
    return 0;
}
