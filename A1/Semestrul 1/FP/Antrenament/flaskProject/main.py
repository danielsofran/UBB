from Persistance.filerepository import FileRepository
from Domain.reading import Reading

repo = FileRepository("readings.txt", Reading.line_to_reading, Reading.reading_to_line)
r1 = Reading(1, 23.6, 0.05)
r2 = Reading(2, 45.7, 0.50)
r3 = Reading(3, 46, 0.66)

repo.adauga(r1)
repo.adauga(r2)
repo.adauga(r3)
