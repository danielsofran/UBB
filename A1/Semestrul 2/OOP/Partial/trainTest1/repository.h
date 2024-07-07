//
// Created by Daniel on 14.05.2022.
//

#ifndef UNTITLED_REPOSITORY_H
#define UNTITLED_REPOSITORY_H

#include <utility>

#include "domain.h"
#include "exceptions.h"

#include "vector"
#include "algorithm"
#include "fstream"

using std::vector;

class Repository{
protected:
    vector<Masina> v;
public:
    Repository() = default;
    virtual void add(const Masina& m) {
        if(std::find(v.begin(), v.end(), m) == v.end()) v.push_back(m);
        else throw RepoException("Element duplicat!");
    }
    virtual void remove(const Masina& m)
    {
        auto it = std::find(v.begin(), v.end(), m);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        v.erase(it);
    }
    virtual void modify(const Masina& m1, const Masina& m2)
    {
        auto it = std::find(v.begin(), v.end(), m1);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        *it = m2;
    }
    virtual bool find(const Masina& m){
        auto it = std::find(v.begin(), v.end(), m);
        return it != v.end();
    }

    template<class Fct>
    const Masina& find(Fct fct){
        auto it = std::find_if(v.begin(), v.end(), fct);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        return *it;
    }
    virtual vector<Masina>::iterator begin() {return v.begin();}
    virtual vector<Masina>::iterator end() {return v.end();}
    size_t size() const {return v.size();}
};

class FileRepository{
private:
    string path;
    vector<Masina> v;

    void load(){
        std::ifstream fin(path);
        v.clear();
        Masina m;
        while(fin>>m)
        {
            v.push_back(m);
        }
    }
    void save(){
        std::ofstream fout(path);
        for(const auto& elem : v)
            fout<<elem<<'\n';
    }
public:
    explicit FileRepository(string path) : path(std::move(path)) {
        load();
    }
    void add(const Masina& m) { 
        load();
        if(std::find(v.begin(), v.end(), m) == v.end()) v.push_back(m);
        else throw RepoException("Element duplicat!");
        save(); 
    }
    void remove(const Masina& m) { 
        load();
        auto it = std::find(v.begin(), v.end(), m);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        v.erase(it);
        save(); 
    }
    void modify(const Masina& m1, const Masina& m2) { 
        load();
        auto it = std::find(v.begin(), v.end(), m1);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        *it = m2;
        save(); 
    }
    bool find(const Masina& m) { 
        load();
        auto it = std::find(v.begin(), v.end(), m);
        return it != v.end();
    }
    template<class Fct>
    const Masina& find(Fct fct){
        load();
        auto it = std::find_if(v.begin(), v.end(), fct);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        return *it;
    }
    vector<Masina>::iterator begin() {return v.begin();}
    vector<Masina>::iterator end() {return v.end();}
    size_t size() const {return v.size();}
};

#endif //UNTITLED_REPOSITORY_H
