#include "thread"
#include <iostream>
#include <chrono>
#include <ctime>
#include <fstream>
#include <cstdlib> 
using namespace std;

const int k = 5;
const int n = 10;
const int m = 10000; 
const int p = 2;
int start = 0;
int endd = 0;
int rest = n % p;
int cat = n / p;
int A[n+ k/2 * 2][m + k/2 * 2], V[n][m], C[k][k];
//int** A = new int*[n + k/2 * 2];
//int** V = new int*[n];
//int** C = new int*[k];

void generateRandomData(int nn, int mm, int kk, int L)
{
    string filename = "data.txt";
    ofstream fout(filename);

    srand(time(0));

    //fout << n << " " << m << '\n';

    for (int i = 0; i < nn; i++)
    {
        for (int j = 0; j < mm; j++)
            fout << (rand() % (L + 1)) << " ";
        fout << '\n';
    }

    //fout << k << '\n';

    for (int i = 0; i < kk; i++)
    {
        for (int j = 0; j < kk; j++)
            fout << (rand() % 2) << " ";
        fout << '\n';
    }

    fout.close();
}

void writeMatrixToFile(string filename) {
    ofstream fout(filename);

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
            fout << V[i][j] << " ";
        fout << '\n';
    }

    fout.close();
}

void readAndBorderMatrix()
{
    ifstream fin("data.txt");

    // Fill the inner matrix
    for (int i = k / 2; i < n + k / 2; i++)
        for (int j = 0 + k / 2; j < m + k / 2; j++)
            fin >> A[i][j];

    // Read the convolution matrix
    for (int i = 0; i < k; i++)
        for (int j = 0; j < k; j++)
            fin >> C[i][j];

    // Fill the top border
    for (int i = k / 2 - 1; i >= 0; i--)
        for (int j = k / 2; j < m + k / 2; j++)
            A[i][j] = A[i + 1][j];

    // Fill the right border
    for (int i = 0; i < n + k / 2; i++)
        for (int j = m + k / 2; j < m + k / 2 * 2; j++)
            A[i][j] = A[i][j - 1];

    // Fill the bottom border
    for (int i = n + k / 2; i < n + k / 2 * 2; i++)
        for (int j = k / 2; j < m + k / 2 * 2; j++)
            A[i][j] = A[i - 1][j];

    // Fill the left border
    for (int j = k / 2 - 1; j >= 0; j--)
        for (int i = 0; i < n + k / 2 * 2; i++)
            A[i][j] = A[i][j + 1];
}

void calculateLine(int start, int end)
{
    for (int i = start; i < end; i++)
        for (int j = 0; j < m; j++)
            for (int ii = -k / 2; ii < k - k / 2; ii++)
                for (int jj = -k / 2; jj < k - k / 2; jj++)
                    V[i][j] += A[i + k / 2 + ii][j + k / 2 + jj] * C[ii + k / 2][jj + k / 2];
}

void calculateColumn(int start, int end)
{
    for (int j = start; j < end; j++)
        for (int i = 0; i < n; i++)
            for (int ii = -k / 2; ii < k - k / 2; ii++)
                for (int jj = -k / 2; jj < k - k / 2; jj++)
                    V[i][j] += A[i + k / 2 + ii][j + k / 2 + jj] * C[ii + k / 2][jj + k / 2];
}

void runLine()
{
    thread th[p];

    auto t_start = std::chrono::high_resolution_clock::now();

    for (int i = 0; i < p; i++)
    {
        endd = start + cat;
        if (rest > 0)
        {
            endd++;
            rest--;
        }
        th[i] = thread(calculateLine, start, endd);
        start = endd;
    }

    for (int i = 0; i < p; i++)
        th[i].join();

    auto t_end = std::chrono::high_resolution_clock::now();

    double elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();

    cout << elapsed_time_ms;

    writeMatrixToFile("runLine.txt");
}

void runColumn()
{
    thread th[p];

    auto t_start = std::chrono::high_resolution_clock::now();

    for (int i = 0; i < p; i++)
    {
        endd = start + cat;
        if (rest > 0)
        {
            endd++;
            rest--;
        }
        th[i] = thread(calculateColumn, start, endd);
        start = endd;
    }

    for (int i = 0; i < p; i++)
        th[i].join();

    auto t_end = std::chrono::high_resolution_clock::now();

    double elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();

    cout << elapsed_time_ms;

    writeMatrixToFile("runColumn.txt");
}

//void allocateMemory()
//{
//    for (int i = 0; i < n + k / 2 * 2; i++)
//    {
//        A[i] = new int[m + k / 2 * 2];
//        for (int j = 0; j < m + k / 2 * 2; j++)
//            A[i][j] = 0;
//    }
//
//    for (int i = 0; i < n; i++)
//    {
//        V[i] = new int[m];
//        for (int j = 0; j < m; j++)
//            V[i][j] = 0;
//    }
//
//    for (int i = 0; i < k; i++)
//    {
//        C[i] = new int[k];
//        for (int j = 0; j < k; j++)
//            C[i][j] = 0;
//    }
//}

int main()
{
    generateRandomData(10, 10000, 5, 5000);
    //allocateMemory();
    readAndBorderMatrix();
    //runLine();
    runColumn();
    return 0;
}