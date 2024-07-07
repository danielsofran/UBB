//
// Created by Daniel on 19.05.2022.
//

#ifndef TRAINTEST2131V1_SERVICE_H
#define TRAINTEST2131V1_SERVICE_H

#include "repo.h"

class Service{
private:
    Repository& r;
public:
    Service(Repository& repo) : r(repo) {} // constructor
    // sterge un element
    // arunca logic_error altfel
    void sterge(const Apartament& ap);
    // filtreaza elementele dupa anumite conditii, returnand vectorul rezultat
    vector<Apartament> filterSupr(int val1, int val2); // returneaza elementele cu suprafete intre val1 si val2
    vector<Apartament> filterPret(int val1, int val2); // returneaza elementele cu preturi intre val1 si val2
    const vector<Apartament>& getAll() const; // returnez toate elementele
};

#endif //TRAINTEST2131V1_SERVICE_H
