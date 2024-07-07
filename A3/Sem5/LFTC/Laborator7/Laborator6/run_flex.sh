$(bison -d syntacticAnalyzer.y)
$(flex lexicalAnalyzer.l)
$(g++ syntacticAnalyzer.tab.c  lex.yy.c fip.cpp itemSymbolTable.cpp lexicalAtomTable.cpp lexicographicalList.cpp symbolTable.cpp transition.cpp -o exe -std=c++14)
