import unittest
from Domain.film import Film
from Domain.client import Client
from Domain.inchirieredto import InchiriereDto
from Validator.validator import ValidatorFilm, ValidatorClient, ValidatorInchiriereDto
from Exceptii.exceptions import *


class TestFilm(unittest.TestCase):
    def setUp(self) -> None:
        self.__f1 = Film(1, "Arthur", "fain", "actiune")
        self.__f2 = Film(-1, "a", "b", "c")
        self.__f3 = Film(0, None, None, None)

    def test_getters(self):
        self.assertEqual(self.__f1.titlu, "Arthur")
        self.assertEqual(self.__f2.id, -1)
        self.assertIsNone(self.__f3.titlu)
        self.assertIsNone(self.__f3.descriere)
        self.assertIsNone(self.__f3.gen)

    def test_setters(self):
        self.__f1.titlu = "Regele Arthur"
        self.assertEqual(self.__f1.titlu, "Regele Arthur")
        self.__f2.gen = "comunist"
        self.assertEqual(self.__f2.gen, "comunist")
        self.__f3.descriere = "frumos"
        self.assertEqual(self.__f3.descriere, "frumos")

    def test_fcts(self):
        self.assertEqual(self.__f1 == self.__f2, False)
        self.__f3 = self.__f1
        self.assertEqual(self.__f1, self.__f3)
        self.assertEqual(str(self.__f1), "1\nArthur\nfain\nactiune")
        self.assertEqual(self.__f1, Film.fromStr("1\nArthur\nfain\nactiune"))
        self.assertEqual(hash(self.__f1), self.__f1.id)
        self.assertEqual(self.__f1.show(), "Id: 1, Titlu: Arthur, Descriere: fain, Gen: actiune")

    def test_validator(self):
        ValidatorFilm.valid(self.__f1)
        self.assertRaises(ValidationError, ValidatorFilm.valid, self.__f2)
        self.assertRaises(TypeError, ValidatorFilm.valid, self.__f3)

class TestClient(unittest.TestCase):
    def setUp(self) -> None:
        self.__c1 = Client(1, "Daniel", "1111111111111")
        self.__c2 = Client(-1, "a", "b")
        self.__c3 = Client(0, "", "")

    def test_getters(self):
        self.assertEqual(self.__c1.nume, "Daniel")
        self.assertEqual(self.__c2.id, -1)
        self.assertEqual(self.__c3.nume, "")
        self.assertEqual(self.__c3.cnp, "")

    def test_setters(self):
        self.__c1.nume = "Regele Arthur"
        self.assertEqual(self.__c1.nume, "Regele Arthur")
        self.__c3.cnp = "3445672890634"
        self.assertEqual(self.__c3.cnp, "3445672890634")

    def test_fcts(self):
        self.assertEqual(self.__c1 == self.__c2, False)
        self.__c3 = self.__c1
        self.assertEqual(self.__c1, self.__c3)
        self.assertEqual(str(self.__c1), "1\nDaniel\n1111111111111")
        self.assertEqual(self.__c1, Client.fromStr("1\nDaniel\n1111111111111"))
        self.assertEqual(hash(self.__c1), self.__c1.id)
        self.assertEqual(self.__c1.show(), "Id: 1, Nume: Daniel, Cnp: 1111111111111")

    def test_validator(self):
        ValidatorClient.valid(self.__c1)
        self.assertRaises(ValidationError, ValidatorClient.valid, self.__c2)
        self.assertRaises(ValidationError, ValidatorClient.valid, self.__c3)

class TestInchiriereDto(unittest.TestCase):
    def setUp(self) -> None:
        self.__i1 = InchiriereDto(1, 4)
        self.__i2 = InchiriereDto(-1, 3)
        self.__i3 = InchiriereDto(0, -3)

    def test_getters(self):
        self.assertEqual(self.__i1.id_film, 4)
        self.assertEqual(self.__i2.id_client, -1)
        self.assertEqual(self.__i3.id, 0^(-3))

    def test_fcts(self):
        self.assertFalse(self.__i1 == self.__i2)
        self.__i3 = self.__i1
        self.assertFalse(self.__i1 != self.__i3)
        self.assertTrue(self.__i1.id == self.__i3.id)
        self.assertEqual(self.__i1, InchiriereDto.fromStr("4\n1"))
        self.assertEqual(str(self.__i1), "4\n1")
        self.assertEqual(hash(self.__i1), self.__i1.id_film ^ self.__i1.id_client)

    def test_validator(self):
        ValidatorInchiriereDto.valid(self.__i1)
        self.assertRaises(ValidationError, ValidatorInchiriereDto.valid, self.__i2)
        self.assertRaises(ValidationError, ValidatorInchiriereDto.valid, self.__i3)

if __name__ == '__main__':
    unittest.main()
