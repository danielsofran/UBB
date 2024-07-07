//
// Created by Daniel on 14.05.2022.
//

#ifndef UNTITLED_EXCEPTIONS_H
#define UNTITLED_EXCEPTIONS_H

#include "iostream"
#include "string"
#include <exception>
#include <utility>

using std::string ;
using std::exception ;
using std::ostream;

class MyException : exception{
protected:
    string ex;
public:
    explicit MyException(string exc) : ex(std::move(exc)){}
    friend ostream& operator<<(ostream& out, const MyException& m)
    {
        out<<m.ex;
        return out;
    }
};

class RepoException : public MyException{
    using MyException::MyException;};

class ServiceException : public MyException{
    using MyException::MyException;};

#endif //UNTITLED_EXCEPTIONS_H
