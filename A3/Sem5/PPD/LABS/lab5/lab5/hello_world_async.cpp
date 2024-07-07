//#include <mpi.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//#include <iostream>
//#include <string>
//
//using namespace std;
//
//MPI_Request recv_request[10];
//
//int main(int argc, char** argv) {
//	int err = 0;
//	int a[10]; int total = 0; // maxim 10 procese
//
//	MPI_Request send_request;
//	err = MPI_Init(&argc, &argv);
//
//	int world_size;
//	MPI_Comm_size(MPI_COMM_WORLD, &world_size);
//	int world_rank;
//	MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);
//
//	if (world_rank != 0)
//		//transmitere asincrona
//		MPI_Isend(&world_rank, 1, MPI_INT, 0, 10, MPI_COMM_WORLD, &send_request);
//
//	else
//	{// procesul 0 asteapta de la toate cate un mesaj cu MPI_Irecv (fara blocare)
//	// apoi verifica (MPI_test) daca au venit sau nu mesajele – daca da le preia si le adauga intr-un string
//		string buff = "hello from 0";
//		int flag[10];
//		for (int i = 0; i < world_size; i++)
//			flag[i] = 0;
//
//		MPI_Status status[10];
//		for (int i = 1; i < world_size; i++)
//			MPI_Irecv(a + i, 1, MPI_INT, i, 10, MPI_COMM_WORLD, recv_request + i);
//
//		while (total < world_size - 1) {
//			for (int i = 1; i < world_size; i++) {
//				if (flag[i] == 0) {
//					MPI_Test(recv_request + i, &flag[i], &status[i]);
//					if (flag[i] != 0)
//					{
//						buff += " hello from ";
//						buff += to_string(a[i]);
//						total++;
//					}
//				}
//			}
//		}
//		cout << buff;
//	}
//	MPI_Finalize();
//	return 0;
//
//}