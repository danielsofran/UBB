//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL1_DOMAIN_H
#define MODEL1_DOMAIN_H

#include "string"
#include "vector"

using std::string;
using std::vector;

class Task{
public:
    Task(int id, const string &descriere, const vector<string> &programatori, const string &stare);

    bool operator==(const Task &rhs) const;

    bool operator!=(const Task &rhs) const;

    void setStare(const string &stare);

    int getId() const;

    const string &getDescriere() const;

    const vector<string> &getProgramatori() const;

    bool operator<(const Task &rhs) const;

    const string &getStare() const;

private:
    int id;
    string descriere;
    vector<string> programatori;
    string stare;
};



#endif //MODEL1_DOMAIN_H
