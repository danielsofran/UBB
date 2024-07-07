from Domain.film import Film
from Domain.client import Client
from Domain.inchiriere import Inchiriere
from Controller.validation import ValidatorFilm, ValidatorClient, ValidatorInchiriere


class TestFilm:

    def getters(self):  # testeaza constructorul si getterii
        id = 1
        titlu = "MamaMia"
        descriere = "Naspa"
        gen = "Romance"
        f = Film(id, titlu, descriere, gen)
        ValidatorFilm.film(f)
        assert f.id == id
        assert f.titlu == titlu
        assert f.descriere == descriere
        assert f.gen == gen

    def setters(self):  # testeaza setterii
        id = 1
        titlu = "MamaMia"
        descriere = "Naspa"
        gen = "Romance"
        f = Film(id, titlu, descriere, gen)

        try:
            f.id = 2
        except:
            pass
        else:
            raise Exception("Id-ul se poate seta!")

        f = Film(-1)
        try:
            ValidatorFilm.film(f)
        except:
            pass
        else:
            raise Exception("Id negativ!")

        titlu = "Intaliania"
        f.titlu = titlu
        assert f.titlu == titlu

        descriere = "Chiar ok"
        f.descriere = descriere
        assert f.descriere == descriere

        gen = "Melancolic"
        f.gen = gen
        assert f.gen == gen

        id = 1
        titlu = " \n"
        descriere = "Naspa"
        gen = "38432"
        f = Film(id, titlu, descriere, gen)
        try:
            ValidatorFilm.film(f)
        except Exception as ex:
            assert len(ex.args[0].split('\n')) == 4  # se prind 2 erori + ultimul \n

        f = Film(0)
        try:
            ValidatorFilm.film(f)
        except Exception as ex:
            assert len(ex.args[0].split('\n')) == 2  # se prind 1 erori

        f = Film()
        f.descriere = "coar cuvinte???"
        ValidatorFilm.film(f)

    def output(self):  # testeaza conversiile la string
        id = 1
        titlu = "MamaMia"
        descriere = "Naspa"
        gen = "Romance"
        f = Film(id, titlu, descriere, gen)
        large = "Id: {0}\nTitlu: {1}\nDescriere: {2}\nGen: {3}"
        assert str(f) == large.format(id, titlu, descriere, gen)
        assert f.show() == f.titlu

    def egal(self):  # testeaza fct de ==, !=
        id = 2
        titlu = "MamaMia"
        descriere = "Naspa"
        gen = "Romance"
        f = Film(id, titlu, descriere, gen)
        assert f != Film()
        g = Film(id, titlu, descriere, gen)
        assert f == g

    def convertors(self):  # verifica convertorii din iterabil si string
        id = 1
        titlu = "MamaMia"
        descriere = "Naspa"
        gen = "Romance"
        f = Film(id, titlu, descriere, gen)
        g = Film.fromIterable((id, titlu, descriere, gen))
        h = Film.fromStr(f"{id} {titlu} {descriere} {gen}")
        try:
            Film.fromIterable((1, "Avatar"))
            Film.fromStr(f"2tata,mamafain")
        except:
            pass
        else:
            raise Exception
        assert f == g

    def __init__(self):  # apeleaza toate testele
        self.getters()
        self.setters()
        self.output()
        self.egal()


class TestClient:
    def getters(self):
        id = 1
        nume = "Dan Bogdan"
        cnp = 3557433214739
        c = Client(id, nume, cnp)
        assert c.id == id
        assert c.nume == nume
        assert c.cnp == cnp
        ValidatorClient.client(c)

    def setters(self):
        c = Client(-1)
        try:
            ValidatorClient.client(c)
        except:
            pass
        else:
            raise Exception("Id negativ!")

        id = 1
        nume = "Dan Bogdan"
        cnp = 3557433214739
        c = Client(id, nume, cnp)

        try:
            c.id = 2
        except:
            pass
        else:
            raise Exception("Id-ul poate fi setat!")

        c.nume = "Ana-Maria"

        c.nume = nume
        assert c.nume == nume

        cnp = "153820392645454"
        c.cnp = cnp
        assert c.cnp == cnp

        c.cnp = "23232323232 33"
        try:
            ValidatorClient.client(c)
        except:
            pass
        else:
            raise Exception("Cnp Invalid!")

        c.nume = "@Doi"
        try:
            ValidatorClient.client(c)
        except:
            pass
        else:
            raise Exception("Nume Invalid!")

    def output(self):
        id = 1
        nume = "Dan Bogdan"
        cnp = 3557433214739
        c = Client(id, nume, cnp)
        large = "Id: {0}\nNume: {1}\nCNP: {2}"
        assert str(c) == large.format(id, nume, cnp)
        assert c.show() == c.nume

    def egal(self):
        id = 2
        nume = "Dan Bogdan"
        cnp = 3557433214739
        c = Client(id, nume, cnp)
        assert c != Client()
        g = Client(id, nume, cnp)
        assert c == g

    def convertors(self):
        id = 1
        nume = "Dan Bogdan-Dumitre"
        cnp = 3557433214739
        c = Client(id, nume, cnp)
        g = Client.fromIterable((1, nume, cnp))
        h = Client.fromStr(f"{id} {nume} {cnp}")
        assert c == g
        assert g == h

    def __init__(self):
        self.getters()
        self.setters()
        self.output()
        self.egal()
        self.convertors()


class TestInchiriere:
    def getters(self):
        id = 1
        nume = "Dan Bogdan"
        cnp = 3557433214739
        c = Client(id, nume, cnp)
        id = 1
        titlu = "MamaMia"
        descriere = "Naspa"
        gen = "Romance"
        f = Film(id, titlu, descriere, gen)
        i = Inchiriere(c, f, inchiriat=True)
        ValidatorInchiriere.inchiriere(i)
        assert i.film == f
        assert i.client == c
        assert i.inchiriat == True

    def setters(self):
        id = 1
        nume = "Dan Bogdan"
        cnp = 3557433214739
        c = Client(id, nume, cnp)
        id = 1
        titlu = "MamaMia"
        descriere = "Naspa"
        gen = "Romance"
        f = Film(id, titlu, descriere, gen)
        i = Inchiriere(c, f, inchiriat=True)

        i.film.titlu = "Arthur"
        assert i.film.titlu == "Arthur"
        i.inchiriat = False
        assert i.inchiriat == False
        ValidatorInchiriere.inchiriere(i)

    def string(self):  # operatiile de conversie intre sir de caractere si clasa
        id = 1
        nume = "Dan Bogdan"
        cnp = 3557433214739
        c = Client(id, nume, cnp)
        id = 1
        titlu = "MamaMia"
        descriere = "Naspa"
        gen = "Romance"
        f = Film(id, titlu, descriere, gen)
        i = Inchiriere(c, f, inchiriat=True)
        assert Inchiriere.fromStr(i.save("*"), "*") == i

    def __init__(self):
        self.getters()
        self.setters()
        self.string()


def runall():
    TestFilm()
    TestClient()
    TestInchiriere()
