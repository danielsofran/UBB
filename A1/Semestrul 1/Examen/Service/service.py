from Domain.carte import Carte
from Repository.filerepo import FileRepository
from Repository.history import History
from Repository.repository import Repository
from exceptii import ServiceException
from Validator.validator import Validator


class Service:
    """
    clasa care transmite datele introduse de utilizator repo-ului
    """
    def __init__(self, repo: (Repository, FileRepository)): # constructor - creeaza un service pe baza repository ului din memorie sau din fisier
        self.__repo = repo
        self.__filtru_titlu = ""
        self.__filtru_an = -1
        self.__history = History()

    def adauga(self, id: int, titlu: str, autor: str, anAp: int): # adauga in repo
        carte = Carte(id, titlu, autor, anAp)
        Validator.valideaza(carte)
        self.__repo.adauga(carte)

    def stergere(self, cifra: str): # sterge toate cartile care au cifra in nr aparitiei
        # verific daca cifra e introdusa corect
        if len(cifra)!=1:
            raise ServiceException("Cifra introdusa incorect!")
        _cifra = 0
        try: _cifra = int(cifra)
        except: raise ServiceException("Cifra introdusa incorect!")
        if _cifra < 0 or _cifra > 9:
            raise ServiceException("Cifra introdusa incorect!")

        # determin elementele ce trebuie sterse
        de_sters = []
        for carte in self.__repo:
            if cifra in str(carte.anAp):
                de_sters.append(carte)
        # adaug aceasta lista in istoric
        self.__history.adauga(de_sters)
        # sterg elem
        for carte in de_sters:
            self.__repo.stergere(carte)

    @property
    def filtru(self): # determina filtrul curent
        rez = ""
        if self.__filtru_titlu != "":
            rez += f"filtru titlu: {self.__filtru_titlu}\n"
        if self.__filtru_an != -1:
            rez += f"filtru an: {self.__filtru_an}\n"
        return rez

    def set_filtrare(self, filtru_titlu: str, filtru_an: int): # seteaza filtrul
        self.__filtru_titlu = filtru_titlu
        self.__filtru_an = filtru_an

    def filtrare(self): # filtreaza dupa filtrele setate anterior
        carti = []
        for carte in self.__repo:
            if self.__filtru_titlu!="" and self.__filtru_an != -1:
                if self.__filtru_titlu in carte.titlu and carte.anAp < self.__filtru_an:
                    carti.append(carte)
            elif self.__filtru_titlu!="":
                if self.__filtru_titlu in carte.titlu:
                    carti.append(carte)
            elif self.__filtru_an != -1:
                if carte.anAp < self.__filtru_an:
                    carti.append(carte)
            else: carti.append(carte)
        return carti

    def undo(self): # refac ultima stergere
        carti_sterse_ult_data = self.__history.pop()
        for carte in carti_sterse_ult_data:
            self.__repo.adauga(carte) # se reflecta in fisier daca e repo de fisiere
