from Domain.film import Film
from Domain.client import Client
from Domain.inchiriere import Inchiriere

import random
from string import ascii_letters

class GeneratorString:
    @staticmethod
    def generare_sir(a=0, b=100):  # genereaza un sir de caractere literale cu lungimea cuprinsa intre a si b
        lungime = random.randint(a, b)
        sir = ''.join(random.choice(ascii_letters) for i in range(lungime))
        return sir

class GenerateFilm:
    @staticmethod
    def film(): # genereaza un film
        id = random.randint(1, 1000)
        titlu = GeneratorString.generare_sir(5, 15)
        descriere = GeneratorString.generare_sir(10, 20)
        gen = GeneratorString.generare_sir(5, 10)
        # 100% valid
        return Film(id, titlu, descriere, gen)

class GenerateClient:
    @staticmethod
    def client(): # genereaza un client
        id = random.randint(1, 1000)
        nume = GeneratorString.generare_sir(6, 16)
        cnp = ''.join(random.choice("1234567890") for i in range(13))
        return Client(id, nume, cnp)

class GenerateInchiriere:
    @staticmethod
    def inchiriere(): # genereaza o inchiriere
        film = GenerateFilm.film()
        client = GenerateClient.client()
        inchiriat = True
        return Inchiriere(client, film, inchiriat)

