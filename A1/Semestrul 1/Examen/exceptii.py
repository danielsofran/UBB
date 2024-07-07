""" clasa care defineste propriile exceptii """
class MyException(Exception): pass
class RepoException(MyException): pass
class ServiceException(MyException): pass
class ValidationException(MyException): pass
