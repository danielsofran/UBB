CREATE OR ALTER PROCEDURE CreateForPair
	@scoala NVARCHAR(MAX),
	@echipa NVARCHAR(MAX)
AS
BEGIN
	SET NOCOUNT ON
	-- adaugam echipa scolii
	INSERT INTO Echipe(nume) VALUES(@echipa)
	DECLARE @idEchipa INT = SCOPE_IDENTITY()

	-- generam elevi pt scoala respectiva
	DECLARE @nrElevi INT
	EXEC RandInt 30, 40, @nrElevi OUTPUT
	WHILE @nrElevi > 0
	BEGIN
		DECLARE @numeElev NVARCHAR(MAX)
		SELECT TOP 1 @numeElev=nume FROM SugestiiNumeElevi ORDER BY NEWID()
		INSERT INTO Elev(nume, scoala) VALUES(@numeElev, @scoala)
		SET @nrElevi -= 1
	END

	-- unii elevi sunt jucatori
	DECLARE @nrJucatori INT = 15
	WHILE @nrJucatori > 0
	BEGIN
		DECLARE @idElev INT
		SELECT TOP 1 @idElev=id FROM Elev WHERE scoala=@scoala AND
			id NOT IN (SELECT idElev FROM Jucator WHERE idEchipa=@idEchipa)
			ORDER BY NEWID()
		BEGIN TRY
			INSERT INTO Jucator(idElev, idEchipa) VALUES(@idElev, @idEchipa)
		END TRY
		BEGIN CATCH
			CONTINUE
		END CATCH
		SET @nrJucatori -= 1
	END
END

GO
CREATE OR ALTER PROCEDURE CreateMeciuri @nr INT AS
BEGIN
	DELETE FROM JucatorActiv
	DELETE FROM Meci
	WHILE @nr > 0
	BEGIN
		DECLARE @idE1 INT, @idE2 INT, @data DATETIME
		SELECT TOP 1 @idE1=id FROM Echipe ORDER BY NEWID()
		SELECT TOP 1 @idE2=id FROM Echipe WHERE id != @idE1 ORDER BY NEWID()
		EXEC RandData @data OUTPUT, 2020, 2022
		BEGIN TRY
			INSERT INTO Meci(idEchipa1, idEchipa2, [data]) VALUES(@idE1, @idE2, @data)
		END TRY
		BEGIN CATCH
			CONTINUE
		END CATCH
		SET @nr -= 1
	END
END

GO
CREATE OR ALTER PROCEDURE CreatePlayersForMeci
	@idMeci INT, @idEchipa INT
AS
BEGIN
	DECLARE @idJucator INT, @pct INT, @k INT

	-- a) jucatorii de pe teren ai echipei
	DECLARE echipaA CURSOR FAST_FORWARD FOR 
		SELECT TOP 8 Jucator.idElev FROM Jucator WHERE idEchipa=@idEchipa
			ORDER BY idElev
	OPEN echipaA
	FETCH NEXT FROM echipaA INTO @idJucator
	WHILE @@FETCH_STATUS = 0
	BEGIN
		-- idJucator Activ participant
		EXEC RandInt 1, 11, @k OUTPUT
		SELECT @pct=CHOOSE(@k, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 3)
		INSERT INTO JucatorActiv(idJucator, idMeci, nrPuncteInscrise, tip)
			VALUES(@idJucator, @idMeci, @pct, 1)
		FETCH NEXT FROM echipaA INTO @idJucator
	END
	CLOSE echipaA
	DEALLOCATE echipaA

	-- b) jucatorii rezerva ai echipei
	DECLARE echipaR CURSOR FAST_FORWARD FOR 
		SELECT TOP 6 Jucator.idElev FROM Jucator WHERE idEchipa=@idEchipa
			ORDER BY idElev DESC
	OPEN echipaR
	FETCH NEXT FROM echipaR INTO @idJucator
	WHILE @@FETCH_STATUS = 0
	BEGIN
		-- idJucator Activ rezerva
		EXEC RandInt 1, 11, @k OUTPUT
		SELECT @pct=CHOOSE(@k, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)
		INSERT INTO JucatorActiv(idJucator, idMeci, nrPuncteInscrise, tip)
			VALUES(@idJucator, @idMeci, @pct, 0)
		FETCH NEXT FROM echipaR INTO @idJucator
	END
	CLOSE echipaR
	DEALLOCATE echipaR
END

GO
CREATE OR ALTER PROCEDURE CreateActivePlayers
AS
BEGIN
	DELETE FROM JucatorActiv
	DECLARE @playersPerTeam INT = 15, @idMeci INT, @idEchipa1 INT, @idEchipa2 INT
	DECLARE meciuri CURSOR FAST_FORWARD FOR
		SELECT id, idEchipa1, idEchipa2 FROM Meci
	OPEN meciuri
	FETCH NEXT FROM meciuri INTO @idMeci, @idEchipa1, @idEchipa2
	WHILE @@FETCH_STATUS = 0
	BEGIN
		-- generam jucatori activi la meci
		-- 1. care joaca la echipa1, dintre cei care joaca la echipa 1
		-- 2. care joaca la echipa2, dinre cei care joaca la echipa 2
		EXEC CreatePlayersForMeci @idMeci, @idEchipa1
		EXEC CreatePlayersForMeci @idMeci, @idEchipa2
		FETCH NEXT FROM meciuri INTO @idMeci, @idEchipa1, @idEchipa2
	END
	CLOSE meciuri
	DEALLOCATE meciuri
END