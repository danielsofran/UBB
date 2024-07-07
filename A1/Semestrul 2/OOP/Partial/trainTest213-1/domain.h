//
// Created by Daniel on 18.05.2022.
//

#ifndef TRAINTEST213_1_DOMAIN_H
#define TRAINTEST213_1_DOMAIN_H

#include "string"

using std::string;

class Apartament{
private:
    int supr=0;
    string strada;
    int pret=0;

public:
    Apartament() = default;
    Apartament(int supr, string strada, int pret) : supr(supr), strada(strada), pret(pret) {}
    Apartament(const Apartament&) = default;

    int getSupr() const;
    string getStrada() const;
    int getPret() const;

    bool operator==(const Apartament& ap) const;
};

#endif //TRAINTEST213_1_DOMAIN_H
