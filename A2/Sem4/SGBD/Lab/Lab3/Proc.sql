CREATE OR ALTER PROCEDURE InsertPersoanaClub
	-- persoana
	@nume VARCHAR(MAX),
	@email VARCHAR(MAX),
	@dateBirth DATETIME,
	-- club
	@denumire VARCHAR(MAX),
	@descriere VARCHAR(MAX),
	-- persoana club
	@dateJoined DATETIME
AS
BEGIN
	DECLARE @dp_id INT, @p_id INT, @c_id INT,  @msg VARCHAR(MAX)
	BEGIN TRAN;
	SET @msg = 'Transaction type 1 began;'
	BEGIN TRY
		IF dbo.ValidPersoana(@nume, @descriere, @dateBirth) = 0
			THROW 50001, 'Persoana invalida', 1

		INSERT INTO dbo.DetaliiPersoane(nume, email, data_nast)
			VALUES(@nume, @descriere, @dateBirth)
		SET @dp_id = @@IDENTITY
		SET @msg += 'Detalii persoana with id '+CAST(@dp_id AS VARCHAR)+' inserted;'
		
		INSERT INTO dbo.Persoane(detalii_id, sport_fav_id) VALUES(@dp_id, NULL)
		SET @p_id = @@IDENTITY
		SET @msg += 'Persoana with id '+CAST(@p_id AS VARCHAR)+' inserted;'

		IF dbo.ValidClub(@denumire, @descriere) = 0
			THROW 50002, 'Club invalid', 1
		INSERT INTO dbo.Cluburi(denumire, descriere) VALUES(@denumire, @descriere)
		SET @c_id = @@IDENTITY
		SET @msg += 'Club with id '+CAST(@c_id AS VARCHAR)+' inserted;'

		IF dbo.ValidPersoanaClub(@dateBirth, @dateJoined) = 0
			THROW 50003, 'Persoana Club invalida', 1
		INSERT INTO dbo.PersoaneCluburi(persoana_id, club_id, moment_join)
			VALUES(@p_id, @c_id, @dateJoined)

		SET @msg += 'Persoana Club inserted;'
		SET @msg += 'Transaction committed'

		EXEC AddLog @msg
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		SET @msg += 'ROOLING BACK' 
		IF @@TRANCOUNT > 0
			ROLLBACK TRAN
		EXEC AddLog @msg
	END CATCH
END
GO

CREATE OR ALTER PROCEDURE InsertPersoanaClubV2
	-- persoana
	@nume VARCHAR(MAX),
	@email VARCHAR(MAX),
	@dateBirth DATETIME,
	-- club
	@denumire VARCHAR(MAX),
	@descriere VARCHAR(MAX),
	-- persoana club
	@dateJoined DATETIME
AS
BEGIN
	DECLARE @dp_id INT, @p_id INT, @c_id INT, @lst_svp INT = 0, @msg VARCHAR(MAX)
	BEGIN TRAN;
	SET @msg = 'Transaction type 2 began;'
	BEGIN TRY
		IF dbo.ValidPersoana(@nume, @descriere, @dateBirth) = 0
			THROW 50001, 'Persoana invalida', 1

		INSERT INTO dbo.DetaliiPersoane(nume, email, data_nast)
			VALUES(@nume, @descriere, @dateBirth)
		SET @dp_id = @@IDENTITY

		SAVE TRAN detaliipersoana
		SET @lst_svp = 1
		SET @msg += 'Detalii persoana with id '+CAST(@dp_id AS VARCHAR)+' inserted;'
		
		INSERT INTO dbo.Persoane(detalii_id, sport_fav_id) VALUES(@dp_id, NULL)
		SET @p_id = @@IDENTITY

		SAVE TRAN persoana
		SET @lst_svp = 2
		SET @msg += 'Persoana with id '+CAST(@p_id AS VARCHAR)+' inserted;'

		IF dbo.ValidClub(@denumire, @descriere) = 0
			THROW 50002, 'Club invalid', 1
		INSERT INTO dbo.Cluburi(denumire, descriere) VALUES(@denumire, @descriere)
		SET @c_id = @@IDENTITY

		SAVE TRAN club
		SET @msg += 'Club with id '+CAST(@c_id AS VARCHAR)+' inserted;'
		SET @lst_svp = 3

		IF dbo.ValidPersoanaClub(@dateBirth, @dateJoined) = 0
			THROW 50003, 'Persoana Club invalida', 1
		INSERT INTO dbo.PersoaneCluburi(persoana_id, club_id, moment_join)
			VALUES(@p_id, @c_id, @dateJoined)

		SET @msg += 'Persoana Club with id '+CAST(@c_id AS VARCHAR)+' inserted;'
		SET @msg += 'Transaction Committed'

		EXEC AddLog @msg
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		SET @msg += 'ROOLING BACK '+CAST(@lst_svp AS VARCHAR)+' inserts' 
		IF @lst_svp = 0
		BEGIN
			ROLLBACK TRAN
			EXEC AddLog @msg
		END
		ELSE 
		BEGIN
			IF @lst_svp = 1
				ROLLBACK TRAN detaliipersoana
			ELSE IF @lst_svp = 2
				ROLLBACK TRAN persoana
			ELSE IF @lst_svp = 3
				ROLLBACK TRAN club
			EXEC AddLog @msg
			COMMIT TRAN
		END
	END CATCH
END
GO