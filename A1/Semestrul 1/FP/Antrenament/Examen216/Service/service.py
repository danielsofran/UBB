from Domain.generator import Generator
from Domain.spectacol import Spectacol
from Repository.repository import FileRepository
from Validator.validator import Validator


class Service:
    def __init__(self, valid: Validator, repo: FileRepository):
        self.__valid = valid
        self.__repo = repo

    def adauga(self, titlu: str, artist: str, gen: str, durata: int):
        spect = Spectacol(titlu, artist, gen, durata)
        self.__valid.spectacol(spect)
        self.__repo.adauga(spect)

    def modfifcare(self, titlu: str, artist: str, gen: str, durata: int):
        spect = Spectacol(titlu, artist, gen, durata)
        self.__valid.spectacol(spect)
        self.__repo.modificare(spect)

    def generare(self, nr: int): # egenram nr spectacole
        for i in range(nr):
            spect = Generator.generate()
            try:
                self.__valid.spectacol(spect)
                self.__repo.adauga(spect)
            except:
                nr += 1

    def exporta(self, path: str): # mutam toate spectacolele intr-un repo nou
        repo = FileRepository(path)
        for elem in self.__repo:
            repo.adauga(elem)

