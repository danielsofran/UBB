from Exceptii.exceptii import ValidationException, RepoException


class Consola:
    def __init__(self, serviceplayers, servicegames, serviceenrollments):
        self.__servicegames = servicegames
        self.__serviceplayers = serviceplayers
        self.__serviceenrollments = serviceenrollments

    def run(self):
        while True:
            cmd = input("Comanda: ")
            if cmd == "":
                continue
            if cmd == "exit":
                return
            if cmd == "add_game":
                try:
                    self.__ui_adauga_game()
                except ValidationException as ve:
                    print(ve)
                except RepoException as re:
                    print(re)
            elif cmd == "print_games":
                self.__ui_afiseaza_games()
            elif cmd == "print_players":
                self.__ui_print_players()
            elif cmd == "print_enrollments":
                self.__ui_print_enrollments()
            else:
                print("Comanda invalida!")

    def __ui_adauga_game(self):
        try:
            id = int(input("Id: "))
        except ValueError:
            print("Id numeric invalid!")
            return
        nume = input("Nume: ")
        self.__servicegames.adauga(id, nume)
        print("Game adaugat cu succes!")

    def __ui_afiseaza_games(self):
        games = self.__servicegames.get_all()
        if len(games) == 0:
            print("Nu exista jocuri in lista!")
            return
        for game in games:
            print(game)

    def __ui_print_players(self):
        players = self.__serviceplayers.get_all()
        if len(players) == 0:
            print("Nu exista playeri in lista!")
            return
        for player in players:
            print(player)

    def __ui_print_enrollments(self):
        enrollments = self.__serviceenrollments.get_all()
        if len(enrollments) == 0:
            print("Nu exista enrollment-uri in lista!")
            return
        for enrollment in enrollments:
            print(enrollment)




