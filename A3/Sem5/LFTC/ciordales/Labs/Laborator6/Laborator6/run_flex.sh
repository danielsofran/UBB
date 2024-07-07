filePath=$1
echo $filePath
$(flex $filePath)
$(g++ lex.yy.c fip.cpp itemSymbolTable.cpp lexicalAtomTable.cpp lexicographicalList.cpp symbolTable.cpp transition.cpp -o exe -std=c++14)
