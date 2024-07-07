#include "IteratorLP.h"
#include "Lista.h"
#include <exception>

IteratorLP::IteratorLP(const Lista& l) :lista(l) {
	/* de adaugat */
	indice = l.primOcupat;
}

void IteratorLP::prim() {
	/* de adaugat */
	indice = lista.primOcupat;
}

void IteratorLP::urmator() {
	/* de adaugat */
	if (!valid()) throw std::exception();
	indice = lista.urm[indice];
}

bool IteratorLP::valid() const {
	/* de adaugat */
	return indice >= 1 && indice <= lista.capacitate;
}

TElem IteratorLP::element() const {
	/* de adaugat */
	if (!valid()) throw std::exception();
	return lista.elements[indice];
}


