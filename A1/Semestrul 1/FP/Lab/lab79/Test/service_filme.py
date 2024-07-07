from Controller.servicefilmeclienti import ServiceFilme, ValidatorFilm, RepositoryFilm, Film


class TestServiceFilme:
    def ctor_get_set(self):  # testeaza constructorul, getterii si setterii
        s = ServiceFilme()
        assert s.Validator == ValidatorFilm
        assert s.repo == RepositoryFilm()
        assert s.type == Film
        assert s.msg == ""
        assert s.singular == "filmul"
        assert s.plural == "filme"
        s.singular = "film"
        assert s.singular == "film"
        s.type = int
        assert s.type == int

    def adauga(self):
        s = ServiceFilme()
        assert len(s.repo) == 0
        s.adauga((1, "Arthur", "frumos", "actiune"))
        s.adauga((1, "MamaMia", "naspa", "romance"))
        assert len(s.repo) == 1
        assert s.msg[0] != "F"  # id duplicat
        s.adauga((2, "Arthur", "frumos", "actiune"))
        assert s.msg[0] == "F"  # success
        s.adauga((-1, "tit", "desc", "gen"))
        assert s.msg[0] != "F"  # fail
        s.adauga((3))
        assert s.msg[0] != 'F'  # fail

    def stergere(self):
        s = ServiceFilme()
        s.adauga((1, "Arthur", "frumos", "actiune"))
        s.adauga((2, "MamaMia", "naspa", "romance"))
        assert len(s.repo) == 2
        s.sterge((1, "Arthur", "frumos", "actiune"))
        assert s.msg[0] == "F"  # succes
        assert len(s.repo) == 1
        s.sterge((2, "MamaMia", "naspa", "romance"))
        assert s.msg[0] == "F"  # succes
        assert len(s.repo) == 0
        s.sterge((2, "MamaMia", "naspa", "romance"))
        assert s.msg[0] != "F"  # fail
        assert len(s.repo) == 0

    def modificare(self):
        s = ServiceFilme()
        s.adauga((1, "Arthur", "frumos", "actiune"))
        s.adauga((2, "MamaMia", "naspa", "romance"))
        s.modificare((1, "Arthur", "frumos", "actiune"), (3, "Arthur", "frumos", "actiune"))
        assert s.msg[0] == "F"  # success
        assert s.repo[0] == Film.fromIterable((3, "Arthur", "frumos", "actiune"))
        s.modificare((1, "Arthur", "frumos", "actiune"), (3, "Arthur", "frumos", "actiune"))
        assert s.msg[0] != "F"  # fail

    def vizualizare(self):
        s = ServiceFilme()
        s.adauga((1, "Arthur", "frumos", "actiune"))
        s.adauga((2, "MamaMia", "naspa", "romance"))
        s.vizualizare()
        rez = "Lista de Filme:\n" + str(s.repo[0]) + '\n' + str(s.repo[1])
        assert s.msg == rez

    def cautare(self):
        titlu = "Rezultate cautare filme:\n"
        s = ServiceFilme()
        s.adauga((1, "Arthur", "frumos", "actiune"))
        s.adauga((2, "MamaMia", "naspa", "actiune"))
        s.cautare((1, "", "", ""))
        assert s.msg == titlu + str(s.repo[0])
        s.cautare(("", "MamaMia", "naspa", ""))
        assert s.msg == titlu + str(s.repo[1])
        s.cautare(("", "", "", "actiune"))
        assert s.msg == titlu + str(s.repo[0]) + "\n" + str(s.repo[1])

    def filtrare(self):
        s = ServiceFilme()
        s.adauga((1, "Arthur", "frumos", "actiune"))
        s.adauga((2, "MamaMia", "naspa", "actiune"))
        s.adauga((3, "Ariciul", "pentru copii", "desen"))
        s.filtrare("Ar")
        assert len(s.msg.split('\n')) == 2 * 4

    def __init__(self):
        self.ctor_get_set()
        self.adauga()
        self.stergere()
        self.modificare()
        self.vizualizare()
        self.cautare()


def runall():
    TestServiceFilme()
