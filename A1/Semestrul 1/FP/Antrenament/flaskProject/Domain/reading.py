from Domain.entity import Entity

class Reading(Entity):
    def __init__(self, eid, temperature, humidity):
        self.__temperature = temperature
        self.__humidity = humidity
        super().__init__(eid)

    def __str__(self):
        return f"{self.id}, {self.temperature}, {self.humidity}"

    @property
    def temperature(self):
        return self.__temperature

    @property
    def humidity(self):
        return self.__humidity

    @staticmethod
    def reading_to_line(reading):
        return f"{reading.id},{reading.temperature},{reading.humidity}"

    @staticmethod
    def line_to_reading(line):
        parts = line.split(",")
        return Reading(int(parts[0]), float(parts[1]), float(parts[2]))


