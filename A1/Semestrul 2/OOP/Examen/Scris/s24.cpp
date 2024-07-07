//
// Created by Daniel on 05.06.2022.
//

#include "bits/stdc++.h"

using namespace std;

class Examen{
private:
    string titlu;
    string ora;
public:
    Examen(string titlu, string ora): titlu(titlu), ora(ora) {}
    string getDescriere() const {return titlu+" "+ora;}
};

template<class T>
class ToDo{
private: vector<T> v;
public:
    ToDo() = default;

    friend ToDo& operator<<(ToDo<T>& todo,  T elem)
    {
        todo.v.push_back(elem);
        return todo;
    }

    void printToDoList(ostream& out)
    {
        out<<"De facut:";
        for(const auto& elem : v)
            out<<elem.getDescriere();
    }
};

int main()
{
    ToDo<Examen> todo;
    Examen oop{ "oop scris","8:00" };
    todo << oop << Examen{"oop practic", "11:00"}; //Adauga 2 examene la todo
    std::cout << oop.getDescriere(); //tipareste la consola: oop scris ora 8:00
//itereaza elementele adaugate si tipareste la consola lista de activitati
//in acest caz tipareste: De facut:oop scris ora 8:00;oop practic ora 11:00
    todo.printToDoList(std::cout);
}