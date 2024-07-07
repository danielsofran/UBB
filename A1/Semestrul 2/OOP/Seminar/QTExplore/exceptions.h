#pragma once
#include <iostream>
#include <string>
#include <exception>
#include <utility>

using std::string ;
using std::exception ;
using std::ostream;

class MyException : exception{ // clasa exceptie de baza
protected:
    string ex;
public:
    explicit MyException(string exc) : ex(std::move(exc)){} // constructor cu un argument
    string getMessage() const { return ex;} // getter la mesajul de eroare
    friend ostream& operator<<(ostream& out, const MyException& m) // operator de afisare
    {
        out<<m.ex;
        return out;
    }
};

class RepoException : public MyException{
    using MyException::MyException; // clasa RepoException derivata din clasa de baza MyException
};

class ServiceException : public MyException{
    using MyException::MyException; // clasa ServiceException derivata din clasa de baza MyException
};
