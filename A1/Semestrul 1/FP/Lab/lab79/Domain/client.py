#from Controller.validFilmClient import ValidatorClient

class Client: # tipul de date client
    def __init__(self, id=1, nume="Șofran Daniel", cnp="1632084562102"):
        self.__id = id
        self.__nume = nume
        self.__cnp = cnp
        #ValidatorClient.client(self)
        #self.__cnp = str(cnp)

    def get_id(self): return self.__id      # getter id
    def get_nume(self): return self.__nume  # getter nume
    def get_cnp(self): return self.__cnp    # getter cnp

    def set_nume(self, nume):       # setter nume
        #ValidatorClient.nume(nume)
        self.__nume = nume

    def set_cnp(self, cnp):
        #ValidatorClient.cnp(cnp)    # setter cnp
        self.__cnp = str(cnp)

    id = property(get_id)  # proprietate id
    nume = property(get_nume, set_nume)  # proprietate nume
    cnp = property(get_cnp, set_cnp)  # proprietate cnp

    def __eq__(self, other):  # operator de egalitate
        if not isinstance(other, Client): return False
        if self.id == other.id: return True
        return False

    def __hash__(self):
        return hash(self.id) ^ hash(self.nume) ^ hash(self.cnp)

    def __str__(self):  # conversia la string, returneaza TOATE campurile
        return f"Id: {self.id}\nNume: {self.nume}\nCNP: {self.cnp}"

    def show(self):  # afiseaza cel mai semnificativ camp
        return self.nume

    def save(self, sep='~'):  # salveaza datele clasei intr-un string
        return f"{self.id}{sep}{self.nume}{sep}{self.cnp}"

    @classmethod
    def fromStr(cls, str):
        '''
        converteste din string in Client
        :param str: sirul
        :rtype: Client
        :raise: IOException, ValueError
        '''
        try:
            i = 0
            while str[i].isdigit(): i = i + 1
            id = int(str[:i])
            j = i
            while not str[j].isdigit(): j = j + 1
            cnp = str[j:j + 14]  # primele 13 cifre
            return cls(id, str[i + 1:j - 1], cnp)
        except:
            raise IOError("Client introdus incorect!")

    @classmethod
    def fromIterable(cls, it):
        '''
        converteste primele date dintr-un iterabil la Client
        :param it: list/tuple/...
        :rtype: Client
        :raise: ValueError
        '''
        id = 0
        if it[0] != "": id = int(it[0])
        return cls(id, it[1], it[2])
