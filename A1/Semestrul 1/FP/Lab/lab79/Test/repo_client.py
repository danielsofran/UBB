from Repository.client import RepositoryClient, Client


class TestRepoClient:
    def ctor_get_set(self):
        r = RepositoryClient()
        assert r.type == Client
        assert r.l == []
        try:
            r.type = int
            assert False
        except:
            pass

    def where(self):
        r = RepositoryClient()
        r.extend([Client(1, "Arthur", "142578390254"), Client(2, "Dani", "1425362788907"),
                  Client(3, "Paul", "142578390254")])
        assert len(r) == 3
        r1 = r.where(id=1)
        assert len(r1) == 1
        assert r1[0] == r[0]
        r1 = r.where(cnp="142578390254")
        assert len(r1) == 2
        assert r1[0] == r[0] and r1[1] == r[2]
        r1 = r.where(nume="Dani")
        assert len(r1) == 1
        assert r1[0] == r[1]

    def __init__(self):
        self.ctor_get_set()
        self.where()


def runall():
    TestRepoClient()
