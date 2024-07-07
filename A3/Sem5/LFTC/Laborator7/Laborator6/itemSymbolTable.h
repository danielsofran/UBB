#pragma once
#include <string>
using namespace std;

class ItemSymbolTable {
private:
	string symbol;
	int indexSymbolTable;
	ItemSymbolTable* nextElement;

public:
	ItemSymbolTable(const string& symbol) : symbol{ symbol } {
		this->nextElement = nullptr;
	}

	string getSymbol() { return this->symbol; }
	int getIndexSymbolTable() { return this->indexSymbolTable; }
	ItemSymbolTable* getNextElement() { return this->nextElement; }

	void setSymbol(const string& symbol) { this->symbol = symbol; }
	void setIndexSymbolTable(int indexSymbolTable) { this->indexSymbolTable = indexSymbolTable;  }
	void setNextElement(ItemSymbolTable* nextElement) { this->nextElement = nextElement; }

};