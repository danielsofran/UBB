#pragma once
#include "IteratorColectie.h"

typedef int TElem;
typedef bool(*Relatie)(TElem, TElem);

#define NIL -0x3f3f3f3f

//in implementarea operatiilor se va folosi functia (relatia) rel (de ex, pentru <=)
// va fi declarata in .h si implementata in .cpp ca functie externa colectiei
bool rel(TElem, TElem);

class IteratorColectie;

struct Nod{
    TElem val = NIL;
    int frecv = 0;
    int st = NIL;
    int dr = NIL;

    bool isNULL() const {return val == NIL; }
};

class Colectie {
friend class IteratorColectie;
private:
	int size;
    int capacity;
    int primLiber;
    int rad;
    Nod* noduri;

    void actPrimLiber(); // theta(1)
    void resize(); // theta(1)
    int minim(int nod); // O(h)

    int adaugaRec(int nod, TElem e); // O(h)
    int stergeRec(int nod, TElem e, bool& gasit); //O(h)
    int stergeRecNod(int nod, TElem e, bool& gasit); //O(h)
public:
		//constructorul implicit
		Colectie(); // theta(1)

		//adauga un element in colectie
		void adauga(TElem e); // O(h)

		//sterge o aparitie a unui element din colectie
		//returneaza adevarat daca s-a putut sterge
		bool sterge(TElem e); // O(h)

		//verifica daca un element se afla in colectie
		bool cauta(TElem elem) const; // O(h)

		//returneaza numar de aparitii ale unui element in colectie
		int nrAparitii(TElem elem) const;


		//intoarce numarul de elemente din colectie;
		int dim() const; // theta(1)

		//verifica daca colectia e vida;
		bool vida() const; // theta(1)

		//returneaza un iterator pe colectie
		IteratorColectie iterator() const; // O(h)

		// destructorul colectiei
		~Colectie(); // theta(1)

        // BC theta(n)
        // WC theta(n*h)
        // AC theta(n*h)
        // GC O(n*h)
        int stergeToateElementeleRepetitive();
        // elimină complet toate elementele care apar de mai multe ori în colecție (toate aparițiile lor sunt eliminate)
        // returnează numărul de elemente eliminate

};

