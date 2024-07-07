#pragma once
#include "lexicographicalList.h"
#include "itemSymbolTable.h"
#include <string.h>
#include <unordered_map>
using namespace std;

class SymbolTable {
private:
	int NO_FOUND = 0;
	LexicographicalList lexicographicalList;

public:
	SymbolTable() = default;

	int getCodeFromSymbolTable(const string& symbol);
	void addIdentifierToSymbolTable(const string& symbol);

	string toString();
};
