from Domain.game import Game
from Validare.validatorgame import ValidatorGame


class ServiceGames:
    def __init__(self, repogames):
        self.__repogames = repogames

    def __len__(self):
        return len(self.__repogames)

    def adauga(self, id, nume):
        game = Game(id, nume)
        ValidatorGame.valideaza(game)
        self.__repogames.adauga(game)

    def get_all(self):
        return self.__repogames.get_all()

