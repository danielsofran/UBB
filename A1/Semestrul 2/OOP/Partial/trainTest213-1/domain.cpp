//
// Created by Daniel on 18.05.2022.
//

#include "domain.h"

int Apartament::getSupr() const {
    return supr;
}

string Apartament::getStrada() const {
    return strada;
}

int Apartament::getPret() const {
    return pret;
}

bool Apartament::operator==(const Apartament &ap) const {
    return ap.supr == supr && ap.pret == pret && ap.strada == strada;
}
