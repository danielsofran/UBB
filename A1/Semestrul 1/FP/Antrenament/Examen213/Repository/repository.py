from Domain.emisiune import Emisiune
from exceptii import RepoException
import os

class Repository:
    def __init__(self, filename):
        self._file = filename
        self._l = []

    def __read(self):
        with open(self._file, "r") as f:
            self._l.clear()
            for line in f:
                if line!="":
                    obj = Emisiune.fromStr(line)
                    self._l.append(obj)

    def __write(self):
        with open(self._file, "w") as f:
            for elem in self._l:
                f.write(repr(elem)+"\n")

    def __len__(self):
        self.__read()
        return len(self._l)

    def __iter__(self):
        self.__read()
        return iter(self._l)

    def stergere(self, e: Emisiune):
        self.__read()
        if e in self._l:
            self._l.remove(e)
        else: raise RepoException(f"Emisiunea nu exista!")
        self.__write()

    def modificare(self, e1: Emisiune):
        self.__read()
        lst = []
        gasit = False
        for elem in self._l:
            if elem == e1:
                elem = e1
                gasit = True
            lst.append(elem)
        if not gasit:
            raise RepoException("Emisiunea nu exista!")
        self._l = lst
        self.__write()



