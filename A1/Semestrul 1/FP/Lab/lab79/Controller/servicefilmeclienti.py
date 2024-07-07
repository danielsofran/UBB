from Domain.film import Film
from Controller.validation import ValidatorFilm
from Repository.film import RepositoryFilm

from Domain.client import Client
from Controller.validation import ValidatorClient
from Repository.client import RepositoryClient

from Controller.generalservice import ServiceCRUD


class ServiceFilme(ServiceCRUD):
    def __init__(self):  # constructor
        super().__init__(Film, RepositoryFilm, ValidatorFilm, "filmul", "filme")

    def __validateIterable(self, other):  # valideaza un criteriu introdus
        self.msg = ""
        if other[0] == "" and other[1] == "" and other[2] == "" and other[3] == "":
            self.msg = "Nu ati introdus suficiente date!"
            return
        obj = self.type.fromIterable(other)
        kwargs = dict()

        try:
            self.Validator.id(obj.id)
        except:
            if obj.id != 0:
                self.msg += "Id invalid!\n"
        else:
            kwargs["id"] = obj.id

        try:
            self.Validator.titlu(obj.titlu)
        except:
            if obj.titlu != "":
                self.msg += "Titlu invalid!\n"
        else:
            kwargs["titlu"] = obj.titlu

        try:
            self.Validator.descriere(obj.descriere)
        except:
            if obj.descriere != "":
                self.msg += "Descriere invalida!\n"
        else:
            kwargs["descriere"] = obj.descriere

        try:
            self.Validator.gen(obj.gen)
        except:
            if obj.gen != "": self.msg += "Gen invalid!\n"
        else:
            kwargs["gen"] = obj.gen

        # print(kwargs)

        if self.msg != "": self.msg = self.msg[:-1]
        return kwargs

    def sterge(self, other):  # sterge valorile valide din repo
        kwargs = self.__validateIterable(other)
        if kwargs is not None and len(kwargs) > 0:
            super().sterge(**kwargs)

    def cautare(self, other):  # cauta valorile valide din repo
        kwargs = self.__validateIterable(other)
        if kwargs is not None and len(kwargs) > 0:
            super().cautare(**kwargs)

    def filtrare(self, other):  # fct de filtrare
        rez = self.repo.where(function=lambda elem: elem.titlu[:len(other)] == other)
        if len(rez) == 0:
            self.msg = f"Nn exista astfel de {self.plural} in lista!"
        else:
            self.msg = str(rez)

class ServiceClienti(ServiceCRUD):
    def __init__(self):  # constructor
        super().__init__(Client, RepositoryClient, ValidatorClient, "clientul", "clienți")

    def __validateIterable(self, other):  # valideaza un criteriu introdus
        self.msg = ""
        if other[0] == "" and other[1] == "" and other[2] == "":
            self.msg = "Nu ati introdus suficiente date!"
            return
        obj = self.type.fromIterable(other)
        kwargs = dict()

        try:
            self.Validator.id(obj.id)
        except:
            if obj.id != 0:
                self.msg += "Id invalid!\n"
            else:
                pass
        else:
            kwargs["id"] = obj.id

        try:
            self.Validator.nume(obj.nume)
        except:
            if obj.nume != "": self.msg += "Nume invalid!\n"
        else:
            kwargs["nume"] = obj.nume

        try:
            self.Validator.cnp(obj.cnp)
        except:
            if len(obj.cnp) > 0:
                self.msg += "CNP invalid!\n"
        else:
            kwargs["cnp"] = obj.cnp

        if self.msg != "": self.msg = self.msg[:-1]
        return kwargs

    def sterge(self, other):  # sterge valorile valide din repo
        kwargs = self.__validateIterable(other)
        if kwargs is not None and len(kwargs) > 0:
            super().sterge(**kwargs)

    def cautare(self, other):  # cauta valorile valide din repo
        kwargs = self.__validateIterable(other)
        if kwargs is not None and len(kwargs) > 0:
            super().cautare(**kwargs)

    def filtrare(self, other):  # fct de filtrare
        rez = self.repo.where(function=lambda elem: elem.nume[:len(other)] == other)
        if len(rez) == 0:
            self.msg = f"Nn exista astfel de {self.plural} in lista!"
        else:
            self.msg = str(rez)
