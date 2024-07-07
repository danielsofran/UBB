from Exceptii.exceptii import MyError


class Console:
    def __init__(self, service):
        self.__service = service

    def __print_menu(self):
        print("Comenzi:\n"
              "adauga carte\n"
              "sterge carte dupa id\n"
              "modifica carte dupa id\n"
              "cauta carte dupa id\n"
              "vizualizeaza\n"
              "exit\n\n")

    def __agauga(self):
        id = input("Id: ")
        titlu = input("Titlu: ")
        desc = input("Descriere: ")
        autor = input("Autor")
        try: id = int(id)
        except ValueError:
            print("Id invalid!")
            return
        try: self.__service.adauga(id, titlu, desc, autor)
        except MyError as me: print(str(me))
        else: print("Catre adaugata cu succes!")

    def __stergere_id(self):
        id = input("Id: ")
        try: id = int(id)
        except ValueError:
            print("Id invalid!")
            return
        try: self.__service.stergere_id(id)
        except MyError as me: print(str(me))
        else: print("Carte stearsa cu succes!")

    def __modificare(self):
        id = input("Id: ")
        titlu = input("Titlu: ")
        desc = input("Descriere: ")
        autor = input("Autor")
        try:
            id = int(id)
        except ValueError:
            print("Id invalid!")
            return
        try:
            self.__service.modificare(id, titlu, desc, autor)
        except MyError as me:
            print(str(me))
        else: print("CArte modificata cu succes!")

    def __cautare_id(self):
        id = input("Id: ")
        try:
            id = int(id)
        except ValueError:
            print("Id invalid!")
            return
        try:
            carte = self.__service.cautare_id(id)
        except MyError as me:
            print(str(me))
        else:
            print(carte.show())

    def __vizualizare(self):
        try: carti = self.__service.vizualizare()
        except MyError as me: print(str(me))
        else:
            for carte in carti:
                print(carte.show())

    def run(self):
        while True:
            self.__print_menu()
            cmd = input("Introduceti comanda: ")
            cmd = cmd.strip().lower()
            if cmd=="": continue
            elif cmd=="adauga carte": self.__agauga()
            elif cmd == "sterge carte dupa id": self.__stergere_id()
            elif cmd == "modifica carte dupa id": self.__modificare()
            elif cmd == "cauta carte dupa id": self.__cautare_id()
            elif cmd == "vizualizare carti": self.__vizualizare()
            elif cmd == "exit": break
            else: print("Comanda invalida!")

