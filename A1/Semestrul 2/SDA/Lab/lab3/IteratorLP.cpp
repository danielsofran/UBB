#include "IteratorLP.h"
#include "Lista.h"
#include <exception>

IteratorLP::IteratorLP(const Lista& l):lista(l) {
	curent = l.first;
}

void IteratorLP::prim(){
	curent = lista.first;
}

void IteratorLP::anterior(){
    if(curent == nullptr)
        throw std::exception();
    curent = curent->prev;
}

void IteratorLP::urmator(){
    if(curent == nullptr)
        throw std::exception();
	curent = curent->next;
}

bool IteratorLP::valid() const{
	return curent != nullptr;
}

TElem IteratorLP::element() const{
	/* de adaugat */
    if(curent != NULL)
        return curent->value;
    throw std::exception();
}


