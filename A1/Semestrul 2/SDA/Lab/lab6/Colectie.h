#pragma once

#include <utility>

#define NULL_TELEM -1
typedef int TElem;
#define INIT_LENGTH 90001
#define NIL (-0x3f3f3f3f)
//const std::pair<int,int> NIL = std::pair<int,int>{-1, -1};

class IteratorColectie;

class Colectie
{
	friend class IteratorColectie;

private:
    //reprezentare folosind o TD - rezolvare coliziuni prin liste intrepatrunse
    int m; //numarul de locatii din tabela de dispersie
    TElem *e; //vectorul elementelor - vector dinamic
    int *frecvente; // vectorul in care indicele corespunzator elementului e e frecventa sa
    int *urm; //vectorul legaturilor
    int primLiber; // locatia primei pozitii libere din tabela

    int nrelem; // numerul de elemente, pentru a nu reitera containerul

    //actualizare primLiber
    void actPrimLiber();
    //functia de dispersie
    int d(TElem e) const;

    void resize();
    void rehash();

    int getAnterior(int);
public:
		//constructorul implicit
		Colectie();

		//adauga un element in colectie
		void adauga(TElem e);

		//sterge o aparitie a unui element din colectie
		//returneaza adevarat daca s-a putut sterge
		bool sterge(TElem e);

		//verifica daca un element se afla in colectie
		bool cauta(TElem elem) const;

		//returneaza numar de aparitii ale unui element in colectie
		int nrAparitii(TElem elem) const;


		//intoarce numarul de elemente din colectie;
		int dim() const;

		//verifica daca colectia e vida;
		bool vida() const;

        // returneaza nr de elemente care au frecveta data
        int elementeCuFrecventaData(int frecventa) const;

        //returneaza un iterator pe colectie
		IteratorColectie iterator() const;

		// destructorul colectiei
		~Colectie();
};

