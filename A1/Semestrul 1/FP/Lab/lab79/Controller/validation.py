class ValidatorFilm:  # clasa statica care verifica datele unui film
    @staticmethod
    def id(id):
        '''
        verifica id-ul filmului
        :param id: int
        :raise: ValueError
        '''
        try:
            id = int(id)
        except:
            raise ValueError("Id-ul unui client trebuie sa fie un numar!")
        if (id <= 0): raise ValueError(f"Id-ul {id} al unui film trebuie sa fie pozitiv!", id)

    @staticmethod
    def titlu(titlu):
        '''
        verifica titlul unui film
        :param titlu: string
        :raise: ValueError
        '''
        if not isinstance(titlu, str):
            raise ValueError("Numele trebuie sa fie un text!", titlu)
        if len(titlu) == 0:
            raise ValueError("Numele nu poate fi vid!")
        for c in titlu:
            if c == '-' or c == ' ': continue
            if not c.isalnum():
                raise ValueError(f"Numele nu pot contine caractere '{c}'")

    @staticmethod
    def descriere(text):
        '''
        verifica descrierea unui film
        :param text: string
        :raise: ValueError
        '''
        if not isinstance(text, str) or text == "":
            raise ValueError("Descrierea trebuie sa fie un text!")

    @staticmethod
    def gen(gen):
        '''
        verifica genul unui film
        :param gen: string
        :raise: ValeuError
        '''
        if not isinstance(gen, str):
            raise ValueError("Descrierea trebuie sa fie un cuvant!")
        if len(gen) == 0:
            raise ValueError("Gen inexistent!")
        if not gen.isalpha():
            raise ValueError("Genul trebuie sa fie format din litere!")
        l = gen.split()
        if len(l) != 1:
            raise ValueError("Genul trebuie sa fie un singur cuvant!")

    @staticmethod
    def iterable(it):
        '''
        verifica corectitudinea unui set de date de intrare stocat intr-un iterabil
        :param it: iterabil
        :raise: ValueError
        '''
        errs = ""
        try:
            assert len(it) == 4
        except:
            errs += "Date insuficiente!\n"
        try:
            ValidatorFilm.id(it[0])
        except ValueError as e:
            errs += e.args[0] + "\n"

        try:
            ValidatorFilm.titlu(it[1])
        except ValueError as e:
            errs += e.args[0] + "\n"

        try:
            ValidatorFilm.descriere(it[2])
        except ValueError as e:
            errs += e.args[0] + "\n"

        try:
            ValidatorFilm.gen(it[3])
        except ValueError as e:
            errs += e.args[0] + "\n"

        if errs != "":
            raise ValueError(errs)

    @staticmethod
    def film(film):
        '''
        verifica datele unui film
        :param film: Film
        :raise: ValueError
        '''
        errs = ""

        try:
            ValidatorFilm.id(film.id)
        except ValueError as e:
            errs += e.args[0] + "\n"

        try:
            ValidatorFilm.titlu(film.titlu)
        except ValueError as e:
            errs += e.args[0] + "\n"

        try:
            ValidatorFilm.descriere(film.descriere)
        except ValueError as e:
            errs += e.args[0] + "\n"

        try:
            ValidatorFilm.gen(film.gen)
        except ValueError as e:
            errs += e.args[0] + "\n"

        if errs != "":
            raise ValueError(errs)


class ValidatorClient:  # valideaza un client
    @staticmethod
    def id(id):
        '''
        verifica id-ul client-ului
        :param id: int
        :raise: ValueError
        '''
        try:
            id = int(id)
        except:
            raise ValueError("Id-ul unui client trebuie sa fie un numar!")
        if (id <= 0): raise ValueError(f"Id-ul {id} al unui client trebuie sa fie pozitiv!", id)

    @staticmethod
    def nume(nume):
        '''
        verifica numele unui client
        :param nume: string
        :raise: ValueError
        '''
        if not isinstance(nume, str):
            raise ValueError("Numele trebuie sa fie un text!", nume)
        if len(nume) == 0:
            raise ValueError("Numele nu poate fi vid!")
        hasalpha = False
        ch = ""
        for c in nume:
            if c == '-' or c == ' ':
                if not c in ch: ch += c
            elif not c.isalpha():
                raise ValueError(f"Numele nu pot contine caractere '{c}'!")
            else:
                hasalpha = True
        if not hasalpha and len(ch) > 0:
            raise ValueError(f"Numele nu poate conține numai caractere '{ch}'!")

    @staticmethod
    def cnp(cnp):
        '''
        verifica cnp-ul clientului
        :param cnp: int or string
        :raise: ValueError
        '''
        if not isinstance(cnp, (int, str)):
            raise ValueError("CNP-ul trebuie sa fie un numar!")
        if isinstance(cnp, int):
            if 1000000000000 > cnp or cnp > 9999999999999:
                raise ValueError("CNP-ul trebuie sa contina exact 13 cifre!")
        else:
            if not cnp.isdecimal():
                raise ValueError("CNP-ul trebuie sa fie un numar!")
            if len(cnp) != 13:
                raise ValueError("CNP-ul trebuie sa contina exact 13 cifre!")

    @staticmethod
    def iterable(it):
        '''
        verifica un set de date de intrare stocat intr-un iterabil
        :param it: iteraibl
        :raise: ValueError
        '''
        errs = ""
        try:
            assert len(it) == 3
        except:
            errs += "Date insuficiente!\n"
        try:
            ValidatorClient.id(it[0])
        except ValueError as e:
            errs += e.args[0] + "\n"

        try:
            ValidatorClient.nume(it[1])
        except ValueError as e:
            errs += e.args[0] + "\n"

        try:
            ValidatorClient.cnp(it[2])
        except ValueError as e:
            errs += e.args[0] + "\n"

        if errs != "":
            raise ValueError(errs)

    @staticmethod
    def client(client):
        '''
        verifica datele unui client
        :param client:
        :raise: ValueError
        '''
        errs = ""

        try:
            ValidatorClient.id(client.id)
        except ValueError as e:
            errs += e.args[0] + "\n"

        try:
            ValidatorClient.nume(client.nume)
        except ValueError as e:
            errs += e.args[0] + "\n"

        try:
            ValidatorClient.cnp(client.cnp)
        except ValueError as e:
            errs += e.args[0] + "\n"

        if errs != "":
            raise ValueError(errs)


class ValidatorInchiriere:
    @staticmethod
    def inchiriere(obj):
        ValidatorFilm.film(obj.film)
        ValidatorClient.client(obj.client)
