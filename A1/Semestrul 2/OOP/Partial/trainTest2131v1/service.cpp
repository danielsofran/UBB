//
// Created by Daniel on 19.05.2022.
//

#include "service.h"

void Service::sterge(const Apartament &ap) {
    r.sterge(ap);
}

vector<Apartament> Service::filterSupr(int val1, int val2) {
    vector<Apartament>* rez = new vector<Apartament>;
    if(val1>val2) std::swap(val1, val2);
    for(const auto& elem : r.getAll())
        if(elem.getSupr() >= val1 && elem.getSupr() <= val2)
            rez->push_back(elem);
    return *rez;
}

vector<Apartament> Service::filterPret(int val1, int val2) {
    vector<Apartament>* rez= new vector<Apartament>;
    if(val1>val2) std::swap(val1, val2);
    for(const auto& elem : r.getAll())
        if(elem.getPret() >= val1 && elem.getPret() <= val2)
            rez->push_back(elem);
    return *rez;
}

const vector<Apartament> &Service::getAll() const {
    return r.getAll();
}
