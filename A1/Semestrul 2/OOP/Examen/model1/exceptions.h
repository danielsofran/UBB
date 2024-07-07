//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL1_EXCEPTIONS_H
#define MODEL1_EXCEPTIONS_H

#include <stdexcept>
#include "exception"

using std::exception;

class MyException : public std::logic_error {
    using std::logic_error::logic_error;
};

class RepoException : public MyException{
    using MyException::MyException;
};

class ServiceException : public MyException{
    using MyException::MyException;
};

#endif //MODEL1_EXCEPTIONS_H
