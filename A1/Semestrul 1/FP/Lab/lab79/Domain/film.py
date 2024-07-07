#from Controller.validFilmClient import ValidatorFilm

class Film: # tipul de date film
    def __init__(self, id=1, titlu="Film", descriere="interesant", gen="actiune"): # constructor
        self.__id = id
        self.__titlu = titlu
        self.__descriere = descriere
        self.__gen = gen
        #ValidatorFilm.film(self)

    def get_id(self): return self.__id                  # getter id
    def get_titlu(self): return self.__titlu            # getter titlu
    def get_descriere(self): return self.__descriere    # getter descrere
    def get_gen(self): return self.__gen                # getter gen

    def set_titlu(self, titlu):            # setter titlu
        #ValidatorFilm.titlu(titlu)
        self.__titlu = titlu

    def set_descriere(self, descriere):    # setter descriere
        #ValidatorFilm.descriere(descriere)
        self.__descriere = descriere

    def set_gen(self, gen):                # setter gen
        #ValidatorFilm.gen(gen)
        self.__gen = gen

    id = property(get_id)  # proprietate id
    titlu = property(get_titlu, set_titlu)  # proprietate titlu
    descriere = property(get_descriere, set_descriere)  # proprietate descriere
    gen = property(get_gen, set_gen)  # proprietate gen

    def __eq__(self, other):  # operator de egalitate
        if not isinstance(other, Film): return False
        if self.id == other.id: return True  # and self.descriere == other.descriere and self.gen == other.gen: return True
        return False

    def __hash__(self):
        return hash(self.id) ^ hash(self.titlu) ^ hash(self.gen) ^ hash(self.descriere)

    def __str__(self):  # conversia la string, returneaza TOATE campurile
        return f"Id: {self.id}\nTitlu: {self.titlu}\nDescriere: {self.descriere}\nGen: {self.gen}"

    def show(self):  # returneaza cel mai semnificativ camp
        return self.titlu

    def save(self, sep='~'):  # salveaza datele clasei intr-un string
        return f"{self.id}{sep}{self.titlu}{sep}{self.descriere}{sep}{self.gen}"

    @classmethod
    def fromStr(cls, str, sep='~'):
        '''
        converteste din string in Film
        :param str: sirul
        :rtype: Film
        :raise: IOException, ValueError
        '''
        try:
            sir = str.split(sep)
            return cls(int(sir[0]), sir[1], sir[2], sir[3])
        except:
            raise IOError("Film introdus incorect!")

    @classmethod
    def fromIterable(cls, it):
        '''
        converteste primele date dintr-un iterabil la Film
        :param it: list/tuple/...
        :rtype: Film
        :raise: ValueError
        '''
        id = 0
        if it[0] != "": id = int(it[0])
        return cls(id, it[1], it[2], it[3])
