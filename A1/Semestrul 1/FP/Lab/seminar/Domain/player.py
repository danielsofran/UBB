class Player:
    def __init__(self, id, nume, bani):
        self.__id = id
        self.__nume = nume
        self.__bani = bani

    @property
    def id(self):
        return self.__id

    @property
    def nume(self):
        return self.__nume
    @nume.setter
    def nume(self, value):
        self.__nume = value

    @property
    def bani(self):
        return self.__bani
    @bani.setter
    def bani(self, value):
        self.__bani = value

    def __eq__(self, other):
        return self.__id == other.id

    def __str__(self):
        return f"Player-ul {self.__nume} cu id-ul {self.__id} si valuta {self.__bani}"

