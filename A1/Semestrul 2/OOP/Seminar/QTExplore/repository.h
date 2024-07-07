#pragma once
#include "domain.h"
#include "exceptions.h"

#include <vector>
#include <algorithm>
#include <fstream>

using std::vector;
using std::ifstream;
using std::ofstream;

class FileRepository{
private:
    string path;
    vector<Produs> v;

    void load(){ // incarcam continutul din fisier
        ifstream fin(path);
        v.clear();
        Produs m;
        while(fin>>m)
        {
            v.push_back(m);
        }
    }
    void save(){ // salvam continutul in fisier
        ofstream fout(path);
        for(const auto& elem : v)
            fout<<elem<<'\n';
    }
public:
    explicit FileRepository(string path) : path(std::move(path)) { // constructor
        load();
    }
    /** adaug elementul m la repo
     * arunc RepoException daca exista deja
     */
    void add(const Produs& m) {
        load();
        if(std::find(v.begin(), v.end(), m) == v.end()) v.push_back(m);
        else throw RepoException("Element duplicat!\n");
        save();
    }
    /** sterg elementul m din repo
     * arunc RepoException daca nu exista
     */
    void remove(const Produs& m) {
        load();
        auto it = std::find(v.begin(), v.end(), m);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        v.erase(it);
        save();
    }
    /** datele elementului m1 devin datele elementului
     * arunc RepoException daca nu gasesc m1
     */
    void modify(const Produs& m1, const Produs& m2) {
        load();
        auto it = std::find(v.begin(), v.end(), m1);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        *it = m2;
        save();
    }
    /** caut elementul m in repo
     * returnez adevarat daca il gasesc sau fals altfel
     */
    bool find(const Produs& m) {
        load();
        auto it = std::find(v.begin(), v.end(), m);
        return it != v.end();
    }
    /** caut elementul care respecta predicatul fct in repo
     * arunc RepoException daca nu exista
     */
    template<class Fct>
    const Produs& find(Fct fct){
        load();
        auto it = std::find_if(v.begin(), v.end(), fct);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        return *it;
    }
    vector<Produs>::iterator begin() {return v.begin();} // iterator catre inceputul listei de elemente
    vector<Produs>::iterator end() {return v.end();} // iterator catre sfarsitul listei de elemente
    size_t size() const {return v.size();} // numarul de elemente
};
