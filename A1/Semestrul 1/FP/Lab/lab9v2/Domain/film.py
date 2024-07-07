'''
Created on 23 nov. 2021

@author: Daniel
'''

class Film(object):
    '''
    classdocs
    '''


    def __init__(self, id, titlu, descriere, gen):
        '''
        Constructor
        '''
        self.__id = id
        self.__titlu = titlu
        self.__descriere = descriere
        self.__gen = gen

    def get_id(self): # getter id
        return self.__id


    def get_titlu(self): # getter titlu
        return self.__titlu


    def get_descriere(self): # getter descriere
        return self.__descriere


    def get_gen(self): # getter gen
        return self.__gen


    def set_titlu(self, value): # setter titlu
        self.__titlu = value


    def set_descriere(self, value): # setter descriere
        self.__descriere = value


    def set_gen(self, value): # setter gen
        self.__gen = value

    id = property(get_id, None, None, None) # proprietate id
    titlu = property(get_titlu, set_titlu, None, None) # proprietate titlu
    descriere = property(get_descriere, set_descriere, None, None) # proprietate descriere
    gen = property(get_gen, set_gen, None, None) # proprietate gen

    def show(self): # functie pentru afisarea unui film
        return f"Id: {self.id}, Titlu: {self.titlu}, Descriere: {self.descriere}, Gen: {self.gen}"

    def __str__(self): # functie pt codarea unui film ca string
        return f"{self.id}\n{self.titlu}\n{self.descriere}\n{self.gen}"

    def __eq__(self, other): # operator de egalitate
        return self.id == other.id

    def __hash__(self): # fct hash
        return self.id

    @classmethod
    def fromStr(cls, str): # functie de conversie din string
        sir = str.split('\n')
        return cls(int(sir[0]), sir[1], sir[2], sir[3])
