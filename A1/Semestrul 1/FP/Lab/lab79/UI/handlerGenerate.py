from Domain.generatorrandom import GenerateClient, GenerateFilm, GenerateInchiriere


class HandlerGenerate:
    def __init__(self, servicefilm, serviceclient, serviceinchirieri):
        self.__servicefilm = servicefilm
        self.__serviceclient = serviceclient
        self.__serviceinchirieri = serviceinchirieri

    def film(self):
        f = GenerateFilm.film()
        lungime = len(self.__servicefilm)
        self.__servicefilm.adauga((f.id, f.titlu, f.descriere, f.gen))
        if len(self.__servicefilm) > lungime:
            print(f)
        else:
            self.film()

    def client(self):
        c = GenerateClient.client()
        lungime = len(self.__serviceclient.repo)
        self.__serviceclient.adauga((c.id, str(c.nume), str(c.cnp)))
        if len(self.__serviceclient) > lungime:
            print(c)
        else:
            print(c)
            self.client()

    def inchiriere(self):
        i = GenerateInchiriere.inchiriere()
        print(i)
        lungime = len(self.__serviceinchirieri)
        self.__serviceinchirieri.adauga(i)
        if len(self.__serviceinchirieri) > lungime:
            print("Inchirierea generata automat a fost adaugata in lista")
        else:
            print("Inchirierea generata automat nu a putut fi adaugata din cauza faptului ca filmul/clientul nu exista in lista!")