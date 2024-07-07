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
//        // Initialize vectors with some values only in process 0
//        for (int i = 0; i < vectorSize; ++i) {
//            vectorA[i] = i; // Example values, modify as needed
//            vectorB[i] = i;  // Example values, modify as needed
//        }
//
//        // Send vectors to child processes
//        for (int i = 1; i < world_size; ++i) {
//            MPI_Send(vectorA.data(), vectorSize, MPI_INT, i, 20, MPI_COMM_WORLD);
//            MPI_Send(vectorB.data(), vectorSize, MPI_INT, i, 30, MPI_COMM_WORLD);
//        }
//
//        for (int i = 1; i < world_size; ++i) {
//            MPI_Recv(finalResult.data() + (i - 1) * elementsPerProcess, elementsPerProcess, MPI_INT, i, 40, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
//        }
//
//        // Root process prints the final result
//        cout << "Final result: ";
//        for (int i = 0; i < vectorSize; ++i) {
//            cout << finalResult[i] << " ";
//        }
//        cout << endl;
//    }
//    else {
//        // Receive vectors from the root process
//        MPI_Recv(vectorA.data(), vectorSize, MPI_INT, 0, 20, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
//        MPI_Recv(vectorB.data(), vectorSize, MPI_INT, 0, 30, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
//
//        // Each child process calculates its part of the sum
//        for (int i = 0; i < elementsPerProcess; ++i) {
//            partialResult[i] = vectorA[(world_rank - 1) * elementsPerProcess + i] + vectorB[(world_rank - 1) * elementsPerProcess + i];
//        }
//
//        // Send partial result back to the root process
//        MPI_Send(partialResult.data(), elementsPerProcess, MPI_INT, 0, 40, MPI_COMM_WORLD);
//    }
//
//    MPI_Finalize();
//    return 0;
//}