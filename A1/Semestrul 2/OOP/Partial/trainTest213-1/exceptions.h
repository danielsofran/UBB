//
// Created by Daniel on 18.05.2022.
//

#ifndef TRAINTEST213_1_EXCEPTIONS_H
#define TRAINTEST213_1_EXCEPTIONS_H

#include "exception"
#include "string"

using std::string;

class MyException : public std::exception{
    string msg;
public:
    MyException(string msg) : msg(msg){}
    string getMessage() const {return msg;}
};

class RepoException : public MyException
{
    using MyException::MyException;
};

class ServiceException : public MyException
{
    using MyException::MyException;
};

#endif //TRAINTEST213_1_EXCEPTIONS_H
