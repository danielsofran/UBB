from Repository.repository import Repository, FileRepository
from Exceptii.exceptions import RepositoryError
from Domain.film import Film
from Domain.client import Client
from Domain.inchirieredto import InchiriereDto

class RepoFilm(Repository): # repository de filme
    def __init__(self): # constructor
        super(RepoFilm, self).__init__(Film)

class RepoClient(Repository): # repository de clienti
    def __init__(self): # constructor
        super(RepoClient, self).__init__(Client)

class FileRepoFilm(FileRepository): # repository de filme dintr-un fisier
    def __init__(self, file): # constructor
        super(FileRepoFilm, self).__init__(Film, file, 4)

class FileRepoClient(FileRepository): # repository de clienti dintr-un fisier
    def __init__(self, file): # constructor
        super(FileRepoClient, self).__init__(Client, file, 3)

class RepoInchiriere(Repository): # repository inchiriere ca DTO
    def __init__(self): # constructor
        super(RepoInchiriere, self).__init__(InchiriereDto)

    def stergere(self, inchiriere): # functia de stergere va avea ca parametru o inchiriere, adica ambele id-uri, nu doar un id
        for elem in self._container:
            if elem == inchiriere:
                self._container.remove(inchiriere)
                break
        else:
            raise RepositoryError("Inchirierea data nu a fost gasita!")

class FileRepoInchiriere(FileRepository): # repository inchiriere ca DTO dintr-un fisier
    def __init__(self, file): # constructor
        super(FileRepoInchiriere, self).__init__(InchiriereDto, file, 2)

    def stergere(self, inchiriere): # functia de stergere va avea ca parametru o inchiriere, adica ambele id-uri, nu doar un id
        self._read_from_file()
        for elem in self._container:
            if elem == inchiriere:
                self._container.remove(inchiriere)
                break
        else:
            raise RepositoryError("Inchirierea data nu a fost gasita!")
        self._write_to_file()
