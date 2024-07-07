class Spectacol:
    genuri = ["Comedie", "Concert", "Balet", "Altele"]
    def __init__(self, titlu: str, artist: str, gen: str, durata: int):
        self.__titlu = titlu
        self.__artist = artist
        self.__gen = gen
        self.__durata = durata

    @property
    def titlu(self):
        return self.__titlu

    @property
    def artist(self):
        return self.__artist

    @property
    def gen(self):
        return self.__gen

    @property
    def durata(self):
        return self.__durata

    def __eq__(self, other):
        return self.__titlu==other.__titlu and self.__artist == other.__artist #and self.__durata==other.__durata and self.__gen == other.__gen

    def __repr__(self):
        return f"{self.artist},{self.titlu},{self.durata},{self.gen}"

    @staticmethod
    def fromStr(sir: str):
        sir = sir.strip().split(",")
        return Spectacol(sir[1], sir[0], sir[3], int(sir[2]))





