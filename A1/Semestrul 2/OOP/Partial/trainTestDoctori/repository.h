//
// Created by Daniel on 17.05.2022.
//

#ifndef TRAINTESTDOCTORI_REPOSITORY_H
#define TRAINTESTDOCTORI_REPOSITORY_H

#include "domain.h"
#include "fstream"
#include "vector"
#include <sstream>

using std::ifstream;
using std::ofstream;
using std::vector;

class FileRepository{
private:
    string path;
    vector<Doctor> v;

    void load(); // incarcam date
public:
    explicit FileRepository(string path); // constructor cu path ca param
    int size() const; // returnez dim
    const vector<Doctor>& getAll(); // retuznez un vector cu toate elem
};

#endif //TRAINTESTDOCTORI_REPOSITORY_H
