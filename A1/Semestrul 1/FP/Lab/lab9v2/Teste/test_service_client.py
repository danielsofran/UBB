import unittest
from random import randint

from Exceptii.exceptions import *
from Domain.client import Client
from Repository.repos import RepoClient
from Service.serviceclient import ServiceClient

class TestServiceClient(unittest.TestCase):
    def setUp(self) -> None:
        self.l = [Client(1, "Arthur", "3452637890243"),
                  Client(2, "Madagascar", "5453627389456"),
                  Client(3, "Pinguini", "453627364598")]
        self.r = RepoClient()
        self.s = ServiceClient(self.r)

    def test_adauga(self):
        self.s.adauga(self.l[0].id, self.l[0].nume, self.l[0].cnp)
        self.assertEqual(len(self.r), 1)
        self.assertRaises(ValidationError, self.s.adauga, -1, "a", self.l[0].cnp)
        self.assertRaises(ValidationError, self.s.adauga, 1, "", self.l[0].cnp)
        self.assertRaises(ValidationError, self.s.adauga, 1, "a", "")
        self.assertRaises(ValidationError, self.s.adauga, 1, "a", "345263748934")
        self.assertRaises(ValidationError, self.s.adauga, 1, "a", "04526334748934")
        self.assertRaises(ValidationError, self.s.adauga, 0, "", "")

    def test_modificare(self):
        for i in range(3):
            self.r.adauga(self.l[i])
        self.assertEqual(len(self.r), 3)
        self.s.modificare(3, "Mamamia", "4536209856743")
        self.assertEqual(self.r.get_all()[-1], Client(3, "Mamamia", "4536209856743"))
        self.assertRaises(ValidationError, self.s.modificare, -1, "a", self.l[0].cnp)
        self.assertRaises(ValidationError, self.s.modificare, 2, "", "")
        self.assertRaises(RepositoryError, self.s.modificare, 4, "a", self.l[0].cnp)

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

    def test_catua_nume(self):
        for i in range(3):
            self.r.adauga(self.l[i])
        self.assertEqual(len(self.r), 3)
        f = self.s.cautare_nume(self.l[0].nume)
        self.assertEqual(f[0], self.l[0])
        self.s.adauga(4, self.l[0].nume, self.l[0].cnp)
        f = self.s.cautare_nume(self.l[0].nume)
        self.assertEqual(f[1].nume, self.l[0].nume)
        self.assertRaises(ValidationError, self.s.cautare_nume, "")
        self.assertRaises(ServiceError, self.s.cautare_nume, "ab_c")

    def test_cauta_cnp(self):
        for i in range(3):
            self.r.adauga(self.l[i])
        self.assertEqual(len(self.r), 3)
        f = self.s.cautare_cnp(self.l[0].cnp)
        self.assertEqual(f[0], self.l[0])
        self.s.adauga(4, 'a', self.l[0].cnp)
        f = self.s.cautare_cnp(self.l[0].cnp)
        self.assertEqual(f[1].cnp, self.l[0].cnp)
        self.assertRaises(ValidationError, self.s.cautare_cnp, "35463789205600")
        self.assertRaises(ServiceError, self.s.cautare_cnp, "3546378920565")

    def test_vizualizare(self): # metoda testata black box
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

if __name__ == '__main__':
    unittest.main()
