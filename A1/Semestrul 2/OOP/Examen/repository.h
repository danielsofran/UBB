//
// Created by Daniel on 08.06.2022.
//

#ifndef EXAMEN_REPOSITORY_H
#define EXAMEN_REPOSITORY_H

#include "domain.h"
#include "exceptions.h"
#include "vector"
#include "algorithm"
#include "fstream"

using std::vector;

class Repository{
private:
    string file;
    vector<Joc> jocuri;

    void load(); // incarca din fisier
    void save(); // salveaza in fisier
public:
    Repository(const string &file); // constructor, incarca din fisier
    void add(const Joc& joc); // adauga un joc in fisier, daca nu e duplicat
    void modify(const Joc& joc1, const Joc& Joc2); // modifica jocul cu id ul primului in al doilea
    const vector<Joc>& getAll(); // incarca toate datele din fisier
    int size(); // returneaza nr de jocuri
};

#endif //EXAMEN_REPOSITORY_H
