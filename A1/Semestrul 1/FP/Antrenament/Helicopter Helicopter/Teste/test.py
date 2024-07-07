from Domain.helicopter import Helicopter
from Repository.repository import RepoHelicopter
from Service.service import ServiceHelicopter

class Test:
    def test_helicopter(self):  # test domain
        h1 = Helicopter(1, "a", "b", 2000)
        assert h1.id == 1
        assert h1.nume == 'a'
        assert h1.scopuri == 'b'
        assert h1.an == 2000
        sir = "Elicopterul 1 'a', scopurile: b, anul 2000"
        assert str(h1) == sir
        assert Helicopter.fromStr("1,a,b,2000").scopuri == h1.scopuri

    def test_repo(self):  # test repo
        repo = RepoHelicopter("Teste/test.txt")
        assert len(repo.get_all())
        assert repo.get_all()[1].scopuri == "b a"

    def test_service_elicoptere_cu_scop(self):  # test service functionalitatea 1
        repo = RepoHelicopter("Teste/test.txt")
        service = ServiceHelicopter(repo)
        elicoptere = service.elicoptere_cu_scop_dat("b")
        assert len(elicoptere) == 2
        elicoptere = service.elicoptere_cu_scop_dat("a")
        assert len(elicoptere) == 1
        elicoptere = service.elicoptere_cu_scop_dat("aaa")
        assert len(elicoptere) == 0

    def test_service_grupare(self):  # teste service functionalitatea 2
        repo = RepoHelicopter("Teste/test.txt")
        service = ServiceHelicopter(repo)
        grupare_pe_scopuri = service.grupare_ani_pe_scopuri()
        assert len(grupare_pe_scopuri) == 2
        assert grupare_pe_scopuri.keys() == {"a", "b"}
        assert grupare_pe_scopuri['a'] == [1990]
        assert grupare_pe_scopuri['b'] == [2000, 1990]

    def run_all(self):  # rulam toate testele
        self.test_helicopter()
        self.test_repo()
        self.test_service_grupare()
        self.test_service_elicoptere_cu_scop()
