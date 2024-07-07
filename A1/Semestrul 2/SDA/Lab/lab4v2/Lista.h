#pragma once
//#include "IteratorLP.h"
#include "MDO.h"

template<typename T>
class IteratorLP;

template<typename T>
class Lista;

template<typename T>
class ListaOrdonata;

template<typename T>
class Lista {
private:
	friend class IteratorLP<T>;
    friend class ListaOrdonata<T>;
    friend class MDO;
    typedef IteratorLP<T> iterator;
	/* aici e reprezentarea */

	T* elements;
	int* urm;
	int dimensiune, capacitate;
	int primLiber;
	int primOcupat, ultimOcupat;//indice catre primul element din lista
	
	void initSpatiuLiber(int capacitate) { //
        for (int i = 1; i < capacitate; i++)
        {
            urm[i] = i + 1;
        }
        urm[capacitate] = 0;
        primLiber = 1;
    }
	void aloca(int& i) {//aloc spatiu pt a stoca un nou element
        i = primLiber;
        primLiber = urm[primLiber];
    }
	void dealoca(int i) {//dealoc spatiul ocupat de pozitia i pt a putea fi folosit la urm inserare
        urm[i] = primLiber;
        primLiber = i;
    }
	int creeazaNod(T e) {
        if (primLiber == 0) {
            //realocare
            T* newelements = new T[capacitate * 2 + 1];
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
	Lista(){
        /* de adaugat */
        capacitate = 100;
        dimensiune = 0;
        elements = new T[capacitate + 1];
        urm = new int[capacitate + 1];
        initSpatiuLiber(capacitate);
        primOcupat = 0;
        ultimOcupat = 0;
    }

    Lista(const Lista& lista)
    {
        capacitate = lista.capacitate;
        dimensiune = lista.dimensiune;
        elements = new T[capacitate + 1];
        urm = new int[capacitate + 1];
        for(int i=1;i<=capacitate;++i)
            elements[i] = lista.elements[i],
            urm[i] = lista.urm[i];
        primLiber = lista.primLiber;
        primOcupat = lista.primOcupat;
        ultimOcupat = lista.ultimOcupat;
    }

    Lista& operator=(const Lista& lista)
    {
        if(&lista == this) return *this;
        capacitate = lista.capacitate;
        dimensiune = lista.dimensiune;
        elements = new T[capacitate + 1];
        urm = new T[capacitate + 1];
        for(int i=1;i<=capacitate;++i)
            elements[i] = lista.elements[i],
                    urm[i] = lista.elements[i];
        primLiber = lista.primLiber;
        primOcupat = lista.primOcupat;
        ultimOcupat = lista.ultimOcupat;
        return *this;
    }

	// returnare dimensiune
	int dim() const{
        /* de adaugat */
        return dimensiune;
    }

	// verifica daca lista e vida
	bool vida() const{
        /* de adaugat */
        return primOcupat == 0;
    }

	// prima pozitie din lista
	IteratorLP<T> prim() const{
        /* de adaugat */
        return IteratorLP<T>(*this);
    }

	// returnare element de pe pozitia curenta
	//arunca exceptie daca poz nu e valid
	T element(IteratorLP<T> poz) const{
        /* de adaugat */
        return poz.element();
    }

	// modifica element de pe pozitia poz si returneaza vechea valoare
	//arunca exceptie daca poz nu e valid
	T modifica(IteratorLP<T> poz, T e){
        /* de adaugat */
        if (!poz.valid())
            throw std::exception();
        T elem = elements[poz.indice];
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
	void adaugaInceput(T e){
        /* de adaugat */
        int nouIndice = creeazaNod(e);
        urm[nouIndice] = primOcupat;
        primOcupat = nouIndice;
        if (ultimOcupat == 0) ultimOcupat = nouIndice;//cand avem un elem prima pozitie coresp cu ultima
        dimensiune++;
    }

	// adaugare element la sfarsit
	void adaugaSfarsit(T e){
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
	void adauga(IteratorLP<T>& poz, T e){
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
	T sterge(IteratorLP<T>& poz){
        /* de adaugat */
        if (!poz.valid())
            throw std::exception();
        T elem = elements[poz.indice];
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

	// cauta element si returneaza prima pozitie pe care apare (sau iterator invalid)
	IteratorLP<T> cauta(T e) const{
        /* de adaugat */
        IteratorLP<T> it(*this);
        it.prim();
        while (it.valid() && it.element() != e)
            it.urmator();
        return it;
    }

	//destructor

	~Lista(){
        /* de adaugat */
        delete[] urm;
        delete[] elements;
    }
};

template<typename T>
class IteratorLP {
    friend class Lista<T>;
    friend class ListaOrdonata<T>;
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
    T element() const{
        /* de adaugat */
        if (!valid()) throw std::exception();
        return lista.elements[indice];
    }

};

template<typename T>
class ListaOrdonata {
    friend class IteratorLP<T>;
    friend class Lista<T>;
    typedef IteratorLP<T> iterator;
private:
    Lista<T> lista;
    bool(*rel)(T, T);

public:
    ListaOrdonata(bool(*relatie)(T, T)){
        rel = relatie;
        lista = Lista<T>();
    }
    ListaOrdonata(const ListaOrdonata& lista)=default;

    int dim() const {return lista.dim();}
    bool vida() const{ return lista.vida(); }
    iterator prim() const {return lista.prim();}
    T element(iterator it) const {return lista.element(it); }
    T modifica(iterator it, T elem) { return lista.modifica(it, elem); }
    void adauga(T elem)
    {
        iterator it(lista);
        while(it.valid() && rel(it.element(), elem))
            it.urmator();
        if(!it.valid()) lista.adaugaSfarsit(elem);
        else {
            lista.adauga(it, elem);
        }
    }

    // sterge element de pe o pozitie poz si returneaza elementul sters
    //dupa stergere poz este pozitionat pe elementul de dupa cel sters
    //arunca exceptia daca poz nu e valid
    void sterge(T elem)
    {
        iterator it = lista.cauta(elem);
        lista.sterge(it);
    }
    iterator cauta(T elem){ // cauta element si returneaza prima pozitie pe care apare (sau iterator invalid)
//        iterator it(lista);
//        int st = 1;
//        int dr = lista.dim();
//        while (st < dr){
//            int mij=(st+dr)/2;
//            // urm[mij] ?!?!?!? - poate fi invalid!
//            if(lista.elements[mij] == elem){
//                it.indice = mij;
//                return it;
//            }
//            if(rel(lista.elements[st], lista.elements[dr])) dr = mij-1;
//            else st = mij+1;
//        }
        return lista.cauta(elem);
    }
};