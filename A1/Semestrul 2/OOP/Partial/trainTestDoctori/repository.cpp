//
// Created by Daniel on 17.05.2022.
//

#include "repository.h"

#include <utility>

void FileRepository::load() {
    ifstream fin(path);
    string line;
    v.clear();
    while (std::getline(fin, line))
    {
        std::istringstream sin(line);
        string token[5];
        std::getline(sin, token[0], ',');
        std::getline(sin, token[1], ',');
        std::getline(sin, token[2], ',');
        std::getline(sin, token[3], ',');
        std::getline(sin, token[4], ',');
        bool inc = token[4][0] != '0';
        Doctor d{token[0], token[1], token[2], token[3], inc};
        v.push_back(d);
    }
    fin.close();
}

FileRepository::FileRepository(string path) {
    this->path = std::move(path);
    load();
}

const vector<Doctor> &FileRepository::getAll() {
    return v;
}

int FileRepository::size() const {
    return v.size();
}
