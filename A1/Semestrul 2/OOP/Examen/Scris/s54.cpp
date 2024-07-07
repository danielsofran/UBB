//
// Created by Daniel on 05.06.2022.
//

#include "bits/stdc++.h"
using namespace std;

template<class T>
class Geanta{
private:
    string nume;
    vector<T> v;
public:
    Geanta(const string& nume) : nume(nume) {}
    Geanta(const Geanta&) = default;
    Geanta& operator+(const T& elem){
        v.push_back(elem);
        return *this;
    }
    auto begin() {return v.begin();}
    auto end() {return v.end(); }
};

void calatorie() {
    Geanta<string> ganta{ "Ion" };//creaza geanta pentru Ion
    ganta = ganta + string{ "haine" }; //adauga obiect in ganta
    ganta + string{ "pahar" };
    for (auto o : ganta) {//itereaza obiectele din geanta
        cout << o << "\n";
    }
}

int main(){
    calatorie();
}