//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL3_EXCEPTIONS_H
#define MODEL3_EXCEPTIONS_H

#include "domain.h"
#include "exception"
#include "stdexcept"

class MyException : public std::logic_error {
public:
    MyException(string s) : std::logic_error(s){}
};
class RepoException : public MyException {
    using MyException::MyException;
};
class ServiceException : public MyException {
    using MyException::MyException;
};

#endif //MODEL3_EXCEPTIONS_H
