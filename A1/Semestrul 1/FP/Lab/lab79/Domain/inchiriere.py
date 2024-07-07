class Inchiriere:  # relatie intre client si film
    def __init__(self, client, film, inchiriat=True):  # constructor
        self.__client = client
        self.__film = film
        self.__inchiriat = inchiriat

    @property
    def client(self): return self.__client  # getter client

    @client.setter
    def client(self, val): self.__client = val  # setter client

    @property
    def film(self): return self.__film  # getter film

    @film.setter
    def film(self, val): self.__film = val  # setter film

    @property
    def inchiriat(self): return self.__inchiriat  # getter valoare de inchiriere

    @inchiriat.setter
    def inchiriat(self, val): self.__inchiriat = val  # setter valoare de inchiriere

    def __bool__(self): return self.inchiriat

    def __eq__(self,
               other): return self.client.id == other.client.id and self.film.id == other.film.id  # operator de egalitate, verifica daca id-urile sunt egale

    def __str__(self): return f"Clientul {self.client.nume} a inchiriat filmul {self.film.titlu}"

    def save(self, sep="*"):  # salveaza datele clasei intr-un string
        s = self.client.save()
        s += sep + self.film.save()
        return s

    @classmethod
    def fromStr(cls, s, sep="*"):
        from Domain.client import Client
        from Domain.film import Film
        s = s.split(sep)
        c = Client.fromStr(s[0])
        f = Film.fromStr(s[1])
        return cls(c, f, True)
