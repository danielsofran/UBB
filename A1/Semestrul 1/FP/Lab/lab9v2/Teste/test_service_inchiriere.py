import unittest
from Exceptii.exceptions import *
from Domain.film import Film
from Repository.repos import RepoFilm
from Service.servicefilm import ServiceFilm
from Domain.client import Client
from Repository.repos import RepoClient
from Service.serviceclient import ServiceClient
from Domain.inchirieredto import InchiriereDto
from Repository.repos import RepoInchiriere
from Service.serviceinchiriere import ServiceInchiriere


class TestServiceInchiriere(unittest.TestCase):
    def setUp(self) -> None:
        self.lf = [Film(1, "Arthur", "fain", "actiune"),
                  Film(2, "Madagascar", "5 stele", "desen animat"),
                  Film(3, "Pinguini", "naspa", "documentar")]
        self.rf = RepoFilm()
        for film in self.lf:
            self.rf.adauga(film)
        self.sf = ServiceFilm(self.rf)
        self.lc = [Client(1, "Andrei", "3452637890243"),
                  Client(2, "Daniel", "5453627389456"),
                  Client(3, "Paul", "453627364598")]
        self.rc = RepoClient()
        for client in self.lc:
            self.rc.adauga(client)
        self.sc = ServiceClient(self.rc)
        self.li = [InchiriereDto(1, 1),
                   InchiriereDto(1, 2),
                   InchiriereDto(1, 3),
                   InchiriereDto(2, 2),
                   InchiriereDto(2, 3),
                   InchiriereDto(3, 3)]
        self.ri = RepoInchiriere()
        self.si = ServiceInchiriere(self.ri, self.rc, self.rf)

    def test_inchiriaza(self):
        for i in range(1, len(self.rc)+1):
            for j in range(1, len(self.rf)+1):
                self.si.inchiriaza(i, j)
        self.assertEqual(len(self.ri), 9)
        self.assertRaises(DuplicatedIDError, self.si.inchiriaza, 1, 1)
        self.assertRaises(RepositoryError, self.si.inchiriaza, -1, 0)
        self.assertEqual(len(self.ri), 9)

    def test_returneaza(self):
        for i in range(1, len(self.rc) + 1):
            for j in range(1, len(self.rf) + 1):
                self.si.inchiriaza(i, j)
        self.assertEqual(len(self.ri), 9)
        self.si.returneaza(1, 3)
        self.assertEqual(len(self.ri), 8)
        self.assertRaises(RepositoryError, self.si.returneaza, 1, 3)

    def test_raport_11(self):
        for i in range(1, len(self.rc) + 1):
            for j in range(i, len(self.rf) + 1):
                self.si.inchiriaza(i, j)
        self.assertEqual(len(self.ri), 6)
        d = self.si.raport_clienti_cu_filme_dupa_nume()
        excpected_d = {self.lc[0]: [self.lf[0], self.lf[1], self.lf[2]],
                       self.lc[1]: [self.lf[1], self.lf[2]],
                       self.lc[2]: [self.lf[2]]}
        self.assertEqual(d, excpected_d)

    def test_raport_12(self):
        for i in range(1, len(self.rc) + 1):
            for j in range(i, len(self.rf) + 1):
                self.si.inchiriaza(i, j)
        self.assertEqual(len(self.ri), 6)
        d = self.si.raport_clienti_cu_filme_dupa_nr_filmelor()
        excpected_d = {self.lc[2]: [self.lf[2]],
                       self.lc[1]: [self.lf[1], self.lf[2]],
                       self.lc[0]: [self.lf[0], self.lf[1], self.lf[2]]}
        self.assertEqual(d, excpected_d)

    def test_raport_2(self):
        for i in range(1, len(self.rc) + 1):
            for j in range(i, len(self.rf) + 1):
                self.si.inchiriaza(i, j)
        self.assertEqual(len(self.ri), 6)
        d = self.si.raport_filme_inchiriate()
        self.lf.reverse()
        for i in range(len(d)):
            self.assertEqual(d[i], self.lf[i])

    def test_raport_3(self):
        for i in range(1, len(self.rc) + 1):
            for j in range(i, len(self.rf) + 1):
                self.si.inchiriaza(i, j)
        self.assertEqual(len(self.ri), 6)
        d = self.si.raport_primii_clienti_cu_cele_mai_multe_filme()
        self.assertEqual(len(d), 1)
        dnume, dnr = d[0]
        self.assertEqual(dnume, self.lc[0].nume)
        self.assertEqual(dnr, 3)

    def test_raport_4(self):
        self.sf.adauga(4, "Abis", "a", "b")
        self.sf.adauga(5, "Aba", "c", "d")
        self.sf.adauga(6, "Abanos", "e", "f")
        self.sf.adauga(7, "Abai", "g", "h")
        self.sc.adauga(4, "Ninja", "3429997067543")
        self.assertEqual(len(self.rf), 7)
        self.assertEqual(len(self.rc), 4)
        self.si.inchiriaza(1, 4)
        self.si.inchiriaza(2, 2)
        self.si.inchiriaza(1, 5)
        self.si.inchiriaza(2, 6)
        self.si.inchiriaza(3, 7)
        self.si.inchiriaza(4, 4)
        filme = self.si.raport_cele_mai_putin_inchiriate_filme_care_incep_cu_un_string_dat("Ab")
        self.assertEqual(len(filme), 2)
        self.assertEqual(filme[0], Film(5, "Aba", "c", "d"))
        self.assertEqual(filme[1], Film(7, "Abai", "g", "h"))

if __name__ == '__main__':
    unittest.main()
