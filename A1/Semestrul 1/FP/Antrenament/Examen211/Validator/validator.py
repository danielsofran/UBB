from Domain.produs import Produs
from exceptii import ValidatorException


class Validator:
    @staticmethod
    def id(id: int): # valideaza un id
        if id<0:
            raise ValidatorException("id invalid!")

    @staticmethod
    def denumire(den: str): # valideaza o denumire
        for c in den:
            if c in ",~!.1234567890_'":
                raise ValidatorException("denumire invalida!")

    @staticmethod
    def pret(pret: int): # valideaza un pret
        if pret<=0:
            raise ValidatorException("pret invalid")

    @staticmethod
    def produs(prod: Produs): # valideaza un produs
        Validator.id(prod.id)
        Validator.denumire(prod.denumire)
        Validator.pret(prod.pret)

