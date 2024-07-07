//
// Created by Daniel on 08.06.2022.
//

#include "domain.h"

Joc::Joc(int id, int dim, const string &tabla, const string &jucator, const string &stare) : id(id), dim(dim),
                                                                                             jucator(jucator),
                                                                                             tabla(tabla),
                                                                                             stare(stare) {}

int Joc::getId() const {
    return id;
}

int Joc::getDim() const {
    return dim;
}

const string &Joc::getJucator() const {
    return jucator;
}

const string &Joc::getTabla() const {
    return tabla;
}

const string &Joc::getStare() const {
    return stare;
}

bool Joc::operator==(const Joc &rhs) const {
    return id == rhs.id;
}

bool Joc::operator!=(const Joc &rhs) const {
    return !(rhs == *this);
}

bool Joc::operator<(const Joc &rhs) const {
    return stare < rhs.stare;
}

std::ostream &operator<<(std::ostream &os, const Joc &joc) {
    os <<joc.id<<' '<<joc.dim<<' '<<joc.tabla<<' '<<joc.jucator<<' '<<joc.stare;
    return os;
}

std::istream &operator>>(std::istream &is, Joc &joc) {
    string line;
    std::getline(is, line);
    std::istringstream sin(line);
    sin>>joc.id>>joc.dim>>joc.tabla>>joc.jucator;
    std::getline(sin, joc.stare);
    while(joc.stare[0] == ' ')
        joc.stare = joc.stare.substr(1);
    return is;
}

string Joc::getPoz(int row, int col) const {
    string rez;
    rez+=tabla[row*dim+col];
    return rez;
}

void Joc::setPoz(int row, int col, string state) {
    tabla[row*dim+col] = state[0];
}

void Joc::setJucator(const string &jucator) {
    Joc::jucator = jucator;
}

void Joc::setStare(const string &stare) {
    Joc::stare = stare;
}


