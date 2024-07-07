#pragma once

#include <exception>
#include "Lista.h"

template<typename T>
class IteratorLP {
	friend class Lista<T>;
private:

	//constructorul primeste o referinta catre Container
	//iteratorul va referi primul element din container

	IteratorLP(const Lista<T>& l) :lista(l) {
        /* de adaugat */
        indice = l.primOcupat;
    }

	//contine o referinta catre containerul pe care il itereaza
	const Lista<T>& lista;

	/* aici e reprezentarea  specifica a iteratorului */
	int indice;

public:

	//reseteaza pozitia iteratorului la inceputul containerului
	void prim() {
        /* de adaugat */
        indice = lista.primOcupat;
    }

	//muta iteratorul in container
	// arunca exceptie daca iteratorul nu e valid
	void urmator(){
        /* de adaugat */
        if (!valid()) throw std::exception();
        indice = lista.urm[indice];
    }

	//verifica daca iteratorul e valid (indica un element al containerului)
	bool valid() const{
        /* de adaugat */
        return indice >= 1 && indice <= lista.capacitate;
    }

	//returneaza valoarea elementului din container referit de iterator
	//arunca exceptie daca iteratorul nu e valid
	TElem element() const{
        /* de adaugat */
        if (!valid()) throw std::exception();
        return lista.elements[indice];
    }

};


