#include "symbolTable.h"
#include <iostream>

int SymbolTable::getCodeFromSymbolTable(const string& symbol) {
    ItemSymbolTable* itemSymbolTable = lexicographicalList.findElement(symbol);
    if (itemSymbolTable == nullptr)
        return NO_FOUND;
    return itemSymbolTable->getIndexSymbolTable();
}

void SymbolTable::addIdentifierToSymbolTable(const string& symbol) {
    ItemSymbolTable* item = new ItemSymbolTable(symbol);
    lexicographicalList.addItem(item);
}

string SymbolTable::toString() {
    unordered_map<int, ItemSymbolTable*> hashMap;
    ItemSymbolTable* current = lexicographicalList.getHead();

    while (current != nullptr) {
        hashMap[current->getIndexSymbolTable()]= current;
        current = current->getNextElement();
    }

    int noElements = lexicographicalList.getSize();
    string text = "";
    for (int i = 1; i <= noElements; i++) {
        ItemSymbolTable* element = hashMap[i];
        int indexNeigh = -1;
        if (element != nullptr && element->getNextElement() != nullptr)
            indexNeigh = element->getNextElement()->getIndexSymbolTable();
        string line = "" + to_string(i) + " " + element->getSymbol() + " " + to_string(indexNeigh) + "\n";
        text = text + line;
    }

    return text;
}