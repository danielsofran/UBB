//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL3_REPOSITORY_H
#define MODEL3_REPOSITORY_H

#include "domain.h"
#include "exceptions.h"

#include "vector"
#include "fstream"
#include "sstream"

using std::vector;
using std::ifstream;
using std::ofstream;

class Repository{
private:
    string file;
    vector<Produs> v;

    void load();
    void save();

public:
    Repository(string file) {
        this->file = file;
        load();
    }
    Repository(const Repository&) = delete;
    void add(const Produs&);
    const vector<Produs>& getAll() const;
};

#endif //MODEL3_REPOSITORY_H
