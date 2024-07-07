from Repository.repos import RepoFilm, RepoClient, FileRepoFilm, FileRepoClient, RepoInchiriere, FileRepoInchiriere
from Service.servicefilm import ServiceFilm
from Service.serviceclient import ServiceClient
from Service.serviceinchiriere import ServiceInchiriere

from UI.Console import Console

# repofilm = RepoFilm()
# repoclient = RepoClient()
# repoinchiriere = RepoInchiriere()

repofilm = FileRepoFilm("Files/filme.txt")
repoclient = FileRepoClient("Files/clienti.txt")
repoinchiriere = FileRepoInchiriere("Files/inchirieri.txt")

servicefilm = ServiceFilm(repofilm)
serviceclient = ServiceClient(repoclient)
serviceinchiriere = ServiceInchiriere(repoinchiriere, repoclient, repofilm)

consola = Console(servicefilm, serviceclient, serviceinchiriere)
consola.run()
