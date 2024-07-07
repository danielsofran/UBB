#pragma once
#include "Lista.h"

class IteratorLP{
	friend class Lista;
private:

	// constructorul primeste o referinta catre Container
	// iteratorul va referi primul element din container

	explicit IteratorLP(const Lista& lista);

	// contine o referinta catre containerul pe care il itereaza
	const Lista& lista;

	/* aici e reprezentarea  specifica a iteratorului */
    Nod curent;
	
public:

		// reseteaza pozitia iteratorului la inceputul containerului
		void prim(); // theta(1)

        // muta iteratorul in container
        // arunca exceptie daca iteratorul nu e valid
        void anterior(); // theta(1)

		// muta iteratorul in container
		// arunca exceptie daca iteratorul nu e valid
		void urmator(); // theta(1)


        // verifica daca iteratorul e valid (indica un element al containerului)
		bool valid() const; // theta(1)

		// returneaza valoarea elementului din container referit de iterator
		// arunca exceptie daca iteratorul nu e valid
		TElem element() const; // theta(1)

};


