//
// Created by Daniel on 19.05.2022.
//

#include "repository.h"

void Repository::load() {
    ifstream fin(path);
    Device d;
    v.clear();
    string line;
    while (std::getline(fin, line)) {
        istringstream sin(line);
        sin>>d;
        v.push_back(d);
    }
    fin.close();
}

Repository::Repository(string path) {
    this->path = path;
    load();
}

const vector<Device> &Repository::getAll() {
    load();
    return v;
}
