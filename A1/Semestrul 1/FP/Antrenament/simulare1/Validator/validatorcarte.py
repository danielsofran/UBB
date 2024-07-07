from Exceptii.exceptii import ValidatorError

class ValidatorCarte:
    @staticmethod
    def valid(carte):
        errs = ""
        if carte.id <=0:
            errs += "Id invalid!\n"
        if carte.titlu == "":
            errs += "Titlu invalid!\n"
        if carte.descriere == "":
            errs += "Descriere invalida!\n"
        if carte.autor == "":
            errs += "Autor invalid!\n"
        if len(errs)>0:
            raise ValidatorError(errs)
