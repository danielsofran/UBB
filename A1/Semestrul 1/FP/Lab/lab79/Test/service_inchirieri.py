from Controller.serviceinchirieri import ServiceInchirieri, RepositoryFilm, RepositoryClient, Inchiriere
from Controller.servicefilmeclienti import ServiceFilme, ServiceClienti, Film, Client
from UI.handlerRapoarte import HandlerRapoarte


class TestInchirieri:
    def inchiriere(self):
        f = Film(1, "Arthur", "fain", "actiune")
        c = Client(1, "Daniel", "3664738890223")
        sf = ServiceFilme()
        sc = ServiceClienti()
        sf.adauga((1, "Arthur", "fain", "actiune"))
        sc.adauga((1, "Daniel", "3664738890223"))
        s = ServiceInchirieri(sf.repo, sc.repo)
        s.adauga(Inchiriere(sc.repo[0], sf.repo[0]))
        assert len(s.repo) == 1
        sc.sterge((1, "Daniel", "3664738890223"))

        # sc.modificare((1, "Daniel", "3664738890223"), (1, "Daniel", "1111111111111"))
        # sf.modificare((1, "Arthur", "fain", "actiune"), (1, "Arthur", "fain", "drama"))
        h = HandlerRapoarte(s)
        # h.r1()

    def __init__(self):
        self.inchiriere()


def runall():
    TestInchirieri()
