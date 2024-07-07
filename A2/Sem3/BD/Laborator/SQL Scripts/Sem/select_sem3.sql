--EXEC InsertSectiune 'Flat rides section', 'Flat rides are usually considered to be those that move their passengers in a plane generally parallel to the ground.'
--EXEC InsertSectiune 'Roller coasters section', 'Amusement parks often feature multiple roller coasters of primarily timber or steel construction'
--EXEC InsertSectiune 'Water rides section', 'Amusement parks with water resources generally feature a few water rides, such as the log flume, bumper boats, rapids and rowing boats.'

--SELECT * FROM Sectiuni

--EXEC AdaugaCategorie @nume = 'Pensionari'
--EXEC AdaugaCategorie @nume = 'Adulti'
--EXEC AdaugaCategorie @nume = 'Copii'

--SELECT * FROM Categorii

EXEC InsertAtractie 'Montan russe', 'Descriere', 12, 'Roller coasters section'
EXEC InsertAtractie 'Train', 'Oldest transport ever', 3, 'Transport rides section'

SELECT * FROM Atractii
SELECT * FROM Sectiuni