from UI.handlerCRUD import Input
from Controller.servicefilmeclienti import ServiceClienti, ServiceFilme
from Domain.inchiriere import Inchiriere


class HandlerInchiriere:
    def __init__(self, service):
        self.__service = service

    @property
    def service(self):
        return self.__service

    def inchiriere(self):
        print("Introduceti clientul:\n")
        client = Input.client()
        s = ServiceClienti()
        s.adauga(client)
        if len(s.repo) == 0:
            print(s)
            return
        client = s.repo[0]
        print("\nIntroduceti filmul:\n")
        film = Input.film()
        s = ServiceFilme()
        s.adauga(film)
        if len(s.repo) == 0:
            print(s)
            return
        film = s.repo[0]
        inchiriere = Inchiriere(client, film)
        self.service.adauga(inchiriere)
        print(self.service)

    def returnare(self):
        print("Introduceti clientul:\n")
        client = Input.client()
        s = ServiceClienti()
        s.adauga(client)
        if len(s.repo) == 0:
            print(s)
            return
        client = s.repo[0]
        print("\nIntroduceti filmul:\n")
        film = Input.film()
        s = ServiceFilme()
        s.adauga(film)
        if len(s.repo) == 0:
            print(s)
            return
        film = s.repo[0]
        r = self.service.repo.where(client=client, film=film)
        if len(r) > 0:
            self.service.sterge(r[0])
        else:
            self.service.msg = f"Clientul {client.show()} nu a inchiriat filmul {film.show()}"
        print(self.service)
