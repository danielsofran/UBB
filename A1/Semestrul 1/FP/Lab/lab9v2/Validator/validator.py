from Exceptii.exceptions import ValidationError

class ValidatorFilm:
    @staticmethod
    def valid(film): # valideaza un film
        errs = ""
        if film.id <=0:
            errs += "Id invalid!\n"
        if len(film.titlu) == 0:
            errs += "Titlu invalid!\n"
        if len(film.descriere) == 0:
            errs += "Descriere invalida!\n"
        if len(film.gen) == 0:
            errs += "Gen invalid!\n"
        if len(errs)>0:
            raise ValidationError(errs)

class ValidatorClient:
    @staticmethod
    def valid(client): # valideaza un client
        errs = ""
        if client.id <=0:
            errs += "Id invalid!\n"
        if client.nume == "":
            errs += "Nume invalid!\n"
        if not str(client.cnp).isdigit() or len(client.cnp)!=13:
            errs += "Cnp invalid!\n"
        if len(errs):
            raise ValidationError(errs)

class ValidatorInchiriereDto:
    @staticmethod
    def valid(inchirieredto): # valideaza o inchiriere
        errs = ""
        if inchirieredto.id_film <=0:
            errs += "Id film invalid!\n"
        if inchirieredto.id_client <=0:
            errs += "Id client invalid!\n"
        if len(errs)>0:
            raise ValidationError(errs)
