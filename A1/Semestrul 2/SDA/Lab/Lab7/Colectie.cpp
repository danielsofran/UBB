#include "Colectie.h"
#include "IteratorColectie.h"
#include <iostream>
#include "cassert"

using namespace std;

bool rel(TElem e1, TElem e2) {
    return e1 <= e2;
}

Colectie::Colectie() {
	capacity = 10;
    size = 0;
    primLiber = 0;
    rad = NIL;
    noduri = new Nod[capacity];
}

void Colectie::actPrimLiber(){
    if(!noduri[primLiber].isNULL())
        primLiber++;
}

void Colectie::resize() {
    Nod* newnoduri = new Nod[capacity*2];
    memcpy(newnoduri, noduri, sizeof(Nod)*capacity);
    actPrimLiber();
    capacity *=2;
    delete[] noduri;
    noduri = newnoduri;
}

int Colectie::minim(int nod){
    int curent = nod;
    while(noduri[curent].st != NIL)
        curent = noduri[curent].st;
    return curent;
}

int Colectie::adaugaRec(int nod, TElem e) {
    if(nod == NIL)
    {
        noduri[primLiber] = {e, 1, NIL, NIL};
        int old = primLiber;
        actPrimLiber();
        return old;
    }
    else if(noduri[nod].val == e)
    {
        noduri[nod].frecv++;
        return nod;
    }
    else if(!rel(noduri[nod].val, e)){
        noduri[nod].st = adaugaRec(noduri[nod].st, e);
        return nod;
    }
    else{
        noduri[nod].dr = adaugaRec(noduri[nod].dr, e);
        return nod;
    }
}

void Colectie::adauga(TElem e) {
    if(vida()){
        rad = 0;
        noduri[rad] = {e, 1, NIL, NIL};
        primLiber = 1;
        size++;
        return;
    }
    if(size == capacity - 1)
        resize();
    adaugaRec(rad, e);
    size++;
}

int Colectie::stergeRec(int nod, TElem e, bool &gasit) {
    if(nod == NIL) return NIL;
    if(noduri[nod].val == e){
        gasit = true;
        if(noduri[nod].frecv > 1){
            noduri[nod].frecv--;
            return nod;
        }
        if(noduri[nod].st == NIL && noduri[nod].dr == NIL)
            return NIL;
        if(noduri[nod].st == NIL)
            return noduri[nod].dr;
        if(noduri[nod].dr == NIL)
            return noduri[nod].st;
        int min = minim(noduri[nod].dr);
        noduri[nod].val = noduri[min].val;
        noduri[nod].dr = stergeRec(noduri[nod].dr, noduri[min].val, gasit);
        return nod;
    }
    else if(!rel(noduri[nod].val, e)){
        noduri[nod].st = stergeRec(noduri[nod].st, e, gasit);
        return nod;
    }
    else{
        noduri[nod].dr = stergeRec(noduri[nod].dr, e, gasit);
        return  nod;
    }
}

int Colectie::stergeRecNod(int nod, TElem e, bool &gasit) {
    if(nod == NIL) return NIL;
    if(noduri[nod].val == e){
        gasit = true;
        if(noduri[nod].st == NIL && noduri[nod].dr == NIL)
            return NIL;
        if(noduri[nod].st == NIL)
            return noduri[nod].dr;
        if(noduri[nod].dr == NIL)
            return noduri[nod].st;
        int min = minim(noduri[nod].dr);
        noduri[nod].val = noduri[min].val;
        noduri[nod].dr = stergeRec(noduri[nod].dr, noduri[min].val, gasit);
        return nod;
    }
    else if(!rel(noduri[nod].val, e)){
        noduri[nod].st = stergeRec(noduri[nod].st, e, gasit);
        return nod;
    }
    else{
        noduri[nod].dr = stergeRec(noduri[nod].dr, e, gasit);
        return  nod;
    }
}

bool Colectie::sterge(TElem e) {
    bool gasit = false;
    rad = stergeRec(rad, e, gasit);
    if(!gasit) return false;
    size--;
    return true;
}

bool Colectie::cauta(TElem elem) const {
	return nrAparitii(elem) > 0;
}


int Colectie::nrAparitii(TElem elem) const {
	int nod = rad;
    while (nod != NIL){
        if(noduri[nod].val == elem)
            return noduri[nod].frecv;
        if(!rel(noduri[nod].val, elem))
            nod = noduri[nod].st;
        else nod = noduri[nod].dr;
    }
    return 0;
}



int Colectie::dim() const {
	return size;
}


bool Colectie::vida() const {
	return size==0;
}


IteratorColectie Colectie::iterator() const {
	return  IteratorColectie(*this);
}

Colectie::~Colectie() {
	delete[] noduri;
}

int Colectie::stergeToateElementeleRepetitive() {
    stack<int> s;
    int nod = rad;
    while (nod != NIL){
        s.push(nod);
        nod = noduri[nod].st;
    }
    bool gasit;
    int elims=0;
    while(!s.empty())
    {
        nod = s.top(); s.pop();
        if(noduri[nod].frecv > 1) {
            elims+=noduri[nod].frecv;
            rad = stergeRecNod(rad, noduri[nod].val, gasit);
            assert(gasit==true);
        }
        if(noduri[nod].dr != NIL) {
            nod = noduri[nod].dr;
            while (nod != NIL) {
                s.push(nod);
                nod = noduri[nod].st;
            }
        }
    }
    size-=elims;
    return elims;
}
