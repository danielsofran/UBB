class Enrollment:
    def __init__(self, id, player, game, level):
        self.__game = game
        self.__player = player
        self.__id = id
        self.__level = level

    @property
    def id(self):
        return self.__id

    @property
    def game(self):
        return self.__game
    @game.setter
    def game(self, value):
        self.__game = value

    @property
    def player(self):
        return self.__player
    @player.setter
    def player(self, value):
        self.__player = value

    @property
    def level(self):
        return self.__level
    @level.setter
    def level(self, value):
        self.__level = value

    def __eq__(self, other):
        return self.__id == other.id

    def __str__(self):
        return f"{self.__game} si are level-ul {self.__level}"
