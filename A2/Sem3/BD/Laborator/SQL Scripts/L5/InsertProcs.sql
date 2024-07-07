CREATE OR ALTER PROCEDURE InsertDetaliiPersoane 
	@nume VARCHAR(MAX), @email VARCHAR(MAX), @data DATETIME, @id INT OUTPUT
AS
BEGIN
	SET @id = NULL
	BEGIN TRY
		INSERT INTO DetaliiPersoane(nume, email, data_nast) VALUES(@nume, @email, @data)
		SET @id = SCOPE_IDENTITY()
	END TRY
	BEGIN CATCH
	END CATCH
END

GO
CREATE OR ALTER PROCEDURE InsertPersoane
	@detalii_id INT, @sport_fav_id INT, @id INT OUTPUT
AS
BEGIN
	SET @id = NULL
	BEGIN TRY
		INSERT INTO Persoane(detalii_id, sport_fav_id) VALUES(@detalii_id, @sport_fav_id)
		SET @id = SCOPE_IDENTITY()
	END TRY
	BEGIN CATCH
	END CATCH
END

GO
CREATE OR ALTER PROCEDURE InsertClubui 
	@denumire VARCHAR(MAX), @descriere TEXT, @id INT OUTPUT
AS
BEGIN
	SET @id = NULL
	BEGIN TRY
		INSERT INTO Cluburi(denumire, descriere) VALUES(@denumire, @descriere)
		SET @id = SCOPE_IDENTITY()
	END TRY
	BEGIN CATCH
	END CATCH
END

GO
CREATE OR ALTER PROCEDURE InsertPersoaneCluburi
	@persoana_id INT, @club_id INT, @moment DATETIME, @ok BIT OUTPUT AS
BEGIN
	BEGIN TRY
		INSERT INTO PersoaneCluburi(persoana_id, club_id, moment_join) VALUES(@persoana_id, @club_id, @moment)
		SET @ok = 1
	END TRY
	BEGIN CATCH
		SET @ok = 0
	END CATCH
END

GO
CREATE OR ALTER PROCEDURE AddPersoane 
	@nr INT AS
BEGIN
	DECLARE @nume VARCHAR(MAX), @email VARCHAR(MAX), @data DATETIME
	DECLARE @detalii_id INT, @sport_fav_id INT, @id INT
	PRINT 'Generating '+CAST(@nr AS VARCHAR)+' persoane...'
	WHILE @nr > 0
	BEGIN
		EXEC RandNume @nume OUTPUT
		EXEC RandEmail @email OUTPUT
		EXEC RandData @data OUTPUT, 2022, 2000
		EXEC RandId 'Sporturi', @sport_fav_id OUTPUT
		EXEC InsertDetaliiPersoane @nume, @email, @data, @detalii_id OUTPUT
		IF @detalii_id IS NULL
			CONTINUE
		EXEC InsertPersoane @detalii_id, @sport_fav_id, @id OUTPUT
		IF @id IS NULL
			CONTINUE
		SET @nr -= 1
	END
	PRINT 'Persoane were generated!'
END

GO
CREATE OR ALTER PROCEDURE AddCluburi
	@nr INT AS
BEGIN
	DECLARE @den VARCHAR(MAX), @descr VARCHAR(MAX), @id INT
	PRINT 'Generating '+CAST(@nr AS VARCHAR)+' cluburi...'
	WHILE @nr > 0
	BEGIN
		EXEC RandNume @den OUTPUT
		EXEC RandText @descr OUTPUT
		EXEC InsertClubui @den, @descr, @id OUTPUT
		IF @id IS NULL
			CONTINUE
		SET @nr -= 1
	END
	PRINT 'Cluburi were generated!'
END

GO
CREATE OR ALTER PROCEDURE AddPersoaneCluburi 
	@nr INT AS
BEGIN
	DECLARE @moment DATETIME, @pers_id INT, @club_id INT, @ok BIT = 0
	PRINT 'Generating '+CAST(@nr AS VARCHAR)+' persoane cluburi...'
	WHILE @nr > 0
	BEGIN
		EXEC RandId 'Persoane', @pers_id OUTPUT
		EXEC RandId 'Cluburi', @club_id OUTPUT
		EXEC RandData @moment OUTPUT, 2021, 2022
		EXEC InsertPersoaneCluburi @pers_id, @club_id, @moment, @ok OUTPUT
		IF @ok = 0
			CONTINUE
		SET @nr -= 1
	END
	PRINT 'PersoaneCluburi were generated!'
END