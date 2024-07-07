//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL3_OBSERVER_H
#define MODEL3_OBSERVER_H

#include "vector"

using std::vector;

class Observer{
public:
    virtual void updateObs() = 0;
};

class Observable {
private:
    vector<Observer*> v;
public:
    void addObserver(Observer* obs) {v.push_back(obs);}
    void remove(Observer* obs) {
        v.erase(std::remove(v.begin(), v.end(), obs), v.end());
    }

protected:
    void notify(){
        for(auto obs : v)
            obs->updateObs();
    }
};

#endif //MODEL3_OBSERVER_H
