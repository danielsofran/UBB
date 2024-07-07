from Repository.filerepo import FileRepository
from Service.service import Service
from UI.console import Console

repo = FileRepository("file.txt")   # instantiez si determin colectia
service = Service(repo)             # creez service-ul
console = Console(service)          # creez consola

console.run()                       # rulez aplicatia
