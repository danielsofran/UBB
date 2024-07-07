import os
from unittest import TestCase

from Domain.spectacol import Spectacol
from Repository.repository import FileRepository
from Service.service import Service
from Validator.validator import Validator


class TestService(TestCase):
    def setUp(self) -> None:
        self.r = FileRepository("test1.txt")
        self.l = [Spectacol("Titlu1", "Artist1", "Concert", 60), Spectacol("Titlu2", "Artist2", "Balet", 45),
             Spectacol("Titlu3", "Artist3", "Altele", 55)]
        self.spect = Spectacol("Titlu", "Artist", "Concert", 60)
        for elem in self.l:
            self.r.adauga(elem)
        self.s = Service(Validator(), self.r)

    def tearDown(self) -> None:
        os.remove("test1.txt")

    def test_adauga(self):
        self.assertEqual(len(self.r), 3)
        self.s.adauga(self.spect.titlu, self.spect.artist, self.spect.gen, self.spect.durata)
        self.assertEqual(len(self.r), 4)

    def test_modfifcare(self):
        self.assertEqual(len(self.r), 3)
        self.s.modfifcare(self.l[0].titlu, self.l[0].artist, "Altele", 660)
        self.assertEqual(len(self.r), 3)
        for elem in self.r:
            self.assertEqual(elem.gen, "Altele")
            self.assertEqual(elem.durata, 660)
            break

    def test_generare(self):
        self.assertEqual(len(self.r), 3)
        self.s.generare(7)
        self.assertEqual(len(self.r), 10)

    def test_exporta(self):
        self.s.exporta("test2.txt")
        repo = FileRepository("test2.txt")
        i = 0
        for elem in repo:
            self.assertEqual(self.l[i], elem)
            i += 1
        os.remove("test2.txt")
