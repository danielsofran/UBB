from Exceptii.exceptii import DuplicatedIdException, RepositoryException


class Repository:
    def __init__(self, type_of_entity, path):
        self.__entities = []
        self.__type = type_of_entity
        self.__path = path

    def __read_from_file(self): # citire din fisier
        with open(self.__path, "r") as f:
            self.__entities.clear()
            for line in f:
                entity = self.__type.fromRepr(line.strip())
                self.__entities.append(entity)

    def __write_to_file(self): # scriere in fisier
        with open(self.__path, "w") as f:
            for entity in self.__entities:
                f.write(repr(entity)+"\n")

    def __len__(self): # lnr de entitati
        self.__read_from_file()
        return len(self.__entities)

    def adauga(self, entity):
        self.__read_from_file()
        for _entity in self.__entities:
            if _entity == entity:
                raise DuplicatedIdException("Id duplicat la adaugare!")
        self.__entities.append(entity)
        self.__write_to_file()

    def stergere(self, id):
        self.__read_from_file()
        for entity in self.__entities:
            if entity.id == id:
                self.__entities.remove(entity)
                self.__write_to_file()
                break
        else: raise RepositoryException("Id-ul dat nu a fost gasit!")

    def modificare(self, id, entity):
        self.__read_from_file()
        for index in range(len(self.__entities)):
            if self.__entities[index].id == id:
                self.__entities[index] = entity
                self.__write_to_file()
                break
        else:
            raise RepositoryException("Id-ul dat nu a fost gasit!")

    def cauta_id(self, id):
        self.__read_from_file()
        for entity in self.__entities:
            if entity.id == id:
                return entity
        else:
            raise RepositoryException("Id-ul dat nu a fost gasit!")

    def get_all(self):
        self.__read_from_file()
        return self.__entities[:]
