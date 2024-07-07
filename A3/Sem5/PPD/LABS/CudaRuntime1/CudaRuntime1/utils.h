#pragma once

#include <string>

void borderMatrix(int** A, int n, int m);

void generateRandomData(int n, int m, int L);

void writeMatrixToFile(const std::string& filename, int** V, int n, int m);

void readMatrixFromFile(const std::string& filename, int n, int m, int** V, int** conv);

