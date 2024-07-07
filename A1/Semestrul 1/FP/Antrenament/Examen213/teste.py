import os

from Domain.emisiune import Emisiune
from Repository.repository import Repository
from Service.service import Service
from exceptii import RepoException, ServiceException


def test_repo():
    with open("test.txt", "w") as f:
        with open("file.txt") as ft:
            f.write(ft.read(1000000000))
    r = Repository("test.txt")
    r.stergere(Emisiune("Jurnal", "stiri", 0, "3"))
    assert len(r) == 4
    r.modificare(Emisiune("Reportaj", "informare", 3, "viata la oras"))
    for elem in r:
        assert elem.durata == 3
        assert elem.descriere == "viata la oras"
        break

    os.remove("test.txt")

def create_srv():
    with open("test.txt", "w") as f:
        with open("file.txt") as ft:
            f.write(ft.read(1000000000))
    r = Repository("test.txt")
    s = Service(r)
    return r, s

def test_stergere(r, s):
    assert len(r) == 5
    s.stergere("Jurnal", "stiri")
    assert len(r) == 4
    try:
        s.stergere("1", "2")
    except: assert len(r) == 4
    else: assert False

def test_modificare(r, s):
    assert len(r) == 4
    s.modificare("Reportaj", "informare", 2, "informatii")
    for elem in r:
        assert elem.durata==2
        break
    try: s.modificare("1", "2", 3, "4")
    except RepoException: pass
    else: assert False

def test_program(r, s):
    l = s.program(1, 3)
    assert s.ore(l) == 2
    s.stergere("Divertisment", "Film")
    try: l=s.program(1, 2)
    except ServiceException: pass
    else: assert False

def test_blocare(r, s):
    s.blocheaza("informare")
    try: s.stergere("Reportaj", "informare")
    except: pass
    else: assert False

def test():
    test_repo()
    r, s = create_srv()
    test_stergere(r, s)
    test_modificare(r, s)
    test_program(r, s)
    test_blocare(r,s)

if __name__ == "__main__":
    test()
