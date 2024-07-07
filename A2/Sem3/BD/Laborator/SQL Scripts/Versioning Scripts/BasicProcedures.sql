DROP PROCEDURE ModifyColumn;
DROP PROCEDURE AddDefault;
DROP PROCEDURE RemoveDefault;
DROP PROCEDURE CreateTable;
DROP PROCEDURE DropTable;
DROP PROCEDURE AddColumn;
DROP PROCEDURE RemoveColumn;
DROP PROCEDURE AddFK;
DROP PROCEDURE RemoveFK;
GO

CREATE PROCEDURE ModifyColumn
	@table VARCHAR(100),
	@column VARCHAR(100),
	@type VARCHAR(100)
AS
BEGIN
	--DECLARE @SQLString NVARCHAR(200)
	--DECLARE @ParamDef NVARCHAR(100)
	--SET @SQLString = N'ALTER TABLE @ptable ALTER COLUMN @pcolumn @ptype'
	--SET @ParamDef = N'@ptable VARCHAR(100), @pcolumn VARCHAR(100), @ptype VARCHAR(100)'
	--EXEC sp_executesql @SQLString, @ParamDef, @table, @column, @type
	DECLARE @SQL NVARCHAR(500)
	DECLARE @UndoSQL NVARCHAR(500)

	DECLARE @coltype VARCHAR(100)  --column initial type
	EXEC GetColumnType @table, @column, @coltype OUTPUT

	SET @SQL = 'ALTER TABLE '+@table+' ALTER COLUMN '+@column+' '+@type
	SET @UndoSQL = 'ALTER TABLE '+@table+' ALTER COLUMN '+@column+' '+@coltype
	EXEC(@SQL)

	-- store version
	EXEC StoreNewVersion @SQL, @UndoSQL
END
GO

CREATE PROC AddDefault 
	@table VARCHAR(100),
	@column VARCHAR(100),
	@value NVARCHAR(100)
AS
BEGIN
	DECLARE @SQL NVARCHAR(500)
	DECLARE @UndoSQL NVARCHAR(500)
	DECLARE @name VARCHAR(100)
	SET @name = 'df_'+@table+'_'+@column
	SET @SQL='ALTER TABLE '+@table+' ADD CONSTRAINT '+@name+' DEFAULT '+char(39)+@value+char(39)+' FOR '+@column
	SET @UndoSQL='ALTER TABLE '+@table+' DROP CONSTRAINT '+@name
	EXEC(@SQL)

	EXEC StoreNewVersion @SQL, @UndoSQL
END
GO

CREATE PROC RemoveDefault
	@table VARCHAR(100),
	@column VARCHAR(100)
AS
BEGIN 
	DECLARE @SQL NVARCHAR(500)
	DECLARE @UndoSQL NVARCHAR(500)
	DECLARE @name VARCHAR(100), @value VARCHAR(100)

	SELECT @value=COLUMN_DEFAULT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME=@table AND COLUMN_NAME=@column
	IF @value IS NULL
	BEGIN
		DECLARE @MsgError VARCHAR(100)
		SET @MsgError = 'NU pot determina valoarea default al campului '+@column+' din tabela '+@table
		RAISERROR(@MsgError, 18, 0)
	END

	DECLARE @vallen INT = LEN(@value)
	SET @vallen -= 2
	SET @value = SUBSTRING(@value, 2, @vallen)

	SET @name = 'df_'+@table+'_'+@column
	SET @SQL = 'ALTER TABLE '+@table+' DROP CONSTRAINT '+@name
	SET @UndoSQL = 'ALTER TABLE '+@table+' ADD CONSTRAINT '+@name+' DEFAULT '+@value+' FOR '+@column
	EXEC(@SQL)

	EXEC StoreNewVersion @SQL, @UndoSQL
END
GO

CREATE PROC CreateTable
	@table VARCHAR(100),
	@fields VARCHAR(300)
