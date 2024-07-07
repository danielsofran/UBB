from Repository.generic import Repository
from Domain.client import Client

class RepositoryClient(Repository): # lista de clienti
    def __init__(self):
        self._type = Client
        super().__init__(self._type)

    def where(self, **kwargs): # implementarea functiei de cautare
        fcts = []
        rez = self.copy()
        for key, value in kwargs.items():
            if key == "id":
                fcts.append(lambda elem: elem.id == value)
                rez = list(filter(fcts[-1], rez))
            elif key == "nume":
                fcts.append(lambda elem: elem.nume == value)
                rez = list(filter(fcts[-1], rez))
            elif key == "cnp":
                fcts.append(lambda elem: elem.cnp == str(value))
                rez = list(filter(fcts[-1], rez))
            elif key == "fields":
                fcts.append(lambda elem: elem == Client.fromIterable(value))
                rez = list(filter(fcts[-1], rez))
            elif key == "client":
                fcts.append(lambda elem: elem == value)
                rez = list(filter(fcts[-1], rez))
            elif key == "function":
                fcts.append(value)
                rez = list(filter(fcts[-1], rez))
        r = RepositoryClient()
        r.extend(rez)
        return r
