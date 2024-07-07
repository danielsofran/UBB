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
    vector<Carte> v;

    void load(){ // incarcam continutul din fisier
        ifstream fin(path);
        v.clear();
        Carte m;
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
    void add(const Carte& m) {
        load();
        if(std::find(v.begin(), v.end(), m) == v.end()) v.push_back(m);
        else throw RepoException("Element duplicat!\n");
        save();
    }
    /** sterg elementul m din repo
     * arunc RepoException daca nu exista
     */
    void remove(const Carte& m) {
        load();
        auto it = std::find(v.begin(), v.end(), m);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        v.erase(it);
        save();
    }
    /** datele elementului m1 devin datele elementului
     * arunc RepoException daca nu gasesc m1
     */
    void modify(const Carte& m1, const Carte& m2) {
        load();
        auto it = std::find(v.begin(), v.end(), m1);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        *it = m2;
        save();
    }
    /** caut elementul m in repo
     * returnez adevarat daca il gasesc sau fals altfel
     */
    bool find(const Carte& m) {
        load();
        auto it = std::find(v.begin(), v.end(), m);
        return it != v.end();
    }
    /** caut elementul care respecta predicatul fct in repo
     * arunc RepoException daca nu exista
     */
    template<class Fct>
    const Carte& find(Fct fct){
        load();
        auto it = std::find_if(v.begin(), v.end(), fct);
        if(it == v.end()) throw RepoException("Element negasit!\n");
        return *it;
    }
    template<class Fct>
    void sort(Fct fct){
        load();
        std::sort(v.begin(), v.end(), fct);
        save();
    }
    vector<Carte>::iterator begin() {return v.begin();} // iterator catre inceputul listei de elemente
    vector<Carte>::iterator end() {return v.end();} // iterator catre sfarsitul listei de elemente
    size_t size() const {return v.size();} // numarul de elemente
};
