from Domain.helicopter import Helicopter

class RepoHelicopter:
    """
    multime de elicoptere
    """
    def __init__(self, fiename):
        """
        constructor
        :param fiename: numele fisierului
        """
        self.__container = {}
        self.__filename = fiename

    def __read_from_file(self): # incarc datele din fisier in container
        with open(self.__filename) as f:
            self.__container.clear()
            for line in f:
                if line != "":
                    helicopter = Helicopter.fromStr(line.strip())
                    self.__container[helicopter.id] = helicopter

    def get_all(self):  # returneaza toate elicopterele ca o lista
        self.__read_from_file()
        return list(self.__container.values())


