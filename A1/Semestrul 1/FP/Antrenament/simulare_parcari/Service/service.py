class ServiceParcari:
    """
        manager-ul de parcari
    """
    def __init__(self, repo_parcari):
        """
        constructor
        :param repo_parcari: parcarile stocate
        """
        self.__repo = repo_parcari

    def locuri_de_pe_o_strada(self, strada):
        """
        returneaza toate parcarile ...
        :param strada: numele strazii unde cautam parcarile
        :return: lista de parcari
        """
        locuri = []
        for loc in self.__repo.get_all():
            if loc.strada == strada:
                locuri.append(loc)
        locuri.sort(key=lambda loc: loc.nume, reverse=True)
        return locuri

    def cel_mai_utilizat_loc_per_strada(self):
        """
        ...
        :return: dictionar in care cheia este strada, iar valoarea e locul
        """
        locuri_utilizate = {}
        for loc in self.__repo.get_all():
            if loc.strada not in locuri_utilizate:
                locuri_utilizate[loc.strada] = loc
            elif locuri_utilizate[loc.strada].numar_utilizari < loc.numar_utilizari:
                locuri_utilizate[loc.strada] = loc
        return locuri_utilizate
