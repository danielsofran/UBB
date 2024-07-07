//
// Created by Daniel on 17.05.2022.
//

#ifndef TRAINTESTDOCTORI_SERVICE_H
#define TRAINTESTDOCTORI_SERVICE_H

#include "repository.h"
#include "algorithm"

using std::runtime_error;

class ServiceException{
    string msg;
public:
    ServiceException(string m) : msg(m){}
    const char* getMessage() const {return msg.c_str(); }
};

class Service{
private:
    FileRepository& r;
public:
    Service(FileRepository& fr) : r(fr){} // constructor
    const Doctor& find(string nume, string prenume, string sectie) const;
    const Doctor& find(string text) const;
    vector<Doctor>& filterNume(string nume) const;
    vector<Doctor>& filterSectie(string sectie) const;
    const vector<Doctor>& getAll();
};

#endif //TRAINTESTDOCTORI_SERVICE_H
