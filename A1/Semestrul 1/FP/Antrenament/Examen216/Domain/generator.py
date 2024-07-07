import random

from Domain.spectacol import Spectacol


class Generator:
    @staticmethod
    def __generate_titlu_artist(): # generez titlu sau nume de artist
        alfabet = "qertuiopasdfghjklzcvbnm"
        vocale = "aeiou"
        consoane = "".join([c for c in alfabet if not c in vocale])
        lungime = random.randint(9, 12)
        sir = consoane
        titlu = ""
        index_space = random.randint(0 ,lungime-2)
        if random.randint(0, 3) == 1:
            sir = vocale
        for i in range(lungime):
            if i == index_space:
                titlu += " "
                continue
            titlu += random.choice(sir)
            if sir == vocale:
                sir = consoane
            else:
                sir = vocale
        if " " in titlu:
            return titlu
        else:
            return Generator.__generate_titlu_artist()

    @staticmethod
    def generate(): # generez un spectacol
        titlu = Generator.__generate_titlu_artist()
        artist = Generator.__generate_titlu_artist()
        gen = Spectacol.genuri[random.randint(0, len(Spectacol.genuri)-1)]
        durata = random.randint(1, 24) * 60
        spect = Spectacol(titlu, artist, gen, durata)
        return spect

