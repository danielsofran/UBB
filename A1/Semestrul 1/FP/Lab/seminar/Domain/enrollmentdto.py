class EnrollmentDto:
    def __init__(self, id_enrollment, id_player, id_game, level):
        self.__id_enrollment = id_enrollment
        self.__id_player = id_player
        self.__id_game = id_game
        self.__level = level

    @property
    def id_enrollment(self):
        return self.__id_enrollment
    @property
    def id_player(self):
        return self.__id_player
    @property
    def id_game(self):
        return self.__id_game
    @property
    def level(self):
        return self.__level
