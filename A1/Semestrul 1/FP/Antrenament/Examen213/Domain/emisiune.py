class Emisiune:
    def __init__(self, nume: str, tip: str, durata: int, descriere: str): # constructor
        self.__nume = nume
        self.__tip = tip
        self.__durata = durata
        self.__descriere = descriere

    @property
    def nume(self):
        return self.__nume

    @property
    def tip(self):
        return self.__tip

    @property
    def durata(self):
        return self.__durata

    @property
    def descriere(self):
        return self.__descriere

    def __eq__(self, other):
        return self.__nume == other.__nume and self.__tip == other.__tip

    def __repr__(self):
        return f"{self.__nume}~{self.__tip}~{self.__durata}~{self.__descriere}"

    def __hash__(self):
        return hash(self.__tip) ^ hash(self.__nume)

    @staticmethod
    def fromStr(sir):
        sir = sir.strip().split("~")
        return Emisiune(sir[0], sir[1], int(sir[2]), sir[3])
