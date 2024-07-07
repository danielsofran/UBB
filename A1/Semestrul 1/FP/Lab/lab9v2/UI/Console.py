from Exceptii.exceptions import *

class Console:
    def __init__(self, servicefilm, serviceclient, serviceinchiriere): # constructor
        self.__servicefilm = servicefilm
        self.__serviceclient = serviceclient
        self.__serviceinchiriere = serviceinchiriere

    def __print_menu(self): # functie care afiseaza meniul
        print("\nComenzi:",
              "filme [adauga|sterge|modifica|vizualizeaza|genereaza|sorteaza]",
              "clienti [adauga|sterge|modifica|vizualizeaza|genereaza]",
              "filme cauta dupa [id|titlu|descriere|gen]",
              "clienti catua dupa [id|nume|cnp]",
              "inchiriere|returnare",
              "rapoarte:",
              "\tclienti cu filme ordonati dupa nume",
              "\tclienti cu filme ordonati dupa numarul de filme inchiriate",
              "\tcele mai inchiriate filme",
              "\tprimii 30% clienti cu cele mai multe filme",
              "\ttop 50% cele mai putin inchiriate filme care incep cu un string dat, sortate alfabetic dupa nume",
              "exit",
              sep='\n')

    #region Film
    def __adauga_film(self): # adauga film
        id = input("Id: ")
        try:
            id = int(id)
        except ValueError:
            print("Id numeric invalid!")
            return
        titlu = input("Titlu: ")
        descriere = input("Descriere: ")
        gen = input("Gen: ")
        try: self.__servicefilm.adauga(id, titlu, descriere, gen)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else: print("Film adaugat cu succes!")

    def __stergere_film(self): # sterge film
        id = input("Id: ")
        try: id = int(id)
        except ValueError:
            print("Id numeric invalid!")
            return
        try: self.__servicefilm.stergere(id)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else: print("Film sters!")

    def __modificare_film(self): # modificare film
        id = input("Id: ")
        try:
            id = int(id)
        except ValueError:
            print("Id numeric invalid!")
            return
        titlu = input("Titlu nou: ")
        descriere = input("Descriere noua: ")
        gen = input("Gen nou: ")
        try: self.__servicefilm.modificare(id, titlu, descriere, gen)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else: print("Film modificat!")

    def __vizualizeaza_film(self): # vizualizare filme
        lst = self.__servicefilm.vizualizare()
        if len(lst) == 0:
            print("Nu exista filme!")
            return
        for film in lst:
            print(film.show(), sep='\n')

    def __cauta_film(self): # cautare film dupa id
        id = input("Id: ")
        try:
            id = int(id)
        except ValueError:
            print("Id numeric invalid!")
            return
        try: film = self.__servicefilm.cautare(id)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else: print(film.show(), sep='\n')

    def __cauta_film_titlu(self): # cautare film dupa titlu
        titlu = input("Titlu: ")
        try: filme = self.__servicefilm.cautare_titlu(titlu)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else:
            for film in filme:
                print(film.show())

    def __cauta_film_descriere(self): # cautare film dupa descriere
        descriere = input("Descriere: ")
        try: filme = self.__servicefilm.cautare_descriere(descriere)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else:
            for film in filme:
                print(film.show())

    def __cauta_film_gen(self): # cautare film dupa gen
        gen = input("Gen: ")
        try: filme = self.__servicefilm.cautare_gen(gen)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else:
            for film in filme:
                print(film.show())

    def __genereaza_film(self): # generare numer de filme
        nr = input("Numarul de filme generate: ")
        try:
            numar = int(nr)
        except:
            print("Numar invalid!")
            return
        self.__servicefilm.generate_random(numar)
        print(f"{numar} de filme generate cu succes!")

    def __sortare_multipla(self): # sorteaza dupa titlu si descriere
        lst = self.__servicefilm.sortare_dupa_titlu_sau_descriere()
        if len(lst) == 0:
            print("Nu exista filme!")
            return
        for film in lst:
            print(film.show(), sep='\n')
    #endregion

    # region Client
    def __adauga_client(self): # adaugare client
        id = input("Id: ")
        try:
            id = int(id)
        except ValueError:
            print("Id numeric invalid!")
            return
        nume = input("Nume: ")
        cnp = input("Cnp: ")
        try: self.__serviceclient.adauga(id, nume, cnp)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else: print("Client adaugat cu succes!")

    def __stergere_client(self): # stergere client
        id = input("Id: ")
        try:
            id = int(id)
        except ValueError:
            print("Id numeric invalid!")
            return
        try: self.__serviceclient.stergere(id)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else: print("Client sters!")

    def __modificare_client(self): # modificare client
        id = input("Id: ")
        try:
            id = int(id)
        except ValueError:
            print("Id numeric invalid!")
            return
        nume = input("Nume: ")
        cnp = input("Cnp: ")
        try: self.__serviceclient.modificare(id, nume, cnp)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else: print("Client modificat!")

    def __vizualizeaza_client(self): # vizualizare clienti
        lst = self.__serviceclient.vizualizare()
        if len(lst) == 0:
            print("Nu exista clienti!")
            return
        for client in lst:
            print(client.show(), sep='\n')

    def __cauta_client(self): # cauta client dupa id
        id = input("Id: ")
        try:
            id = int(id)
        except ValueError:
            print("Id numeric invalid!")
            return
        try: client = self.__serviceclient.cautare(id)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else: print(client.show(), sep='\n')

    def __cauta_client_nume(self): # cauta client dupa nume
        nume = input("Nume: ")
        try: clienti = self.__serviceclient.cautare_nume(nume)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else:
            for client in clienti:
                print(client.show()+"\n")

    def __cauta_client_cnp(self): # cauta client dupa cnp
        cnp = input("Cnp: ")
        try: clienti = self.__serviceclient.cautare_cnp(cnp)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else:
            for client in clienti:
                print(client.show()+"\n")

    def __genereaza_client(self): # generare numer de clienti
        nr = input("Numarul de clienti generati: ")
        try:
            numar = int(nr)
        except:
            print("Numar invalid!")
            return
        self.__serviceclient.generate_random(numar)
        print(f"{numar} de clienti generati cu succes!")
    # endregion

    #region Inchirieri Rapoarte
    def __inchiriere(self): # inchiriaza un film existent
        id_client = input("Id client: ")
        try:
            id_client = int(id_client)
        except ValueError:
            print("Id numeric invalid!")
            return
        id_film = input("Id film: ")
        try:
            id_film = int(id_film)
        except ValueError:
            print("Id numeric invalid!")
            return
        try: self.__serviceinchiriere.inchiriaza(id_client, id_film)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else: print("Inchirierea s-a efectuat cu succes!")

    def __returnare(self): # returnare film inchiriat
        id_client = input("Id client: ")
        try:
            id_client = int(id_client)
        except ValueError:
            print("Id numeric invalid!")
            return
        id_film = input("Id film: ")
        try:
            id_film = int(id_film)
        except ValueError:
            print("Id numeric invalid!")
            return
        try: self.__serviceinchiriere.returneaza(id_client, id_film)
        except DuplicatedIDError as de:
            print(str(de))
        except ValidationError as ve:
            print(str(ve))
        except RepositoryError as re:
            print(str(re))
        except ServiceError as se:
            print(str(se))
        else: print("Returnarea s-a efectuat cu succes!")

    def __raport_clienti_cu_filme_dupa_nume(self): # raport cu fiecare client, ordonati crescator dupa nume, si filmele inchiriate de acestia
        rez = self.__serviceinchiriere.raport_clienti_cu_filme_dupa_nume()
        if len(rez) == 0:
            print("Nu exista astfel de inchirieri!")
            return
        for client in rez:
            print(client.show())
            for film in rez[client]:
                print("\t" + film.show())

    def __raport_clienti_cu_filme_dupa_nr_filmelor(self): # raport cu fiecare client, ordonati crescator dupa numarul de filme inchiriate, si filmele inchiriate de acestia
        rez = self.__serviceinchiriere.raport_clienti_cu_filme_dupa_nr_filmelor()
        if len(rez) == 0:
            print("Nu exista astfel de inchirieri!")
            return
        for client in rez:
            print(client.show())
            for film in rez[client]:
                print("\t" + film.show())

    def __raport_filme_inchiriate(self): # raport toate filmele ordonate descrescator dupa numarul de clienti care le-au inchiriat
        rez = self.__serviceinchiriere.raport_filme_inchiriate()
        if len(rez) == 0:
            print("Nu exista astfel de inchirieri!")
            return
        for film in rez:
            print(film.show())

    def __raport_primii_clienti_cu_cele_mai_inchiriate_filme(self): # primii 30% clienti cu cele mai multe filme inchiriate
        rez = self.__serviceinchiriere.raport_primii_clienti_cu_cele_mai_multe_filme()
        if len(rez) == 0:
            print("Nu exista astfel de inchirieri!")
            return
        for pereche in rez:
            print(f"{pereche[0]} a inchiriat {pereche[1]} filme")

    def __raport_cele_mai_putin_inchiriate_filme_care_incep_cu_un_string_dat(self): # cele mai putin inchiriate 50% filme care incep cu un string dat,
        string_dat = input("Introduceti inceputul titlului: ")
        if len(string_dat) == 0: raise ServiceError("Stringul dat nu poate fi vid!")
        filme = self.__serviceinchiriere.raport_cele_mai_putin_inchiriate_filme_care_incep_cu_un_string_dat(string_dat)
        if len(filme) == 0:
            print("Nu exista astfel de filme!")
            return
        for film in filme:
            print(film.show())
    #endregion

    def run(self): # functia de rulare a consolei
        while True:
            self.__print_menu()
            cmd = input("Introduceti comanda: ")
            cmd = cmd.strip().lower()
            if cmd == "": continue
            elif cmd == "exit": return
            elif cmd == "filme adauga": self.__adauga_film()
            elif cmd == "filme sterge": self.__stergere_film()
            elif cmd == "filme modifica": self.__modificare_film()
            elif cmd == "filme vizualizeaza": self.__vizualizeaza_film()
            elif cmd == "filme cauta dupa id": self.__cauta_film()
            elif cmd == "filme cauta dupa titlu": self.__cauta_film_titlu()
            elif cmd == "filme cauta dupa descriere": self.__cauta_film_descriere()
            elif cmd == "filme cauta dupa gen": self.__cauta_film_gen()
            elif cmd == "filme genereaza": self.__genereaza_film()
            elif cmd == "filme sorteaza": self.__sortare_multipla()
            elif cmd == "clienti adauga": self.__adauga_client()
            elif cmd == "clienti sterge": self.__stergere_client()
            elif cmd == "clienti modifica": self.__modificare_client()
            elif cmd == "clienti vizualizeaza": self.__vizualizeaza_client()
            elif cmd == "clienti cauta dupa id": self.__cauta_client()
            elif cmd == "clienti cauta dupa nume": self.__cauta_client()
            elif cmd == "clienti cauta dupa cnp": self.__cauta_client()
            elif cmd == "clienti genereaza": self.__genereaza_client()
            elif cmd == "inchiriere": self.__inchiriere()
            elif cmd == "returnare": self.__returnare()
            elif cmd == "rapoarte clienti cu filme ordonati dupa nume": self.__raport_clienti_cu_filme_dupa_nume()
            elif cmd == "rapoarte clienti cu filme ordonati dupa numarul de filme inchiriate": self.__raport_clienti_cu_filme_dupa_nr_filmelor()
            elif cmd == "rapoarte cele mai inchiriate filme": self.__raport_filme_inchiriate()
            elif cmd == "rapoarte primii 30% clienti cu cele mai multe filme": self.__raport_primii_clienti_cu_cele_mai_inchiriate_filme()
            elif cmd == "rapoarte top 50% cele mai putin inchiriate filme care incep cu un string dat, sortate alfabetic dupa nume": self.__raport_cele_mai_putin_inchiriate_filme_care_incep_cu_un_string_dat()
            else: print("Comanda invalida!\n")
