//
// Created by Daniel on 19.05.2022.
//

#ifndef TRAINTEST2172_SERVICE_H
#define TRAINTEST2172_SERVICE_H

#include "repository.h"
#include "algorithm"

class Service{
private:
    Repository& r;
public:
    Service(Repository& r) : r(r) {}
    vector<Device> sortModel();
    vector<Device> sortPret();
    const vector<Device>& getAll();
};

#endif //TRAINTEST2172_SERVICE_H
