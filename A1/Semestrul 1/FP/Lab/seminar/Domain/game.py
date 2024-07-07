class Game:
    def __init__(self, id, nume):
        self.__id = id
        self.__nume = nume

    @property
    def id(self):
        return self.__id

    @property
    def nume(self):
        return self.__nume
    @nume.setter
    def nume(self, value):
        self.__nume = value

    def __eq__(self, other):
        return self.__id == other.__id

    def __str__(self):
        return f"Jocul {self.__nume} cu id-ul {self.__id}"