DROP PROCEDURE GotoVersion;
GO

CREATE PROCEDURE GotoVersion 
	@version INT
AS
BEGIN
	DECLARE @errormsg NVARCHAR(200)
	IF NOT EXISTS(SELECT versiune FROM VersiuniBD WHERE versiune=@version)
	BEGIN
		SET @errormsg = 'Nu exista backup pentru versiunea '+CAST(@version AS VARCHAR)
		RAISERROR(@errormsg, 18, 0)
		RETURN 1
	END

	DECLARE @currentversion INT
	EXEC GetCurrentVersion @currentversion OUTPUT

	IF @currentversion = @version
	BEGIN
		PRINT 'Aceasta este versiunea curenta a bazei de date. Nu exista modificari de efectuat.'
		RETURN 0
	END

	DECLARE @nextversion INT, @cmdid INT, @SQL NVARCHAR(500)

	WHILE @currentversion < @version -- redo
	BEGIN
		-- get the next version
		SELECT TOP 1 @nextversion=versiune, @cmdid=do_id FROM VersiuniBD WHERE versiune > @currentversion ORDER BY versiune
		
		-- no version found or 0 reached
		IF @cmdid IS NULL 
		BEGIN
			IF @nextversion != 0
			BEGIN
				RAISERROR('cmdid is NULL and nextversion is not 0 in undo WHILE', 19, 0)
				RETURN 1
			END
			UPDATE VersiuniBD SET is_current = 1 WHERE versiune = 0
			RETURN 0
		END

		-- get the command for cmdid
		SELECT TOP 1 @SQL=sqlstring FROM ComenziSQL WHERE id=@cmdid

		-- no command found
		IF @SQL IS NULL
		BEGIN
			SET @errormsg = 'No SQL command for id '+@cmdid
			RAISERROR(@errormsg, 18, 0)
			RETURN 1
		END

		-- try execute command
		BEGIN TRY
			EXEC(@SQL)
		END TRY
		BEGIN CATCH
			SET @errormsg = 'Eroare la EXEC: '+ERROR_MESSAGE()
			RAISERROR(@errormsg, 18, 0)
			RETURN 1
		END CATCH

		-- success, actualizam versiunea curenta
		UPDATE VersiuniBD SET is_current = 0 WHERE versiune=@currentversion
		UPDATE VersiuniBD SET is_current = 1 WHERE versiune=@nextversion
		SET @currentversion = @nextversion
		SET @cmdid = NULL
		SET @SQL = NULL
	END

	WHILE @currentversion > @version -- undo
	BEGIN
		SELECT @cmdid=undo_id FROM VersiuniBD WHERE versiune = @currentversion

		-- no version found or 0 reached
		IF @cmdid IS NULL 
		BEGIN
			IF @currentversion != 0
			BEGIN
				RAISERROR('cmdid is NULL and nextversion is not 0 in undo WHILE', 18, 0)
				RETURN 1
			END
			UPDATE VersiuniBD SET is_current = 1 WHERE versiune = 0
			RETURN 0
		END

		-- get the command for cmdid
		SELECT TOP 1 @SQL=sqlstring FROM ComenziSQL WHERE id=@cmdid
		--SELECT @SQL as 'SQL'

		-- no command found
		IF @SQL IS NULL
		BEGIN
			SET @errormsg = 'No SQL command for id '+@cmdid
			RAISERROR(@errormsg, 18, 0)
			RETURN 1
		END

		-- try execute command
		BEGIN TRY
			EXEC(@SQL)
		END TRY
		BEGIN CATCH
			SET @errormsg = ''+@SQL
			RAISERROR(@errormsg, 18, 0)
			RETURN 1
		END CATCH

		-- get the 'next' version, which is actually the previous one
		SELECT TOP 1 @nextversion=versiune FROM VersiuniBD WHERE versiune < @currentversion ORDER BY versiune DESC
		--SELECT @currentversion as 'currentversion', @nextversion as 'nextversion' 

		-- success, actualizam versiunea curenta
		UPDATE VersiuniBD SET is_current = 0 WHERE versiune=@currentversion
		UPDATE VersiuniBD SET is_current = 1 WHERE versiune=@nextversion
		SET @currentversion = @nextversion
		SET @cmdid = NULL
		SET @SQL = NULL
	END
END