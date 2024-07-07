import mysql.connector

from Domain.reading import Reading
from Persistance.repository import Repository


class DatabaseRepository(Repository):
    def __init__(self, host, username, password):
        self.__connection = mysql.connector.connect(
            host = host,
            user = username,
            password = password
        )

    def __read_from_file(self):
        self._entities.clear()
        cursor = self.__connection.cursor()
        cursor.execute("SELECT * FROM Readings")
        results = cursor.fetchall()
        for result in results:
            reading = Reading(result[0], result[1], result[2])
            self._entities[reading.id] = reading

    def __append_to_file(self, entity):
        cursor = self.__connection.cursor()
        values = (entity.id, entity.temperature, entity.humidity)
        cursor.execute("INSERT INTO Readings (id, temperature, humidity) VALUES (%d, %f, %f)", values)
        self.__connection.commit()

    def __write_to_file(self):
        cursor = self.__connection.cursor()
        cursor.execute("DELETE FROM Readings")
        self.__connection.commit()

        for entity in self._entities:
            self.__append_to_file(entity)

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
        super().stergere_dupa_id(eid)
        self.__write_to_file()

    def cauta_dupa_id(self, eid):
        self.__read_from_file()
        return super().cauta_dupa_id(eid)

    def update(self, new_entity):
        self.__read_from_file()
        super().update(new_entity)
        self.__write_to_file()
