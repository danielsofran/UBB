class InchiriereDto:
    def __init__(self, id_client, id_film):
        self.__id_film = id_film
        self.__id_client = id_client

    @property
    def id(self):
        return self.__id_film ^ self.__id_client

    @property
    def id_film(self):
        return self.__id_film

    @property
    def id_client(self):
        return self.__id_client

    def __eq__(self, other):
        return self.id_film == other.id_film and self.id_client == other.id_client

    def __str__(self):
        return f"{self.__id_film}\n{self.__id_client}"

    def __hash__(self):
        return self.id

    @classmethod
    def fromStr(cls, str):
        sir = str.strip().split('\n')
        return cls(int(sir[1]), int(sir[0]))
