from Domain.produs import Produs
from Repository.repository import Repository, FileRepository
from Service.service import Service
from Validator.validator import Validator, ValidatorException
from os import remove

def test_validator_id(): # testeaza validatorul de id
    try: Validator.id(-2)
    except ValidatorException: pass
    else: assert False
    try: Validator.id(-764)
    except ValidatorException: pass
    else: assert False
    try: Validator.id(2)
    except ValidatorException: assert False
    try: Validator.id(23)
    except ValidatorException: assert False
    try: Validator.id(1)
    except ValidatorException: assert False

def test_validator_denumire(): # testeaza validatorul de denumire
    try: Validator.denumire("12,")
    except ValidatorException: pass
    else: assert False
    try: Validator.denumire("baba negra")
    except ValidatorException: assert False

def test_validator_pret(): # testeaza validatorul de pret
    try: Validator.pret(-12)
    except ValidatorException: pass
    else: assert False
    try: Validator.pret(0)
    except ValidatorException: pass
    else: assert False
    try: Validator.pret(12)
    except ValidatorException: assert False

def test_validator_prod(): # testeaza validatorul de produs
    try: Validator.produs(Produs(-1, "textil", 10))
    except ValidatorException: pass
    else: assert False
    try: Validator.produs(Produs(1, "te....xtil", 10))
    except ValidatorException: pass
    else: assert False
    try: Validator.produs(Produs(1, "textil", -10))
    except ValidatorException: pass
    else: assert False
    try: Validator.produs(Produs(1, "textil", 10))
    except ValidatorException: pass

def test_repo_mem(): # testeaza clasa de baza repository (in memorie)
    r = Repository()
    # adauga
    r.adauga(Produs(1, "textil", 10))
    # len
    assert len(r) == 1
    # iter
    for elem in r:
        assert elem == Produs(1, "textil", 10)
    # adauga cu duplicat
    try: r.adauga(Produs(1, "textil", 10))
    except: pass
    assert len(r) == 1
    # sterge inexistent
    try: r.stergere(Produs(2, "textile", 10))
    except: pass
    assert len(r) == 1
    # sterge existent
    r.stergere(Produs(1, "textil", 10))
    assert len(r) == 0

def test_file_repo(): # testeaza repository-ul in fisiere
    # creez un fisier
    filename = "test.txt"
    prods = [Produs(1, "textil", 10), Produs(2, "mancare", 30), Produs(4, "laptop", 1000)]
    with open(filename, "w") as f:
        for prod in prods:
            f.write(repr(prod)+"\n")
    # testez
    r = FileRepository(filename)
    assert len(r) == 3
    r.adauga(Produs(3, "lama", 1))
    prods.append(Produs(3, "lama", 1))
    assert len(r) == 4
    i = 0
    for elem in r:
        assert elem == prods[i]
        i += 1
    r.stergere(Produs(3, "lama", 1))
    prods.remove(Produs(3, "lama", 1))
    assert len(r) == 3
    # sterg fisierul
    remove(filename)

def create_srv(): # creeaza un repo, un service si un nume de fisier
    filename = "test.txt"
    with open(filename, "w") as f:
        pass
    r = FileRepository(filename)
    s = Service(r)
    return filename, r, s

def test_srv_adauga(r, s): # testeaza adaugarea
    # adauga
    s.adauga(1, "textile", 31)
    assert len(r) == 1
    s.adauga(2, "mancare", 41)
    assert len(r) == 2
    s.adauga(3, "utile", 33)
    assert len(r) == 3
    # greseli
    try:
        s.adauga(2, "mancare", 41)
    except:
        pass
    assert len(r) == 3
    try:
        s.adauga(-2, "man,care", 41)
    except:
        pass
    assert len(r) == 3

def test_srv_stergere(r, s): # testeaza stergerea
    # sterge
    # greseli
    try:
        s.sterge("a")
    except:
        pass
    assert len(r) == 3
    try:
        s.sterge("12")
    except:
        pass
    assert len(r) == 3
    try:
        s.sterge("")
    except:
        pass
    assert len(r) == 3
    # ok
    assert 2 == s.sterge("3")
    try:
        s.adauga(1, "textile", 31)
        s.adauga(2, "mancare", 41)
        s.adauga(3, "utile", 33)
    except:
        pass
    assert 1 == s.sterge("4")
    try:
        s.adauga(1, "textile", 31)
        s.adauga(2, "mancare", 41)
        s.adauga(3, "utile", 33)
    except:
        pass
    assert 0 == s.sterge("5")

def test_srv_filtrare(r, s): # testeaza filtrarea
    l = [Produs(1, "textile", 31), Produs(2, "mancare", 41), Produs(3, "utile", 33)]
    assert s.filtrare("", -1) == l
    assert s.filtrare("le", -1) == [l[0], l[2]]
    assert s.filtrare("", 40) == [l[0], l[2]]
    assert s.filtrare("le", 40) == [l[0], l[2]]

def test_undo(r, s): # testeaza undo-ul
    s.sterge("1")
    assert len(r) == 1
    s.undo()
    assert len(r) == 3


def test_service(): # testeaza fct din service
    filename, r, s = create_srv()
    test_srv_adauga(r, s)
    test_srv_stergere(r, s)
    r.stergere(Produs(1, "textile", 31))
    s.adauga(1, "textile", 31)
    s.adauga(2, "mancare", 41)
    s.adauga(3, "utile", 33)
    test_srv_filtrare(r, s)
    test_undo(r, s)
    remove(filename)



def test(): # testeaza tot programul
    test_validator_id()
    test_validator_denumire()
    test_validator_pret()
    test_validator_prod()
    test_repo_mem()
    test_file_repo()
    test_service()

if __name__ == "__main__": # rulez testele
    test()
