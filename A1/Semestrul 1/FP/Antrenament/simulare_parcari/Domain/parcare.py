class Parcare:
    """
        o parcare
    """
    def __init__(self, id: int, nume, strada, numar_utilizari: int):
        """
        constructor
        :param id: id-ul parcarii intreg pozitiv
        :param nume: numele parcarii
        :param strada: numele strazii
        :param numar_utilizari: numarul de utilizari ale parcarii
        """
        self.__id = id
        self.__nume = nume
        self.__strada = strada
        self.__numar_utilizari = numar_utilizari

    @property
    def id(self):  # getter id
        return self.__id

    @property
    def nume(self):  # getter nume
        return self.__nume

    @property
    def strada(self):  # getter strada
        return self.__strada

    @property
    def numar_utilizari(self):  # getter nr utilizari
        return self.__numar_utilizari

    def __str__(self):  # conversie la str
        return f"Parcarea cu id {self.__id}, " \
               f"numele: {self.__nume}, " \
               f"strada: {self.__strada}, " \
               f"numarul de utilizari: {self.__numar_utilizari}"

    @staticmethod
    def fromStr(sir):  # creeaza o parcare dintr-un sir
        sir = sir.split(",")
        return Parcare(int(sir[0]), sir[1], sir[2], int(sir[3]))
