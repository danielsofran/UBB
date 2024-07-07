//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL1_OBSERVER_H
#define MODEL1_OBSERVER_H

#include "vector"
#include "algorithm"
using std::vector;

class Observer{
public:
    virtual void updateObs() = 0;
};

class Observable{
private:
    vector<Observer*> obs;
public:
    void addObserver(Observer* o) { obs.push_back(o); }
    void removeObserver(Observer* o){
        obs.erase(std::remove(obs.begin(), obs.end(), o), obs.end());
    }
    void notify(){
        for(auto o : obs)
            o->updateObs();
    }
};

#endif //MODEL1_OBSERVER_H
