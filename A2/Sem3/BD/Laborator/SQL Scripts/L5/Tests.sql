DECLARE @str VARCHAR(MAX)
DECLARE @i INT
DECLARE @date DATETIME

--EXEC RandStr 50, @str OUTPUT
--EXEC RandEmail @str OUTPUT
--EXEC RandData @date OUTPUT, 2000, 2010
--EXEC RandText @str OUTPUT
--SELECT @date

--SELECT dbo.ValidDate(1, 1, 2021, 10, 15)

--EXEC RandSport @i OUTPUT
--SELECT Sporturi.denumire FROM Sporturi WHERE id=@i

--DELETE FROM DetaliiPersoane WHERE id >= 6
--SET @date = dbo.StrToDate('12.11.2003')
--SELECT * from DetaliiPersoane
--EXEC InsertDetaliiPersoane 'Ion', 'i3.ion@gmail.com', @date, @i OUTPUT
--SELECT * from DetaliiPersoane
--SELECT @i

--EXEC RandId 'DetaliiPersoane', @i OUTPUT
--SELECT @i

--DELETE FROM Cluburi WHERE id > 3
--EXEC AddCluburi 3

--SELECT * FROM Cluburi

--EXEC DeletePersoaneCluburi
--SELECT * FROM PersoaneCluburi
--EXEC AddPersoaneCluburi 5
--SELECT * FROM PersoaneCluburi

--
--EXEC DeleteDetaliiPersoane
SELECT Persoana, COUNT(*) FROM PersoaneCluburiView GROUP BY Persoana ORDER BY COUNT(*) DESC