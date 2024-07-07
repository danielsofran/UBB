from Domain.spectacol import Spectacol
from exceptii import RepoException


class FileRepository:
    def __init__(self, filename):
        self.__path = filename
        self.__l = []
        self.__read()

    def __read(self):
        try: fo = open(self.__path)
        except:
            # creem fisierul daca nu exista
            with open(self.__path, "w"): pass
            self.__l.clear()
            return
        else: fo.close()
        with open(self.__path) as f:
            self.__l.clear()
            for line in f:
                if line!="":
                    try:
                        spect = Spectacol.fromStr(line)
                        self.__l.append(spect)
                    except: pass

    def __write(self):
        with open(self.__path, "w") as f:
            for spect in self.__l:
                f.write(repr(spect)+"\n")

    def adauga(self, spect: Spectacol):
        self.__l.append(spect)
        self.__write()

    def modificare(self, spect: Spectacol):
        if spect in self.__l:
            rez = []
            for elem in self.__l:
                if elem == spect:
                    elem = spect
                rez.append(elem)
            self.__l = rez
        else:
            raise RepoException("Nu au fost gasite astfel de spectacole!")

    def __iter__(self):
        return iter(self.__l)

    def __len__(self):
        return len(self.__l)
