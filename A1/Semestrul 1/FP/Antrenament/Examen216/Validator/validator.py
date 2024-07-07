from Domain.spectacol import Spectacol
from exceptii import ValidatorException


class Validator:
    def __titlu_artist(self, sir: str):
        return sir != ""

    def __durata(self, durata: int):
        return durata > 0

    def __gen(self, gen: str):
        return gen in Spectacol.genuri

    def spectacol(self, spect: Spectacol):
        ex = ""
        if not self.__titlu_artist(spect.titlu):
            ex += "Titlu invalid!\n"
        if not self.__titlu_artist(spect.artist):
            ex += "Artist invalid!\n"
        if not self.__durata(spect.durata):
            ex += "Durata invalida!\n"
        if not self.__gen(spect.gen):
            ex += "Gen invalid!\n"
        if ex!="":
            raise ValidatorException(ex)
