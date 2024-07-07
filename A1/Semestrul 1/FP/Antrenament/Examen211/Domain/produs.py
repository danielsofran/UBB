class Produs:
    def __init__(self, id: int, denumire: str, pret: int): # constructor
        self.__id = id
        self.__den = denumire
        self.__pret = pret

    @property
    def id(self): # getter id
        return self.__id

    @property
    def denumire(self): # getter denumire
        return self.__den

    @property
    def pret(self): # getter pret
        return self.__pret

    def __eq__(self, other): # operator de egalitate intre produse
        return self.__id == other.__id

    def __str__(self): # afisarea catre client
        return f"id: {self.__id}, denumire: {self.__den}, pret: {self.__pret}"

    def __repr__(self): # modul de reprezentare in fisier
        return f"{self.__id}~{self.__den}~{self.__pret}"

    @staticmethod
    def fromStr(sir: str): # converteste o reprezentare din text in obiect
        sir = sir.strip().split("~")
        p = Produs(int(sir[0]), sir[1], int(sir[2]))
        return p
