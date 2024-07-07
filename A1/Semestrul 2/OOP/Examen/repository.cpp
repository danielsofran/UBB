//
// Created by Daniel on 08.06.2022.
//

#include "repository.h"

Repository::Repository(const string &file) : file(file) {
    load();
    save();
}

const vector<Joc> &Repository::getAll() {
    load();
    return jocuri;
}

void Repository::load() {
    std::ifstream fin(file);
    if(!fin.is_open()) throw RepoException("eroare la deschidere fisier citire");
    jocuri.clear();
    Joc joc(0, 0, "", "", "");
    while(fin>>joc)
        jocuri.push_back(joc);
    fin.close();
}

void Repository::save() {
    std::ofstream fout(file);
    if(!fout.is_open()) throw RepoException("eroare la deschidere fisier scriere");
    for(const auto& elem : jocuri)
        fout<<elem<<'\n';
    fout.close();
}

int Repository::size() {
    load();
    return jocuri.size();
}

void Repository::add(const Joc &joc) {
    load();
    auto it=std::find(jocuri.begin(), jocuri.end(), joc);
    if(it != jocuri.end()) throw RepoException("duplicate");
    jocuri.push_back(joc);
    save();
}

void Repository::modify(const Joc &joc1, const Joc &Joc2) {
    load();
    auto it=std::find(jocuri.begin(), jocuri.end(), joc1);
    if(it == jocuri.end()) throw RepoException("inexistent");
    *it = Joc2;
    save();
}



