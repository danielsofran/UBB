from Service.service import Service
from exceptii import MyException

class Console:
    """
    clasa ui pentru afisarea/citirea datelor
    """
    def __init__(self, service: Service): # constructor service e service-ul
        self.__srv = service

    def __input(self): # afiseaza meniul si returneaza comanda data
        print("Meniu de comenzi:",
              "1. Adauga",
              "2. Sterge toate cartile care au cifra data in anul aparitiei",
              "3. Setare filtre",
              "4. Filtrare",
              "5. Undo la ultima stergere",
              "e. Exit\n", sep="\n")
        return input("Introduceti comanda: ")

    def __adauga(self): # functionalitatea de adaugare
        id = input("id: ")
        titlu = input("titlu: ")
        autor = input("autor: ")
        anap = input("an aparitie: ")
        try:
            id = int(id)
            anap = int(anap)
            self.__srv.adauga(id, titlu, autor, anap)
            print("Operatie efectuata cu succes!")
        except MyException as e: # prind doar erorile aruncate de mine, destinate utilizatorului
            print(e)
        except ValueError:
            print("Date numerice introduse incorect!")

    def __stergere(self): # functionalitatea de stergere
        cifra = input("cifra: ")
        try:
            self.__srv.stergere(cifra)
            print("Operatie efectuata cu succes!")
        except MyException as e: # prind doar erorile aruncate de mine, destinate utilizatorului
            print(e)

    def __set_filtru(self): # functionalitatea de setare a filtrului
        filtru_titlu = input("filtru titlu: ")
        filtru_an = input("filtru an aparitie: ")
        try:
            val = int(filtru_an)
        except:
            print("Filtru de an incorect! Filtrul dat nu se va seta!")
            return
        self.__srv.set_filtrare(filtru_titlu, val)
        print("Operatie efectuata cu succes!")

    def __filtrare(self): # functionalitatea de filtrare
        carti = self.__srv.filtrare()
        filtru = self.__srv.filtru
        if filtru == "":
            print("filtru vid")
        if len(carti) == 0:
            print("Nu exista astfel de carti!")
            return
        for carte in carti:
            print(carte)

    def __undo(self): # functionalitatea de undo la ult stergere
        try:
            self.__srv.undo()
            print("Operatie efectuata cu succes!")
        except MyException as e: # prind doar erorile aruncate de mine, destinate utilizatorului
            print(e)

    def run(self): # rulez consola care va afisa meniul si va astepta introducerea comenzilor
        while True:
            cmd = self.__input()
            if cmd == "1": self.__adauga()
            elif cmd == "2": self.__stergere()
            elif cmd == "3": self.__set_filtru()
            elif cmd == "4": self.__filtrare()
            elif cmd == "5": self.__undo()
            elif cmd == "e": break
            else: print("Comanda invalida!")

