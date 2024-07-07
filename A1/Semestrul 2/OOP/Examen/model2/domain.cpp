//
// Created by Daniel on 07.06.2022.
//

#include "domain.h"

Melodie::Melodie(int id, const string &titlu, const string &artist, int rank) : id(id), titlu(titlu), artist(artist),
                                                                                rank(rank) {}

int Melodie::getId() const {
    return id;
}

const string &Melodie::getTitlu() const {
    return titlu;
}

const string &Melodie::getArtist() const {
    return artist;
}

int Melodie::getRank() const {
    return rank;
}

void Melodie::setTitlu(const string &titlu) {
    Melodie::titlu = titlu;
}

void Melodie::setRank(int rank) {
    Melodie::rank = rank;
}

bool Melodie::operator==(const Melodie &rhs) const {
    return id == rhs.id;
}

bool Melodie::operator!=(const Melodie &rhs) const {
    return !(rhs == *this);
}

bool Melodie::operator<(const Melodie &rhs) const {
    return rank < rhs.rank;
}
