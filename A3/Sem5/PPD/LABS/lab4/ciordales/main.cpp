#include <iostream>
#include <fstream>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <cstdlib>

using namespace std;

class Barrier {
public:
    Barrier(int count) : count_(count), released_(false) {
        mutex_ = new mutex;
    }

    ~Barrier() {
        delete mutex_;
    }

    void Wait() {
        unique_lock<mutex> lock(*mutex_);
        if (--count_ == 0) {
            released_ = true;
            condition_.notify_all();
        } else {
            while (!released_) {
                condition_.wait(lock);
            }
        }
    }

private:
    mutex* mutex_;
    condition_variable condition_;
    int count_;
    bool released_;
};

void generateRandomData(int n, int m, int L) {
    string filename = "data.txt";

    ofstream writer(filename);
    writer << n << " " << m << "\n";
    srand(time(0));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; ++j) {
            writer << rand() % (L + 1) << " ";
        }
        writer << "\n";
    }
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; ++j) {
            writer << rand() % 2 << " ";
        }
        writer << "\n";
    }
    writer.close();
}

void writeMatrixToFile(const string& filename, int** V, int n, int m) {
    ofstream writer(filename);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            writer << V[i][j] << " ";
        }
        writer << "\n";
    }
    writer.close();
}

void readMatrixFromFile(const string& filename, int n, int m, int** V) {
    ifstream reader(filename);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            reader >> V[i][j];
        }
    }
    reader.close();
}

void runStaticLine(int n, int m, int** A, int** C) {
    auto t_start = std::chrono::high_resolution_clock::now();
    int* above = new int[m + 2];
    int* current = new int[m + 2];
    copy(A[0], A[0] + (m + 2), above);
    int left = A[1][0];

    for (int i = 1; i <= n; i++) {
        copy(A[i], A[i] + (m + 2), current);
        for (int j = 1; j <= m; j++) {
            int value = 0;

            for (int ii = 0; ii <= 1; ii++) {
                for (int jj = -1; jj <= 1; jj++) {
                    if (ii == 0 && jj == -1) {
                        value += left * C[ii + 1][jj + 1];
                    } else {
                        value += A[i + ii][j + jj] * C[ii + 1][jj + 1];
                    }
                }
            }

            for (int jj = -1; jj <= 1; jj++) {
                value += above[j + jj] * C[0][jj + 1];
            }

            if (j == m) {
                left = A[i + 1][0];
            } else {
                left = A[i][j];
            }
            A[i][j] = value;
        }
        copy(current, current + (m + 2), above);
    }

    auto t_end = std::chrono::high_resolution_clock::now();

    double elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();

    cout << elapsed_time_ms;

    writeMatrixToFile("outSecvential.txt", A, n + 2, m + 2);

    delete[] above;
    delete[] current;
}

