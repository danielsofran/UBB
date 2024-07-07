#pragma once

typedef int TElem;
typedef  bool (*Conditie)(TElem);

class IteratorLP;

struct Nod_ {
    TElem value;
    Nod_* next = nullptr;
    Nod_* prev = nullptr;
};
typedef Nod_* Nod;

class Lista {
private:
	friend class IteratorLP;
	Nod first;
    Nod last;
public:

		// constructor
		Lista (); // theta(1)

		// returnare dimensiune
		int dim() const; // theta(n)

		// verifica daca lista e vida
		bool vida() const; // theta(1)

		// prima pozitie din lista
		IteratorLP prim() const; // theta(1)

		// returnare element de pe pozitia curenta
		//arunca exceptie daca poz nu e valid
		TElem element(IteratorLP poz) const; // theta(1)

		// modifica element de pe pozitia poz si returneaza vechea valoare
		//arunca exceptie daca poz nu e valid
		TElem modifica(IteratorLP poz, TElem e); // theta(1)

		// adaugare element la inceput
		void adaugaInceput(TElem e);//theta(1)

		// adaugare element la sfarsit
		void adaugaSfarsit(TElem e);//theta(1)

		// adaugare element dupa o pozitie poz
		// dupa adaugare poz este pozitionat pe elementul adaugat
		// arunca exceptie daca poz nu e valid
		void adauga(IteratorLP& poz, TElem e); // theta(1)

		// sterge element de pe o pozitie poz si returneaza elementul sters
		// dupa stergere poz este pozitionat pe elementul de dupa cel sters
		// arunca exceptia daca poz nu e valid
		TElem sterge(IteratorLP& poz); // theta(1)

		// cauta element si returneaza prima pozitie pe care apare (sau iterator invalid)
		IteratorLP cauta(TElem e) const;//O(n)

        // păstrează în listă numai elementele care respectă condiția dată
        /**
         * complexitate:
         * BC: theta(n)
         * WC: theta(n)
         * AC: theta(n)
         * General complexity: theta(n)
         */
        void filtreaza(Conditie cond);

        //destructor
		~Lista(); // theta(n)
};
