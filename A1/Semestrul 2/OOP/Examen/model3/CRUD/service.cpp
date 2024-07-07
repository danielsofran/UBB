//
// Created by Daniel on 06.06.2022.
//

#include "service.h"
#include "cctype"

void Service::add(string id, string nume, string tip, string pret) {
    valid(id, nume, tip, pret);
    Produs p(std::stoi(id), nume, tip, std::stod(pret));
    r.add(p);
    notify();
}

vector<Produs> Service::filterPret(double pret) const {
    vector<Produs> rez;
    for(const auto& elem : r.getAll())
        if(elem.getPret() <= pret)
            rez.push_back(elem);
    return rez;
}

const vector<Produs> &Service::getAll() const {
    return r.getAll();
}

map<string, int> Service::tipuri() const {
    map<string, int> rez;
    for(const auto& elem : r.getAll())
        rez[elem.getTip()]++;
    return rez;
}

void Service::valid(string id, string nume, string tip, string pret) {
    string ex;
    if(id.empty()) ex+="Id invalid\n";
    for(char ch : id)
        if(!std::isdigit(ch))
        {
            ex += "Id invalid\n";
            break;
        }
    for(char ch : pret)
        if(!std::isdigit(ch) && ch!='.')
        {
            ex += "Pret invalid\n";
            break;
        }
    if(!ex.empty()) throw ServiceException(ex);
    if(nume.empty()) ex+="Nume invalid\n";
    double dpret = std::stod(pret);
    if(dpret<1 || dpret > 100)
        ex += "Pret invalid\n";
    if(!ex.empty()) throw ServiceException(ex);
}




