//
// Created by Daniel on 08.06.2022.
//

#ifndef EXAMEN_SERVICE_H
#define EXAMEN_SERVICE_H

#include "repository.h"
#include "cctype"
#define cstring const string&

class Service{
private:
    Repository& r;
    int idcurent;

    // functii de validare, arunca ServiceException daca datele nu sunt valide
    void valid(cstring dim, cstring tabla, cstring jucator); // validez dimensiunea, tabla si jucatorul
    void valid(cstring stare); // validez starea
public:
    Service(Repository& repo); // constructor cu repo ca param
    const Joc& find(int id) const; // cauta un joc dupa id, arunca ServiceException daca nu il gaseste
    void add(cstring dim, cstring tabla, cstring jucator); // adauga un joc, arunca ServiceException daca mai exista un joc cu id ul date
    void modify(int id, cstring dim, cstring tabla, cstring jucator, cstring stare); // schimba un joc, arunca ServiceException daca nu il gaseste
    void modifyPoz(int id, int row, int col, string val); // modifica o pozitie a jocului cu id ul dat, arunca ServiceException daca mutarea nu este valida
    const vector<Joc>& getAll(); // returneaza toate jocurile
};

#endif //EXAMEN_SERVICE_H
