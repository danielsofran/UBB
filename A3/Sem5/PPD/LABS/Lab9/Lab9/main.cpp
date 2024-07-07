#include <omp.h>
#include <iostream>
#include <random>
#include <stdlib.h>
#include <chrono>
using namespace std;
const int MAX = 1000;
double aa[MAX][MAX]; double bb[MAX][MAX];; double cc[MAX][MAX];
int a[MAX], b[MAX], c[MAX], d[MAX];

int produs_scalar(int a[MAX], int b[MAX]) {
	int s = 0;
	for (int i = 0; i < MAX; i++)
		s += a[i] * b[i];
	return s;
}

void suma_vectori(int a[MAX], int b[MAX], int c[MAX]) {
	for (int i = 0; i < MAX; i++)
		c[i] = a[i] + b[i];
}

void produs_vectori(int a[MAX], int b[MAX], int c[MAX]) {
	for (int i = 0; i < MAX; i++)
		c[i] = a[i] * b[i];
}


double f(double x, double y, int n) {
	double r = 1;
	for (int i = 0; i < n; i++) {
		r *= x - i;
		r /= (y - i) / x;
	}
	return r;
}

void matrices_sum(double a[MAX][MAX], double b[MAX][MAX], double c[MAX][MAX]) {
	//#pragma omp parallel  num_threads(8) 
	{
		//		#pragma omp for //collapse(2)
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				double x = f(a[i][j], b[i][j], 10);
				c[i][j] = x / (i * j);
			}
		}

	}
}

double PI(long npoints, int no_threads) {
	double sum = 0;
	long i;
#pragma omp parallel shared (npoints) private(i) reduction(+: sum) num_threads(no_threads)
	{
		int num_threads = omp_get_num_threads();
		long sample_points_per_thread = npoints / num_threads;
		//sum = 0;
		double rand_no_x, rand_no_y;

		std::time_t nt = std::chrono::system_clock::to_time_t(std::chrono::system_clock::now());
		thread_local unsigned long seed = time(&nt);

		srand(seed);
		for (i = 0; i < sample_points_per_thread; i++) {
			rand_no_y = (double)(rand()) / (double)(RAND_MAX);
			rand_no_x = (double)(rand()) / (double)(RAND_MAX);
			if (((rand_no_x) * (rand_no_x)+(rand_no_y) * (rand_no_y)) < 1) // R^2= aria patratului
				sum++;
		}
	}
	return 4 * sum / npoints;
}
double PI2(long npoints, int no_threads) {
	double sum = 0;
	long i;
	{
		double rand_no_x, rand_no_y;
		thread_local std::time_t nt = std::chrono::system_clock::to_time_t(std::chrono::system_clock::now());
		thread_local unsigned long seed = time(&nt);
		srand(seed);
		sum = 0;
#pragma omp parallel for num_threads(no_threads) shared (npoints)  private(i)   reduction(+: sum)
		for (i = 0; i < npoints; i++) {
			rand_no_y = (double)(rand()) / (double)(RAND_MAX);
			rand_no_x = (double)(rand()) / (double)(RAND_MAX);
			if (((rand_no_x) * (rand_no_x)+(rand_no_y) * (rand_no_y)) < 1) // R^2= aria patratului=1
				sum++;
		}
	}
	return 4 * sum / npoints;
}

