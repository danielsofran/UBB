//
// Created by Daniel on 06.06.2022.
//

#include "service.h"

Service::Service(Repository &r) : r(r) {}

void Service::add(int id, const string &descriere, const vector<string> &programatori, const string &stare) {
    Task t(id, descriere, programatori, stare);
    valid(t);
    r.add(t);
    notify();
}

void Service::valid(const Task & t) {
    auto stari = vector<string>{"open", "inprogress", "closed"};
    auto stare = t.getStare();
    int nrp = t.getProgramatori().size();
    if(stare != stari[0] && stare != stari[1] &&stare != stari[2])
        throw ServiceException("Stare invalida");
    if(nrp <1 || nrp > 4)
        throw ServiceException("Numar invalid de programatori");
    if(t.getDescriere().empty())
        throw ServiceException("Descriere vida!");
}

const vector<Task> &Service::getAll() const {
    return r.getAll();
}

vector<Task> Service::filterNumePr(string numepr) const {
    vector<Task> rez;
    for(const auto& elem : r.getAll())
    {
        for(const auto& npr : elem.getProgramatori())
            if(npr == numepr || std::includes(npr.begin(), npr.end(), numepr.begin(), numepr.end()))
            {
                rez.push_back(elem);
                continue;
            }
    }
    return rez;
}

vector<Task> Service::filterStare(string stare) const {
    vector<Task> rez;
    for(const auto& elem : r.getAll())
        if(elem.getStare() == stare)
            rez.push_back(elem);
    return rez;
}

void Service::changeState(int id, string state) {
    r.changeState(id, state);
    notify();
}
