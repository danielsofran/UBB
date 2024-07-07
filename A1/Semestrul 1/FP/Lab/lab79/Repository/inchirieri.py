from Repository.generic import Repository
from Domain.inchiriere import Inchiriere


class RepositoryInchirieri(Repository):
    def __init__(self):
        super().__init__(Inchiriere)

    def where(self, **kwargs):  # cautare
        rez = self.copy()
        for key, value in kwargs.items():
            if key == "film":
                rez = list(filter(lambda elem: elem.film == value, rez))
            elif key == "client":
                rez = list(filter(lambda elem: elem.client == value, rez))
            elif key == "inchiriat":
                rez = list(filter(lambda elem: elem.inchiriat == value, rez))
            elif key == "function":
                rez = list(filter(value, rez))
        r = RepositoryInchirieri()
        r.extend(rez)
        return r

    def save(self):
        if self.file is None: return
        l = self.l.copy()
        with open(self.file, "w") as f:
            while len(l) > 0:
                i = l.pop()
                if i.inchiriat:
                    f.write(i.save())
