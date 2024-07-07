from Domain.carte import Carte
from repository.repocarti import RepoCarti, FileRepoCarti
from service.servicecarte import ServiceCarte
from ui.console import Console

repo = FileRepoCarti("carti.txt")
service = ServiceCarte(repo)
console = Console(service)

console.run()
console.run()
