#include "lexicographicalList.h"
#include <iostream>

ItemSymbolTable* LexicographicalList::findElement(const string& symbol) {
	ItemSymbolTable* copyHead = head;
	while (copyHead != nullptr) {
		if (copyHead->getSymbol() == symbol)
			return copyHead;
		copyHead = copyHead->getNextElement();
	}
	return nullptr;
}

void LexicographicalList::addItem(ItemSymbolTable* item) {
	size = size + 1;
	item->setIndexSymbolTable(size);

	if (size == 1) {
		head = item;
		item->setNextElement(nullptr);
		return;
	}

	ItemSymbolTable* current = head;
	ItemSymbolTable* prev = nullptr;
	while (current != nullptr && item->getSymbol() > current->getSymbol()) {
		prev = current;
		current = current->getNextElement();
	}

	if (current != nullptr && head->getSymbol() == current->getSymbol()) {
		item->setNextElement(head);
		head = item;
		return;
	}

	if (current == nullptr) {
		prev->setNextElement(item);
		item->setNextElement(nullptr);
		return;
	}

	prev->setNextElement(item);
	item->setNextElement(current);

}