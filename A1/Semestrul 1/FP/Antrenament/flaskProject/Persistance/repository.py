class RepositoryException(Exception): pass

class Repository:
    def __init__(self):
        self._entities = {}

    def __len__(self):
        return len(self._entities)

    def __str__(self):
        sir = ""
        for entity in self._entities.values():
            sir += str(entity) + "\n"
        return sir

    def get_all(self):
        return list(self._entities.values())

    def adauga(self, entitate):
        if entitate.id in self._entities:
            raise RepositoryException("Entitate existenta!")
        self._entities[entitate.id] = entitate

    def cauta_dupa_id(self, eid):
        if eid not in self._entities:
            raise RepositoryException("Entitate inexistenta!")
        return self._entities[eid]

    def stergere_dupa_id(self, eid):
        if eid not in self._entities:
            raise RepositoryException("Entitate inexistenta!")
        del self._entities[eid]

    def update(self, new_entity):
        if new_entity.id not in self._entities:
            raise RepositoryException("Enitiate inexistenta!")
        self._entities[new_entity.id] = new_entity


