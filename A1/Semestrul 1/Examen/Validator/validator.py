from Domain.carte import Carte
from exceptii import ValidationException

class Validator:
    """
    clasa care valideaza datele unei carti
    """
    @staticmethod
    def valideaza(carte: Carte):
        """
        :param carte: carte
        :raise: ValidationException daca unul dintre campuri e invalid
        """
        if carte.id < 0:
            raise ValidationException("Id invalid!")
        if carte.titlu == "":
            raise ValidationException("Titlu invalid!")
        if carte.autor == "":
            raise ValidationException("Autor invalid")
        if carte.anAp < 0:
            raise ValidationException("Anul aparitiei invalid!")
