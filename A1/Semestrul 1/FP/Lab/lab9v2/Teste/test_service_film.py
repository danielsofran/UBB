import unittest
from random import randint

from Exceptii.exceptions import *
from Domain.film import Film
from Repository.repos import RepoFilm
from Service.servicefilm import ServiceFilm

class TestServiceFilm(unittest.TestCase):
    def setUp(self) -> None:
        self.l = [Film(1, "Arthur", "fain", "actiune"),
                  Film(2, "Madagascar", "5 stele", "desen animat"),
                  Film(3, "Pinguini", "naspa", "documentar")]
        self.r = RepoFilm()
        self.s = ServiceFilm(self.r)

    def test_adauga(self):
        self.s.adauga(self.l[0].id, self.l[0].titlu, self.l[0].descriere, self.l[0].gen)
        self.assertEqual(len(self.r), 1)
        self.assertRaises(ValidationError, self.s.adauga, -1, "a", "b", "c")
        self.assertRaises(ValidationError, self.s.adauga, 1, "", "b", "c")
        self.assertRaises(ValidationError, self.s.adauga, 1, "a", "", "c")
        self.assertRaises(ValidationError, self.s.adauga, 1, "a", "b", "")
        self.assertRaises(ValidationError, self.s.adauga, 0, "", "", "")

    def test_modificare(self):
        for i in range(3):
            self.r.adauga(self.l[i])
        self.assertEqual(len(self.r), 3)
        self.s.modificare(3, "Mamamia", "ok", "italian")
        self.assertEqual(self.r.get_all()[-1], Film(3, "Mamamia", "ok", "italian"))
        self.assertRaises(ValidationError, self.s.modificare, -1, "a", "b", "c")
        self.assertRaises(ValidationError, self.s.modificare, 2, "", "", "")
        self.assertRaises(RepositoryError, self.s.modificare, 4, "a", "b", "c")

    def test_stergere(self):
        for i in range(3):
            self.r.adauga(self.l[i])
        self.assertEqual(len(self.r), 3)
        self.s.stergere(2)
        self.assertEqual(len(self.r), 2)
        self.assertRaises(ValidationError, self.s.stergere, -1)
        self.assertRaises(RepositoryError, self.s.stergere, 4)

        self.s.stergere(1)
        self.s.stergere(3)
        self.assertEqual(len(self.r), 0)
        self.assertRaises(RepositoryError, self.s.stergere, 1)

    def test_cauta_id(self):
        for i in range(3):
            self.r.adauga(self.l[i])
        self.assertEqual(len(self.r), 3)
        f = self.s.cautare(2)
        self.assertEqual(f, self.l[1])
        self.s.stergere(2)
        self.assertRaises(ServiceError, self.s.cautare, 2)
        self.assertRaises(ValidationError, self.s.cautare, -1)
        f = self.s.cautare(1)
        self.assertEqual(f, self.l[0])

    def test_catua_titlu(self):
        for i in range(3):
            self.r.adauga(self.l[i])
        self.assertEqual(len(self.r), 3)
        f = self.s.cautare_titlu(self.l[0].titlu)
        self.assertEqual(f[0], self.l[0])
        self.s.adauga(4, self.l[0].titlu, "b", "c")
        f = self.s.cautare_titlu(self.l[0].titlu)
        self.assertEqual(f[1].titlu, self.l[0].titlu)
        self.assertRaises(ValidationError, self.s.cautare_titlu, "")
        self.assertRaises(ServiceError, self.s.cautare_titlu, "ab_c")

    def test_cauta_descriere(self):
        for i in range(3):
            self.r.adauga(self.l[i])
        self.assertEqual(len(self.r), 3)
        f = self.s.cautare_descriere(self.l[0].descriere)
        self.assertEqual(f[0], self.l[0])
        self.s.adauga(4, 'a', self.l[0].descriere, "c")
        f = self.s.cautare_descriere(self.l[0].descriere)
        self.assertEqual(f[1].descriere, self.l[0].descriere)
        self.assertRaises(ValidationError, self.s.cautare_descriere, "")
        self.assertRaises(ServiceError, self.s.cautare_descriere, "ab_c")

    def test_catua_gen(self):
        for i in range(3):
            self.r.adauga(self.l[i])
        self.assertEqual(len(self.r), 3)
        f = self.s.cautare_gen(self.l[0].gen)
        self.assertEqual(f[0], self.l[0])
        self.s.adauga(4, 'a', "b", self.l[0].gen)
        f = self.s.cautare_gen(self.l[0].gen)
        self.assertEqual(f[1].gen, self.l[0].gen)
        self.assertRaises(ValidationError, self.s.cautare_gen, "")
        self.assertRaises(ServiceError, self.s.cautare_gen, "ab_c")

    def test_vizualizare(self):
        for i in range(3):
            self.r.adauga(self.l[i])
        self.assertEqual(len(self.r), 3)
        self.assertEqual(self.s.vizualizare(), self.l)

    def test_genereaza(self):
        sum = 0
        for i in range(1+randint(1, 10)):
            sum += i
            self.s.generate_random(i)
            self.assertEqual(len(self.r), sum)

    def test_dortare_multipla(self):
        self.s.adauga(2, "A", "Ab", "b")
        self.s.adauga(1, "A", "A", "b")
        self.s.adauga(3, "Ab", "A", "b")
        l = self.s.sortare_dupa_titlu_sau_descriere()
        assert len(l) == 3
        assert l[0].titlu == "A"
        assert l[1].titlu == "A"
        assert l[1].descriere == "Ab"
        assert l[2].titlu == "Ab"

if __name__ == '__main__':
    unittest.main()
