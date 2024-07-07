#pragma once
#include "itemSymbolTable.h"
#include <string.h>
using namespace std;

class LexicographicalList {
private:
	ItemSymbolTable* head;
	int size;

public:
	LexicographicalList() = default;

	int getSize() { return this -> size; }
	ItemSymbolTable* getHead() { return this->head; }

	ItemSymbolTable* findElement(const string& symbol);
	
	void addItem(ItemSymbolTable* item);


};