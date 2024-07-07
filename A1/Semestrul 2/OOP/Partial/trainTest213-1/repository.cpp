//
// Created by Daniel on 18.05.2022.
//

#include "repository.h"

void FileRepository::load(){
    std::ifstream fin(path);
    string line, token;
    v.clear();
    while(std::getline(fin, line))
    {
        std::istringstream sin(line);
        vector<string> rez;
        while(std::getline(sin, token, ','))
            rez.push_back(token);
        Apartament ap(std::stoi(rez[0]), rez[1], std::stoi(rez[2]));
        v.push_back(ap);
    }
    fin.close();
}

void FileRepository::save() {
    std::ofstream fout(path);
    for(const auto& elem : v)
        fout<<elem.getSupr()<<','<<elem.getStrada()<<','<<elem.getSupr()<<",\n";
    fout.close();
}

void FileRepository::remove(const Apartament &ap) {
    load();
    auto it = std::find(v.begin(), v.end(), ap);
    if(it == v.end()) throw RepoException("Element negasit!");
    v.erase(it);
    save();
}

//template<class T>
//vector<Apartament> FileRepository::filter(T fct) const {
//    vector<Apartament>* rez = new vector<Apartament>;
//    for(const auto& elem : v)
//        if(fct(elem))
//            rez->push_back(elem);
//    return *rez;
//}

const vector<Apartament>& FileRepository::getAll() const {
    return v;
}


