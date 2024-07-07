from Exceptii.exceptii import ValidationException


class ValidatorPlayer:
    @staticmethod
    def valideaza(player):
        erori = ""
        if player.id <=0:
            erori += "Id invalid!\n"
        if player.nume == "":
            erori += "Nume invalid!\n"
        if player.bani <= 0:
            erori += "Prea dator omu... Prea putini bani, nu facem profit\n"
        if len(erori) >0:
            raise ValidationException(erori)
