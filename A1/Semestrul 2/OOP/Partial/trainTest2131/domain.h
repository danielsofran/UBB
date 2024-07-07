//
// Created by Daniel on 17.05.2022.
//

#ifndef TRAINTEST2131_DOMAIN_H
#define TRAINTEST2131_DOMAIN_H

#include <string>
#include "iostream"
#include "sstream"

using std::string;
using std::istringstream;
using std::istream;
using std::ostream;

class Procesor{
private:
    string nume;
    int nrThreaduri=0;
    string socluProcesor;
    int pret=0;
public:
    Procesor() = default;
    Procesor(string name, int nrthr, string soclu, int pret) : nume(name), nrThreaduri(nrthr), socluProcesor(soclu), pret(pret) {}
    Procesor(const Procesor&) = default;

    string getNume() const;
    int getNrThreaduri() const;
    string getSoclu() const;
    int getPret() const;

    friend istream& operator>>(istream& in, Procesor& p);
    friend ostream& operator<<(ostream& out, const Procesor& p);
};

class PlacaDeBaza{
private:
    string nume;
    string soclu;
    int pret=0;
public:
    PlacaDeBaza() = default;
    PlacaDeBaza(string name, string soclu, int pret) : nume(std::move(name)), soclu(std::move(soclu)), pret(pret) {}
    PlacaDeBaza(const PlacaDeBaza&) = default;

    string getNume() const;
    string getSoclu() const;
    int getPret() const;

    friend istream& operator>>(istream& in, PlacaDeBaza& p);
    friend ostream& operator<<(ostream& out, const PlacaDeBaza& p);
};

#endif //TRAINTEST2131_DOMAIN_H
