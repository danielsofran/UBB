#include <chrono>
#include <stdio.h>
#include <iostream>
#include <fstream>
#include "utils.h"
#include "secv.h"

using namespace std;

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
                    }
                    else {
                        value += A[i + ii][j + jj] * C[ii + 1][jj + 1];
                    }
                }
            }

            for (int jj = -1; jj <= 1; jj++) {
                value += above[j + jj] * C[0][jj + 1];
            }

            if (j == m) {
                left = A[i + 1][0];
            }
            else {
                left = A[i][j];
            }
            A[i][j] = value;
        }
        copy(current, current + (m + 2), above);
    }

    auto t_end = std::chrono::high_resolution_clock::now();

    double elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();

    cout << elapsed_time_ms;

    writeMatrixToFile("..\\x64\\Debug\\outSecvential.txt", A, n + 2, m + 2);

    delete[] above;
    delete[] current;
}

void staticProgram() {
    int n, m, p = 2;
    int** A, ** C;
    string fileName = "..\\x64\\Debug\\data.txt";
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

    runStaticLine(n, m, A, C);

    for (int i = 0; i < n + 2; i++) {
        delete[] A[i];
    }
    delete[] A;

    for (int i = 0; i < 3; i++) {
        delete[] C[i];
    }
    delete[] C;
}