//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL3_SERVICE_H
#define MODEL3_SERVICE_H

#include "repository.h"
#include "observer.h"

#include "map"
#include "algorithm"

using std::map;

class Service : public Observable{
private:
    Repository& r;
    void valid(string id, string nume, string tip, string pret);
public:
    Service(Repository& r) : r(r) {}
    void add(string id, string nume, string tip, string pret);
    vector<Produs> filterPret( double pret) const;
    const vector<Produs>& getAll() const;
    map<string, int> tipuri() const;
};

#endif //MODEL3_SERVICE_H
