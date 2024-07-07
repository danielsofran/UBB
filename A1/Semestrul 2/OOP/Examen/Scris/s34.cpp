//
// Created by Daniel on 05.06.2022.
//

#include "bits/stdc++.h"
using namespace std;

template<class T>
class Expresie{
private:
    vector<T> vals;
public:
    Expresie(T v){
        vals.push_back(v);
    }
    Expresie& operator=(const Expresie&)=default;
    Expresie operator+(T elem)
    {
        T rez = vals.back() + elem;
        vals.push_back(rez);
        return *this;
    }
    Expresie operator-(T elem)
    {
        T rez = vals.back() - elem;
        vals.push_back(rez);
        return *this;
    }
    T valoare() const {return vals.back(); }
    Expresie& undo(){
        vals.pop_back();
        return *this;
    }
};

void operatii() {
    Expresie<int> exp{ 3 };//construim o expresie,pornim cu operandul 3
//se extinde expresia in dreapta cu operator (+ sau -) si operand
    exp = exp + 7 + 3;
    exp = exp - 8;
//tipareste valoarea expresiei (in acest caz:5 rezultat din 3+7+3-8)
    cout << exp.valoare()<<"\n";
    exp.undo(); //reface ultima operatie efectuata
//tipareste valoarea expresiei (in acest caz:13 rezultat din 3+7+3)
    cout << exp.valoare() << "\n";
    exp.undo().undo();
    cout << exp.valoare() << "\n"; //tipareste valoarea expresiei (in acest caz:3)
}

int main(){
    operatii();
}