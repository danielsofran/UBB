--SELECT dbo.ValidClub('', 'def')

--SELECT dbo.ValidPersoana('a', 'b', '13/13/2023')

--SELECT dbo.ValidPersoanaClub('1/1/2020', '1/1/2012')

EXEC DeleteDetaliiPersoane
EXEC DeletePersoaneCluburi
EXEC DeleteCluburi

--EXEC AddPersoane 3
--EXEC AddCluburi 3
--EXEC AddPersoaneCluburi 3

---- TESTS

EXEC ClearLog

--EXEC InsertPersoanaClub 'Nume', 'email@ex.com', '3/3/2003', 'Club1', 'descr', '5/5/2005'

EXEC InsertPersoanaClubV2 'Nume', 'email@ex.com', '3/3/2003', '', 'descr', '5/5/2005'

SELECT * FROM TransactionLog

SELECT * FROM DetaliiPersoane
SELECT * FROM Persoane
SELECT * FROM Cluburi
SELECT * FROM PersoaneCluburi
