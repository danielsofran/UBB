//
// Created by Daniel on 17.05.2022.
//

#include "domain.h"
#include "vector"
#include "string"

string Procesor::getNume() const {
    return nume;
}

int Procesor::getNrThreaduri() const {
    return nrThreaduri;
}

string Procesor::getSoclu() const {
    return socluProcesor;
}

int Procesor::getPret() const {
    return pret;
}

istream &operator>>(istream &in, Procesor &p) {
    string line;
    std::getline(in, line);
    if(line.length() == 0) return in;
    istringstream sin(line);
    std::vector<string> rez;
    while (std::getline(sin, line, ','))
        rez.push_back(line);
    p.nume = rez[0];
    p.nrThreaduri = std::stoi(rez[1]);
    p.socluProcesor = rez[2];
    p.pret = std::stoi(rez[3]);
    return in;
}

ostream &operator<<(ostream &out, const Procesor &p) {
    out<<p.nume<<','<<p.nrThreaduri<<','<<p.socluProcesor<<','<<p.pret<<',';
    return out;
}

string PlacaDeBaza::getNume() const {
    return nume;
}

string PlacaDeBaza::getSoclu() const {
    return soclu;
}

int PlacaDeBaza::getPret() const {
    return pret;
}

istream &operator>>(istream &in, PlacaDeBaza &p) {
    string line;
    std::getline(in, line);
    if(line.empty()) return in;
    istringstream sin(line);
    std::vector<string> rez;
    while (std::getline(sin, line, ','))
        rez.push_back(line);
    p.nume = rez[0];
    p.soclu = rez[1];
    p.pret = std::stoi(rez[2]);
    return in;
}

ostream &operator<<(ostream &out, const PlacaDeBaza &p) {
    out<<p.nume<<','<<p.soclu<<','<<p.pret<<',';
    return out;
}
