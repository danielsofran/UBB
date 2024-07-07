//
// Created by Daniel on 19.05.2022.
//

#include "repo.h"

void Repository::load() {
    std::ifstream fin(path);
    v.clear();
    string token, line;
    while (std::getline(fin, line))
    {
        std::istringstream sin(line);
        vector<string> tkns;
        while (std::getline(sin, token, ','))
            tkns.push_back(token);
        Apartament ap(std::stoi(tkns[0]), tkns[1], std::stoi(tkns[2]));
        v.push_back(ap);
    }
    fin.close();
}

void Repository::save() {
    std::ofstream fout(path);
    for(const auto & elem : v)
        fout<<elem.getSupr()<<','<<elem.getStrada()<<','<<elem.getPret()<<','<<'\n';
    fout.close();
}

Repository::Repository(string path) {
    this->path = path;
    load();
}

void Repository::sterge(const Apartament & ap) {
    load();
    auto it = std::find(v.begin(), v.end(), ap);
    if(it == v.end()) throw std::logic_error("Element negasit!");
    v.erase(it);
    save();
}

const vector<Apartament>& Repository::getAll() const {
    return v;
}




