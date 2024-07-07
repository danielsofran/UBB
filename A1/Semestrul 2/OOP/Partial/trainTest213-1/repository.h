//
// Created by Daniel on 18.05.2022.
//

#ifndef TRAINTEST213_1_REPOSITORY_H
#define TRAINTEST213_1_REPOSITORY_H

#include "domain.h"
#include "exceptions.h"

#include "vector"
#include "algorithm"
#include "fstream"
#include "sstream"
#include "string"

using std::vector;

class FileRepository{
private:
    string path;
    vector<Apartament> v;

    void load();
    void save();
public:
    FileRepository(string path) : path(path){
        load();
    }
    void remove(const Apartament& ap);
    const vector<Apartament>& getAll() const;
    template<class T>
    vector<Apartament> filter(T fct) const
    {
        vector<Apartament>* rez = new vector<Apartament>;
        for(const auto& elem : v)
            if(fct(elem))
                rez->push_back(elem);
        return *rez;
    }
};

#endif //TRAINTEST213_1_REPOSITORY_H
