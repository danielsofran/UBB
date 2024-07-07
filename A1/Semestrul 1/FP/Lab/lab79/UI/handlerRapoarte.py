class HandlerRapoarte:
    def __init__(self, service):
        self.__service = service

    @property
    def service(self): return self.__service

    def r1(self):  # raportul 1
        self.service.showclientifilme()
        print(self.service)

    def r2(self):  # raportul 2
        self.service.showcelemaiinchiriatefilme()
        print(self.service)

    def r3(self):  # raportul 3
        self.service.showclientiicucelemaimultefilme()
        print(self.service)
