from Console.console import Console
from Repository.repository import Repository
from Service.service import Service

r = Repository("file_test.txt")
s = Service(r)
c = Console(s)
c.run()
