#pragma once

#include "MDO.h"


class IteratorMDO{
	friend class MDO;
private:

	//constructorul primeste o referinta catre Container
	//iteratorul va referi primul element din container
	IteratorMDO(const MDO& dictionar);

	//contine o referinta catre containerul pe care il itereaza
	const MDO& dict;
	/* aici e reprezentarea  specifica a iteratorului */
    int index; // pentru vectorul urm
    int indexval; // pentru lista de valori

public:

		//reseteaza pozitia iteratorului la inceputul containerului
		void prim();// theta(1)

		//muta iteratorul in container
		// arunca exceptie daca iteratorul nu e valid
		void urmator();// theta(1)

		//verifica daca iteratorul e valid (indica un element al containerului)
		bool valid() const;// theta(1)

		//returneaza valoarea elementului din container referit de iterator
		//arunca exceptie daca iteratorul nu e valid
		TElem element() const;// theta(nr_valori)
};

