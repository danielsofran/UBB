from repository.repository import Repository, FileRepository
from Domain.carte import Carte

class RepoCarti(Repository):
    def __init__(self):
        super().__init__(Carte)

class FileRepoCarti(FileRepository):
    def __init__(self, filename):
        super().__init__(Carte, filename)
