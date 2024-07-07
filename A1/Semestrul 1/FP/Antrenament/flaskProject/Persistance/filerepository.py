from Persistance.repository import Repository

class FileRepository(Repository):
    def __init__(self, filename:str, entity_from_line, entity_to_line):
        self.__filename = filename
        self.__entity_from_line = entity_from_line
        self.__entity_to_line = entity_to_line
        super().__init__()

    def __read_from_file(self):
        with open(self.__filename, "r") as f:
            self._entities.clear()
            for line in f:
                line = line.strip()
                if len(line)>0:
                    entity = self.__entity_from_line(line)
                    self._entities[entity.id] = entity

    def __append_to_file(self, entity):
        with open(self.__filename, "a") as f:
            f.write(self.__entity_to_line(entity)+"\n")

    def __write_to_file(self):
        with open(self.__filename, "w") as f:
            for entityid in self._entities:
                self.__entity_to_line(self._entities[entityid])

    def __str__(self):
        self.__read_from_file()
        return super().__str__()

    def __len__(self):
        self.__read_from_file()
        return len(super())

    def get_all(self):
        self.__read_from_file()
        return super().get_all()

    def adauga(self, entitate):
        self.__read_from_file()
        super().adauga(entitate)
        self.__append_to_file(entitate)

    def stergere_dupa_id(self, eid):
        self.__read_from_file()
        super(FileRepository, self).stergere_dupa_id(eid)
        self.__write_to_file()

    def cauta_dupa_id(self, eid):
        self.__read_from_file()
        return super().cauta_dupa_id(eid)

    def update(self, new_entity):
        self.__read_from_file()
        super().update(new_entity)
        self.__write_to_file()
