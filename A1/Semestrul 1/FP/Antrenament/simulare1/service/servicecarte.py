from Domain.carte import Carte
from Validator.validatorcarte import ValidatorCarte


class ServiceCarte:
    def __init__(self, repo):
        self.__repo = repo

    def adauga(self, id, titlu, descriere, autor):
        carte = Carte(id, titlu, descriere, autor)
        ValidatorCarte.valid(carte)
        self.__repo.adauga(carte)

    def stergere_id(self, id):
        c = Carte(id, "a", "b", "c")
        ValidatorCarte.valid(c)
        self.__repo.stergere(id)

    def modificare(self, id, titlu, descriere, autor):
        carte = Carte(id, titlu, descriere, autor)
        ValidatorCarte.valid(carte)
        self.__repo.modificare(id, carte)

    def cauta_id(self, id):
        c = Carte(id, "a", "b", "c")
        ValidatorCarte.valid(c)
        return self.__repo.cauta_id(id)

    def vizualizare(self):
        return self.__repo.get_all()
    
