//
// Created by Daniel on 18.05.2022.
//

#include "service.h"

#include <utility>

Apartament Service::find(string data) const {
    std::istringstream sin(data);
    string str, spr, ssup;
    sin>>str>>ssup>>spr;
    int pret, supr;
    try{
        pret = std::stoi(spr);
        supr = std::stoi(ssup);
    }
    catch (...){
        throw ServiceException("Date invalide");
    }
    Apartament ap(supr, str, pret);
    return ap;
}

void Service::remove(string data) {
    Apartament ap = find(data);
    r.remove(ap);
}

vector<Apartament> Service::filterSup(int s1, int s2) const {
    if(s1>s2) std::swap(s1, s2);
    auto l = [&s1, &s2](const Apartament& ap){
        return ap.getSupr() >= s1 && ap.getSupr() <= s2;
    };
    return r.filter(l);
}

vector<Apartament> Service::filterPret(int p1, int p2) const {
    auto l = [&p1, &p2](const Apartament& ap){
        return ap.getPret() >= p1 && ap.getPret() <= p2;
    };
    return r.filter(l);
}

const vector<Apartament> &Service::getAll() const {
    return r.getAll();
}
