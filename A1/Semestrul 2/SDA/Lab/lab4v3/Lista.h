#pragma once
//#include "IteratorLP.h"

template<typename T>
class IteratorLP;

template<typename TElem>
class Lista {
private:
	friend class IteratorLP<TElem>;
    typedef IteratorLP<TElem> iterator;
	/* aici e reprezentarea */

	TElem* elements;
	int* urm;
	int dimensiune, capacitate;
	int primLiber;
	int primOcupat, ultimOcupat;//indice catre primul element din lista
	
	void initSpatiuLiber(int capacitate_) { // theta(capacitate)
        for (int i = 1; i < capacitate_; i++)
        {
            urm[i] = i + 1;
        }
        urm[capacitate_] = 0;
        primLiber = 1;
    }
	void aloca(int& i) {//aloc spatiu pt a stoca un nou element theta(1)
        if(primLiber<0 || primLiber>capacitate)
        {
            capacitate = 100;
            dimensiune = 0;
            elements = new TElem[capacitate + 1];
            urm = new int[capacitate + 1];
            initSpatiuLiber(capacitate);
            primOcupat = 0;
            ultimOcupat = 0;
        }
        i = primLiber;
        primLiber = urm[primLiber];
    }
	void dealoca(int i) {//dealoc spatiul ocupat de pozitia i pt a putea fi folosit la urm inserare theta(1)
        urm[i] = primLiber;
        primLiber = i;
    }
	int creeazaNod(TElem e) { // O(capacitate)
        if (primLiber == 0) {
            //realocare
            TElem* newelements = new TElem[capacitate * 2 + 1];
            int* newurmator = new int[capacitate * 2 + 1];
            for (int i = 1; i <= capacitate; i++) {
                newelements[i] = elements[i];
                newurmator[i] = urm[i];
            }
            delete[] urm;
            delete[] elements;

            urm = newurmator;
            elements = newelements;
            //reinitializare spatiu liber
            primLiber = capacitate + 1;
            for (int i = primLiber; i < capacitate * 2; i++)
                urm[i] = i + 1;
            urm[2 * capacitate] = 0;
            capacitate *= 2;
        }
        int i;
        aloca(i);
        elements[i] = e;
        urm[i] = 0;
        return i;
    }

public:

	// constructor
	Lista(){ // theta(1)
        /* de adaugat */
        capacitate = 100;
        dimensiune = 0;
        elements = new TElem[capacitate + 1];
        urm = new int[capacitate + 1];
        initSpatiuLiber(capacitate);
        primOcupat = 0;
        ultimOcupat = 0;
    }

    void clear(){ // curat toate elementele, reinitializez spatiul liber, theta(1)
        capacitate = 100;
        dimensiune = 0;
        elements = new TElem[capacitate + 1];
        urm = new TElem[capacitate + 1];
        initSpatiuLiber(capacitate);
        primOcupat = 0;
        ultimOcupat = 0;
    }

    Lista(const Lista& lista) // theta(capacitate)
    {
        capacitate = lista.capacitate;
        dimensiune = lista.dimensiune;
        elements = new TElem[capacitate + 1];
        urm = new TElem[capacitate + 1];
        for(int i=1;i<=capacitate;++i)
            elements[i] = lista.elements[i],
            urm[i] = lista.elements[i];
        primLiber = lista.primLiber;
        primOcupat = lista.primOcupat;
        ultimOcupat = lista.ultimOcupat;
    }

    Lista& operator=(const Lista& lista) // theta(capacitate)
    {
        if(&lista == this) return *this;
        capacitate = lista.capacitate;
        dimensiune = lista.dimensiune;
        elements = new TElem[capacitate + 1];
        urm = new TElem[capacitate + 1];
        for(int i=1;i<=capacitate;++i)
            elements[i] = lista.elements[i],
                    urm[i] = lista.elements[i];
        primLiber = lista.primLiber;
        primOcupat = lista.primOcupat;
        ultimOcupat = lista.ultimOcupat;
        return *this;
    }

	// returnare dimensiune
	int dim() const{ // theta(1)
        /* de adaugat */
        return dimensiune;
    }

	// verifica daca lista e vida
	bool vida() const{ // theta(1)
        /* de adaugat */
        return primOcupat == 0;
    }

	// prima pozitie din lista
	IteratorLP<TElem> prim() const{ // theta(1)
        /* de adaugat */
        return IteratorLP<TElem>(*this);
    }

	// returnare element de pe pozitia curenta
	//arunca exceptie daca poz nu e valid
	TElem element(IteratorLP<TElem> poz) const{ // theta(1)
        /* de adaugat */
        return poz.element();
    }

