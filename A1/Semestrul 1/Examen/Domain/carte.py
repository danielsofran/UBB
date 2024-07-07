class Carte:
    """
    clasa care stocheaza datele unei carti precum si operatiile pe care aceasta le poate efectua asupra datelor sale
    """
    def __init__(self, id: int, titlu: str, autor: str, anAp: int): # constructor - creeaza o carte
        self.__id = id
        self.__autor = autor
        self.__titlu = titlu
        self.__anAp = anAp

    @property
    def id(self): # getter id
        return self.__id

    @property
    def titlu(self): # getter titlu
        return self.__titlu

    @property
    def autor(self): # getter autor
        return self.__autor

    @property
    def anAp(self): # getter an aparitie
        return self.__anAp

    def __eq__(self, other): # 2 carti sunt identice daca au acelasi id
        return self.__id == other.__id

    def __str__(self): # convertirea la str pt utilizator
        return f"id: {self.__id}, titlu: {self.__titlu}, autor: {self.__autor}, an aparitie: {self.__anAp}"

    def __repr__(self): # convertirea la str pt memorarea in fisier
        return f"{self.__id}~{self.__titlu}~{self.__autor}~{self.__anAp}"

    @staticmethod
    def fromStr(sir: str): # decodarea dintr-o reprezentare repr
        sir = sir.strip().split("~")
        return Carte(int(sir[0]), sir[1], sir[2], int(sir[3]))

