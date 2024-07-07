from Domain.produs import Produs
from exceptii import *

class Repository:
    def __init__(self): # constructor
        self._l = []

    def __len__(self): # nr de elem
        return len(self._l)

    def __iter__(self): # pointer catre primul elem
        return iter(self._l[:])

    def adauga(self, prod: Produs): # agauga produsul in colectie
        # duplicatediderror daca exista deja
        if not prod in self._l:
            self._l.append(prod)
        else: raise DuplicatedIdException(f"id {prod.id} duplicat!")

    def stergere(self, prod: Produs): # sterge predusul din colectie
        # repoexception daca nu exista
        if not prod in self._l:
            raise RepoException(f"produsul cu id-ul {prod.id} nu a fost gasit!")
        self._l.remove(prod)


class FileRepository(Repository):
    def __init__(self, filename): # constructor
        self._path = filename
        super().__init__()

    def __read(self): # citire produse din fisier
        with open(self._path, "r") as f:
            self._l.clear()
            for line in f:
                if line!="":
                    obj = Produs.fromStr(line)
                    self._l.append(obj)

    def __write(self): # scriere produse in fisier
        with open(self._path, "w") as f:
            for elem in self._l:
                f.write(repr(elem)+"\n")

    def __len__(self): # nr de elem
        self.__read()
        return super().__len__()

    def __iter__(self): # pointer catre primul elem din colectie
        self.__read()
        return super().__iter__()

    def adauga(self, prod: Produs): # adauga produs in colectie cu modificarea fisierului
        self.__read()
        super().adauga(prod)
        self.__write()

    def stergere(self, prod: Produs): # sterge produs din colectie cu modificarea fisierului
        self.__read()
        super().stergere(prod)
        self.__write()

class History(Repository):
    def adauga(self, lst: list): # adaugam o lista de elemente sterse
        self._l.append(lst)

    def pop(self): # removes and return the last element, aka the last deleted list
        return self._l.pop()


