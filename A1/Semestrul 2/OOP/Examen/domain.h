//
// Created by Daniel on 08.06.2022.
//

#ifndef EXAMEN_DOMAIN_H
#define EXAMEN_DOMAIN_H

#include "string"
#include "iostream"
#include "sstream"
using std::string;

class Joc{
private:
    int id;
    int dim;
    string jucator;
    string tabla;
    string stare;
public:
    Joc(int id, int dim, const string &tabla, const string &jucator, const string &stare);

    int getId() const; // getter id
    int getDim() const; // getter dim
    const string &getJucator() const; // getter jucator
    const string &getTabla() const; // getter table
    const string &getStare() const; // getter stare

    string getPoz(int row, int col) const;
    void setPoz(int row, int col, string state);

    void setJucator(const string &jucator); // seteaza jucatorul
    void setStare(const string &stare); // seteaza starea

    // operatori de egalitate, compara dupa id
    bool operator==(const Joc &rhs) const;
    bool operator!=(const Joc &rhs) const;

    // operator relational, compara dupa stare
    bool operator<(const Joc &rhs) const;

    // operatori de citire/scriere
    friend std::istream &operator>>(std::istream & is, Joc& joc);
    friend std::ostream &operator<<(std::ostream &os, const Joc &joc);
};

#endif //EXAMEN_DOMAIN_H
