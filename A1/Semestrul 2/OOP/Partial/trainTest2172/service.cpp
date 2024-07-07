//
// Created by Daniel on 19.05.2022.
//

#include "service.h"

vector<Device> Service::sortModel() {
    vector<Device> rez = r.getAll();
    std::sort(rez.begin(), rez.end(), [](const Device& d1, const Device& d2){
        return d1.getModel() < d2.getModel();
    });
    return rez;
}

vector<Device> Service::sortPret() {
    vector<Device> rez = r.getAll();
    std::sort(rez.begin(), rez.end(), [](const Device& d1, const Device& d2){
        return d1.getPret() < d2.getPret();
    });
    return rez;
}

const vector<Device> &Service::getAll() {
    return r.getAll();
}
