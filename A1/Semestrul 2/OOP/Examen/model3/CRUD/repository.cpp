//
// Created by Daniel on 06.06.2022.
//

#include "repository.h"

void Repository::load() {
    ifstream fin(file);
    string line;
    string rez[4];
    v.clear();
    while (std::getline(fin, line))
    {
        std::istringstream sin(line);
        for(int i=0;i<4;++i)
            std::getline(sin, rez[i], ',');
        Produs p(std::stoi(rez[0]), rez[1], rez[2], std::stod(rez[3]));
        v.push_back(p);
    }
    fin.close();
}

void Repository::save() {
    ofstream fout(file);
    for(const auto& elem : v)
        fout<<elem.getId()<<','<<elem.getNume()<<','<<elem.getTip()<<','<<elem.getPret()<<",\n";
    fout.close();
}

void Repository::add(const Produs & p) {
    load();
    auto it = std::find(v.begin(), v.end(), p);
    if(it != v.end())
        throw RepoException("Duplicat!");
    v.push_back(p);
    save();
}

const vector<Produs> &Repository::getAll() const {
    return v;
}


