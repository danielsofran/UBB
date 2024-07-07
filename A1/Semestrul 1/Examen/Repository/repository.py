from Domain.carte import Carte
from exceptii import RepoException


class Repository:
    """
    o colectie de carti
    """
    def __init__(self): # constructor - creeaza o colectie vida
        self._l = []

    def __len__(self): # nr de elemente din colectie
        return len(self._l)

    def __iter__(self): # iterator catre primul elem din colectie
        return iter(self._l)

    def adauga(self, carte: Carte): # adauga o carte in colectie
        if not carte in self._l:
            self._l.append(carte)
        else: raise RepoException("Id duplicat!")

    def stergere(self, carte: Carte): # sterge cartea din colectie
        if carte in self._l:
            self._l.remove(carte)
        else: raise RepoException("Cartea nu exista!")