AS
BEGIN
	DECLARE @SQL NVARCHAR(500)
	DECLARE @UndoSQL NVARCHAR(500)
	SET @SQL = 'CREATE TABLE '+@table+'('+@fields+')'
	SET @UndoSQL = 'DROP TABLE '+@table
	EXEC(@SQL)

	EXEC StoreNewVersion @SQL, @UndoSQL
END
GO

CREATE PROCEDURE DropTable 
	@table VARCHAR(100),
	@fields VARCHAR(300)
AS
BEGIN
	DECLARE @SQL NVARCHAR(500)
	DECLARE @UndoSQL NVARCHAR(500)
	
	--DECLARE @fields VARCHAR(300)
	--EXEC GetTableFields @table, @fields OUTPUT

	SET @SQL = 'DROP TABLE '+@table
	SET @UndoSQL = 'CREATE TABLE '+@table+'('+@fields+')'
	EXEC(@SQL)

	EXEC StoreNewVersion @SQL, @UndoSQL
END
GO

CREATE PROCEDURE AddColumn
	@table VARCHAR(100),
	@column VARCHAR(100),
	@type VARCHAR(100)
AS
BEGIN
	DECLARE @SQL NVARCHAR(500)
	DECLARE @UndoSQL NVARCHAR(500)
	SET @SQL = 'ALTER TABLE '+@table+' ADD '+@column+' '+@type
	SET @UndoSQL = 'ALTER TABLE '+@table+' DROP COLUMN '+@column
	EXEC(@SQL)

	EXEC StoreNewVersion @SQL, @UndoSQL
END
GO

CREATE PROCEDURE RemoveColumn
	@table VARCHAR(100),
	@column VARCHAR(100)
AS
BEGIN
	DECLARE @SQL NVARCHAR(500)
	DECLARE @UndoSQL NVARCHAR(500)
	DECLARE @type VARCHAR(100)
	
	EXEC GetColumnType @table, @column, @type OUTPUT
	SET @SQL = 'ALTER TABLE '+@table+' DROP COLUMN '+@column
	SET @UndoSQL = 'ALTER TABLE '+@table+' ADD '+@column+' '+@type
	EXEC(@SQL)

	EXEC StoreNewVersion @SQL, @UndoSQL
END
GO

CREATE PROCEDURE AddFK
	@table VARCHAR(100),
	@column VARCHAR(100),
	@refTable VARCHAR(100),
	@refColumn VARCHAR(100),
	@name VARCHAR(100) = NULL
AS
BEGIN
	DECLARE @SQL NVARCHAR(500)
	DECLARE @UndoSQL NVARCHAR(500)
	IF @name IS NULL
		SET @name = 'FK__'+@table+'_'+@column+'__'+@refTable+'_'+@refColumn
	
	SET @SQL = 'ALTER TABLE '+@table+' ADD CONSTRAINT '+@name+' FOREIGN KEY('+@column+') REFERENCES '+@refTable+'('+@refColumn+')'
	SET @UndoSQL = 'ALTER TABLE '+@table+' DROP CONSTRAINT '+@name
	EXEC(@SQL)

	EXEC StoreNewVersion @SQL, @UndoSQL
END
GO

CREATE PROCEDURE RemoveFK
	@table VARCHAR(100),
	@column VARCHAR(100),
	@refTable VARCHAR(100),
	@refColumn VARCHAR(100),
	@name VARCHAR(100) = NULL
AS
BEGIN
	DECLARE @SQL NVARCHAR(500)
	DECLARE @UndoSQL NVARCHAR(500)
	IF @name IS NULL
		SET @name = 'FK__'+@table+'_'+@column+'__'+@refTable+'_'+@refColumn

	SET @SQL = 'ALTER TABLE '+@table+' DROP CONSTRAINT '+@name
	SET @UndoSQL = 'ALTER TABLE '+@table+' ADD CONSTRAINT '+@name+' FOREIGN KEY('+@column+') REFERENCES '+@refTable+'('+@refColumn+')'
	EXEC(@SQL)

	EXEC StoreNewVersion @SQL, @UndoSQL
END
GO
