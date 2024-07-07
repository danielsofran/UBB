import random

from Domain.emisiune import Emisiune
from Repository.repository import Repository
from exceptii import *

class Service:
    def __init__(self, repo: Repository):
        self.__repo = repo
        self.__blocate = []
        self.__program = []
        self.__exista = False

    def stergere(self, nume, tip):
        e = Emisiune(nume, tip, 0, "-")
        if tip in self.__blocate:
            raise ServiceException("Tipul este blocat!")
        for elem in self.__repo:
            if elem == e:
                self.__repo.stergere(elem)
                return
        raise ServiceException("Emisiunea nu exista!")

    def modificare(self, nume, tip, durata: int, descr):
        e = Emisiune(nume, tip, durata, descr)
        if tip in self.__blocate:
            raise ServiceException("Tipul este blocat!")
        self.__repo.modificare(e)

    def ore(self, lst):
        ore = 0
        for elem in lst:
            ore += elem.durata
        return ore

    def __back(self, lst, ore_date, cand): # mixam emisiunile fara reluari
        for i in [x for x in cand if not (x in lst)]:
            if self.ore(lst) < ore_date:
                lst.append(i)
                if self.ore(lst)>24 or self.ore(lst) > ore_date:
                    lst.pop()
                    continue
                if self.ore(lst) == ore_date:
                    self.__exista = True
                    self.__program = lst[:]
                elif self.ore(lst)<ore_date:
                    self.__back(lst, ore_date, cand)
                lst.pop()

    def __back2(self, lst, ore_date, cand, pusi, minimpusi): # mixam emisiunile cu reluari
        for i in cand:
            if pusi[i] <= minimpusi:
                lst.append(i)
                pusi[i] +=1
                if self.ore(lst)>24:
                    lst.pop()
                    continue
                if self.ore(lst) == ore_date:
                    self.__exista = True
                    self.__program = lst[:]
                elif self.ore(lst)<ore_date:
                    self.__back2(lst, ore_date, cand, pusi, minimpusi)
                pusi[i] -=1
                lst.pop()

    def program(self, o1: int, o2: int):
        cand = [x for x in self.__repo if not x.tip in self.__blocate]
        oreminim = cand[0].durata
        for i in range(1, len(cand)):
            if cand[i].durata < oreminim:
                oreminim = cand[i].durata
        if abs(o2-o1)<oreminim:
            raise ServiceException(
                "Nu exista un program disponibil pt acest interval orar fix! Incercati un numer diferit de ore!")
        random.shuffle(cand)
        self.__back([], abs(o2-o1), cand[:])
        if self.__exista:
            return self.__program[:]
        else:
            pusi = {}
            for i in cand:
                pusi[i] = 0
            minimpusi = 0
            while self.__exista == False:
                self.__back2([], abs(o2-o1), cand[:], pusi, minimpusi)
                minimpusi += 1
            if self.__exista:
                rez = []
                for elem in self.__program:
                    if not elem in rez:
                        rez.append(elem)
                    else:
                        rez.append(Emisiune(elem.nume, elem.tip, elem.durata, elem.descriere+"****"))
                return rez
        raise ServiceException("Nu exista un program disponibil pt acest interval orar fix! Incercati un numer diferit de ore!")


    def blocheaza(self, str):
        str = str.strip().split()
        self.__blocate = [x for x in str]
        if len(str) == 0:
            raise ServiceException("Nu s-au blocat programe!")


