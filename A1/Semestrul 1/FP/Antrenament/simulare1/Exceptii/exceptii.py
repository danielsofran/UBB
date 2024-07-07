class MyError(Exception): pass
class ValidatorError(MyError): pass
class RepositoryError(MyError): pass
class DuplicatedIDError(MyError): pass
class ServiceError(MyError): pass
