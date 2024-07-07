import os

from Domain.carte import Carte
from Repository.filerepo import FileRepository
from Repository.history import History
from Repository.repository import Repository
from Service.service import Service
from Validator.validator import Validator
from exceptii import RepoException, MyException, ValidationException


class Teste:
    l = [Carte(1, "Alice", "Artur", 1930),
         Carte(2, "a", "b", 3),
         Carte(3, "a", "b", 4)]
    def test_carte(self): # testez domeniul
        carte = Carte(1, "Alice", "Artur", 1980)
        assert carte.id == 1
        assert carte.autor == "Artur"
        assert carte.titlu == "Alice"
        assert carte.anAp == 1980
        carte2 = Carte(1, "a", "b", 3)
        carte3 = Carte(2, "a", "b", 4)
        assert carte == carte2
        assert carte2 != carte3
        assert str(carte2) == "id: 1, titlu: a, autor: b, an aparitie: 3"
        assert repr(carte2) == "1~a~b~3"
        assert Carte.fromStr("2~a~b~4") == carte3

    def test_validator(self): # testez validatorul
        # corect
        carte = Carte(1, "Alice", "Artur", 1980)
        Validator.valideaza(carte)
        # id gresit
        carte = Carte(-1, "Alice", "Artur", 1980)
        try: Validator.valideaza(carte)
        except ValidationException: pass
        else: assert False
        # titlu gresit
        carte = Carte(1, "", "Artur", 1980)
        try:
            Validator.valideaza(carte)
        except ValidationException:
            pass
        else:
            assert False
        # autor gresit
        carte = Carte(1, "Alice", "", 1980)
        try:
            Validator.valideaza(carte)
        except ValidationException:
            pass
        else:
            assert False
        # an gresit
        carte = Carte(1, "Alice", "Artur", -100)
        try:
            Validator.valideaza(carte)
        except ValidationException:
            pass
        else:
            assert False

    def create_repo_service(self, am_continut=False, continut=None, name="test.txt"):
        """
        :param am_continut: daca sa introduc sau nu date in el
        :param name: numele fisierului de test
        :return: repo-ul si service-ul
        creeaza un fisier cu un continut de test si repo-ul de fisiere si service-ul respectiv
        """
        if continut == None:
            continut = Teste.l[:]

        # lista de carti
        with open(name, "w") as f:
            if am_continut:
                for carte in continut:
                    f.write(repr(carte)+"\n")
        repo = FileRepository(name)
        return repo, Service(repo)

    def test_repo(self, repo): # testez clasa repo memorie de baza, repo - un repository
        # agauga, len, iter
        assert len(repo) == 0
        repo.adauga(Teste.l[0])
        assert len(repo) == 1
        for elem in repo:
            assert elem == Teste.l[0]
        try: repo.adauga(Carte(1, "Alice", "Artur", 1930))
        except RepoException: pass
        else: assert False
        repo.adauga(Teste.l[1])
        assert len(repo) == 2
        repo.adauga(Teste.l[2])
        assert len(repo) == 3
        # stergere
        repo.stergere(Carte(1, "Alice", "Artur", 1930))
        assert len(repo) == 2
        for elem in repo: # testam daca primul element e cel corect
            assert elem == Teste.l[1]
            break

    def test_repo_history(self): # testez repo-ul din fisier
        h = History()
        # l presupunem ca e ultima lista stearsa
        assert len(h) == 0
        try: h.pop()
        except RepoException: pass
        else: assert False
        h.adauga(Teste.l[:2])
        assert len(h) == 1
        h.adauga(Teste.l[:])
        assert len(h) == 2
        assert h.pop() == Teste.l

    def test_service_adauga(self, repo, service): # testez adaugarea
        assert len(repo) == 3
        service.adauga(100, Teste.l[0].titlu, Teste.l[0].autor, Teste.l[0].anAp)
        assert len(repo) == 4
        try:
            service.adauga(Teste.l[0].id, Teste.l[0].titlu, Teste.l[0].autor, Teste.l[0].anAp)
        except RepoException:
            pass
        else:
            assert False

    def test_service_stergere(self, repo, service): # test stergere
        service.stergere("0")  # se sterg prima si ultima carte din repo
        assert len(repo) == 2
        for elem in repo:  # verific primul element ramas
            assert elem == Teste.l[1]
            break
        service.stergere("7")
        assert len(repo) == 2  # nici un elem sters

        # greseli de introducere a cifrei
        try:
            service.stergere("12")
        except MyException:
            pass
        else:
            assert False
        try:
            service.stergere("a")
        except MyException:
            pass
        else:
            assert False
        try:
            service.stergere("")
        except MyException:
            pass
        else:
            assert False

    def test_filtrare(self, repo, service):
        # e alta ordine fata de campul static
        l = [Carte(2, "a", "b", 3),
             Carte(3, "a", "b", 4),
             Carte(1, "Alice", "Artur", 1980)]

        # test set filtru
        repo.adauga(l[2]) # agaugam elem sters anterior
        assert len(repo) == 3
        assert l == service.filtrare() # nici un filtru
        service.set_filtrare("a", 5) # 2 filtre
        assert service.filtru == "filtru titlu: a\nfiltru an: 5\n"
        assert service.filtrare() == l[:2]
        service.set_filtrare("", 4) # doar filtru an
        assert service.filtrare() == [l[0]]
        service.set_filtrare("li", -1) # doar filtru titlu
        assert service.filtrare() == [l[2]]

    def test_undo(self, repo, service): # testeaza functionalitatea de undo
        # undo la nimic
        try: service.undo()
        except MyException: pass
        else: assert False

        service.stergere("3") # se sterg primele 2
        assert len(repo) == 1
        for elem in repo:
            assert elem == Teste.l[2] # ultimul din lista ramane
        service.stergere("7") # nu se sterge nmk
        assert len(repo) == 1

    def test_service(self, repo, service):
        self.test_service_adauga(repo, service)
        self.test_service_stergere(repo, service)
        self.test_filtrare(repo, service)

    def __init__(self): # rulez toate testele
        """
        functia creeaza fisiere text.txt pe care il foloseste ca suport la repository de fisiere
        fisierele creeate sunt sterse la terminarea unui/unor teste
        """
        # domeniu
        self.test_carte()

        # validator
        self.test_validator()

        #repository
        repo = Repository()
        self.test_repo(repo)
        filerepo, service = self.create_repo_service()
        self.test_repo(filerepo)
        os.remove("test.txt")
        self.test_repo_history()

        # service
        filerepo, service = self.create_repo_service(True)
        self.test_service(filerepo, service)
        os.remove("test.txt")
        l = [Carte(1, "Alice", "Artur", 1930),
             Carte(2, "a", "b", 3),
             Carte(3, "a", "b", 4)]
        filerepo, service = self.create_repo_service(True, continut=l)
        self.test_undo(filerepo, service)
        os.remove("test.txt")

if __name__ == "__main__": # daca proiectul ruleaza acest fisier ca fisier principal
    Teste()                # rulez testele
