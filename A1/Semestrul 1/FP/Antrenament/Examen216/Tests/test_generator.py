from unittest import TestCase

from Domain.generator import Generator
from Validator.validator import Validator


class TestGenerator(TestCase):
    def setUp(self) -> None:
        self.v = Validator()

    def test_generate(self):
        for i in range(100):
            spect = Generator.generate()
            self.v.spectacol(spect)

