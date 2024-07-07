from Domain.entity import Entity

class Nume(Entity):
    def __init__(self, id, nume):
        super().__init__(id)
        self.__nume = nume

print(dir(Nume))
