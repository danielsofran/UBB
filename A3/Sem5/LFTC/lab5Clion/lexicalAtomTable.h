#pragma once
#include "map"
#include <string>
#include <string.h>
#include <iostream>
using namespace std;

class LexicalAtomTable {
private:
	map<string, int> lexicalTable;
	int increment;

public:
	LexicalAtomTable() {
		increment = 2;
		lexicalTable["ID"] = 0;
		lexicalTable["CONST"] = 1;
	}

	int getCodeAtom(const string& lexicalCode) { 
		if(lexicalTable.find(lexicalCode) != lexicalTable.end())
			return lexicalTable[lexicalCode]; 
		return -1;
	}
	int getIdentifierAtomCode() { return 0; }
	int getLiteralAtomCode() { return 1; }

	void addLexicalAtom(const string& lexicalAtom);

	string toString();

};