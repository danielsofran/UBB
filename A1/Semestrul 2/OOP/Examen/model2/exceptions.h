//
// Created by Daniel on 07.06.2022.
//

#ifndef MODEL2_EXCEPTIONS_H
#define MODEL2_EXCEPTIONS_H

#include <stdexcept>
#include "exception"

class MyException : public std::logic_error{ // exceptie proprie
    using std::logic_error::logic_error;
};

class RepoException : public MyException{ // exceptie aruncata de repo
    using MyException::MyException;
};

class ServiceException : public MyException{ // exceptie aruncata de service
    using MyException::MyException;
};

#endif //MODEL2_EXCEPTIONS_H
