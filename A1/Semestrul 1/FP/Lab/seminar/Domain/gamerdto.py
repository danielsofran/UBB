class GamerDto:
    def __init__(self, nume_gamer):
        self.__nume_gamer = nume_gamer
        self.__list_enrollments = []

    def add_enrollment(self, enrollment):
        self.__list_enrollments.append(enrollment)

    def __str__(self):
        string = self.__nume_gamer + ":\n"
        for enrollment in self.__list_enrollments:
            string += "\t" + str(enrollment) + "\n"
        return string
