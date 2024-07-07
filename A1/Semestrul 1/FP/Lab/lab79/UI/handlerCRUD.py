from Controller.servicefilmeclienti import *


class Input:
    @staticmethod
    def film():  # input film
        id = input("Id: ")
        titlu = input("Titlu: ")
        descriere = input("Descriere: ")
        gen = input("Gen: ")
        return (id, titlu, descriere, gen)

    @staticmethod
    def client():  # input client
        id = input("Id: ")
        nume = input("Nume: ")
        cnp = input("CNP: ")
        return (id, nume, cnp)


class HandlerCRUD:
    def __init__(self, service):  # constructor
        if isinstance(service, ServiceFilme):
            self.__type = Film
        elif isinstance(service, ServiceClienti):
            self.__type = Client
        self.__service = service

    @property
    def type(self):  # getter tip
        return self.__type

    @property
    def service(self):  # getter service
        return self.__service

    @property
    def singular(self):  # getter singular
        if self.type == Film:
            return "film"
        elif self.type == Client:
            return "client"

    def input(self):  # input data type
        if self.type == Film:
            return Input.film()
        elif self.type == Client:
            return Input.client()

    def adauga(self):  # io pt adaugare
        print(f"Introduceti un {self.singular}:\n")
        t = self.input()
        self.service.adauga(t)
        print(self.service)

    def sterge(self):  # io pt stergere
        print(f"Introduceti specificatiile dorite:\n")
        t = self.input()
        self.service.sterge(t)
        print(self.service)

    def modificare(self):  # io pt modificare
        print(f"Introduceti {self.singular}ul care urmeaza sa fie inlocuit:\n")
        t1 = self.input()
        print(f"Introduceti {self.singular}ul nou:\n")
        t2 = self.input()
        self.service.modificare(t1, t2)
        print(self.service)

    def vizualizeaza(self):  # io pt vizualizare
        self.service.vizualizare()
        print(self.service)

    def cauta(self):  # io pt cautare
        print(f"Introduceti optiunile de cautare:\n")
        t = self.input()
        self.service.cautare(t)
        print(self.service)

    def filtreaza(self):  # io pt filtrare
        result_str = "nume"
        if self.type == Film: result_str = "titlu"
        s = input(f"Introduceti inceputul {result_str}lui: ")
        self.service.filtrare(s)
        print(self.service)
