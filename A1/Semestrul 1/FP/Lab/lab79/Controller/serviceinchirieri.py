from Repository.inchirieri import RepositoryInchirieri, Inchiriere
from Repository.film import RepositoryFilm
from Repository.client import RepositoryClient
from Controller.generalservice import ServiceCRUD
from Controller.validation import ValidatorInchiriere


class ServiceInchirieri(ServiceCRUD):
    def __init__(self, repoFilm: RepositoryFilm, repoClient: RepositoryClient):  # constructor
        self.__repoFilm = repoFilm
        self.__repoClient = repoClient
        super().__init__(Inchiriere, RepositoryInchirieri, ValidatorInchiriere, "inchiriere", "inchirieri")

    @property
    def repoFilm(self):
        return self.__repoFilm  # getter

    @property
    def repoClient(self):
        return self.__repoClient  # getter

    def __len__(self):  # numara inchirierile
        rel = self.__filme_per_client()
        s = 0
        for client in rel:
            s += len(rel[client])
        return s

    def __validInchiriere(self, other):  # valideaza o inchiriere
        try:
            ValidatorInchiriere.inchiriere(other)
        except Exception as ex:
            self.msg = ex.args[0]
            return False
        if not other.client in self.repoClient:
            self.msg = f"Clientul {other.client.show()} nu a fost gasit!"
            return False
        if not other.film in self.repoFilm:
            self.msg = f"Filmul {other.film.show()} nu a fost gasit!"
            return False
        return True

    def adauga(self, other):  # adauga o inchiriere
        if not self.__validInchiriere(other): return
        # il cautam ca nu cumva sa existe
        for elem in self.repo:
            if elem == other:  # compara id film, client
                if elem.inchiriat == False:
                    elem.inchiriat = True
                    break
        else:
            self.repo.append(other)
            self.msg = f"Clientul {other.client.show()} a inchiriat filmul {other.film.show()}"

    def sterge(self, other):  # sterge o inchiriere
        if not self.__validInchiriere(other): return
        if not other in self.repo or other.inchiriat == False:
            self.msg = f"Nu am gasit filmul {other.film.show()}"
        self.repo[other].inchiriat = False

    def __filme_per_client(self):  # dictionar cu cheia client si valoarea repository de Film
        rel = dict()
        for client in self.repoClient:
            rel[client] = RepositoryFilm()
            for elem in self.repo:
                if elem.client == client and elem.inchiriat == True and elem.film in self.repoFilm:
                    elem.client = client
                    elem.film = self.repoFilm.where(id=elem.film.id)[0]
                    rel[client].append(self.repoFilm.where(id=elem.film.id)[0])
        return rel

    def showclientifilme(self):  # clientii cu filmele lor inchiriate
        rel = self.__filme_per_client()
        self.msg = ""
        for client in sorted(rel.keys(), key=lambda elem: elem.nume):
            filme = rel[client]
            if len(filme) == 0: continue
            self.msg += str(client) + '\n'
            for film in filme:
                self.msg += str(film) + '\n'
            self.msg += '\n'

    def showcelemaiinchiriatefilme(self):  # filmele
        frecv = dict()
        for film in self.repoFilm:
            frecv[film] = 0
            for elem in self.repo:
                if elem.film == film and elem.inchiriat and elem.client in self.repoClient:
                    frecv[film] = frecv[film] + 1
            if frecv[film] == 0: frecv.pop(film)
        self.msg = '\n'
        for film in sorted(frecv, key=lambda elem: frecv[elem], reverse=True):
            self.msg += str(film) + '\n'

    def showclientiicucelemaimultefilme(self):  # primii 30%
        rel = self.__filme_per_client()
        self.msg = ""
        for client in sorted(rel.keys(), key=lambda elem: len(rel[elem]))[:len(rel) * int(30 / 100)]:
            self.msg += f"Clientul {client.show()} a inchiriat {len(rel[client])} filme"
