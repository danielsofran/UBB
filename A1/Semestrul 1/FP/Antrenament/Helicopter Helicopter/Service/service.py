

class ServiceHelicopter:
    """
    service pentru elicoptere
    """

    def __init__(self, repo):
        """
        constructor
        :param repo: repository curent
        """
        self.__repo = repo

    def elicoptere_cu_scop_dat(self, scop):
        """
        afisarea tuturor ... (CTRL+V)
        :param scop: scopul introdus de la tastatura
        :return: lista de elicoptere
        """
        elicoptere = []
        for elicopter in self.__repo.get_all():
            if scop in elicopter.scopuri:
                elicoptere.append(elicopter)
        elicoptere.sort(key=lambda elicopter: elicopter.nume, reverse=True)
        return elicoptere

    def grupare_ani_pe_scopuri(self):
        """
        grupeaza elicopterele dupa scopuri, retinand doar anii
        :return: dictionar cu scopul ca si cheie si lista de ani ca valoare
        """
        scopuri = []
        for elicopter in self.__repo.get_all():
            for scop in elicopter.scopuri.split():
                if not scop in scopuri:
                    scopuri.append(scop)
        grupare = {}
        for scop in scopuri:
            grupare[scop] = []
            for elicopter in self.__repo.get_all():
                if scop in elicopter.scopuri:
                    grupare[scop].append(elicopter.an)
        return grupare
