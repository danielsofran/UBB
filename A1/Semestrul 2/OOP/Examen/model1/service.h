//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL1_SERVICE_H
#define MODEL1_SERVICE_H

#include "repo.h"
#include "GUI/observer.h"
#include "map"

using std::map;

class Service : public Observable {
private:
    Repository& r;
    void valid(const Task&);

public:
    Service(Repository &r);
    void add(int id, const string &descriere, const vector<string> &programatori, const string &stare);
    void changeState(int id, string state);
    vector<Task> filterNumePr(string numepr) const;
    vector<Task> filterStare(string stare) const;
    const vector<Task>& getAll() const;
};

#endif //MODEL1_SERVICE_H
