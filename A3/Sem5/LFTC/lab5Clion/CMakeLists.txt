cmake_minimum_required(VERSION 3.23)
project(lab5Clion)

set(CMAKE_CXX_STANDARD 14)

add_custom_command(
    OUTPUT ${CMAKE_CURRENT_SOURCE_DIR}/lex.yy.c
    COMMAND "C:\\Program Files (x86)\\Flex\\bin\\flex" ${CMAKE_CURRENT_SOURCE_DIR}/lexicalAnalyzer.l
    DEPENDS ${CMAKE_CURRENT_SOURCE_DIR}/lexicalAnalyzer.l
    COMMENT "Generating lex.yy.c"
    WORKING_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}
)

add_executable(lab5Clion lex.yy.c fip.h fip.cpp itemSymbolTable.h itemSymbolTable.cpp lexicalAtomTable.h lexicalAtomTable.cpp lexicographicalList.h lexicographicalList.cpp symbolTable.h symbolTable.cpp transition.h transition.cpp)
