class Helicopter:
    """
    clasa care stocheaza datele referitoare la un elicopter
    """
    def __init__(self, id, nume, scopuri, an_fabricatie):
        """
        constructor elicopter
        :param id: integer positive id
        :param nume: string
        :param scopuri: scopurile elicopterului
        :param an_fabricatie: anul fabricatiei
        """
        self.__id = id
        self.__nume = nume
        self.__scopuri = scopuri
        self.__an = an_fabricatie

    @property
    def id(self):  # getter id
        return self.__id

    @property
    def nume(self):  # getter nume
        return self.__nume

    @property
    def scopuri(self):  # getter scopuri
        return self.__scopuri

    @property
    def an(self):  # getter an
        return  self.__an

    def __str__(self):  # string folosit la afisare
        return f"Elicopterul {self.__id} '{self.__nume}', scopurile: {self.__scopuri}, anul {self.__an}"

    @staticmethod
    def fromStr(sir):  # construieste un elicopter dintr-un sir
        sir = sir.split(",")
        return Helicopter(int(sir[0]), sir[1], sir[2], int(sir[3]))

