class Carte:
    def __init__(self, id, titlu, descriere, autor):
        self.__id = id
        self.__titlu = titlu
        self.__descriere = descriere
        self.__autor = autor

    @property
    def id(self): return self.__id

    @property
    def titlu(self): return self.__titlu
    @titlu.setter
    def titlu(self, val): self.__titlu = val
    @property
    def descriere(self):
        return self.__descriere
    @descriere.setter
    def descriere(self, val):
        self.__descriere = val

    @property
    def autor(self): return self.__autor
    @autor.setter
    def autor(self, val):
        self.__autor = val

    def show(self):
        return f"Cartea {self.__titlu}, cu descrierea {self.__descriere}, autor {self.__autor}"

    def __str__(self):
        return f"{self.__id},{self.__titlu},{self.__descriere},{self.__autor}"

    def __eq__(self, other):
        return self.__id == other.__id

    @staticmethod
    def fromStr(sir):
        s = sir.split(",")
        return Carte(int(s[0]), s[1], s[2], s[3])
