//
// Created by Daniel on 08.06.2022.
//

#ifndef EXAMEN_EXCEPTIONS_H
#define EXAMEN_EXCEPTIONS_H

#include <stdexcept>

class MyException : public std::logic_error { // exceptie proprie
    using std::logic_error::logic_error;
};

class RepoException : public MyException {// exceptie proprie repo
    using MyException::MyException;
};

class ServiceException : public MyException {// exceptie proprie service
    using MyException::MyException;
};

#endif //EXAMEN_EXCEPTIONS_H
