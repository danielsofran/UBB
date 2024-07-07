class Console:
    def __init__(self, service):
        """
        constructor
        :param service: service ul de elicoptere
        """
        self.__service = service

    def __afisare(self): # afisare elicoptere
        scop = input("Scop: ")
        elicoptere = self.__service.elicoptere_cu_scop_dat(scop)
        for elicopter in elicoptere:
            print(elicopter)
        if len(elicoptere) == 0:
            print("Nu exista astfel de elicoptere!")

    def __grupare(self): # afisare grupe pe scopuri
        scopuri = self.__service.grupare_ani_pe_scopuri()
        for scop, ani in scopuri.items():
            print(f" - {scop}: ", end='')
            for an in ani:
                print(f"{an} ", end='')
            print()

    def run(self):
        while True:
            print("afisare - afisare elicoptere dupa un scop dat\n"
                  "grupare - grupeaza elicopterele dupa scopurile lor, afisand anii\n")
            cmd = input("Introduceti comanda:").strip().lower()
            if cmd == "":
                continue
            elif cmd == "afisare":
                self.__afisare()
            elif cmd == "grupare":
                self.__grupare()
            else:
                print("Comanda invalida!")
