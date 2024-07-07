
#include <exception>

#include "IteratorLP.h"
#include "Lista.h"

#include <iostream>

Lista::Lista() {
	/* de adaugat */
    first = NULL;
    last = NULL;
}

int Lista::dim() const {
	/* de adaugat */
    if(first == NULL) return 0;
    int rez = 0;
    Nod prim = Nod{first};
    while(prim != NULL)
        prim = prim->next,
        ++rez;
	return rez;
}


bool Lista::vida() const {
	/* de adaugat */
	return first==NULL;
}

IteratorLP Lista::prim() const {
	/* de adaugat */
    return IteratorLP(*this);
}

TElem Lista::element(IteratorLP poz) const {
	/* de adaugat */
    if(poz.curent == NULL) throw std::exception();
	return poz.element();
}

TElem Lista::sterge(IteratorLP& poz) {
	/* de adaugat */
    if(!poz.valid()) throw std::exception();
    Nod nod = poz.curent;
    TElem val = nod->value;
    Nod nod1 = nod->prev, nod2 = nod->next;
    if(nod1 != nullptr) nod->prev->next = nod2;
    else first=nod->next;
    if(nod2!= nullptr) nod->next->prev = nod1;
    else last=nod->prev;
    delete nod;
	return val;
}

IteratorLP Lista::cauta(TElem e) const{
	/* de adaugat */
    IteratorLP it(*this);
    while (it.valid() && it.element() != e)
        it.urmator();
	return it;
}

TElem Lista::modifica(IteratorLP poz, TElem e) {
	/* de adaugat */
    if(!poz.valid()) throw std::exception();
    TElem val = poz.element();
    poz.curent->value = e;
	return val;
}

void Lista::adauga(IteratorLP& poz, TElem e) {
	/* de adaugat */
    if(!poz.valid()) throw std::exception();
    Nod nod1 = poz.curent;
    //poz.urmator();
    Nod nod2 = poz.curent->next;
    Nod nodnou = new Nod_{e, NULL, NULL};
    nodnou->next = nod2;
    nod1->next = nodnou;
    nodnou->prev = nod1;
    nod2->prev = nodnou;
    poz.urmator();
}

void Lista::adaugaInceput(TElem e) {
	/* de adaugat */
    Nod nod = new Nod_{e, nullptr, nullptr};
    if(first== NULL) first = nod;
    if(last == NULL) last = nod;
}

void Lista::adaugaSfarsit(TElem e) {
	/* de adaugat */
    Nod nod = new Nod_{e, NULL, NULL};
    if(last==NULL) last  = nod, first = nod;
    else if(first == last)
    {
        first->next = nod;
        nod->prev = first;
        last = nod;
    }
    else {
        last->next = nod;
        nod->prev = last;
        last = nod;
    }
}

void Lista::filtreaza(Conditie cond) {
    IteratorLP it(*this);
    while(it.valid())
    {
        if(!cond(it.element()))
        {
            IteratorLP anterior = it;
            it.urmator();
            sterge(anterior);
        }
        else it.urmator();
    }
}

Lista::~Lista() {
	/* de adaugat */
    Nod nod = first;
    while(first!=NULL)
    {
        nod = first->next;
        delete first;
        first = nod;
    }
}
