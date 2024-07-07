//
// Created by Daniel on 08.06.2022.
//

#include "service.h"

Service::Service(Repository &repo) : r(repo) {
    idcurent = 0;
    for (const Joc &j: r.getAll()) {
        if (j.getId() > idcurent)
            idcurent = j.getId();
    }
    idcurent++;
}
void Service::valid(const string &dim, const string &tabla, const string &jucator) {
    for(char ch : dim)
        if(!std::isdigit(ch))
            throw ServiceException("Dimensiune invalida");
    if(dim.empty()) throw ServiceException("Dimensiune invalida");
    int d = stoi(dim);
    if(d<3 || d>5) throw ServiceException("Dimensiune invalida");
    if(tabla.length() != d*d) throw ServiceException("Tabla invalida");
    for(char ch : tabla)
        if(ch != 'X' && ch!='O' && ch != '-')
            throw ServiceException("Tabla invalida");
    if(jucator != "X" && jucator!="O")
        throw ServiceException("Jucator invalid");
}

void Service::valid(const string &stare) {
    if(stare != "Neinceput" && stare != "In derulare" && stare != "Terminat")
        throw ServiceException("Stare invalda");
}

void Service::add(cstring dim, cstring tabla, cstring jucator) {
    valid(dim, tabla, jucator);
    Joc j(idcurent, std::stoi(dim), tabla, jucator, "Neinceput");
    r.add(j);
    idcurent++;
}

void Service::modify(int id, const string &dim, const string &tabla, const string &jucator, cstring stare) {
    Joc j1(id, 0, "", "", "");
    valid(dim, tabla, jucator);
    valid(stare);
    Joc j(id, std::stoi(dim), tabla, jucator, stare);
    r.modify(j1, j);
}

void Service::modifyPoz(int id, int row, int col, string val) {
    Joc j1(id, 0, "", "", "");
    auto it = std::find(r.getAll().begin(), r.getAll().end(), j1);
    Joc j2 = *it;
    if (row < 0 || row >= j2.getDim()) throw ServiceException("Linie invalida");
    if (col < 0 || col >= j2.getDim()) throw ServiceException("Coloana invalida");
    if(j2.getPoz(row, col) != "-") throw ServiceException("Pozitie ocupata");
    j2.setPoz(row, col, val);
    if(val=="X") j2.setJucator("O");
    else j2.setJucator("X");
    bool gasit = false;
    if(j2.getTabla().find('-') == string::npos)
        gasit = true;
    if(!gasit) j2.setStare("In derulare");
    else j2.setStare("Terminat");
    r.modify(j1, j2);
}

const vector<Joc> &Service::getAll() {
    return r.getAll();
}

const Joc &Service::find(int id) const {
    for(const auto& j : r.getAll())
        if(j.getId() == id)
            return j;
    throw ServiceException("Negasit");
}