	// modifica element de pe pozitia poz si returneaza vechea valoare
	//arunca exceptie daca poz nu e valid
	TElem modifica(IteratorLP<TElem> poz, TElem e){ // O(n)
        /* de adaugat */
        if (!poz.valid())
            throw std::exception();
        TElem elem = elements[poz.indice];
        if (poz.indice == primOcupat)// sterg primul
            primOcupat = urm[poz.indice];
        else {
            int q = primOcupat;
            while (urm[q] != poz.indice) // iau primul dinaintea celui dat
                q = urm[q];
            urm[q] = urm[poz.indice];
        }
        dimensiune--;
        dealoca(poz.indice);
        poz.urmator();
        return elem;
    }

	// adaugare element la inceput
	void adaugaInceput(TElem e){ // theta(1)
        /* de adaugat */
        int nouIndice = creeazaNod(e);
        urm[nouIndice] = primOcupat;
        primOcupat = nouIndice;
        if (ultimOcupat == 0) ultimOcupat = nouIndice;//cand avem un elem prima pozitie coresp cu ultima
        dimensiune++;
    }

	// adaugare element la sfarsit
	void adaugaSfarsit(TElem e){ // theta(1)
        /* de adaugat */
        int nouIndice = creeazaNod(e);
        urm[ultimOcupat] = nouIndice;
        ultimOcupat = nouIndice;
        if (primOcupat == 0) {
            primOcupat = nouIndice;//cand avem un elem prima pozitie coresp cu ultima
        }
        dimensiune++;
    }

	// adaugare element dupa o pozitie poz
	//dupa adaugare poz este pozitionat pe 
	// elementul adaugat
	//arunca exceptie daca poz nu e valid
	void adauga(IteratorLP<TElem>& poz, TElem e){ // theta(1)
        /* de adaugat */
        if (!poz.valid()) throw std::exception();
        int nouIndice = creeazaNod(elements[poz.indice]);
        int oldurm = urm[poz.indice];//salvez ultimul indice ca sa-l pot suprascrie
        elements[poz.indice] = e;
        urm[poz.indice] = nouIndice;
        urm[nouIndice] = oldurm;
        dimensiune++;
    }

	// sterge element de pe o pozitie poz si returneaza elementul sters
	//dupa stergere poz este pozitionat pe elementul de dupa cel sters
	//arunca exceptia daca poz nu e valid
	TElem sterge(IteratorLP<TElem>& poz){ // O(n)
        /* de adaugat */
        if (!poz.valid())
            throw std::exception();
        TElem elem = elements[poz.indice];
        if (poz.indice == primOcupat)// sterg primul
            primOcupat = urm[poz.indice];
        else {
            int q = primOcupat;
            while (urm[q] != poz.indice) // iau primul dinaintea celui dat
                q = urm[q];
            urm[q] = urm[poz.indice];
        }
        dimensiune--;
        if(dimensiune == 0)
            primOcupat = 0,
            ultimOcupat = 0;
        dealoca(poz.indice);
        poz.urmator();
        if(dimensiune==0)
            clear();
        return elem;
    }

	// cauta element si returneaza prima pozitie pe care apare (sau iterator invalid)
	IteratorLP<TElem> cauta(TElem e) const{ // O(n)
        /* de adaugat */
        IteratorLP<TElem> it(*this);
        it.prim();
        while (it.valid() && it.element() != e)
            it.urmator();
        return it;
    }

	//destructor

	~Lista(){ // theta(1)
        /* de adaugat */
        delete[] urm;
        delete[] elements;
    }
};

template<typename T>
class IteratorLP {
    friend class Lista<T>;
private:

    //constructorul primeste o referinta catre Container
    //iteratorul va referi primul element din container

    //contine o referinta catre containerul pe care il itereaza
    const Lista<T>& lista;

    /* aici e reprezentarea  specifica a iteratorului */
    int indice;

public:

    //reseteaza pozitia iteratorului la inceputul containerului
    void prim() { // theta(1)
        /* de adaugat */
        indice = lista.primOcupat;
    }

    //muta iteratorul in container
    // arunca exceptie daca iteratorul nu e valid
    void urmator(){// theta(1)
        /* de adaugat */
        if (!valid()) throw std::exception();
        indice = lista.urm[indice];
    }

    //verifica daca iteratorul e valid (indica un element al containerului)
    bool valid() const{// theta(1)
        /* de adaugat */
        return indice >= 1 && indice <= lista.capacitate;
    }

    //returneaza valoarea elementului din container referit de iterator
    //arunca exceptie daca iteratorul nu e valid
    T element() const{// theta(1)
        /* de adaugat */
        if (!valid()) throw std::exception();
        return lista.elements[indice];
    }

    IteratorLP(const Lista<T>& l) :lista(l) {// theta(1)
        /* de adaugat */
        indice = l.primOcupat;
    }
};
