#include <iostream>
#include <thread>
#include <vector>
#include <functional>
#include <windows.h>

using namespace std;

const int n = 10e7;
int n_threads = 4;
int a[n];
int b[n];
int c[n];

void init();
void verify();
void estimate(function<void(void)> f);
void secvential();
void linear_function(int start, int end);
void linear();
void circular_function(int start, int increment);
void circular();

void operation(int i) {
    c[i] = a[i] + b[i];
}




int main() {
    init();
    estimate(circular);
    verify();
    return 0;
}

void init() {
    for (int i = 0; i < n; i++) {
        a[i] = i;
        b[i] = i;
    }
}

void secvential() {
    for (int i = 0; i < n; i++) {
        c[i] = a[i] + b[i];
    }
}

void verify() {
    for (int i = 0; i < n; i++) {
        if (c[i] != a[i] + b[i]) {
            std::cerr << "Error at " << i << " : " << c[i] << std::endl;
            return;
        }
    }
    // light green console output
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
    std::cout << "OK" << std::endl;
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
}

void estimate(function<void(void)> f) {
    auto t_start = chrono::steady_clock::now();
    f();
    auto t_end = chrono::steady_clock::now();
    auto elapsed_time = chrono::duration<double>(t_end - t_start).count();
    std::cout << "Elapsed time: " << elapsed_time << " seconds" << std::endl;
}

// LINEAR

void linear_function(int start, int end) {
    for (int i = start; i < end; i++) {
        operation(i);
    }
}

void linear() {
    int start = 0;
    int segment = n / n_threads;
    vector<thread> threads;
    for (int i = 0; i < n_threads; i++) {
        int begin = start;
        int end = min(start + segment, n);
        threads.push_back(thread(linear_function, begin, end));
        start += segment;
    }
    for (auto &thread : threads) {
        thread.join();
    }
}

// CIRCULAR

void circular_function(int start, int increment) {
    for (int i = start; i < n; i += increment) {
        operation(i);
    }
}

void circular() {
    vector<thread> threads;
    for (int i = 0; i < n_threads; i++) {
        threads.push_back(thread(circular_function, i, n_threads));
    }
    for (auto &thread : threads) {
        thread.join();
    }
}