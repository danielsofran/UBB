from Domain.client import Client
from Domain.generatorrandom import GenerateClient
from Exceptii.exceptions import ServiceError
from Validator.validator import ValidatorClient

class ServiceClient:
    def __init__(self, repo): # constructor, repo - repository-ul asociat clasei
        self.__repo = repo

    def adauga(self, id, nume, cnp): #formare, validare si adaugare in repo
        client = Client(id, nume, cnp)
        ValidatorClient.valid(client)
        self.__repo.adauga(client)

    def stergere(self, id): # validare id, stergere acolo unde exista
        client = Client(id, "nume", "1111111111111")
        ValidatorClient.valid(client)
        self.__repo.stergere(id)

    def modificare(self, id, nume, cnp): # modificare nume si cnp al obiectului cu id-ul dat
        client = Client(id, nume, cnp)
        ValidatorClient.valid(client)
        self.__repo.modificare(id, client)

    def cautare(self, id): # cauta un client dupa id
        client = Client(id, "nume", "1111111111111")
        ValidatorClient.valid(client)
        rez = []
        for elem in self.__repo.get_all():
            if elem.id == id:
                return elem
        if len(rez) == 0:
            raise ServiceError("Nu exista astfel de clienti!")

    def cautare_nume(self, nume): # cauta un client dupa nume
        client = Client(1, nume, "1111111111111")
        ValidatorClient.valid(client)
        rez = []
        for elem in self.__repo.get_all():
            if elem.nume == nume:
                rez.append(elem)
        if len(rez) == 0:
            raise ServiceError("Nu exista astfel de clienti!")
        return rez

    def cautare_cnp(self, cnp): # cauta un client dupa cnp
        client = Client(1, "nume", cnp)
        ValidatorClient.valid(client)
        rez = []
        for elem in self.__repo.get_all():
            if elem.cnp == cnp:
                rez.append(elem)
        if len(rez) == 0:
            raise ServiceError("Nu exista astfel de clienti!")
        return rez

    def vizualizare(self): # toti clientii
        return self.__repo.get_all()

    def generate_random(self, nr): # genereaza random nr de cliente
        if nr == 0: return
        client = GenerateClient.client()
        try: ValidatorClient.valid(client)
        except: self.generate_random(nr)
        try: self.__repo.adauga(client)
        except: self.generate_random(nr)
        self.generate_random(nr-1)


