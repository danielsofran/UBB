
#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include <stdio.h>
#include <iostream>
#include <fstream>
#include <chrono>

#include "utils.h"

using namespace std;

// 1 row of 10 blocks each having 10 threads each computing 10 lines
#define LINES_PER_THREAD 10
#define BLOCK_COUNT 10
#define BLOCK_DIM 10
#define N 1000
#define EXTENDED_N_SIZE 

#define INDEX_X 0
#define INDEX_Y 0

__device__ inline int* getRow(int* dev, size_t pitch, int i) {
    return (int*)((char*)dev + i * pitch);
}

__device__ int *getElem(int *dev, size_t pitch, int i, int j) {
    int* row = getRow(dev, pitch, i);
    return &row[j];
}

__device__ void printMat(int *dev, size_t pitch) {
    int n = 3;
    for (int i = 0; i < n; ++i, printf("\n"))
        for (int j = 0; j < n; ++j)
            printf("%d ", *getElem(dev, pitch, i + INDEX_X, j + INDEX_Y));
}

__host__ void printMatHost(int **host) {
    int n = 3;
    for (int i = 0; i < n; ++i, printf("\n"))
        for (int j = 0; j < n; ++j)
            printf("%d ", host[i + INDEX_X][j + INDEX_Y]);
}

__global__ void convolution(int *devA, size_t pitchA, int *devC, size_t pitchC) {

    int id = blockIdx.x * blockDim.x + threadIdx.x;
    int lineStart = id * 10 + 1, lineEnd = lineStart + 10;
    //printMat(devA, pitchA);
    //printf("%d", *getElem(devA, pitchA, 1000, 0));
    //int* elem = getElem(devA, pitchA, 0, 0);
    //*elem = 0;

    //copy frontiers

    int current[N + 2],
        above[N + 2],
        frontierAbove[N + 2],
        frontierBelow[N + 2];

    //copy(getElem(devA, pitchA, lineStart - 1, 0), getElem(devA, pitchA, lineStart - 1, N + 2), frontierAbove);
    for (int i = 0; i < N + 2; ++i) {
        frontierAbove[i] = *getElem(devA, pitchA, lineStart - 1, i);
        frontierBelow[i] = *getElem(devA, pitchA, lineEnd, i);
    }
    __syncthreads(); // Wait for all threads to get their frontiers

    //cudaMemcpyAsync(&above, getRow(devA, pitchA, lineStart), EXTENDED_N_SIZE, cudaMemcpyDeviceToDevice);
    for (int i = 0; i < N + 2; ++i)
        above[i] = *getElem(devA, pitchA, lineStart, i);
    int left = (*getElem(devA, pitchA, lineStart, 0));

    // first line
    for (int j = 1; j <= N; j++) {
        int value = 0;
        for (int ii = 0; ii <= 1; ii++) {
            for (int jj = -1; jj <= 1; jj++) {
                if (ii == 0 && jj == -1) {
                    value += left * (*getElem(devC, pitchC, ii + 1, jj + 1));
                } else {
                    value += (*getElem(devA, pitchA, lineStart + ii, j + jj)) * (*getElem(devC, pitchC, ii + 1, jj + 1));
                }
            }
        }
        for (int jj = -1; jj <= 1; jj++) {
            value += frontierAbove[j + jj] * (*getElem(devC, pitchC, 0, jj + 1));
        }
        left = (*getElem(devA, pitchA, lineStart, j));
        int *elemToSet = getElem(devA, pitchA, lineStart, j);
        *elemToSet = value;
    }
    left = (*getElem(devA, pitchA, lineStart + 1, 0));

    //inner lines
    for (int i = lineStart + 1; i < lineEnd - 1; i++) {
        //cudaMemcpy(&current, getRow(devA, pitchA, lineStart), EXTENDED_N_SIZE, cudaMemcpyDeviceToDevice);
        for (int k = 0; k < N + 2; ++k)
            current[k] = *getElem(devA, pitchA, i, k);

        for (int j = 1; j <= N; j++) {
            int value = 0;
            for (int ii = 0; ii <= 1; ii++) {
                for (int jj = -1; jj <= 1; jj++) {
                    if (ii == 0 && jj == -1) {
                        value += left * (*getElem(devC, pitchC, ii + 1, jj + 1));
                    } else {
                        value += (*getElem(devA, pitchA, i + ii, j + jj)) * (*getElem(devC, pitchC, ii + 1, jj + 1));
                    }
                }
            }
            for (int jj = -1; jj <= 1; jj++) {
                value += above[j + jj] * (*getElem(devC, pitchC, 0, jj + 1));
            }
            if (j == N) {
                left = (*getElem(devA, pitchA, i + 1, 0));
            } else {
                left = (*getElem(devA, pitchA, i, j));
            }
            *getElem(devA, pitchA, i, j) = value;
        }
        //cudaMemcpy(&above, &current, EXTENDED_N_SIZE, cudaMemcpyDeviceToDevice);
        for (int k = 0; k < N + 2; ++k)
            above[k] = current[k];
    }

    //last line
    for (int j = 1; j <= N; j++) {
        int value = 0;
        for (int jj = -1; jj <= 1; jj++) {
            value += above[j + jj] * (*getElem(devC, pitchC, 0, jj + 1));
        }
        for (int jj = -1; jj <= 1; jj++) {
            if (jj == -1) {
                value += left * (*getElem(devC, pitchC, 1, jj + 1));
            } else {
                value += (*getElem(devA, pitchA, lineEnd - 1, j + jj)) * (*getElem(devC, pitchC, 1, jj + 1));
            }
        }
        for (int jj = -1; jj <= 1; jj++) {
            value += frontierBelow[j + jj] * (*getElem(devC, pitchC, 2, jj + 1));
        }
        left = (*getElem(devA, pitchA, lineEnd - 1, j));
        *getElem(devA, pitchA, lineEnd - 1, j) = value;
    }
}

