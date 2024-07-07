from Repository.repository import Repository, History
from Validator.validator import *
from exceptii import ServiceException


class Service:
    def __init__(self, repo: Repository): # constructor
        self.__repo = repo
        self.__history = History()

    def adauga(self, id: int, den: str, pret: int): # creeaza si adauga elem in colectie
        # validationexception daca nu are date corecte
        prod = Produs(id, den, pret)
        Validator.produs(prod)
        self.__repo.adauga(prod)

    def sterge(self, cifra: str) -> int:
        # sterge toate produsele care contin cifra in prte
        # serviceexception daca cifra nu e introdusa corect
        if len(cifra) != 1 or not (cifra > "0" and cifra < "9"):
            raise ServiceException("cifra introdusa gresit!")
        to_erase = []
        for prod in self.__repo:
            if cifra in str(prod.pret) and not prod in to_erase:
                to_erase.append(prod)
        self.__history.adauga(to_erase)
        for prod in to_erase:
            self.__repo.stergere(prod)
        return len(to_erase)  # nr de elem sterse

    def filtrare(self, text: str, nr: int) -> list:
        # filtreaza datele cu filtrele cerute
        # intoarce rez intr-o lista
        notext = (text == "")
        nonr = (nr == -1)
        rez = []
        for prod in self.__repo:
            if not notext and not nonr:
                if text in prod.denumire and prod.pret < nr:
                    rez.append(prod)
            elif not notext:
                if text in prod.denumire:
                    rez.append(prod)
            elif not nonr:
                if prod.pret < nr:
                    rez.append(prod)
            else:
                rez.append(prod)
        return rez

    def undo(self): # reface ultima stergere efectuata
        # serviceexception daca nu exista operatii ce pot fi refacute
        if len(self.__history) == 0:
            raise ServiceException
        lst = self.__history.pop()
        for prod in lst:
            try: self.__repo.adauga(prod)
            except: pass
