//
// Created by Daniel on 07.06.2022.
//

#ifndef MODEL2_OBSERVER_H
#define MODEL2_OBSERVER_H

#include "vector"
using std::vector;

class Observer{
public:
    virtual void updateObs() = 0; // functie care este notificata cand se produce o schimbare
};

class Observable{
private:
    vector<Observer*> obs;
public:
    void addObserver(Observer* o){obs.push_back(o);} // adaug observerul in lista
    void removeObserver(Observer* o){obs.erase(std::remove(obs.begin(), obs.end(), o), obs.end());} // sterg observer ul din lista
    void notify(){// actualizeaza elementele observate
        for(auto o : obs)
            o->updateObs();
    }
};

#endif //MODEL2_OBSERVER_H
