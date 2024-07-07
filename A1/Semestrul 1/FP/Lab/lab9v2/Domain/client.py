class Client(object):
    def __init__(self, id, titlu, cnp): # constructor
        self.__id = id
        self.__nume = titlu
        self.__cnp = cnp

    def get_id(self): # getter id
        return self.__id


    def get_nume(self): # getter nume
        return self.__nume


    def get_cnp(self): # getter cnp
        return self.__cnp


    def set_nume(self, value): # setter nume
        self.__nume = value


    def set_cnp(self, value): # setter cnp
        self.__cnp = value

    id = property(get_id, None, None, None) # proprietate id
    nume = property(get_nume, set_nume, None, None) # proprietate nume
    cnp = property(get_cnp, set_cnp, None, None) # proprietate cnp

    def show(self): # functie pentru afisarea unui client
        return f"Id: {self.id}, Nume: {self.nume}, Cnp: {self.cnp}"

    def __str__(self): # functie pentru codarea unui client sub forma de string
        return f"{self.id}\n{self.nume}\n{self.cnp}"

    def __eq__(self, other): # operator egalitate
        return self.id == other.id

    def __hash__(self): # fac hash
        return self.id

    @classmethod
    def fromStr(cls, str): # functie de convertire din string
        sir = str.split('\n')
        return cls(int(sir[0]), sir[1], sir[2])


