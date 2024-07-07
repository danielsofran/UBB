class MyException(Exception): pass
class ValidationException(MyException): pass
class RepositoryException(MyException): pass
class DuplicatedIdException(MyException): pass
class ServiceException(MyException): pass
