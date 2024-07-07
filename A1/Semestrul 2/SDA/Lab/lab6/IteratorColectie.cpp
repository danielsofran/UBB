#include <exception>
#include "IteratorColectie.h"
#include "Colectie.h"


IteratorColectie::IteratorColectie(const Colectie& c): col(c) { // O(m)
	curent = 0;
    frecvcurenta = 0;
    deplasare();
}

void IteratorColectie::deplasare() { // O(m)
    while ((curent < col.m) && col.e[curent] == NIL)
        curent++;
    frecvcurenta = 1;
}

void IteratorColectie::prim() { // O(m)
	/* de adaugat */
    curent = 0;
    frecvcurenta = 0;
    deplasare();
}


void IteratorColectie::urmator() { // O(m)
	if(frecvcurenta<col.frecvente[curent])
        frecvcurenta++;
    else{
        curent++;
        deplasare();
    }
}


bool IteratorColectie::valid() const { // theta(1)
	return curent < col.m;
}

TElem IteratorColectie::element() const { // theta(1)
    if(!valid()) throw std::exception();
    return col.e[curent];
}
