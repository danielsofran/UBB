from Repository.repository import FileRepository
from Service.service import Service
from UI.console import Console
from teste import test
test()                              # rulez testele

repo = FileRepository("file.txt")   # repo-ul
service = Service(repo)             # service-ul
console = Console(service)          # consola

console.run()   # rulez programul
