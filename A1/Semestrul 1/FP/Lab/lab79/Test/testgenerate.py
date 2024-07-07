from Domain.generatorrandom import GenerateFilm, GenerateClient, GenerateInchiriere
from Controller.validation import ValidatorFilm, ValidatorClient, ValidatorInchiriere


def runall():
    for i in range(10):
        f = GenerateFilm.film()
        ValidatorFilm.film(f)
        c = GenerateClient.client()
        ValidatorClient.client(c)
        ValidatorClient.iterable((c.id, c.nume, c.cnp))
        i = GenerateInchiriere.inchiriere()
        ValidatorInchiriere.inchiriere(i)
