//
// Created by Daniel on 07.06.2022.
//

#ifndef MODEL2_REPOSITORY_H
#define MODEL2_REPOSITORY_H

#include "domain.h"
#include "exceptions.h"

#include "vector"
#include "map"
#include "fstream"
#include "sstream"

using std::vector;
using std::map;

class Repository{
private:
    string file;
    vector<Melodie> melodii;
    map<int, int> nrRank;

    void load(); // incarc continutul fisierului
    void save(); // salvez datele in fisier
public:
    Repository(const string &file); // constructor care are numele fisierului ca param
    /**
     * modifica prima melodie in cea de-a doua
     * @param m1 Melodie
     * @param m2 Melodie
     * @throw RepoException, daca prima melodie nu este gasita
     */
    void modify(const Melodie& m1, const Melodie& m2);
    /**
     * sterge melodia din repo, salvand in fisier
     * @param m Melodie
     */
    void sterge(const Melodie& m);
    /**
     * @param rank int, rankul unei melodii
     * @return nr de melodii cu rank ul respectiv
     */
    int nrMeldii(int rank);
    const vector<Melodie>& getAll(); // returneaza toate elementele
};

#endif //MODEL2_REPOSITORY_H
