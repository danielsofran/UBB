IF TYPE_ID('String') IS NULL
	CREATE TYPE String FROM VARCHAR(100) NOT NULL

DROP FUNCTION udf_GetFK
DROP FUNCTION udf_GetPK
DROP FUNCTION udf_IsExcludedTable
DROP FUNCTION udf_GetNrFK
DROP FUNCTION udf_GetColumnType
DROP FUNCTION udf_DateToStr
DROP FUNCTION udf_GetUniqueFields
DROP PROCEDURE GetFKVal
DROP PROCEDURE GetTableFields

GO
CREATE FUNCTION udf_GetFK(@table String) RETURNS TABLE AS
RETURN SELECT  obj.name AS FK_NAME,
    tab1.name AS [table],
    col1.name AS [column],
    tab2.name AS [referenced_table],
    col2.name AS [referenced_column]
FROM sys.foreign_key_columns fkc
INNER JOIN sys.objects obj
    ON obj.object_id = fkc.constraint_object_id
INNER JOIN sys.tables tab1
    ON tab1.object_id = fkc.parent_object_id
INNER JOIN sys.schemas sch
    ON tab1.schema_id = sch.schema_id
INNER JOIN sys.columns col1
    ON col1.column_id = parent_column_id AND col1.object_id = tab1.object_id
INNER JOIN sys.tables tab2
    ON tab2.object_id = fkc.referenced_object_id
INNER JOIN sys.columns col2
    ON col2.column_id = referenced_column_id AND col2.object_id = tab2.object_id
WHERE tab1.name=@table

GO
CREATE FUNCTION udf_IsExcludedTable(@tablename String) RETURNS BIT AS
BEGIN
	IF @tablename IN ('Tests', 'Views', 'Tables', 'TestTables', 'TestViews', 'TestRunTables', 'TestRunViews', 'TestRuns')
		RETURN 1
	RETURN 0
END

GO 
CREATE FUNCTION udf_GetPK(@tablename String) RETURNS TABLE
	AS RETURN SELECT K.COLUMN_NAME as COL
	FROM    INFORMATION_SCHEMA.TABLE_CONSTRAINTS AS C
			JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE AS K ON C.TABLE_NAME = K.TABLE_NAME
															 AND C.CONSTRAINT_CATALOG = K.CONSTRAINT_CATALOG
															 AND C.CONSTRAINT_SCHEMA = K.CONSTRAINT_SCHEMA
															 AND C.CONSTRAINT_NAME = K.CONSTRAINT_NAME
	WHERE   C.CONSTRAINT_TYPE = 'PRIMARY KEY'
			AND C.TABLE_NAME=@tablename;

GO
CREATE PROCEDURE GetTableFields 
	@table VARCHAR(100), @fields VARCHAR(300) OUTPUT, @createfields VARCHAR(300) OUTPUT AS
BEGIN
	DECLARE @colname VARCHAR(100), @type VARCHAR(100), @colmaxlen INT
	DECLARE cursor_is CURSOR FOR 
		SELECT COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME=@table
	SET @fields = ''
	SET @createfields = ''
	
	OPEN cursor_is
	FETCH NEXT FROM cursor_is INTO @colname, @type, @colmaxlen
	WHILE @@FETCH_STATUS = 0
	BEGIN
		IF NOT EXISTS(SELECT * FROM udf_GetPK(@table) WHERE COL=@colname)
		BEGIN
			IF @fields = ''
				SET @fields += @colname
			ELSE
				SET @fields += ','+@colname
		END
		IF @type LIKE '%char' AND @colmaxlen IS NOT NULL
			SET @createfields += @colname+' '+@type+'('+CAST(@colmaxlen AS VARCHAR)+'), '
		ELSE
			SET @createfields += @colname+' '+@type+', '
		FETCH NEXT FROM cursor_is INTO @colname, @type, @colmaxlen
	END
	CLOSE cursor_is
	DEALLOCATE cursor_is
END

