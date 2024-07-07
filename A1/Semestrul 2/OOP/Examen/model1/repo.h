//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL1_REPO_H
#define MODEL1_REPO_H

#include "domain.h"
#include "fstream"
#include "sstream"
#include "exceptions.h"

class Repository{
private:
    string file;
    vector<Task> v;

    void load();
    void save();
public:
    Repository(const string &file);
    void add(const Task& t);
    void changeState(int id, string state);
    vector<Task>& getAll();
};

#endif //MODEL1_REPO_H
