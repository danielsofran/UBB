from Repository.generic import Repository


class TestRepo:
    def ctor_get_set(self):
        r = Repository(int)
        assert r.type == int
        assert r.l == []
        try:
            r.type = str
        except:
            pass
        else:
            raise AssertionError

    def overriden_builtins(self):
        r = Repository(int)
        assert len(r) == 0
        r.append(3)
        r.append(5)
        assert 0 not in r
        assert 3 in r
        assert len(r) == 2
        assert r[0] == 3
        r[0] = 0
        assert r[0] == 0
        s = 0
        for x in r:
            s += x
        assert s == 5
        r2 = Repository(int)
        r2.append(0)
        r2.append(5)
        assert r == r2
        r2.remove(0)
        assert r != r2
        assert r.index(0) == 0
        try:
            r.index(3)
        except:
            pass
        else:
            raise AssertionError
        r2.clear()
        assert len(r2) == 0
        assert r.pop() == 5
        assert len(r) == 1
        r2 = r.copy()
        assert r == r2
        r2[0] = 6
        assert r != r2
        try:
            r2.append(3.5)
        except:
            pass
        else:
            raise AssertionError
        r.extend([3, 5, 2, 6, 7, 2, 3, 8])
        assert len(r) == 9
        r.sort(reverse=True)
        r2.clear()
        r2.extend([8, 7, 6, 5, 3, 3, 2, 2, 0])
        assert r == r2

    def str(self):
        r2 = Repository(int)
        r2.extend([8, 7, 6, 5, 3, 3, 2, 2, 0])
        s = str(r2)
        assert len(s.split('\n')) == 9

    def where(self):
        r2 = Repository(int)
        r2.extend([8, 7, 6, 5, 3, 3, 2, 2, 0])
        try:
            r2.where(id="", descriere="", gen="actiune")
            assert False
        except NotImplementedError:
            pass
        r = r2.where(function=lambda elem: (elem % 2 == 0))
        r1 = Repository(int)
        r1.extend([8, 6, 2, 2, 0])
        assert r == r1

    def __init__(self):
        self.ctor_get_set()
        self.overriden_builtins()
        self.str()


def runall():
    TestRepo()