GO
CREATE FUNCTION udf_GetNrFK(@table String) RETURNS INT AS
BEGIN
	IF (SELECT COUNT(*) FROM udf_GetFK(@table)) = 0
		RETURN 0
	DECLARE @Rez INT
	SET @Rez = 0
	DECLARE @col String
	DECLARE @reftable String
	DECLARE @refcol String

	DECLARE cursorFK CURSOR FOR
		SELECT [column], referenced_table, referenced_column FROM udf_GetFK(@table)
	OPEN cursorFK
	FETCH NEXT FROM cursorFK INTO @col, @reftable, @refcol
	WHILE @@FETCH_STATUS = 0
	BEGIN
		SET @Rez += 1 + dbo.udf_GetNrFK(@reftable)
		FETCH NEXT FROM cursorFK INTO @col, @reftable, @refcol
	END
	CLOSE cursorFK
	DEALLOCATE cursorFK
	RETURN @Rez
END

GO

CREATE FUNCTION udf_GetColumnType(@table VARCHAR(100), @column VARCHAR(100))
	RETURNS VARCHAR(100)
AS
BEGIN
	DECLARE @columntype VARCHAR(100);
	DECLARE @columninf VARCHAR(100);
	SELECT TOP 1 @columntype=DATA_TYPE, @columninf=CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME=@table AND COLUMN_NAME=@column
	IF @columntype LIKE '%char' AND @columninf IS NOT NULL
		RETURN @columntype + '('+@columninf+')'
	RETURN @columntype
END
GO

CREATE PROCEDURE GetFKVal
	@table String,
	@col String,
	@rez VARCHAR(100) OUTPUT
AS
BEGIN
	DECLARE @refT String = NULL
	DECLARE @refC String = NULL
	DECLARE @refCType String = NULL
	SELECT @refT=referenced_table, @refC=referenced_column FROM dbo.udf_GetFK(@table) WHERE [column]=@col
	SET @refCType = dbo.udf_GetColumnType(@refT, @refC)
	--SELECT @refC, @refT, @refCType

	DECLARE @SQL NVARCHAR(MAX)
	DECLARE @whereExcluded NVARCHAR(MAX) = N''
	SELECT @whereExcluded=colvals FROM CacheExcluded WHERE tablename=@refT AND colname=@refC
	IF @whereExcluded <> ''
		SET @whereExcluded = 'WHERE CHARINDEX(CAST('+@refC+' as VARCHAR), '+char(39)+@whereExcluded+char(39)+') <= 0'
	--PRINT 'WhereExcluded: '+@whereExcluded
	SET @SQL = 'SELECT TOP 1 @result=CAST('+@refC+' as VARCHAR(100)) FROM '+@refT+' '+@whereExcluded+' ORDER BY NEWID()'
	EXECUTE sp_executesql @SQL, N'@result VARCHAR(100) OUTPUT', @result=@rez OUTPUT
	IF @rez IS NOT NULL
	BEGIN
		
		IF EXISTS(SELECT * FROM dbo.udf_GetUniqueFields(@table) WHERE COL=@col)
			IF EXISTS(SELECT * FROM CacheExcluded WHERE tablename=@refT AND colname=@refC)
				UPDATE CacheExcluded SET colvals+=@rez+',' WHERE tablename=@refT AND colname=@refC
			ELSE
				INSERT INTO CacheExcluded(tablename, colname, colvals) VALUES(@refT, @refC, @rez)
	END
	ELSE
	BEGIN
		DECLARE @Error VARCHAR(100)='Prea putine date in tabela din care iau FK-ul pt tabela '+@table
		RAISERROR(@Error, 17, 1)
	END
END

GO
CREATE FUNCTION udf_DateToStr(@date DATETIME) RETURNS VARCHAR(100)
AS 
BEGIN
	RETURN FORMAT(@date, 'dd.MM.yyyy hh:mm:ss')
END

GO
CREATE FUNCTION udf_GetUniqueFields(@tablename String) RETURNS TABLE
AS RETURN SELECT cu.COLUMN_NAME as COL
FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc 
    inner join INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE cu 
        on cu.CONSTRAINT_NAME = tc.CONSTRAINT_NAME 
where 
    tc.CONSTRAINT_TYPE = 'UNIQUE'
    and tc.TABLE_NAME = @tablename
