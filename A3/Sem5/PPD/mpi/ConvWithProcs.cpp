//#include <iostream>
//#include <stdio.h>
//#include <mpi.h>
//#include <stdlib.h> 
//#include <fstream>
//#include <cassert>
//#include <chrono>
//#include <string>
//#include <vector>
//
//using namespace std;

//int getPos(int i, int j, int n) {
//	return i * n + j;
//}
//
//pair<int, int> getCoords(int pos, int n) {
//	int i = pos / n;
//	int j = pos - i * n;
//	return { i, j };
//}
//
//const int n = 1000, k = 3;
//int filter[k][k];
//int matrix[n + 2][n];
//int answer[n][n];
//
//
//int main(int argc, char* argv[])
//{
//	int myid, numprocs, namelen;
//	char processor_name[MPI_MAX_PROCESSOR_NAME];
//
//	MPI_Init(NULL, NULL);
//	MPI_Comm_rank(MPI_COMM_WORLD, &myid);  // get current process id
//	MPI_Comm_size(MPI_COMM_WORLD, &numprocs);      // get number of processeser
//	MPI_Get_processor_name(processor_name, &namelen);
//
//	const string inputFile = "C:/Users/Andrei Bejan/Desktop/in.txt";
//	ifstream fin(inputFile);
//	bool t1 = stoi(argv[1]) == 0;
//	auto tStart1 = std::chrono::high_resolution_clock::now();
//
//	if (myid == 0) {
//
//		int a, b, c;
//		fin >> a >> b >> c;
//
//		for (int i = 0; i < k; i++) {
//			for (int j = 0; j < k; j++) {
//				fin >> filter[i][j];
//			}
//		}
//	}
//
//	MPI_Bcast(filter, k*k, MPI_INT, 0, MPI_COMM_WORLD);
//
//	if (myid == 0) {
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				fin >> matrix[i+1][j];
//			}
//		}
//		for (int j = 0; j < n; j++) {
//			matrix[0][j] = matrix[1][j];
//			matrix[n+1][j] = matrix[n][j];
//		}
//		fin.close();
//	}
//
//	int* displacements = new int[numprocs];
//	int* offset = new int[numprocs];
//	int noLinesPerProc = n / numprocs;
//	
//	int lastLine = 0;
//	for (int i = 0; i < numprocs; i++) {
//		offset[i] = (noLinesPerProc + 2) * n;
//		displacements[i] = lastLine;
//		lastLine += offset[i] - 2 * n;
//	}
//
//	vector<int> buffero((noLinesPerProc + 2) * n);
//	vector<int> solution(noLinesPerProc * n);
//
//	auto tStart2 = std::chrono::high_resolution_clock::now();
//	MPI_Scatterv(matrix, offset, displacements, MPI_INT, &buffero[0], offset[myid], MPI_INT, 0, MPI_COMM_WORLD);
//
//	for (int i = 0; i < noLinesPerProc; i++) {
//		for (int j = 0; j < n; j++) {
//			for (int a = 0; a < k; a++) {
//				for (int b = 0; b < k; b++) {
//					int buffer_i = i + 1 - k / 2 + a;
//					int buffer_j = j - k / 2 + b;
//
//					buffer_j = max(min(buffer_j, n - 1), 0);
//
//					solution[getPos(i, j, n)] += filter[a][b] * buffero[getPos(buffer_i, buffer_j, n)];
//				}
//			}
//		}
//	}
//	int* backDisplacements = new int[numprocs];
//	int* backOffset = new int[numprocs];
//	int backLastLine = 0;
//	for (int i = 0; i < numprocs; i++) {
//		backOffset[i] = noLinesPerProc * n;
//		backDisplacements[i] = backLastLine;
//		backLastLine += backOffset[i];
//	}
//	MPI_Gatherv(&solution[0], noLinesPerProc * n, MPI_INT, answer, backOffset, backDisplacements, MPI_INT, 0, MPI_COMM_WORLD);
//
//	if (myid == 0) {
//		const string outputFile = "C:/Users/Andrei Bejan/Desktop/out.txt";
//		ofstream fout(outputFile);
//
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				fout << answer[i][j] << " ";
//			}
//			fout << "\n";
//		}
//		fout.close();
//		auto tEnd = std::chrono::high_resolution_clock::now();
//
//		const string truthFile = "C:/Users/Andrei Bejan/Desktop/truth.txt";
//		ifstream finTruth(truthFile);
//		ifstream finOut(outputFile);
//
//		int x, y;
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++)
//			{
//				finTruth >> x;
//				finOut >> y;
//				assert(x == y);
//			}
//		}
//
//		if (t1) {
//			double elapsedTimeMs = std::chrono::duration<double, std::milli>(tEnd - tStart1).count();
//			cout << elapsedTimeMs;
//		}
//		else {
//			double elapsedTimeMs = std::chrono::duration<double, std::milli>(tEnd - tStart2).count();
//			cout << elapsedTimeMs;
//		}
//	}
//
//	MPI_Finalize();
//}




#include <iostream>
#include <stdio.h>
#include <mpi.h>
#include <stdlib.h>
#include <fstream>
#include <cassert>
#include <chrono>
#include <string>

using namespace std;


