//
// Created by Daniel on 06.06.2022.
//

#include "repo.h"

Repository::Repository(const string &file) : file(file) { load(); }

void Repository::load() {
    std::ifstream fin(file);
    if(!fin.is_open())
        throw RepoException("Eroare fisier!");
    v.clear();
    string line;
    while (std::getline(fin, line))
        if(!line.empty()){
            std::istringstream sin(line);
            vector<string> s;
            string token;
            while (std::getline(sin, token, ','))
                s.push_back(token);
            vector<string> progr(s.begin()+3, s.end());
            Task t(std::stoi(s[0]), s[1], progr, s[2]);
            v.push_back(t);
        }
    fin.close();
}

void Repository::save() {
    std::ofstream fout(file);
    for(const auto& elem : v) {
        fout << elem.getId() << "," << elem.getDescriere()<<","<<elem.getStare()<<",";
        for(const auto& p : elem.getProgramatori())
            fout<<p<<',';
        fout<<'\n';
    }
    fout.close();
}

void Repository::add(const Task &t) {
    load();
    for(const auto& elem : v)
        if(elem == t)
            throw RepoException("Id duplicat");
    v.push_back(t);
    save();
}

vector<Task> &Repository::getAll() {
    load();
    return v;
}

void Repository::changeState(int id, string state) {
    load();
    for(auto& elem : v)
        if(elem.getId() == id)
            elem.setStare(state);
    save();
}
