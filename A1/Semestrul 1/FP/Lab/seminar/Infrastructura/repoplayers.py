from Domain.player import Player
from Exceptii.exceptii import RepoException


class RepoPlayers:
    def __init__(self):
        self._players = []

    def __len__(self):
        return len(self._players)

    def adauga(self, player):
        if player in self._players:
            raise RepoException("player existent!")
        self._players.append(player)

    def cauta_dupa_id(self, id_player):
        ok = True
        for player in self._players:
            if player.id == id_player:
                ok = False
                return player
        if ok:
            raise RepoException("player inexistent!")

    def get_all(self):
        return self._players[:]


class FileRepoPlayers(RepoPlayers):
    def __init__(self, filepath):
        RepoPlayers.__init__(self)
        self.__filepath = filepath

    def adauga(self, player):
        self.__read_all_from_file()
        RepoPlayers.adauga(self, player)
        self.__append_to_file(player)

    def __len__(self):
        self.__read_all_from_file()
        return RepoPlayers.__len__(self)

    def get_all(self):
        self.__read_all_from_file()
        return RepoPlayers.get_all(self)

    def cauta_dupa_id(self, id_player):
        self.__read_all_from_file()
        return RepoPlayers.cauta_dupa_id(self,id_player)

    def __read_all_from_file(self):
        with open(self.__filepath, "r") as f:
            self._players = []
            lines = f.readlines()
            for line in lines:
                line = line.strip()
                if len(line) > 0:
                    parts = line.split(',')
                    id = int(parts[0])
                    nume = parts[1]
                    bani = float(parts[2])
                    player = Player(id, nume, bani)
                    self._players.append(player)

    def __append_to_file(self, player):
        with open(self.__filepath, "a") as f:
            f.write(f"{player.id},{player.nume}\n")