int main() {
    std::chrono::steady_clock::time_point t_start, t_end;
    cudaSetDevice(0);
    const string filename = "..\\x64\\Debug\\data.txt";
    int **hostA, **hostC;
    int *deviceA, *deviceC;
    size_t pitchA, pitchC;

    // initialize host memory
    hostA = (int **) malloc(sizeof(int *) * (N + 2));
    for (int i = 0; i < N + 2; ++i) hostA[i] = (int *) malloc(sizeof(int) * (N + 2));
    hostC = (int **) malloc(sizeof(int *) * 3);
    for (int i = 0; i < 3; ++i) hostC[i] = (int *) malloc(sizeof(int) * 3);

    // initialize device (GPU) memory
    cudaMallocPitch(&deviceA, &pitchA, (N + 2) * sizeof(int), N + 2);
    cudaMallocPitch(&deviceC, &pitchC, 3 * sizeof(int), 3);

    readMatrixFromFile(filename, N, N, hostA, hostC);
    borderMatrix(hostA, N, N);

    // Copy data from host to device for matrix A
    for (int i = 0; i < N + 2; ++i) {
        cudaMemcpy(reinterpret_cast<char *>(deviceA) + i * pitchA, hostA[i], (N + 2) * sizeof(int),
                   cudaMemcpyHostToDevice);
    }

    // Copy data from host to device for matrix C
    for (int i = 0; i < 3; ++i) {
        cudaMemcpy(reinterpret_cast<char *>(deviceC) + i * pitchC, hostC[i], 3 * sizeof(int), cudaMemcpyHostToDevice);
    }

    t_start = std::chrono::high_resolution_clock::now();

    convolution<<<BLOCK_COUNT, BLOCK_DIM>>>(deviceA, pitchA, deviceC, pitchC);
    cudaDeviceSynchronize();

    t_end = std::chrono::high_resolution_clock::now();

    for (int i = 0; i < N + 2; ++i) {
        cudaMemcpy(reinterpret_cast<char *>(hostA[i]), reinterpret_cast<char *>(deviceA) + i * pitchA,
            (N + 2) * sizeof(int), cudaMemcpyDeviceToHost);
    }

    writeMatrixToFile("..\\x64\\Debug\\outThreads.txt", hostA, N + 2, N + 2);

    cudaFree(deviceA);
    cudaFree(deviceC);
    for (int i = 0; i < N + 2; ++i)
        free(hostA[i]);
    free(hostA);
    for (int i = 0; i < 3; ++i)
        free(hostC[i]);
    free(hostC);

    cudaError_t cudaStatus = cudaSuccess;
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "addWithCuda failed!");
        return 1;
    }

    // cudaDeviceReset must be called before exiting in order for profiling and
    // tracing tools such as Nsight and Visual Profiler to show complete traces.
    cudaStatus = cudaDeviceReset();
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "cudaDeviceReset failed!");
        return 1;
    }

    double elapsed_time_ms = std::chrono::duration<double, std::milli>(t_end - t_start).count();

    cout << elapsed_time_ms;

    return 0;
}

