from Service.service import Service
from exceptii import *


class Console:
    def __init__(self, service: Service):
        self.__srv = service

    def __input(self):
        print("Meniu",
              "1. Adauga",
              "2. MOdifica genul si durata",
              "3. Genereaza spectacole",
              "4. Exporta",
              "e Exit\n", sep="\n")
        return input("Introduceti optiunea: ")

    def __adauga(self):
        titlu = input("Titlu: ")
        artist = input("Artist: ")
        gen = input("Gen: ")
        durata = input("Durata(s): ")
        try:
            durata = int(durata)
            self.__srv.adauga(titlu, artist, gen, durata)
            print("Element adaugat!")
        except ValueError:
            print("Durata invalida!")
        except MyException as me:
            print(me)

    def __modifica(self):
        titlu = input("Titlu: ")
        artist = input("Artist: ")
        gen = input("Gen: ")
        durata = input("Durata(s): ")
        try:
            durata = int(durata)
            self.__srv.modfifcare(titlu, artist, gen, durata)
        except ValueError:
            print("Durata invalida!")
        except MyException as me:
            print(me)

    def __genereaza(self):
        nr = input("Inntroduceti nr de spectacole: ")
        try:
            nr = int(nr)
            self.__srv.generare(nr)
        except ValueError:
            print("Numar introdus gresit!")

    def __exporta(self):
        file = input("Nume fisier: ")
        self.__srv.exporta(file)

    def run(self):
        while True:
            cmd = self.__input()
            if cmd == "1": self.__adauga()
            elif cmd == "2": self.__modifica()
            elif cmd == "3": self.__genereaza()
            elif cmd == "4": self.__exporta()
            elif cmd == "e": break
            else: print("Comanda invalida!")
