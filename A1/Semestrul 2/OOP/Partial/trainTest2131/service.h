//
// Created by Daniel on 18.05.2022.
//

#ifndef TRAINTEST2131_SERVICE_H
#define TRAINTEST2131_SERVICE_H

#include "repository.h"

class Service{
private:
    FileRepository<Procesor>& rCPU;
    FileRepository<PlacaDeBaza>& rPlB;

public:
    Service(FileRepository<Procesor>& r1, FileRepository<PlacaDeBaza>& r2) : rCPU(r1), rPlB(r2){}

    void addPlaca(string nume, string soclu, int pret);

    const Procesor& findProcesor(string numenr);
    const PlacaDeBaza& findPlaca(string nume);

    vector<PlacaDeBaza> filterSoclu(string soclu);

    const vector<Procesor>& getProcesoare() const;
    const vector<PlacaDeBaza>& getPlaci() const;
};

#endif //TRAINTEST2131_SERVICE_H
