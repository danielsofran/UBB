import functools

from Domain.film import Film
from Domain.generatorrandom import GenerateFilm
from Exceptii.exceptions import ServiceError
from Service.sortari import mysorted
from Validator.validator import ValidatorFilm

class ServiceFilm:
    def __init__(self, repo): # service de filme, repo - repository-ul asociat
        self.__repo = repo

    def adauga(self, id, titlu, descriere, gen): # creare, validare, adaugare de film
        film = Film(id, titlu, descriere, gen)
        ValidatorFilm.valid(film)
        self.__repo.adauga(film)

    def stergere(self, id): # validare id, stergere film dupa id
        film = Film(id, "titlu", "descriere", "gen")
        ValidatorFilm.valid(film)
        self.__repo.stergere(id)

    def modificare(self, id, titlu, descriere, gen): # actualizare film dupa id, validare actualizare
        film = Film(id, titlu, descriere, gen)
        ValidatorFilm.valid(film)
        self.__repo.modificare(id, film)

    def cautare(self, id): # cautare dupa id
        film = Film(id, "titlu", "descriere", "gen")
        ValidatorFilm.valid(film)
        for elem in self.__repo.get_all():
            if elem.id == id:
                return elem
        raise ServiceError("Nu exista astfel de filme!")

    def cautare_titlu(self, titlu): # cautare fupa titlu
        film = Film(1, titlu, "descriere", "gen")
        ValidatorFilm.valid(film)
        rez = []
        for elem in self.__repo.get_all():
            if elem.titlu == titlu:
                rez.append(elem)
        if len(rez) == 0:
            raise ServiceError("Nu exista astfel de filme!")
        return rez

    def cautare_descriere(self, descriere): # cautare dupa descriere
        film = Film(1, "titlu", descriere, "gen")
        ValidatorFilm.valid(film)
        rez = []
        for elem in self.__repo.get_all():
            if elem.descriere == descriere:
                rez.append(elem)
        if len(rez) == 0:
            raise ServiceError("Nu exista astfel de filme!")
        return rez

    def cautare_gen(self, gen): # cautare dupa gen
        film = Film(1, "titlu", "descriere", gen)
        ValidatorFilm.valid(film)
        rez = []
        for elem in self.__repo.get_all():
            if elem.gen == gen:
                rez.append(elem)
        if len(rez) == 0:
            raise ServiceError("Nu exista astfel de filme!")
        return rez

    def vizualizare(self): # functie care returneaza toate filmele
        return self.__repo.get_all()

    def generate_random(self, nr): # genereaza random nr de filme
        if nr == 0: return
        film = GenerateFilm.film()
        try: ValidatorFilm.valid(film)
        except: self.generate_random(nr)
        self.__repo.adauga(film)
        self.generate_random(nr-1)

    def __cmp_titlu_descriere(self, film1, film2): # functia de comparare
        if film1.titlu < film2.titlu:
            return -1
        elif film1.titlu > film2.titlu:
            return 1
        else:
            if film1.descriere < film2.descriere:
                return -1
            elif film1.descriere > film2.descriere:
                return 1
            return 0

    def sortare_dupa_titlu_sau_descriere(self): # sorteaza cresactor dupa nume, daca numele sunt egale, crescator dupa descriere
        filme = self.__repo.get_all()
        filme = mysorted(filme, key=functools.cmp_to_key(self.__cmp_titlu_descriere))
        return filme


