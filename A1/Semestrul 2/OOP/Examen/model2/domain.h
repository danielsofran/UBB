//
// Created by Daniel on 07.06.2022.
//

#ifndef MODEL2_DOMAIN_H
#define MODEL2_DOMAIN_H

#include "string"

using std::string;

class Melodie{
private:
    int id;
    string titlu;
    string artist;
    int rank;
public:
    Melodie(int id, const string &titlu, const string &artist, int rank); // constructor cu parametrii
    Melodie(const Melodie&) = default; // constructor de copiere
    Melodie& operator=(const Melodie&) = default; // operator de atribuire

    int getId() const; // getter, returneaza id
    const string &getTitlu() const;// getter, returneaza titlu
    const string &getArtist() const;// getter, returneaza artist
    int getRank() const;// getter, returneaza rank

    void setTitlu(const string &titlu); // setter titlu
    void setRank(int rank); // setter rank

    // operatori de egalitate, compara dupa id
    bool operator==(const Melodie &rhs) const;
    bool operator!=(const Melodie &rhs) const;

    // operator relational, compara dupa rank
    bool operator<(const Melodie &rhs) const;
};

#endif //MODEL2_DOMAIN_H
