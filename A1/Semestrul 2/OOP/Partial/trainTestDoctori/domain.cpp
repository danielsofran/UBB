//
// Created by Daniel on 17.05.2022.
//

#include "domain.h"

string Doctor::getCnp() const {
    return cnp;
}

string Doctor::getNume() const {
    return nume;
}

string Doctor::getPrenume() const {
    return prenume;
}

string Doctor::getSectie() const {
    return sectie;
}

bool Doctor::getInConcediu() const {
    return inconcediu;
}

bool Doctor::operator==(const Doctor & d) const {
    return d.nume == nume && d.prenume == prenume && d.sectie == sectie;
}

bool Doctor::operator!=(const Doctor & d) const {
    return !(*this == d);
}
