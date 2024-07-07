from Repository.repository import RepoParcari
from Service.service import ServiceParcari
from UI.consola import Consola
from Teste.test import Test

Test.run_all()

repo = RepoParcari("parcari.txt")
service = ServiceParcari(repo)
consola = Consola(service)

consola.run()
