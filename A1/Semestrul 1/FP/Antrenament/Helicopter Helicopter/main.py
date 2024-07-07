from Repository.repository import RepoHelicopter
from Service.service import ServiceHelicopter
from UI.console import Console
from Teste.test import Test

test = Test()
test.run_all()

repo = RepoHelicopter("elicoptere.txt")
srv = ServiceHelicopter(repo)
console = Console(srv)

console.run()
