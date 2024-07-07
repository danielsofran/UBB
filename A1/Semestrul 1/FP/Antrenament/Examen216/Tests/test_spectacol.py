from unittest import TestCase

from Domain.spectacol import Spectacol
from Validator.validator import Validator
from exceptii import ValidatorException


class TestSpectacol(TestCase):
    def setUp(self) -> None:
        self.s = Spectacol("Titlu", "Artist", "Concert", 60)
        self.valid = Validator()

    def test_titlu(self):
        self.assertEqual(self.s.titlu , "Titlu")

    def test_artist(self):
        self.assertEqual(self.s.artist , "Artist")

    def test_gen(self):
        self.assertEqual(self.s.gen , "Concert")

    def test_durata(self):
        self.assertEqual(self.s.durata , 60)

    def test_from_str(self):
        self.assertEqual(Spectacol.fromStr("Artist,Titlu,60,Gen"), self.s)

    def test_valid(self):
        self.assertRaises(ValidatorException, self.valid.spectacol, Spectacol("Titlu", "", "Concert", 60))
        self.assertRaises(ValidatorException, self.valid.spectacol, Spectacol("", "Artist", "Concert", 60))
        self.assertRaises(ValidatorException, self.valid.spectacol, Spectacol("Titlu", "Artist", "mama", 60))
        self.assertRaises(ValidatorException, self.valid.spectacol, Spectacol("Titlu", "Artist", "Concert", -60))
        self.valid.spectacol(self.s)
