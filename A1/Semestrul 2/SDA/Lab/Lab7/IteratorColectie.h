#pragma once
#include "Colectie.h"
#include "stack"

using std::stack;

class Colectie;
typedef int TElem;

class IteratorColectie
{
	friend class Colectie;

private:
    //constructorul primeste o referinta catre Container
	//iteratorul va referi primul element din container
   IteratorColectie(const Colectie& c); // O(h)

    //contine o referinta catre containerul pe care il itereaza
	const Colectie& col;
	/* aici e reprezentarea  spcifica a iteratorului*/

    stack<int> s;
    int frecv = 1;
public:

		//reseteaza pozitia iteratorului la inceputul containerului
		void prim(); // O(h)

		//muta iteratorul in container
		// arunca exceptie daca iteratorul nu e valid
		void urmator(); // O(h)

		//verifica daca iteratorul e valid (indica un element al containerului)
		bool valid() const; // theta(1)

		//returneaza valoarea elementului din container referit de iterator
		//arunca exceptie daca iteratorul nu e valid
		TElem element() const; // theta(1)
};

