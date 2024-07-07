from Domain.parcare import Parcare


class RepoParcari:
    """
    toate parcarile stocate in fisierul dat
    """
    def __init__(self, filename):
        self.__filename = filename
        self.__container = []

    def __read_from_file(self):  # citire din fisier
        with open(self.__filename) as f:
            self.__container.clear()
            for line in f.readlines():
                if len(line) > 0:
                    try:
                        line = line.strip()
                        parcare = Parcare.fromStr(line)
                        self.__container.append(parcare)
                    except Exception:
                        continue

    def get_all(self):  # returneaza toate parcarile
        self.__read_from_file()
        return self.__container[:]
