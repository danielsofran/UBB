//
// Created by Daniel on 17.05.2022.
//

#ifndef TRAINTEST2131_REPOSITORY_H
#define TRAINTEST2131_REPOSITORY_H

#include "domain.h"
#include "vector"
#include "algorithm"
#include "fstream"

using std::vector;

template<class T>
class FileRepository{
private:
    string path;
    vector<T> v;

    void load(){
        std::ifstream fin(path);
        v.clear();
        T elem;
        while (fin>>elem)
            v.push_back(elem);
        fin.close();
    }

    void save(){
        std::ofstream fout(path);
        for(const auto& elem : v)
            fout<<elem<<'\n';
        fout.close();
    }
public:
    FileRepository(string path) : path(path) {
        load();
    }
    void add(T elem){
        load();
        v.push_back(elem);
        save();
    }
    const vector<T>& getAll() const {
        return v;
    }
};

#endif //TRAINTEST2131_REPOSITORY_H
