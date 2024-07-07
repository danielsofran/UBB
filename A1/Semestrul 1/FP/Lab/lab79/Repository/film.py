from Repository.generic import Repository
from Domain.film import Film

class RepositoryFilm(Repository): # lista de filme
    def __init__(self):
        self._type = Film
        super().__init__(self._type)

    def where(self, **kwargs): # implementarea functiei de cautare
        rez = self.copy()
        for key, value in kwargs.items():
            if key == "id":
                rez = list(filter(lambda argument: (argument.id == value), rez))
            elif key == "titlu":
                rez = list(filter(lambda argument: (argument.titlu == value), rez))
            elif key == "descriere":
                rez = list(filter(lambda argument: (argument.descriere == value), rez))
            elif key == "gen":
                rez = list(filter(lambda argument: (argument.gen == value), rez))
            elif key == "fields":
                rez = list(filter(lambda argument: (argument == Film.fromIterable(value)), rez))
            elif key == "film":
                rez = list(filter(lambda argument: (argument == value), rez))
            elif key == "function":
                rez = list(filter(value, rez))
        r = RepositoryFilm()
        r.extend(rez)
        return r
