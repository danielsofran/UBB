//
// Created by Daniel on 17.05.2022.
//

#include "service.h"

const Doctor &Service::find(string nume, string prenume, string sectie) const {
    Doctor ds{"", nume, prenume, sectie, 0};
    auto it = std::find(r.getAll().begin(), r.getAll().end(), ds);
    if(it == r.getAll().end()) throw ServiceException("Element negasit");
    return *it;
}

vector<Doctor> &Service::filterNume(string nume) const {
    vector<Doctor>* rez = new vector<Doctor>;
    for(const auto& d : r.getAll())
        if(d.getNume() == nume)
            rez->push_back(d);
    return *rez;
}

vector<Doctor> &Service::filterSectie(string sectie) const {
    vector<Doctor>* rez = new vector<Doctor>;
    for(const auto& d : r.getAll())
        if(d.getSectie() == sectie)
            rez->push_back(d);
    return *rez;
}

const vector<Doctor> &Service::getAll() {
    return r.getAll();
}

const Doctor &Service::find(string text) const {
    std::istringstream sin(text);
    string t1, t2, t3;
    sin>>t1>>t2>>t3;
    return find(t1, t2, t3);
}
