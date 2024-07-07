import os
from unittest import TestCase

from Domain.spectacol import Spectacol
from Repository.repository import FileRepository


class TestFileRepository(TestCase):
    def setUp(self) -> None:
        self.r = FileRepository("test.txt")
        l = [Spectacol("Titlu1", "Artist1", "Concert", 60), Spectacol("Titlu2", "Artist2", "Balet", 45), Spectacol("Titlu3", "Artist3", "Altele", 55)]
        self.spect = Spectacol("Titlu", "Artist", "Concert", 60)
        for elem in l:
            self.r.adauga(elem)

    def tearDown(self) -> None:
        os.remove("test.txt")

    def test_adauga(self):
        self.assertEqual(len(self.r), 3)
        self.r.adauga(self.spect)
        self.assertEqual(len(self.r), 4)

    def test_modificare(self):
        self.r.modificare(Spectacol("Titlu1", "Artist1", "Altele", 660))
        for elem in self.r:
            self.assertEqual(elem.gen, "Altele")
            self.assertEqual(elem.durata, 660)
            break

