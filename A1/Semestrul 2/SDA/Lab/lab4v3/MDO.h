#pragma once

#include <vector>
#include "Lista.h"

typedef int TCheie;
typedef int TValoare;

#include <utility>
typedef std::pair<TCheie, TValoare> TElem;

using namespace std;

class IteratorMDO;

typedef bool(*Relatie)(TCheie, TCheie);
typedef bool(*Conditie)(TElem);

class MDO {
	friend class IteratorMDO;
    private:
        int primLiber;
        int primOcupat;
        int capacity;
        int dimensiune;
	    vector<Lista<TValoare>> valori;
        vector<TCheie> chei;
        vector<int> urm;
        Relatie rel;

        void aloca(int&); // aloc spatiu pentru a stoca o cheie si lista sa de valori // theta(1)
        void dealoca(int); // sterg cheia si lista de valori,// theta(1)
        int creeazaCheie(TCheie);// theta(1)
    public:

	// constructorul implicit al MultiDictionarului Ordonat
	MDO(Relatie r);//theta(capacity)

	// adauga o pereche (cheie, valoare) in MDO
	void adauga(TCheie c, TValoare v);//theta(capacity)

	//cauta o cheie si returneaza vectorul de valori asociate
	vector<TValoare> cauta(TCheie c) const; // O(nr_chei)

	//sterge o cheie si o valoare 
	//returneaza adevarat daca s-a gasit cheia si valoarea de sters
	bool sterge(TCheie c, TValoare v); // O(nrchei+nrvalori)

	//returneaza numarul de perechi (cheie, valoare) din MDO 
	int dim() const; // theta(1)

	//verifica daca MultiDictionarul Ordonat e vid 
	bool vid() const; // theta(1)

	// se returneaza iterator pe MDO
	// iteratorul va returna perechile in ordine in raport cu relatia de ordine
	IteratorMDO iterator() const; // theta(1)

    // păstrează în multidicționar numai acele perechi ale căror valoare respectă condiția dată

    // filtrez elementele ca sa respecte conditia
    /*
     * complexitate
     * caz favorabil: theta(dimensiune)
     * caz defavorabil: theta(dimensiune)
     * caz mediu: theta(dimensiune)
     * complexitate generala: theta(dimensiune)
     */
    void filtreaza(Conditie);
};