int main() {



	int sum = 0;
	omp_set_num_threads(6);

	/*
#pragma omp parallel
	{
		cout << omp_get_num_threads() << endl;
	#pragma omp critical
		{		cout << "hello i am " << omp_get_thread_num() << endl; }
	}
	*/



	auto start = std::chrono::system_clock::now();

	for (int i = 0; i < MAX; i++) {
		a[i] = i; b[i] = i;
		for (int j = 0; j < MAX; j++) {
			aa[i][j] = (i + 1) * (j + 1) * 0.1;
			bb[i][j] = (i + 1) * (j + 1) * 0.1;
		}
	}

	auto end = std::chrono::system_clock::now();
	auto diff = end - start;
	cout << "computation time init matrices= " << chrono::duration <double, milli>(diff).count() << " ms" << endl;

	start = std::chrono::system_clock::now();
	omp_set_num_threads(4);
#pragma omp parallel num_threads(6) default (none) shared(sum, cout)
	{
		cout << "hello from  " << omp_get_thread_num() << endl;
#pragma omp for collapse(2)
		for (int i = 0; i < MAX; i++) {
			//a[i] = i; b[i] = i;
			for (int j = 0; j < MAX; j++) {
				aa[i][j] = (i - 1) * (j + 2) * 0.2;
				bb[i][j] = (i - 1) * (j + 1) * 0.3;
			}
		}
	}
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time init matrices pragma=" << chrono::duration <double, milli>(diff).count() << " ms" << endl;

	start = std::chrono::system_clock::now();
	int x = produs_scalar(a, b);
	suma_vectori(a, b, c);
	produs_vectori(a, b, d);
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time 3 functii=" << chrono::duration <double, milli>(diff).count() << " ms" << endl;


	start = std::chrono::system_clock::now();
#pragma omp parallel num_threads(3) 
	{
#pragma omp sections
		{
#pragma omp section
			{
				int x = produs_scalar(a, b);
				//#pragma omp critical
				{
					//	cout <<"produs scalar="<<x<<"  " << omp_get_thread_num()<<endl;
				}

			}
#pragma omp section
			{
				suma_vectori(a, b, c);
				//	#pragma omp critical
				{
					//		cout << "suma vectori="  << omp_get_thread_num()<<endl;
				}

			}
#pragma omp section
			{
				produs_vectori(a, b, d);
				//#pragma omp critical
				{
					//		cout << "produs vectori=" << omp_get_thread_num()<<endl;
				}

			}

		}
	}
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time 3 functii cu sections=" << chrono::duration <double, milli>(diff).count() << " ms" << endl;



#pragma omp parallel num_threads(5) 
	{
#pragma omp for
		for (int i = 0; i < MAX; i++)
			c[i] = a[i] + b[i];// +omp_get_thread_num();//+ omp_get_num_threads()
		// sum = 0; trebuie sa fie initializata in afara regiunii paralele
#pragma omp for reduction(+:sum)
		for (int i = 0; i < MAX; i++)
			sum += a[i];
	}

	//for (int i = 0; i < MAX; i++)
	//	cout << c[i] << "  ";
	cout << endl << "sum=" << sum << endl;

	long points = 1 << 20;

	start = std::chrono::system_clock::now();
	cout << endl << PI(points, 4) << endl;
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time " << chrono::duration <double, milli>(diff).count() << " ms" << endl;


	start = std::chrono::system_clock::now();
	cout << endl << PI2(points, 4) << endl;
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time " << chrono::duration <double, milli>(diff).count() << " ms" << endl;


	start = std::chrono::system_clock::now();
	matrices_sum(aa, bb, cc);
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time " << chrono::duration <double, milli>(diff).count() << " ms" << endl;
	/*
	start = std::chrono::system_clock::now();
	cout << endl << "PI2-2=" << PI2(points, 2) << endl;
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time " << chrono::duration <double, milli>(diff).count() << " ms" << endl;

	start = std::chrono::system_clock::now();
	cout << endl << PI(points, 4) << endl;
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time " << chrono::duration <double, milli>(diff).count() << " ms" << endl;
	start = std::chrono::system_clock::now();
	cout << endl << "PI2-4=" << PI2(points, 4) << endl;
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time " << chrono::duration <double, milli>(diff).count() << " ms" << endl;

	start = std::chrono::system_clock::now();
	cout << endl << PI(points, 6) << endl;
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time " << chrono::duration <double, milli>(diff).count() << " ms" << endl;

	start = std::chrono::system_clock::now();
	cout << endl << "PI2-6=" << PI2(points, 6) << endl;
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time " << chrono::duration <double, milli>(diff).count() << " ms" << endl;

	start = std::chrono::system_clock::now();
	cout << endl << PI(points, 8) << endl;
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time " << chrono::duration <double, milli>(diff).count() << " ms" << endl;

	start = std::chrono::system_clock::now();
	cout << endl << "PI2-8=" << PI2(points, 8) << endl;
	end = std::chrono::system_clock::now();
	diff = end - start;
	cout << "computation time " << chrono::duration <double, milli>(diff).count() << " ms" << endl;
	*/
	return 0;
}