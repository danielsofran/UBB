from Repository.film import RepositoryFilm, Film


class TestRepoFilm:
    def ctor_get_set(self):
        r = RepositoryFilm()
        assert r.type == Film
        assert r.l == []
        try:
            r.type = int
            assert False
        except:
            pass

    def where(self):
        r = RepositoryFilm()
        r.extend([Film(1, "Arthur", "fain", "actiune"), Film(2, "Arthur", "fain", "altele"),
                  Film(3, "MamaMia", "italian", "actiune")])
        assert len(r) == 3
        r1 = r.where(id=1)
        assert len(r1) == 1
        assert r1[0] == r[0]
        r1 = r.where(titlu="Arthur", descriere="fain")
        assert len(r1) == 2
        assert r1[0] == r[0] and r1[1] == r[1]
        r1 = r.where(gen="actiune")
        assert len(r1) == 2
        assert r1[0] == r[0] and r1[1] == r[2]

    def __init__(self):
        self.ctor_get_set()
        self.where()


def runall():
    TestRepoFilm()
