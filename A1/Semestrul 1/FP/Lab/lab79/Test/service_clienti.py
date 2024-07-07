from Controller.servicefilmeclienti import ServiceClienti, ValidatorClient, RepositoryClient, Client


class TestServiceClienti:
    def ctor_get_set(self):  # testeaza constructorul, getterii si setterii
        s = ServiceClienti()
        assert s.Validator == ValidatorClient
        assert s.repo == RepositoryClient()
        assert s.type == Client
        assert s.msg == ""
        assert s.singular == "clientul"
        assert s.plural == "clienți"
        s.singular = "client"
        assert s.singular == "client"
        s.type = int
        assert s.type == int

    def adauga(self):
        s = ServiceClienti()
        assert len(s.repo) == 0
        s.adauga(("1", "Arthur", "1425783902542"))
        s.adauga((2, "Dani", "1425362788907"))
        s.adauga((35, "Paul", "1425783902542"))
        assert len(s.repo) == 3
        s.adauga((1, "Boier", "15552678390234"))
        assert len(s.repo) == 3
        s.adauga((3, "Matyas23", "2555555555555"))
        assert len(s.repo) == 3  # nume incorect
        s.adauga((-1, "Eu", "1444423456378"))
        assert len(s.repo) == 3  # id negativ
        s.adauga((3, "Sarpilii", "12345678909888"))
        assert len(s.repo) == 3  # cnp incorect
        s.adauga((3))
        assert len(s.repo) == 3  # date insuficiente

    def stergere(self):
        s = ServiceClienti()
        s.adauga((1, "Arthur", "1425783902542"))
        s.adauga((2, "Dani", "1425362788907"))
        s.sterge((35, "Paul", "1425783902542"))
        assert len(s.repo) == 2
        s.sterge((2, "Dani", "1425362788907"))
        assert len(s.repo) == 1
        s.sterge((1, "Arthur", "1425783902540"))
        assert len(s.repo) == 1

    def modificare(self):
        s = ServiceClienti()
        s.adauga((1, "Arthur", "1425783902542"))
        s.adauga((2, "Dani", "1425362788907"))
        s.sterge((35, "Paul", "1425783902542"))
        s.modificare((1, "Arthur", "1425783902542"), (3, "Arthur", "1425783902542"))
        assert s.repo[0].id == 3
        assert s.repo[0].cnp == "1425783902542"
        s.modificare((3, "Arthur", "1425783902542"), (3, "Arthur", "1425783902542"))
        assert s.msg[:4] == "Cele"
        s.modificare(("1", "Dani", "123442567388"), ("31", "b3uicbid", "1222222222222"))
        assert s.repo[1] == Client.fromIterable((2, "Dani", "1425362788907"))
        s.modificare((1, "Arthur", "frumos", "actiune"), (3, "Arthur", "frumos", "actiune"))
        assert s.msg[0] != "C"  # fail

    def vizualizare(self):
        s = ServiceClienti()
        s.adauga((1, "Arthur", "1425783902542"))
        s.adauga((2, "Dani", "1425362788907"))
        s.adauga((35, "Paul", "1425783902542"))
        s.vizualizare()
        rez = "Lista de Clienți:\n" + str(s.repo[0]) + '\n' + str(s.repo[1]) + '\n' + str(s.repo[2])
        assert s.msg == rez

    def cautare(self):
        titlu = "Rezultate cautare clienți:\n"
        s = ServiceClienti()
        s.adauga((1, "Arthur", "1425783902542"))
        s.adauga((2, "Dani", "1425362788907"))
        s.adauga((35, "Paul", "1425783902542"))
        s.cautare((1, "", ""))
        assert s.msg == titlu + str(s.repo[0])
        s.cautare(("", "Dani", "1425362788907"))
        assert s.msg == titlu + str(s.repo[1])
        s.cautare(("", "", "342523432"))
        assert s.msg[:3] == "CNP"  # id invalid

    def filtrare(self):
        s = ServiceClienti()
        s.adauga((1, "Daniel", "1425783902542"))
        s.adauga((2, "Dani", "1425362788907"))
        s.adauga((35, "Paul", "1425783902542"))
        s.filtrare("Da")
        assert len(s.msg.split('\n')) == 2 * 3

    def __init__(self):
        self.ctor_get_set()
        self.adauga()
        self.stergere()
        self.modificare()
        self.vizualizare()
        self.cautare()
        self.filtrare()


def runall():
    TestServiceClienti()
