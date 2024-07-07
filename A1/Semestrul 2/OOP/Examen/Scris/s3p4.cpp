//
// Created by Daniel on 05.06.2022.
//

#include "bits/stdc++.h"
using namespace std;

template<class T>
class Adder{
private:
    stack<T> s;
public:
    Adder(T val) {s.push(val);}
    Adder& operator+(T elem){
        s.push(s.top()+elem);
    }
    T suma() const{return s.top();}
    friend ostream& operator<<(ostream& out, Adder<T> t){
        out<<t.s.top();
        return out;
    }
    Adder& operator--(int fictiv){
        s.pop();
        return *this;
    }
    Adder& operator--(){
        s.pop();
        return *this;
    }
};

int main(){
    Adder<int> add{ 1 };//construim un adder,pornim cu valoarea 1
    add = add + 7 + 3; //se adauga valorile 7 si 3
    add + 8;
    cout << add << "\n";//tipareste suma (in acest caz:19)
    add--; //elimina ultimul termen adaugat
    cout << add.suma() << "\n";//tipareste suma (in acest caz:11)
    --add--;
    cout << add.suma() << "\n";//tipareste suma (in acest caz:1)
}