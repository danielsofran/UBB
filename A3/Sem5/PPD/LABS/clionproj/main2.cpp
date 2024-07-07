#include <cassert>
#include <iostream>
#include <chrono>
#include <string>
#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <fstream>

using namespace std;

const string inPath = "C:/Users/aerap/Desktop/PPD/lab3/tema1J/data.txt";
const string outPath = "C:/Users/aerap/Desktop/PPD/lab3/tema1J/outMPI.txt";
const string corectPath = "C:/Users/aerap/Desktop/PPD/lab3/tema1J/outSecvential.txt";


int main(int argc, char* argv[])
{
    int proc_id, iProc, nrProc;
    char processors[MPI_MAX_PROCESSOR_NAME];

    MPI_Init(NULL, NULL);
    MPI_Comm_rank(MPI_COMM_WORLD, &proc_id);  // get current process id
    MPI_Comm_size(MPI_COMM_WORLD, &iProc);      // get number of processeser
    MPI_Get_processor_name(processors, &nrProc);

    int n, m, k;

    if (proc_id == 0) {
        bool t1 = stoi(argv[1]) == 0;
        auto tStart1 = std::chrono::high_resolution_clock::now();

        ifstream in_data(inPath);

        in_data >> n >> m >> k;

        int** K = new int* [k];
        for (int i = 0; i < k; i++)
            K[i] = new int[k];

        for (int i = 0; i < k; i++)
            for (int j = 0; j < k; j++)
                in_data >> K[i][j];

        MPI_Bcast(&n, 1, MPI_INT, 0, MPI_COMM_WORLD);
        MPI_Bcast(&m, 1, MPI_INT, 0, MPI_COMM_WORLD);
        MPI_Bcast(&k, 1, MPI_INT, 0, MPI_COMM_WORLD);

        for (int i = 0; i < k; i++) {
            MPI_Bcast(K[i], k, MPI_INT, 0, MPI_COMM_WORLD);
        }

        int nLPP = n / (iProc - 1);
        //cout << "NOLINES" << nLPP << "\n";
        int** M = new int* [nLPP + 2];
        for (int i = 0; i < nLPP + 2; i++) {
            M[i] = new int[m];
        }

        auto t2 = std::chrono::high_resolution_clock::now();
        for (int p = 1; p < iProc; p++) {
            if (p == 1) {
                for (int i = 1; i < nLPP + 2; i++) {
                    for (int j = 0; j < m; j++) {
                        in_data >> M[i][j];
                    }
                }
                for (int j = 0; j < m; j++) {
                    M[0][j] = M[1][j];
                }
            }
            else if (p == iProc - 1) {
                for (int j = 0; j < m; j++) {
                    M[0][j] = M[nLPP][j];
                    M[1][j] = M[nLPP + 1][j];
                }

                for (int i = 2; i < nLPP + 1; i++) {
                    for (int j = 0; j < m; j++) {
                        in_data >> M[i][j];
                    }
                }

                for (int j = 0; j < m; j++) {
                    M[nLPP + 1][j] = M[nLPP][j];
                }
            }
            else {
                for (int j = 0; j < m; j++) {
                    M[0][j] = M[nLPP][j];
                    M[1][j] = M[nLPP + 1][j];
                }

                for (int i = 2; i < nLPP + 2; i++) {
                    for (int j = 0; j < m; j++) {
                        in_data >> M[i][j];
                    }
                }
            }

            for (int i = 0; i < nLPP + 2; i++) {
                MPI_Send(M[i], m, MPI_INT, p, 0, MPI_COMM_WORLD);
            }
        }

        ofstream out_rez(outPath);
        MPI_Status mpi_status_vec;

        for (int p = 1; p < iProc; p++) {
            for (int i = 0; i < nLPP; i++) {
                MPI_Recv(M[i], m, MPI_INT, p, 0, MPI_COMM_WORLD, &status);
                for (int j = 0; j < m; j++) {
                    out_rez << M[i][j] << " ";
                }
                out_rez << "\n";
            }
        }
        out_rez.close();
        auto t2End = std::chrono::high_resolution_clock::now();

        ifstream in_correct(corectPath);
        ifstream in_rez(outPath);

        int e1, e2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
            {
                in_correct >> e1;
                in_rez >> e2;
                if (e1 != e2)
                    cerr << "Items not equal at: " << i << "," << j << ": " << e1 << " != " << e2 << '\n';
                //assert(e1 == e2);
            }
        }

        if (t1) {
            double elapsedTimeMs = std::chrono::duration<double, std::milli>(t2End - tStart1).count();
            cout << elapsedTimeMs;
        }
        else {
            double elapsedTimeMs = std::chrono::duration<double, std::milli>(t2End - t2).count();
            cout << elapsedTimeMs;
        }

    }
    else {
        MPI_Bcast(&n, 1, MPI_INT, 0, MPI_COMM_WORLD);
        MPI_Bcast(&m, 1, MPI_INT, 0, MPI_COMM_WORLD);
        MPI_Bcast(&k, 1, MPI_INT, 0, MPI_COMM_WORLD);

        int** filter = new int* [k];
        for (int i = 0; i < k; i++)
            filter[i] = new int[k];

        for (int i = 0; i < k; i++) {
            MPI_Bcast(filter[i], k, MPI_INT, 0, MPI_COMM_WORLD);
        }

        int nLLp = n / (iProc - 1);
        int** buf = new int* [nLLp + 2];
        for (int i = 0; i < nLLp + 2; i++) {
            buf[i] = new int[m];
        }
        MPI_Status stat_vec;

        for (int i = 0; i < nLLp + 2; i++) {
            MPI_Recv(buf[i], m, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
        }

        int** R = new int* [nLLp];
        for (int i = 0; i < nLLp; i++) {
            R[i] = new int[m];
        }
        for (int i = 0; i < nLLp; i++) {
            for (int j = 0; j < m; j++) {
                R[i][j] = 0;
                for (int a = 0; a < k; a++)
                    for (int b = 0; b < k; b++) {
                        int bi = i + 1 - k / 2 + a;
                        int bj = j - k / 2 + b;

                        R[i][j] += filter[a][b] * buf[bi][max(min(bj, m - 1), 0)];
                    }
            }
        }

        for (int i = 0; i < nLLp; i++) {
            MPI_Send(R[i], m, MPI_INT, 0, 0, MPI_COMM_WORLD);
        }
    }

    MPI_Finalize();
}
