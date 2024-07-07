//
// Created by Daniel on 19.05.2022.
//

#ifndef TRAINTEST2131V1_REPO_H
#define TRAINTEST2131V1_REPO_H

#include "domain.h"
#include "vector"
#include "fstream"
#include "sstream"
#include "algorithm"

using std::vector;

class Repository{
private:
    string path;
    vector<Apartament> v;

    void load();
    void save();
public:
    explicit Repository(string path);
    void sterge(const Apartament&);
    const vector<Apartament>& getAll() const;
};

#endif //TRAINTEST2131V1_REPO_H
