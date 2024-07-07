from UI.meniuri import *
from UI.handlerCRUD import HandlerCRUD
from UI.handlerIR import HandlerInchiriere
from UI.handlerRapoarte import HandlerRapoarte
from UI.handlerGenerate import HandlerGenerate
from Controller.servicefilmeclienti import ServiceFilme, ServiceClienti
from Controller.serviceinchirieri import ServiceInchirieri


def gestioneaza(service):  # submeniul 1 si 2
    h = HandlerCRUD(service)
    meniu = Meniuri.CRUD(h)
    meniu.run()


def inchirierereturnare(service):  # submeniul 3
    h = HandlerInchiriere(service)
    meniu = Meniuri.InchiriereReturnare(h)
    meniu.run()


def rapoarte(service):  # submeniul 4
    h = HandlerRapoarte(service)
    meniu = Meniuri.Raportare(h)
    meniu.run()


def inchidere(sfilme, sclienti, sinchirieri):
    sfilme.repo.save("filme.txt")
    sclienti.repo.save("clienti.txt")
    sinchirieri.repo.save("inchirieri.txt")
    exit(0)

def generate(sfilme, sclienti, sinchirieri):
    h = HandlerGenerate(sfilme, sclienti, sinchirieri)
    meniu = Meniuri.Generate(h)
    meniu.run()

def run():  # meniul principal
    m = Meniu("\n\tMENIU\n", clear_after_input=True)
    m.culoaretitlu = cl.Fore.LIGHTMAGENTA_EX
    sfilme = ServiceFilme()
    sclienti = ServiceClienti()
    sinchirieri = ServiceInchirieri(sfilme.repo, sclienti.repo)
    sfilme.repo.file = "filme.txt"
    sclienti.repo.file = "clienti.txt"
    sinchirieri.repo.file = "inchirieri.txt"
    o = Optiune(1, "Gestioneaza lista de filme", gestioneaza, sfilme)
    m += o
    o = Optiune(2, "Gestioneaza lista de clienti", gestioneaza, sclienti)
    m += o
    o = Optiune(3, "Inchiriere/Returnare filme", inchirierereturnare, sinchirieri)
    m += o
    o = Optiune(4, "Rapoarte", rapoarte, sinchirieri)
    m += o
    o = Optiune(5, "Genereaza random", generate, sfilme, sclienti, sinchirieri)
    m+=o
    o = Optiune("e", "Exit", inchidere, sfilme, sclienti, sinchirieri)
    m += o
    m.run()
