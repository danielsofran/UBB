//#include <mpi.h>
//#include <iostream>
//#include <string>
//
//using namespace std;
//
//int main(int argc, char** argv) {
//    int err = 0;
//    int a[10];
//    int total = 0; // maxim 10 procese
//
//    err = MPI_Init(&argc, &argv);
//
//    int world_size; // nr de procese
//    MPI_Comm_size(MPI_COMM_WORLD, &world_size);
//    int world_rank; // nr proces curent
//    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);
//
//    if (world_rank != 0) {
//        // transmitere sincrona
//        MPI_Send(&world_rank, 1, MPI_INT, 0, 10, MPI_COMM_WORLD);
//    }
//    else {
//        // procesul 0 asteapta de la toate cate un mesaj cu MPI_Recv (cu blocare)
//        // apoi le preia si le adauga intr-un string
//        string buff = "hello from 0";
//
//        MPI_Status status[10];
//        for (int i = 1; i < world_size; i++) {
//            MPI_Recv(a + i, 1, MPI_INT, i, 10, MPI_COMM_WORLD, &status[i]);
//            buff += " hello from ";
//            buff += to_string(a[i]);
//            total++;
//        }
//
//        cout << buff;
//    }
//
//    MPI_Finalize();
//    return 0;
//}