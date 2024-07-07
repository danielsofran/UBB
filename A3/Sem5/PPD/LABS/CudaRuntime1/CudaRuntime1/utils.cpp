#include <iostream>
#include <fstream>
#include "utils.h"

using namespace std;

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

void generateRandomData(int n, int m, int L) {
    string filename = "..\\x64\\Debug\\data.txt";

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

void readMatrixFromFile(const string& filename, int n, int m, int** V, int** conv) {
    ifstream reader(filename);
    reader >> n >> m;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            reader >> V[i][j];
        }
    }
    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
            reader >> conv[i][j];
        }
    }
    reader.close();
}