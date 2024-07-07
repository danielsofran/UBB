CREATE OR ALTER PROCEDURE DeleteDetaliiPersoane AS
BEGIN
	EXEC DeletePersoaneCluburi
	EXEC DeletePersoane
	DELETE FROM DetaliiPersoane
END

GO
CREATE OR ALTER PROCEDURE DeletePersoane AS
BEGIN
	EXEC DeletePersoaneCluburi
	DELETE FROM PostariCluburi
	DELETE FROM PostariClub
	DELETE FROM ComentariiClub
	DELETE FROM MesajeData
	DELETE FROM ActivitatiPersoane
	DELETE FROM Persoane
END

GO
CREATE OR ALTER PROCEDURE DeleteCluburi AS
BEGIN
	EXEC DeletePersoaneCluburi
	DELETE FROM PostariCluburi
	DELETE FROM PostariClub
	DELETE FROM ComentariiClub
	DELETE FROM MesajeData
	DELETE FROM Cluburi
END

GO
CREATE OR ALTER PROCEDURE DeletePersoaneCluburi AS
BEGIN
	DELETE FROM PersoaneCluburi
END

GO
CREATE OR ALTER PROCEDURE UpdatePersoane AS
BEGIN
	DECLARE @nume VARCHAR(MAX), @mod INT
	EXEC RandNume @nume OUTPUT
	EXEC RandInt 20, 50, @mod OUTPUT
	UPDATE DetaliiPersoane SET nume=@nume WHERE id % @mod = 0
END

GO
CREATE OR ALTER PROCEDURE UpdateCluburi AS
BEGIN
	DECLARE @den VARCHAR(MAX), @mod INT
	EXEC RandNume @den OUTPUT
	EXEC RandInt 20, 50, @mod OUTPUT
	UPDATE Cluburi SET denumire=@den WHERE id % @mod = 0
END
