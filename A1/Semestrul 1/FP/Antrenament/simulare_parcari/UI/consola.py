class Consola:
    """
        meniu al comenzilor
    """
    def __init__(self, srv):
        """
        constructor
        :param srv: service-ul de parcari
        """
        self.__service = srv

    def run(self):  # ruleaza meniul de comenzi
        while True:
            print("Comenzi:")
            print("locuri de pe o strada - toate locurile de pe strada data\n"
                  "cel mai utilizat loc - de pe toate strazile")
            cmd = input("Introduceti comanda: ").strip().lower()
            if cmd == "":
                continue
            elif cmd == "locuri de pe o strada":
                self.__locuri_de_pe_o_strada()
            elif cmd == "cel mai utilizat loc":
                self.__cel_mai_utilizat_loc()
            else:
                print("Comanda invalida!")

    def __locuri_de_pe_o_strada(self):  # functionalitatea 1
        strada = input("Strada: ")
        if strada == "":
            print("Strada invalida!")
            return
        locuri = self.__service.locuri_de_pe_o_strada(strada)
        for loc in locuri:
            print(loc)
        if len(locuri) == 0:
            print("Nu exista locuri pe aceasta strada!")

    def __cel_mai_utilizat_loc(self):  # functionalitatea 2
        locuri_utilizate = self.__service.cel_mai_utilizat_loc_per_strada()
        for strada, loc in locuri_utilizate.items():
            print(f" - {strada}: {loc.nume}")
