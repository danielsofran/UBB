class Entity:
    def __init__(self, id):
        """
        constructor
        :param id: id
        """
        self.__id = id

    @property
    def id(self): # getter
        return self.__id

    def __eq__(self, other): # operator de egalitate
        return self.__id == other.__id

    def __repr__(self): # folosita pt scrierea in fisier, nedefinia
        raise NotImplementedError

    def __str__(self): # folosita pt afisare, nedefinia
        raise NotImplementedError

    @staticmethod
    def fromRepr(sir): # folosita pt citirea in fisier, nedefinia
        raise NotImplementedError
