//#include <mpi.h>
//#include <iostream>
//#include <vector>
//
//using namespace std;
//
//int main(int argc, char** argv) {
//    int err = MPI_Init(&argc, &argv);
//
//    int world_size;
//    MPI_Comm_size(MPI_COMM_WORLD, &world_size);
//
//    int world_rank;
//    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);
//
//    const int vectorSize = 6; // Set the size of the vectors
//    const int elementsPerProcess = vectorSize / (world_size - 1); // Exclude the root process
//
//    vector<int> vectorA(vectorSize, 0);
//    vector<int> vectorB(vectorSize, 0);
//    vector<int> partialResult(elementsPerProcess, 0);
//    vector<int> finalResult(vectorSize, 0);
//
//    if (world_rank == 0) {
//        vector<int> vectorARecv(vectorSize, 0);
//        vector<int> vectorRecv(vectorSize, 0);
//        // Initialize vectors with some values only in process 0
//        for (int i = 0; i < vectorSize; ++i) {
//            vectorA[i] = i; // Example values, modify as needed
//            vectorB[i] = i; // Example values, modify as needed
//        }
//
//        // Scatter vectors to child processes
//        MPI_Scatter(vectorA.data(), elementsPerProcess, MPI_INT, MPI_IN_PLACE, 0, MPI_INT, 0, MPI_COMM_WORLD);
//        MPI_Scatter(vectorB.data(), elementsPerProcess, MPI_INT, MPI_IN_PLACE, 0, MPI_INT, 0, MPI_COMM_WORLD);
//
//        // Each child process calculates its part of the sum
//        for (int i = 0; i < elementsPerProcess; ++i) {
//            partialResult[i] = vectorA[i] + vectorB[i];
//        }
//
//        // Gather partial results back to the root process
//        MPI_Gather(partialResult.data(), elementsPerProcess, MPI_INT, finalResult.data(), elementsPerProcess, MPI_INT, 0, MPI_COMM_WORLD);
//
//        // Root process prints the final result
//        if (world_rank == 0) {
//            cout << "Final result: ";
//            for (int i = 0; i < vectorSize; ++i) {
//                cout << finalResult[i] << " ";
//            }
//            cout << endl;
//        }
//    }
//    else {
//        vector<int> localVectorA(vectorSize, 0);
//        vector<int> localVectorB(vectorSize, 0);
//
//        // Receive vectors from the root process using Scatter
//        MPI_Scatter(vectorA.data(), vectorSize, MPI_INT, localVectorA.data(), vectorSize, MPI_INT, 0, MPI_COMM_WORLD);
//        MPI_Scatter(vectorB.data(), vectorSize, MPI_INT, localVectorB.data(), vectorSize, MPI_INT, 0, MPI_COMM_WORLD);
//
//        // Each child process calculates its part of the sum
//        for (int i = 0; i < elementsPerProcess; ++i) {
//            partialResult[i] = localVectorA[i] + localVectorB[i];
//        }
//
//        // Send partial result back to the root process using Gather
//        MPI_Gather(partialResult.data(), elementsPerProcess, MPI_INT, NULL, 0, MPI_INT, 0, MPI_COMM_WORLD);
//    }
//
//    MPI_Finalize();
//    return 0;
//}