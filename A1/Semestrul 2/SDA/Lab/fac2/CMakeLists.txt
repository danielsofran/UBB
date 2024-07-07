cmake_minimum_required(VERSION 3.21)
project(fac2 C)

set(CMAKE_C_STANDARD 99)

add_executable(fac2 complex.c complex.h main.c matrix.c matrix.h operations.c operations.h polynomial.c polynomial.h scalar.c scalar.h tests.c tests.h trigonometry.c trigonometry.h utils.c utils.h vector.c vector.h )
