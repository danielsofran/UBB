from Repository.repository import FileRepository
from Service.service import Service
from UI.console import Console
from Validator.validator import Validator

repo = FileRepository("file.txt")
valid = Validator()
service = Service(valid, repo)
console = Console(service)

console.run()

