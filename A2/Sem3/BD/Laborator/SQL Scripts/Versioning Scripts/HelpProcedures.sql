DROP PROCEDURE GetColumnType;
DROP PROCEDURE GetCurrentVersion;
DROP PROCEDURE ClearVersionCache;
DROP PROCEDURE StoreNewVersion;
DROP PROCEDURE CheckIfNull;
DROP PROCEDURE GetTableFields;
GO

CREATE PROCEDURE GetColumnType
	@table VARCHAR(100),
	@column VARCHAR(100),
	@type VARCHAR(100) OUTPUT
AS
BEGIN
	DECLARE @columntype VARCHAR(100);
	DECLARE @columninf VARCHAR(100);
	SELECT TOP 1 @columntype=DATA_TYPE, @columninf=CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME=@table AND COLUMN_NAME=@column
	IF @columntype LIKE '%char' AND @columninf IS NOT NULL
		SET @type = @columntype + '('+@columninf+')'
	ELSE
		SET @type = @columntype
END
GO

CREATE PROCEDURE GetCurrentVersion @versiune INT OUTPUT 
AS
BEGIN
	SELECT @versiune=versiune FROM VersiuniBD WHERE is_current = 1
END
GO

CREATE PROCEDURE ClearVersionCache
AS
BEGIN
	DELETE FROM VersiuniBD WHERE versiune != 0
	UPDATE VersiuniBD SET is_current = 1
	DELETE FROM ComenziSQL
END
GO

CREATE PROCEDURE CheckIfNull
	@SQL NVARCHAR(500),
	@UndoSQL NVARCHAR(500)
AS
BEGIN
	IF @SQL IS NULL
		RAISERROR('Comanda @SQL este NULL!', 17, 0)
	IF @UndoSQL IS NULL
		RAISERROR('Comanda @UndoSQL este NULL!', 17, 0)
END
GO

CREATE PROCEDURE StoreNewVersion
	@DoSQL NVARCHAR(500),
	@UndoSQL NVARCHAR(500)
AS
BEGIN
	EXEC CheckIfNull @DoSQL, @UndoSQL

	DECLARE @versiune INT
	DECLARE @do_id INT = NULL
	DECLARE @undo_id INT = NULL
	
	EXEC GetCurrentVersion @versiune OUTPUT
	DELETE FROM VersiuniBD WHERE versiune > @versiune  --stergem redo-urile
	UPDATE VersiuniBD SET is_current = 0 WHERE versiune = @versiune
	
	SET @versiune = @versiune+1
	--PRINT 'Versiune noua: '+CAST(@versiune AS VARCHAR)

	-- iau sau inserez comanda do
	SELECT TOP 1 @do_id=id FROM ComenziSQL WHERE sqlstring=@DoSQL
	IF @do_id IS NULL
	BEGIN
		INSERT INTO ComenziSQL(sqlstring) VALUES(@DoSQL)
		SELECT TOP 1 @do_id=id FROM ComenziSQL ORDER BY id DESC  --ultima adaugata
	END

	-- iau sau inserez comanda undo
	SELECT TOP 1 @undo_id=id FROM ComenziSQL WHERE sqlstring=@UndoSQL
	IF @undo_id IS NULL
	BEGIN
		INSERT INTO ComenziSQL(sqlstring) VALUES(@UndoSQL)
		SELECT TOP 1 @undo_id=id FROM ComenziSQL ORDER BY id DESC  --ultima adaugata
	END
	--PRINT 'ID-urile comenzilor: '+CAST(@do_id AS VARCHAR)+';'+CAST(@undo_id AS VARCHAR)
	INSERT INTO VersiuniBD(versiune, do_id, undo_id) VALUES(@versiune, @do_id, @undo_id)
	UPDATE VersiuniBD SET is_current=1 WHERE versiune = @versiune
END
GO

CREATE PROCEDURE GetTableFields 
	@table VARCHAR(100), @fields VARCHAR(300) OUTPUT AS
BEGIN
	DECLARE @colname VARCHAR(100), @type VARCHAR(100), @colmaxlen INT
	DECLARE cursor_is CURSOR FOR 
		SELECT COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME=@table
	SET @fields = ''
	
	OPEN cursor_is
	FETCH NEXT FROM cursor_is INTO @colname, @type, @colmaxlen
	WHILE @@FETCH_STATUS = 0
	BEGIN
		IF @type LIKE '%char' AND @colmaxlen IS NOT NULL
			SET @fields += @colname+' '+@type+'('+CAST(@colmaxlen AS VARCHAR)+'), '
		ELSE
			SET @fields += @colname+' '+@type+', '
		FETCH NEXT FROM cursor_is INTO @colname, @type, @colmaxlen
	END
	CLOSE cursor_is
	DEALLOCATE cursor_is
END