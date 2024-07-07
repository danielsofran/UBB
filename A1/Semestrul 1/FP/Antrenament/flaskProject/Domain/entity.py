class Entity:
    def __init__(self, eid):
        self.__entity_id = eid

    @property
    def id(self):
        return self.__entity_id

    def __eq__(self, other):
        return self.__entity_id == other.__entity_id
