from Domain.carte import Carte
from Repository.repository import Repository, RepoException

class FileRepository(Repository):
    """
    o colectie de carti care are un fisier in care modificarile asupra colectiei se vad in permanenta
    """
    def __init__(self, path): # constructor, path - calea fisierului
        self.__path = path
        super().__init__()
        self.__read() # citesc elementele la PORNIRE

    def __read(self): # citeste elementele din fisier si actualizeaza colectia
        with open(self.__path) as f:
            self._l.clear()
            for line in f:
                if line != "":
                    try:
                        carte = Carte.fromStr(line)
                        self._l.append(carte)
                    except: pass # linia nu e corecta

    def __write(self): # scriu colectia in fisier
        with open(self.__path, "w") as f:
            for carte in self._l:
                f.write(repr(carte)+"\n")

    def __len__(self): # nr de elemente din colectia din fisier
        self.__read()
        return super().__len__()

    def __iter__(self): # iterator pt prima carte din colectia din fisier
        self.__read()
        return super().__iter__()

    def adauga(self, carte: Carte): # adauga o carte in colectia din fisier
        self.__read()
        super().adauga(carte)
        self.__write()

    def stergere(self, carte: Carte): # sterge o carte din colectia din fisier
        self.__read()
        super().stergere(carte)
        self.__write()
