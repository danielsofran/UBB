from Infrastructura.repogames import FileRepoGames
from Infrastructura.repoplayers import FileRepoPlayers
from Infrastructura.repoenrollments import FileRepoEnrollments

from Business.servicegames import ServiceGames
from Business.serviceplayers import ServicePlayers
from Business.serviceenrollments import ServiceEnrollments

from Prezentare.consola import Consola

repo_players = FileRepoPlayers("players.txt")
repo_games = FileRepoGames("games.txt")
repo_enrollments = FileRepoEnrollments("enrollments.txt")

service_players = ServicePlayers(repo_players)
service_games = ServiceGames(repo_games)
service_enrollments = ServiceEnrollments(repo_players, repo_games, repo_enrollments)

consola = Consola(service_players, service_games, service_enrollments)
consola.run()