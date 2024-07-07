//
// Created by Daniel on 18.05.2022.
//

#include "service.h"

void Service::addPlaca(string nume, string soclu, int pret) {
    if(nume.empty() || soclu.empty()) throw std::logic_error("Placa invalida!");
    PlacaDeBaza p{nume, soclu, pret};
    rPlB.add(p);
}

vector<PlacaDeBaza> Service::filterSoclu(string soclu) {
    vector<PlacaDeBaza>* rez = new vector<PlacaDeBaza>;
    for(const PlacaDeBaza& p : rPlB.getAll())
        if(p.getSoclu() == soclu)
            rez->push_back(p);
    return *rez;
}

const vector<Procesor> &Service::getProcesoare() const {
    return rCPU.getAll();
}

const vector<PlacaDeBaza> &Service::getPlaci() const {
    return rPlB.getAll();
}

const Procesor &Service::findProcesor(string numenr) {
    for(const Procesor& p : rCPU.getAll())
        if(p.getNume()+"-"+ std::to_string(p.getNrThreaduri()) == numenr)
            return p;
    throw std::logic_error("no item found");
}

const PlacaDeBaza &Service::findPlaca(string nume) {
    for(const PlacaDeBaza& p : rPlB.getAll())
        if(p.getNume() == nume)
            return p;
    throw std::logic_error("no item found");
}
