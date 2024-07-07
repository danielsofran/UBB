from Service.service import Service
from exceptii import MyException


class Console:
    def __init__(self, srv: Service): # constructor
        self.__srv = srv

    def __menu(self): # afiseaza meniul
        print("Meniu: ",
              "1. Adaugare",
              "2. Stergere",
              "3. Filtrare",
              "4. Undo stergere",
              "exit\n",
              sep="\n")

    def __adauga(self): # agauga un  produs
        # afiseaza mesaje de eroare in cazul in care exista
        id = input("id: ")
        denumire = input("denumire: ")
        pret = input("pret: ")
        try:
            id = int(id)
            pret = int(pret)
            self.__srv.adauga(id, denumire, pret)
            print("Produs agaugat!")
        except ValueError:
            print("Valori introduse gresit!")
        except MyException as e:
            print(str(e))

    def __sterge(self): # sterge toate produsele care au cifra data ca pret
        # afiseaza mesaje de eroare in cazul in care exista
        cif = input("cifra: ")
        try:
            print(f"S-au sters {self.__srv.sterge(cif)} produse!")
        except MyException as e:
            print(str(e))

    def __filtrare(self): # citeste, afiseaza si filtreaza colectia dupa filtrul dat
        # afiseaza mesaje de eroare in cazul in care exista
        text = input("text: ")
        nr = input("numar: ")
        val = 0
        try: val = int(nr)
        except:
            print("Numar introdus gresit!")
            return
        filtru = "Filtru:\n"
        if text != "": filtru += f"Sa contina in denumire {text}\n"
        if val != -1: filtru += f"Sa aiba pretul mai mic decat {val}\n"
        print(filtru)
        rez = self.__srv.filtrare(text, val)
        if len(rez) == 0:
            print("Nu exista!")
            return
        for prod in rez:
            print(prod)

    def __undo(self): # reface ultima op de stergere
        # afiseaza mesaje de eroare in cazul in care nu exista astfel de operatii facute in timpul executiei curente
        try:
            self.__srv.undo()
        except:
            print("Nici o operatie de refacut")

    def run(self): # ruleaza consola
        while True:
            self.__menu()
            cmd = input("Introduceti comanda: ")
            if cmd == "exit":
                break
            elif cmd == "1":
                self.__adauga()
            elif cmd == "2":
                self.__sterge()
            elif cmd == "3":
                self.__filtrare()
            elif cmd == "4":
                self.__undo()
            else:
                print("Comanda invalida!")