int main(int argc, char* argv[])
{
	int myid, numprocs, namelen;
	char processor_name[MPI_MAX_PROCESSOR_NAME];

	MPI_Init(NULL, NULL);
	MPI_Comm_rank(MPI_COMM_WORLD, &myid);  // get current process id
	MPI_Comm_size(MPI_COMM_WORLD, &numprocs);      // get number of processeser
	MPI_Get_processor_name(processor_name, &namelen);

	int n, m, k;

	if (myid == 0) {
		bool t1 = stoi(argv[1]) == 0;
		auto tStart1 = std::chrono::high_resolution_clock::now();

		const string inputFile = "C:/Users/Andrei Bejan/Desktop/in.txt";
		ifstream fin(inputFile);

		fin >> n >> m >> k;

		int** filter = new int*[k];
		for (int i = 0; i < k; i++)
			filter[i] = new int[k];

		for (int i = 0; i < k; i++)
			for (int j = 0; j < k; j++)
				fin >> filter[i][j];

		MPI_Bcast(&n, 1, MPI_INT, 0, MPI_COMM_WORLD);
		MPI_Bcast(&m, 1, MPI_INT, 0, MPI_COMM_WORLD);
		MPI_Bcast(&k, 1, MPI_INT, 0, MPI_COMM_WORLD);

		for (int i = 0; i < k; i++) {
			MPI_Bcast(filter[i], k, MPI_INT, 0, MPI_COMM_WORLD);
		}

		int noLinesPerProc = n / (numprocs - 1);
		//cout << "NOLINES" << noLinesPerProc << "\n";
		int** buffer = new int*[noLinesPerProc + 2];
		for (int i = 0; i < noLinesPerProc+2; i++) {
			buffer[i] = new int[m];
		}

		auto tStart2 = std::chrono::high_resolution_clock::now();
		for (int p = 1; p < numprocs; p++) {
			if (p == 1) {
				for (int i = 1; i < noLinesPerProc + 2; i++) {
					for (int j = 0; j < m; j++) {
						fin >> buffer[i][j];
					}
				}
				for (int j = 0; j < m; j++) {
					buffer[0][j] = buffer[1][j];
				}
			}
			else if (p == numprocs - 1) {
				for (int j = 0; j < m; j++) {
					buffer[0][j] = buffer[noLinesPerProc][j];
					buffer[1][j] = buffer[noLinesPerProc + 1][j];
				}

				for (int i = 2; i < noLinesPerProc + 1; i++) {
					for (int j = 0; j < m; j++) {
						fin >> buffer[i][j];
					}
				}

				for (int j = 0; j < m; j++) {
					buffer[noLinesPerProc + 1][j] = buffer[noLinesPerProc][j];
				}
			}
			else {
				for (int j = 0; j < m; j++) {
					buffer[0][j] = buffer[noLinesPerProc][j];
					buffer[1][j] = buffer[noLinesPerProc + 1][j];
				}

				for (int i = 2; i < noLinesPerProc + 2; i++) {
					for (int j = 0; j < m; j++) {
						fin >> buffer[i][j];
					}
				}
			}

			for (int i = 0; i < noLinesPerProc + 2; i++) {
				MPI_Send(buffer[i], m, MPI_INT, p, 0, MPI_COMM_WORLD);
			}
		}

		const string outputFile = "C:/Users/Andrei Bejan/Desktop/out.txt";
		ofstream fout(outputFile);
		MPI_Status status;

		for (int p = 1; p < numprocs; p++) {
			for (int i = 0; i < noLinesPerProc; i++) {
				MPI_Recv(buffer[i], m, MPI_INT, p, 0, MPI_COMM_WORLD, &status);
				for (int j = 0; j < m; j++) {
					fout << buffer[i][j] << " ";
				}
				fout << "\n";
			}
		}
		fout.close();
		auto tEnd = std::chrono::high_resolution_clock::now();

		const string truthFile = "C:/Users/Andrei Bejan/Desktop/truth.txt";
		ifstream finTruth(truthFile);
		ifstream finOut(outputFile);

		int x, y;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
			{
				finTruth >> x;
				finOut >> y;
				assert(x == y);
			}
		}

		if (t1) {
			double elapsedTimeMs = std::chrono::duration<double, std::milli>(tEnd - tStart1).count();
			cout << elapsedTimeMs;
		}
		else {
			double elapsedTimeMs = std::chrono::duration<double, std::milli>(tEnd - tStart2).count();
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

		int noLinesPerProc = n / (numprocs - 1);
		int** buffer = new int* [noLinesPerProc + 2];
		for (int i = 0; i < noLinesPerProc + 2; i++) {
			buffer[i] = new int[m];
		}
		MPI_Status status;

		for (int i = 0; i < noLinesPerProc + 2; i++) {
			MPI_Recv(buffer[i], m, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
		}

		//cout << myid << "\n";
		//for (int i = 0; i < noLinesPerProc + 2; i++) {
		//	for (int j = 0; j < m; j++) {
		//		cout << buffer[i][j] << " ";
		//	}
		//	cout << "\n";
		//}

		int** solution = new int* [noLinesPerProc];
		for (int i = 0; i < noLinesPerProc; i++) {
			solution[i] = new int[m];
		}
		for (int i = 0; i < noLinesPerProc; i++) {
			for (int j = 0; j < m; j++) {
				solution[i][j] = 0;
				for (int a = 0; a < k; a++)
					for (int b = 0; b < k; b++) {
						int buffer_i = i + 1 - k / 2 + a;
						int buffer_j = j - k / 2 + b;

						solution[i][j] += filter[a][b] * buffer[buffer_i][max(min(buffer_j, m-1), 0)];
					}
			}
		}

		for (int i = 0; i < noLinesPerProc; i++) {
			MPI_Send(solution[i], m, MPI_INT, 0, 0, MPI_COMM_WORLD);
		}
	}

	MPI_Finalize();
}
