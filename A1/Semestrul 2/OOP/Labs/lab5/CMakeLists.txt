cmake_minimum_required(VERSION 3.7.2)
project(lab5 C)

set(CMAKE_C_STANDARD 99)

add_executable(lab5 main.c repository.h repository.c player.h player.c service.h service.c testing.h testing.c ui.h ui.c)
