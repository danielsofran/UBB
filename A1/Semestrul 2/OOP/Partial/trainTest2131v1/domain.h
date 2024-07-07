//
// Created by Daniel on 19.05.2022.
//

#ifndef TRAINTEST2131V1_DOMAIN_H
#define TRAINTEST2131V1_DOMAIN_H

#include "string"

using std::string;

class Apartament{
private:
    int supr = 0;
    string strada;
    int pret = 0;
public:
    Apartament() = default; // constructor default
    // constructor cu parametrii
    Apartament(int supr, string strada, int pret) : supr(supr), strada(strada), pret(pret){}
    Apartament(const Apartament&) = default; // constructor de copiere

    int getSupr() const; // getter suprafata
    string getStrada() const; // getter strada
    int getPret() const; // gettter pret

    bool operator==(const Apartament&) const = default; // operator de egalitate
    bool operator!=(const Apartament&) const = default; // operator de diferentiere
};

#endif //TRAINTEST2131V1_DOMAIN_H
