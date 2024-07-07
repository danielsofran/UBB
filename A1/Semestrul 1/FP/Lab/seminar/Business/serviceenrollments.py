from Domain.enrollment import Enrollment
from Domain.gamerdto import GamerDto


class ServiceEnrollments:
    def __init__(self, repoplayers, repogames, repoenrollments):
        self.__repogames = repogames
        self.__repoplayers = repoplayers
        self.__repoenrollments = repoenrollments

    def get_all(self):
        enrollments = self.__repoenrollments.get_all()
        # georgel: \n fifa 200 \n, ...
        # ...
        playeri_data = {}
        for enrollment in enrollments:
            game = self.__repogames.cauta_dupa_id(enrollment.id_game)
            player = self.__repoplayers.cauta_dupa_id(enrollment.id_player)
            new_enrollment = Enrollment(enrollment.id_enrollment, player, game, enrollment.level)
            if enrollment.id_player not in playeri_data:
                playeri_data[enrollment.id_player] = []
            playeri_data[enrollment.id_player].append(new_enrollment)
        rez = []
        for player_data in playeri_data:
            nume_player = playeri_data[player_data][0].player.nume
            gamerdto = GamerDto(nume_player)
            for enrollment in playeri_data[player_data]:
                gamerdto.add_enrollment(enrollment)
            rez.append(gamerdto)
        return rez



