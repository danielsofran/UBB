from Repository.repos import FileRepoFilm, FileRepoClient, FileRepoInchiriere
from Teste.test_repo import *


class TestFileRepoFilm(TestRepoFilm):
    def setUp(self) -> None:
        self.r = FileRepoFilm("TestFiles/test_filme.txt")
        self.l = [Film(1, "Arthur", "fain", "actiune"),
                  Film(2, "Madagascar", "5 stele", "desen animat"),
                  Film(3, "Pinguini", "naspa", "documentar")]

    def tearDown(self) -> None:
        for elem in self.r.get_all():
            self.r.stergere(elem.id)


class TestFileRepoClient(TestRepoClient):
    def setUp(self) -> None:
        self.r = FileRepoClient("TestFiles/test_clienti.txt")
        self.l = [Client(1, "Arthur", "3452678345264"),
                  Client(2, "Daniel", "2343526738920"),
                  Client(3, "Marcus", "1425362783990")]

    def tearDown(self) -> None:
        for elem in self.r.get_all():
            self.r.stergere(elem.id)


class TestFileRepoInchirieriDto(TestRepoInchirieriDto):
    def setUp(self) -> None:
        self.r = FileRepoInchiriere("TestFiles/test_inchirieri.txt")
        self.l = [InchiriereDto(1, 2),
                  InchiriereDto(1, 3),
                  InchiriereDto(2, 3)]

    def tearDown(self) -> None:
        for elem in self.r.get_all():
            self.r.stergere(elem)


if __name__ == '__main__':
    unittest.main()
