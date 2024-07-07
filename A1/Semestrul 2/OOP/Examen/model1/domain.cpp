//
// Created by Daniel on 06.06.2022.
//

#include "domain.h"

Task::Task(int id, const string &descriere, const vector<string> &programatori, const string &stare) :
        id(id), descriere(descriere),programatori(programatori),stare(stare) {}

int Task::getId() const {
    return id;
}

const string &Task::getDescriere() const {
    return descriere;
}

const vector<string> &Task::getProgramatori() const {
    return programatori;
}

const string &Task::getStare() const {
    return stare;
}

bool Task::operator<(const Task &rhs) const {
    return stare < rhs.stare;
}

bool Task::operator==(const Task &rhs) const {
    return id == rhs.id;
}

bool Task::operator!=(const Task &rhs) const {
    return !(rhs == *this);
}

void Task::setStare(const string &stare) {
    Task::stare = stare;
}

