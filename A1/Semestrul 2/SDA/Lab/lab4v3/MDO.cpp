#include "IteratorMDO.h"
#include "MDO.h"
#include <iostream>
#include <vector>

#include <exception>
using namespace std;

void MDO::aloca(int& i) {
    i = primLiber;
    primLiber = urm[primLiber];
}

void MDO::dealoca(int i) {
    urm[i] = primLiber;
    primLiber = i;
}

int MDO::creeazaCheie(TCheie vCheie){
    if(primLiber == 0)
        throw std::runtime_error("spatiu insuficient");
    int i;
    aloca(i);
    chei[i] = vCheie;
    urm[i] = 0;
    return i;
}

MDO::MDO(Relatie r) {
    capacity = 100;
    dimensiune = 0;
    valori = vector<Lista<TValoare>>(capacity+1);
    chei = vector<TCheie>(capacity+1);
    urm = vector<int>(capacity+1);
    rel = r;
    for(int i=0;i<capacity;++i)
        urm[i] = i+1,
        valori[i] = Lista<TValoare>();
    urm[capacity] = 0;
    valori[capacity] = Lista<TValoare>();
    primLiber = 1;
    primOcupat = 0;
}


void MDO::adauga(TCheie c, TValoare v) {
    if(vid())
    {
        int pozCheie = creeazaCheie(c);
        urm[pozCheie] = primOcupat;
        primOcupat = pozCheie;
        valori[pozCheie].clear();
        valori[pozCheie].adaugaInceput(v);
        dimensiune++;
    } else if (rel(c, chei[primOcupat]))
    {
        if(c==chei[primOcupat])
        {
            valori[primOcupat].adaugaSfarsit(v);
            dimensiune++;
        }
        else{ // adaug la inceput
            int pozCheie = creeazaCheie(c);
            urm[pozCheie] = primOcupat;
            primOcupat = pozCheie;
            valori[pozCheie].adaugaSfarsit(v);
            dimensiune++;
        }
    }
    else {
        int index, oldindex = 0;
        for (index = primOcupat; index > 0 && rel(chei[index], c); oldindex = index, index = urm[index])
            if(chei[index] == c)
            {
                valori[index].adaugaSfarsit(v);
                dimensiune++;
                return;
            }
        if (index == 0) {
            //if (dimensiune == 1)
            { // agaug la final
                int pozCheie = creeazaCheie(c);
                urm[oldindex] = pozCheie;
                valori[pozCheie].clear();
                valori[pozCheie].adaugaSfarsit(v);
                dimensiune++;
            }
        } else { // index = pozitia de la care rel nu mai e valida
            valori[oldindex].adaugaSfarsit(v);
            dimensiune++;
        }
    }
}

vector<TValoare> MDO::cauta(TCheie c) const {
	/* de adaugat */
    vector<TValoare> rez;
    if(primOcupat<0) return rez;
    for(int index=primOcupat; index>0 && rel(chei[index], c);index=urm[index])
        if(chei[index] == c)
        {
            for(auto it=valori[index].prim();it.valid();it.urmator())
                rez.push_back(it.element());
            return rez;
        }
    return rez;
}

bool MDO::sterge(TCheie c, TValoare v) {
    for(int index=primOcupat; index>0 && rel(chei[index], c);index=urm[index])
        if(chei[index] == c) // am gasit cheia
        {
            auto it = valori[index].cauta(v);
            if(!it.valid()) return false;
            valori[index].sterge(it);
            dimensiune--;
            return true;
        }
	return false;
}

int MDO::dim() const {
	return dimensiune;
}

bool MDO::vid() const {
	return dimensiune == 0;
}

IteratorMDO MDO::iterator() const {
	return IteratorMDO(*this);
}

void MDO::filtreaza(Conditie cond) {
    IteratorMDO it = iterator();
    it.prim();
    if(!it.valid()) return;
    Lista<TElem> to_delete;
    for( ; it.valid(); )
    {
        if(cond(it.element()))
            to_delete.adaugaInceput(it.element());
        it.urmator();
    }
    for(auto it=to_delete.prim(); it.valid(); it.urmator())
        sterge(it.element().first, it.element().second);
}
