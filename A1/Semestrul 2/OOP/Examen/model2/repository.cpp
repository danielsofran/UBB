//
// Created by Daniel on 07.06.2022.
//

#include "repository.h"

Repository::Repository(const string &file) : file(file) { load();}

void Repository::load() {
    std::ifstream fin(file);
    if(!fin.is_open()) throw RepoException("error opening file");
    string line, token;
    melodii.clear();
    nrRank.clear();
    while(std::getline(fin, line))
        if(!line.empty())
        {
            std::istringstream sin(line);
            vector<string> s;
            while (std::getline(sin, token, ','))
                s.push_back(token);
            Melodie m(std::stoi(s[0]), s[1], s[2], std::stoi(s[3]));
            melodii.push_back(m);
            nrRank[m.getRank()]++;
        }
    fin.close();
}

void Repository::save() {
    std::ofstream fout(file);
    nrRank.clear();
    for(const auto& elem : melodii) {
        fout << elem.getId() << ',' << elem.getTitlu() << ',' << elem.getArtist() << ',' << elem.getRank() << ",\n";
        nrRank[elem.getRank()]++;
    }
    fout.close();
}

const vector<Melodie> &Repository::getAll() {
    load();
    return melodii;
}

void Repository::modify(const Melodie &m1, const Melodie &m2) {
    load();
    auto it = std::find(melodii.begin(), melodii.end(), m1);
    if(it == melodii.end()) throw RepoException("Melodie inexistenta");
    *it = m2;
    save();
}

void Repository::sterge(const Melodie &m) {
    load();
    auto it = std::find(melodii.begin(), melodii.end(), m);
    if(it == melodii.end()) throw RepoException("Melodie inexistenta");
    melodii.erase(it);
    save();
}

int Repository::nrMeldii(int rank)  {
    return nrRank[rank];
}


