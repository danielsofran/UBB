from Domain.game import Game
from Exceptii.exceptii import RepoException


class RepoGames:
    def __init__(self):
        self._games = []

    def __len__(self):
        return len(self._games)

    def adauga(self, game):
        if game in self._games:
            raise RepoException("Game existent!")
        self._games.append(game)

    def cauta_dupa_id(self, id_game):
        ok = True
        for game in self._games:
            if game.id == id_game:
                ok = False
                return game
        if ok:
            raise RepoException("Game inexistent!")

    def get_all(self):
        return self._games[:]
    
class FileRepoGames(RepoGames):
    def __init__(self, filepath):
        RepoGames.__init__(self)
        self.__filepath = filepath
    
    def adauga(self, game):
        self.__read_all_from_file()
        RepoGames.adauga(self, game)
        self.__append_to_file(game)

    def cauta_dupa_id(self, id_game):
        self.__read_all_from_file()
        return RepoGames.cauta_dupa_id(self, id_game)

    def __len__(self):
        self.__read_all_from_file()
        return RepoGames.__len__(self)

    def get_all(self):
        self.__read_all_from_file()
        return RepoGames.get_all(self)

    def __read_all_from_file(self):
        with open(self.__filepath, "r") as f:
            self._games = []
            lines = f.readlines()
            for line in lines:
                line = line.strip()
                if len(line) > 0:
                    parts = line.split(',')
                    id = int(parts[0])
                    nume = parts[1]
                    game = Game(id, nume)
                    self._games.append(game)

    def __append_to_file(self, game):
        with open(self.__filepath, "a") as f:
            f.write(f"{game.id},{game.nume}\n")
        
