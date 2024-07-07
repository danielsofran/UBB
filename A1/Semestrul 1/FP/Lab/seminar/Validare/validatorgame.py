from Exceptii.exceptii import ValidationException


class ValidatorGame:
    @staticmethod
    def valideaza(game):
        erori = ""
        if game.id <=0:
            erori += "Id invalid!\n"
        if game.nume == "":
            erori += "Nume invalid!\n"
        if len(erori) >0:
            raise ValidationException(erori)