from Service.service import Service
from exceptii import MyException


class Console:
    def __init__(self, srv: Service):
        self.__srv = srv

    def __meniu(self):
        print("Meniu",
              "1. Stergere",
              "2. Modificare",
              "3. Program aleator",
              "4. Blocare emisiuni", sep="\n")

    def __stergere(self):
        nume = input("nume: ")
        tip = input("tip: ")
        try: self.__srv.stergere(nume, tip)
        except MyException as e: print(e)

    def __modificare(self):
        nume = input("nume: ")
        tip = input("tip: ")
        durata = input("durata: ")
        descr = input("descriere: ")
        try:
            durata = int(durata)
            self.__srv.modificare(nume, tip, durata, descr)
        except ValueError:
            print("Durata introdusa invalid!")
        except MyException as e:
            print(e)

    def __blocare(self):
        sir = input("Introduceti programele care vor fi blocate cu spatiu: ")
        try:
            self.__srv.blocheaza(sir)
            print(f"Programele {sir} au fost blocate!")
        except MyException as e:
            print(e)

    def __afis_progr(self, progr, ora):
        table = [["Ora", "Nume", "Tip", "Descriere"]]
        for em in progr:
            table.append([str(ora), em.nume, em.tip, em.descriere])
            ora += em.durata
        spaces = [0 for i in range(4)]
        for line in table:
            for i in range(4):
                spaces[i] = max(spaces[i], len(line[i]))
        for line in table:
            for i in range(4):
                print(line[i] + " "*(spaces[i]-len(line[i])), end=" ")
            print()

    def __program(self):
        i1 = input("Ora inceput")
        i2 = input("Ora final")
        o1 = o2 = 0
        try:
            o1 = int(i1)
            o2 = int(i2)
        except:
            print("Orele au fost introduse gresit!")
            return
        if o1>o2: o1, o2 = o2, o1
        try:
            rez = self.__srv.program(o1, o2)
            self.__afis_progr(rez, o1)
        except MyException as e: print(e)

    def run(self):
        while True:
            self.__meniu()
            cmd = input("Comanda: ")
            if cmd=="1": self.__stergere()
            elif cmd=="2": self.__modificare()
            elif cmd=="3": self.__program()
            elif cmd=="4": self.__blocare()
            else: print("Comanda invalida!")
