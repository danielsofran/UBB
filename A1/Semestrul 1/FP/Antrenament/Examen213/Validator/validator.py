from Domain.emisiune import Emisiune
from exceptii import ValidatorException

class Validator:
    @staticmethod
    def valid(e: Emisiune):
        if "~" in e.nume or "~" in e.tip or "~" in e.descriere:
            raise ValidatorException("Emisiune invalida!")
