//
// Created by Daniel on 19.05.2022.
//

#ifndef TRAINTEST2172_REPOSITORY_H
#define TRAINTEST2172_REPOSITORY_H

#include "domain.h"
#include "fstream"
#include "vector"

using std::ifstream;
using std::ofstream;
using std::vector;

class Repository{
private:
    string path;
    vector<Device> v;

    void load();
public:
    Repository(string path);
    const vector<Device>& getAll();
};

#endif //TRAINTEST2172_REPOSITORY_H