void runThreadsLine(int n, int m, int** A, int** C, int p, Barrier& barrier) {
    thread* threads = new thread[p];
    int rest = n % p;
    int cat = n / p;
    int start = 1;
    int end;

    auto t_start = std::chrono::high_resolution_clock::now();

    for (int i = 0; i < p; i++) {
        end = start + cat;
        if (rest > 0) {
            end++;
            rest--;
        }
        threads[i] = thread([n, m, i, start, end, &C, &A, &barrier]() {
            int* above = new int[m + 2];
            int* current = new int[m + 2];
            int* frontier_above = new int[m + 2];
            int* frontier_below = new int[m + 2];

            copy(A[start - 1], A[start - 1] + (m + 2), frontier_above);
            copy(A[end], A[end] + (m + 2), frontier_below);

            barrier.Wait();

            copy(A[start], A[start] + (m + 2), above);
            int left = A[start][0];

            //first line
            for (int j = 1; j <= m; j++) {
                int value = 0;

                for (int ii = 0; ii <= 1; ii++) {
                    for (int jj = -1; jj <= 1; jj++) {
                        if (ii == 0 && jj == -1) {
                            value += left * C[ii + 1][jj + 1];
                        } else {
                            value += A[start + ii][j + jj] * C[ii + 1][jj + 1];
                        }
                    }
                }

                for (int jj = -1; jj <= 1; jj++) {
                    value += frontier_above[j + jj] * C[0][jj + 1];
                }

                left = A[start][j];
                A[start][j] = value;
            }

            left = A[start + 1][0];

            //inner lines
            for (int i = start + 1; i < end - 1; i++) {
                copy(A[i], A[i] + (m + 2), current);

                for (int j = 1; j <= m; j++) {
                    int value = 0;

                    for (int ii = 0; ii <= 1; ii++) {
                        for (int jj = -1; jj <= 1; jj++) {
                            if (ii == 0 && jj == -1) {
                                value += left * C[ii + 1][jj + 1];
                            } else {
                                value += A[i + ii][j + jj] * C[ii + 1][jj + 1];
                            }
                        }
                    }

                    for (int jj = -1; jj <= 1; jj++) {
                        value += above[j + jj] * C[0][jj + 1];
                    }

                    if (j == m) {
                        left = A[i + 1][0];
                    } else {
                        left = A[i][j];
                    }
                    A[i][j] = value;
                }

                copy(current, current + (m + 2), above);
            }

            //last line
            for (int j = 1; j <= m; j++) {
                int value = 0;

                for (int jj = -1; jj <= 1; jj++) {
                    value += above[j + jj] * C[0][jj + 1];
                }

                for (int jj = -1; jj <= 1; jj++) {
                    if (jj == -1) {
                        value += left * C[1][jj + 1];
                    } else {
                        value += A[end - 1][j + jj] * C[1][jj + 1];
                    }
                }

                for (int jj = -1; jj <= 1; jj++) {
                    value += frontier_below[j + jj] * C[2][jj + 1];
                }

                left = A[end - 1][j];
                A[end - 1][j] = value;
            }

            delete[] above;
            delete[] current;
            delete[] frontier_above;
            delete[] frontier_below;
        });
        start = end;
    }

    for (int i = 0; i < p; i++) {
        threads[i].join();
    }

    auto t_end = std::chrono::high_resolution_clock::now();

    double elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();
    cout << elapsed_time_ms << endl;

    writeMatrixToFile("outThreadsLine.txt", A, n + 2, m + 2);

    delete[] threads;
}

void borderMatrix(int** A, int n, int m) {
    // Fill the top border
    copy(A[1], A[1] + (m + 2), A[0]);

    // Fill the right border
    for (int i = 0; i < n + 2; i++) {
        A[i][m + 1] = A[i][m];
    }

    // Fill the left border
    for (int i = 0; i < n + 2; i++) {
        A[i][0] = A[i][1];
    }

    // Fill the bottom border
    copy(A[n], A[n] + (m + 2), A[n + 1]);
}

void solveThreadsLine(int n, int m, int p, int **A, int **C) {
    Barrier barrier(p);
    runThreadsLine(n, m, A, C, p, barrier);

    int** AA;
    AA = new int* [n + 2];
    for (int i = 0; i < n + 2; i++) {
        AA[i] = new int[m + 2];
    }
    readMatrixFromFile("outSecvential.txt", n + 2, m + 2, AA);

    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= m; ++j) {
            if (A[i][j] != AA[i][j])
                cerr << "A is not equal to AA at position: (" << i << "," << j << ")\n";
        }
    }
}

int main() {
//    generateRandomData(10000, 10000, 5000);
    int n, m, p = 16;
    int** A, ** C;
    string fileName = "data.txt";
    ifstream reader(fileName);
    reader >> n >> m;
    A = new int* [n + 2];
    for (int i = 0; i < n + 2; i++) {
        A[i] = new int[m + 2];
    }
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            reader >> A[i][j];
        }
    }
    C = new int* [3];
    for (int i = 0; i < 3; i++) {
        C[i] = new int[3];
        for (int j = 0; j < 3; j++) {
            reader >> C[i][j];
        }
    }
    reader.close();
    borderMatrix(A, n, m);

//    runStaticLine(n, m, A, C);

    solveThreadsLine(n, m, p, A, C);

    for (int i = 0; i < n + 2; i++) {
        delete[] A[i];
    }
    delete[] A;

    for (int i = 0; i < 3; i++) {
        delete[] C[i];
    }
    delete[] C;

    return 0;
}
