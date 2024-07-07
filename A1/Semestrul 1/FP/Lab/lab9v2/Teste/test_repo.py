import unittest
from Exceptii.exceptions import *
from Domain.film import Film
from Domain.client import Client
from Domain.inchirieredto import InchiriereDto
from Repository.repos import RepoFilm, RepoClient, RepoInchiriere


class TestRepoFilm(unittest.TestCase):
    def setUp(self) -> None:
        self.r = RepoFilm()
        self.l = [Film(1, "Arthur", "fain", "actiune"),
                  Film(2, "Madagascar", "5 stele", "desen animat"),
                  Film(3, "Pinguini", "naspa", "documentar")]

    def test_adauga(self):
        self.assertEqual(len(self.r), 0)
        self.r.adauga(self.l[0])
        self.assertEqual(len(self.r), 1)
        self.assertEqual(self.r.get_all()[0], self.l[0])
        self.assertRaises(DuplicatedIDError, self.r.adauga, self.l[0])
        self.assertRaises(TypeError, self.r.adauga, 1)

    def test_modifica(self):
        self.r.adauga(self.l[0])
        self.r.modificare(1, self.l[1])
        self.assertEqual(len(self.r), 1)
        self.assertEqual(self.r.get_all()[0], self.l[1])

        self.r.adauga(self.l[0])
        self.assertEqual(len(self.r), 2)
        self.assertRaises(DuplicatedIDError, self.r.modificare, 1, self.l[1])
        self.assertRaises(DuplicatedIDError, self.r.modificare, 10, self.l[1])
        self.assertRaises(RepositoryError, self.r.modificare, 10, self.l[2])

    def test_stergere(self):
        self.r.adauga(self.l[0])
        self.r.stergere(1)
        self.assertEqual(len(self.r), 0)
        self.assertRaises(RepositoryError, self.r.stergere, 1)

    def test_cauta(self):
        for obj in self.l:
            self.r.adauga(obj)
        self.assertEqual(self.r.cauta_id(1), self.l[0])
        self.assertEqual(self.r.cauta_id(2), self.l[1])
        self.assertEqual(self.r.cauta_id(3), self.l[2])
        self.assertRaises(RepositoryError, self.r.cauta_id, 4)

class TestRepoClient(unittest.TestCase):
    def setUp(self) -> None:
        self.r = RepoClient()
        self.l = [Client(1, "Arthur", "3452678345264"),
                  Client(2, "Daniel", "2343526738920"),
                  Client(3, "Marcus", "1425362783990")]

    def test_adauga(self):
        self.assertEqual(len(self.r), 0)
        self.r.adauga(self.l[0])
        self.assertEqual(len(self.r), 1)
        self.assertEqual(self.r.get_all()[0], self.l[0])
        self.assertRaises(DuplicatedIDError, self.r.adauga, self.l[0])
        self.assertRaises(TypeError, self.r.adauga, 1)

    def test_modifica(self):
        self.r.adauga(self.l[0])
        self.r.modificare(1, self.l[1])
        self.assertEqual(len(self.r), 1)
        self.assertEqual(self.r.get_all()[0], self.l[1])

        self.r.adauga(self.l[0])
        self.assertEqual(len(self.r), 2)
        self.assertRaises(DuplicatedIDError, self.r.modificare, 1, self.l[1])
        self.assertRaises(DuplicatedIDError, self.r.modificare, 10, self.l[1])
        self.assertRaises(RepositoryError, self.r.modificare, 10, self.l[2])

    def test_stergere(self):
        self.r.adauga(self.l[0])
        self.r.stergere(1)
        self.assertEqual(len(self.r), 0)
        self.assertRaises(RepositoryError, self.r.stergere, 1)

    def test_cauta(self):
        for obj in self.l:
            self.r.adauga(obj)
        self.assertEqual(self.r.cauta_id(1), self.l[0])
        self.assertEqual(self.r.cauta_id(2), self.l[1])
        self.assertEqual(self.r.cauta_id(3), self.l[2])
        self.assertRaises(RepositoryError, self.r.cauta_id, 4)

class TestRepoInchirieriDto(unittest.TestCase):
    def setUp(self) -> None:
        self.r = RepoInchiriere()
        self.l = [InchiriereDto(1, 2),
                  InchiriereDto(1, 3),
                  InchiriereDto(2, 3)]

    def test_adauga(self):
        self.assertEqual(len(self.r), 0)
        self.r.adauga(self.l[0])
        self.assertEqual(len(self.r.get_all()), 1)
        self.assertEqual(self.r.get_all()[0], self.l[0])
        self.assertRaises(DuplicatedIDError, self.r.adauga, InchiriereDto(1, 2))
        self.assertRaises(TypeError, self.r.adauga, 1)

    def test_modifica(self):
        self.r.adauga(self.l[0])
        self.r.modificare(self.l[0].id, self.l[1])
        self.assertEqual(len(self.r), 1)
        self.assertEqual(self.r.get_all()[0], self.l[1])

        self.r.adauga(self.l[0])
        self.assertEqual(len(self.r), 2)
        self.assertRaises(DuplicatedIDError, self.r.modificare, self.l[0].id, self.l[1])
        self.assertRaises(DuplicatedIDError, self.r.modificare, 10, self.l[1])
        self.assertRaises(RepositoryError, self.r.modificare, 10, self.l[2])

    def test_stergere(self):
        self.r.adauga(self.l[0])
        self.r.stergere(self.l[0])
        self.assertEqual(len(self.r), 0)
        self.assertRaises(RepositoryError, self.r.stergere, 1)

    def test_cauta(self):
        for obj in self.l:
            self.r.adauga(obj)
        self.assertEqual(self.r.cauta_id(self.l[0].id), self.l[0])
        self.assertEqual(self.r.cauta_id(self.l[1].id), self.l[1])
        self.assertEqual(self.r.cauta_id(self.l[2].id), self.l[2])
        self.assertRaises(RepositoryError, self.r.cauta_id, 4)

if __name__ == '__main__':
    unittest.main()
