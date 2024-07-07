from Domain.enrollmentdto import EnrollmentDto


class RepoEnrollments:
    def __init__(self):
        self._enrollments = []

    def get_all(self):
        return self._enrollments[:]

class FileRepoEnrollments(RepoEnrollments):
    def __init__(self, filepath):
        RepoEnrollments.__init__(self)
        self.__filepath = filepath

    def __len__(self):
        self.__read_from_file()
        return RepoEnrollments.__len__(self)

    def __read_from_file(self):
        with open(self.__filepath, "r") as f:
            self._players = []
            lines = f.readlines()
            for line in lines:
                line = line.strip()
                if len(line) > 0:
                    parts = line.split(',')
                    id_enrollment = int(parts[0])
                    id_player = int(parts[1])
                    id_game = int(parts[2])
                    level = int(parts[3])
                    enrollment = EnrollmentDto(id_enrollment, id_player, id_game, level)
                    self._enrollments.append(enrollment)

    def get_all(self):
        self.__read_from_file()
        return RepoEnrollments.get_all(self)
