//
// Created by Daniel on 18.05.2022.
//

#ifndef TRAINTEST213_1_SERVICE_H
#define TRAINTEST213_1_SERVICE_H

#include "repository.h"

class Service{
private:
    FileRepository& r;
public:
    Service(FileRepository& r) : r(r) {}
    Apartament find(string data) const;
    void remove(string data);
    vector<Apartament> filterSup(int, int ) const;
    vector<Apartament> filterPret(int , int ) const;
    const vector<Apartament>& getAll() const;
};

#endif //TRAINTEST213_1_SERVICE_H
