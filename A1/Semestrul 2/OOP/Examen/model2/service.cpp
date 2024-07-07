//
// Created by Daniel on 07.06.2022.
//

#include "service.h"

Service::Service(Repository &r) : r(r) {}

void Service::modify(int id, string titlu, int rank) {
    for(const auto& elem : r.getAll())
        if(elem.getId() == id)
        {
            Melodie elem2(elem);
            elem2.setTitlu(titlu);
            elem2.setRank(rank);
            r.modify(elem, elem2);
            notify();
            return;
        }
    throw ServiceException("Id invalid");
}

const vector<Melodie> &Service::getAll() {
    return r.getAll();
}

void Service::sterge(int id) {
    auto it = std::find_if(r.getAll().begin(), r.getAll().end(), [&](const Melodie& item){
        return item.getId() == id;
    });
    if(it == r.getAll().end())
        throw ServiceException("Id negasit");
    int nr = std::count_if(r.getAll().begin(), r.getAll().end(), [&](const Melodie& m){
        return m.getArtist() == it->getArtist();
    });
    for(const auto& elem : r.getAll())
        if(elem.getId() == id) {
            if(nr>1) {
                r.sterge(elem);
                notify();
                return;
            }
            else throw ServiceException("Aceasta e ultima melodie a atristului");
        }
}

int Service::nrMelodiiRank(int rank) const {
    return r.nrMeldii(rank);
}

vector<Melodie> Service::getAllSorted() const {
    auto v = r.getAll();
    std::sort(v.begin(), v.end());
    return v;
}
