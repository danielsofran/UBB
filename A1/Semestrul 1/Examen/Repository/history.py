from Repository.filerepo import FileRepository
from Repository.repository import Repository
from exceptii import RepoException

class History(Repository):
    def adauga(self, carti: list): # adaug o lista de elemente sterse
        self._l.append(carti)       # pot exista duplicate

    def pop(self): # elimin si returnez ultima lista de carti sterse
        if len(self._l) == 0:
            raise RepoException("Nu exista stergeri ce pot fi refacute!")
        return self._l.pop()
