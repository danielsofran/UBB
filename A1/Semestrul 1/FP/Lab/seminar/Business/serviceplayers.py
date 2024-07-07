from Domain.player import Player
from Validare.validatorplayer import ValidatorPlayer


class ServicePlayers:
    def __init__(self, repoplayers):
        self.__repoplayers = repoplayers

    def __len__(self):
        return len(self.__repoplayers)

    def adauga(self, id, nume, bani):
        player = Player(id, nume, bani)
        ValidatorPlayer.valideaza(player)
        self.__repoplayers.adauga(player)

    def get_all(self):
        return self.__repoplayers.get_all()