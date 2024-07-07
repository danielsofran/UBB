#!/bin/bash

# Set default input file
input_file=${1:-input.cpp}

# Compile Bison and Flex
$(bison -dv syntacticAnalyzer.y &> /dev/null)
$(flex lexicalAnalyzer.l)

# Compile the compiler
$(gcc syntacticAnalyzer.tab.c lex.yy.c -o compiler)

# Run the compiler with the input file
output=$(./compiler < "$input_file")

# Use awk to separate the strings before and after the dashed line
before_dash=$(echo "$output" | awk '/----/ {exit} {print}')
after_dash=$(echo "$output" | awk '/----/ {flag=1; next} flag')

# Print the results
echo "$before_dash"
echo "$after_dash" > code.asm

# Run the build script using cmd.exe
cmd.exe /c '.\\build.bat'
