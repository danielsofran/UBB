DROP PROCEDURE PrepareInsertProc;
DROP PROCEDURE CreateInserts;
DROP PROCEDURE RunTableTest;
DROP PROCEDURE RunViewTest;
DROP PROCEDURE CreateTest;
DROP PROCEDURE CreateTest2;
DROP PROCEDURE AddView;
DROP PROCEDURE AddTable;
DROP PROCEDURE RunTest;
GO

CREATE PROCEDURE PrepareInsertProc
	@tablename String,
	@fields VARCHAR(100) OUTPUT,
	@fieldvals VARCHAR(100) OUTPUT,
	@pkvals VARCHAR(100) OUTPUT
AS
BEGIN
	DECLARE @colname String
	DECLARE cursorCols CURSOR FOR 
		SELECT COLUMN_NAME AS COL FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME=@tablename
	
	SET @fields = ''
	SET @fieldvals = ''
	SET @pkvals = ''
	
	OPEN cursorCols
	FETCH NEXT FROM cursorCols INTO @colname
	WHILE @@FETCH_STATUS = 0
	BEGIN
		IF EXISTS(SELECT * FROM dbo.udf_GetFK(@tablename) WHERE [column]=@colname)
		BEGIN
			-- get FK
			DECLARE @FKVal VARCHAR(100)
			EXEC GetFKVal @tablename, @colname, @FKVal OUTPUT
			--PRINT @FKVal
			-- append to field
			IF NOT(@fields = '')
			BEGIN
				SET @fields+=','
				SET @fieldvals+=','
			END
			SET @fields += @colname
			SET @fieldvals += @FKVal
			IF EXISTS(SELECT * FROM dbo.udf_GetPK(@tablename) WHERE COL=@colname )
				SET @pkvals+=@FKVal
		END
		ELSE IF NOT EXISTS(SELECT * FROM dbo.udf_GetPK(@tablename) WHERE COL=@colname )
		BEGIN -- data column
			IF NOT(@fields = '')
			BEGIN
				SET @fields+=','
				SET @fieldvals+=','
			END
			SET @fields += @colname
			SET @fieldvals += 'DEFAULT'
		END
		FETCH NEXT FROM cursorCols INTO @colname
	END
	CLOSE cursorCols
	DEALLOCATE cursorCols
END

GO
CREATE PROCEDURE CreateInserts
	@tablename String,
	@NrRows INT,
	@SQL VARCHAR(MAX) OUTPUT
AS
BEGIN
	DECLARE @NrRowsCpy INT = @NrRows
	DECLARE @pkvals VARCHAR(MAX) = ''
	DECLARE @fields VARCHAR(100) = ''
	DECLARE @fieldvals VARCHAR(100) = ''
	DECLARE @pkval VARCHAR(100) = ''
	DECLARE @pairs VARCHAR(MAX) = ''
	DECLARE @cumulative_errors INT = 0

	-- create cache table
	IF NOT EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='CacheExcluded')
	CREATE TABLE CacheExcluded(
		id INT PRIMARY KEY IDENTITY,
		tablename String NOT NULL,
		colname String NOT NULL,
		colvals NVARCHAR(MAX) NOT NULL,
	)
	DELETE FROM CacheExcluded -- clear cache

	SET @SQL=''
	WHILE @NrRows > 0
	BEGIN
		EXEC PrepareInsertProc @tablename, @fields OUTPUT, @fieldvals OUTPUT, @pkval OUTPUT
		IF CHARINDEX(@pkval, @pkvals) > 0
		BEGIN
			SET @cumulative_errors+=1
			--PRINT 'Errs: '+CAST(@cumulative_errors as VARCHAR)
			IF @cumulative_errors > @NrRowsCpy*@NrRowsCpy
			BEGIN
				PRINT 'Errs: '+CAST(@cumulative_errors as VARCHAR)
				DECLARE @Error VARCHAR(100)='Prea putine date in tabela din care iau FK-ul pt tabela '+@tablename
				RAISERROR(@Error, 17, 1)
				BREAK
			END
			CONTINUE -- duplicate pk, generate another
		END
		ELSE SET @cumulative_errors=0
		IF NOT(@pairs = '')
			SET @pairs+=', '
		--PRINT '('+@fieldvals+')'
		SET @pairs += '('+@fieldvals+')'
		SET @pkvals+=@pkval+','
		--PRINT 'Cache: '+@pkvals
		SET @NrRows -= 1
	END
	SET @SQL = 'INSERT INTO '+@tablename+'('+@fields+') VALUES '+@pairs
END

GO
CREATE PROCEDURE RunTableTest 
	@tablename String,
	@NrRows INT,
	@StartAt DATETIME OUTPUT,
	@EndAt DATETIME OUTPUT
AS
BEGIN
	DECLARE @DeleteSQL VARCHAR(100) = 'DELETE FROM '+@tablename
	DECLARE @InsertSQL VARCHAR(MAX)
	EXEC CreateInserts @tablename, @NrRows, @InsertSQL OUTPUT
	EXEC(@DeleteSQL)
	SET @StartAt = GETDATE()
	EXEC(@InsertSQL)
	SET @EndAt = GETDATE()
END

GO
CREATE PROCEDURE RunViewTest
	@tablename String,
	@StartAt DATETIME OUTPUT,
	@EndAt DATETIME OUTPUT
AS
BEGIN
	SET @StartAt = GETDATE()
	EXEC('SELECT * FROM '+@tablename)
	SET @EndAt = GETDATE()
END

GO
CREATE PROCEDURE AddView 
	@viewname NVARCHAR(50),
	@rez INT OUTPUT
AS
BEGIN
	SET @rez = -1
	SELECT @rez=ViewID FROM [Views] WHERE Name=@viewname
	IF @rez=-1
	BEGIN
		INSERT INTO [Views](Name) VALUES(@viewname)
		SELECT @rez=ViewID FROM [Views] WHERE Name=@viewname
	END
END

GO
CREATE PROCEDURE AddTable
	@tablename NVARCHAR(50),
	@rez INT OUTPUT
AS
BEGIN
	SET @rez = -1
	SELECT @rez=TableID FROM [Tables] WHERE Name=@tablename
	IF @rez=-1
	BEGIN
		INSERT INTO [Tables](Name) VALUES(@tablename)
		SELECT @rez=TableID FROM [Tables] WHERE Name=@tablename
	END
END

GO
CREATE PROCEDURE CreateTest 
	@testname NVARCHAR(50), 
	@NrRows INT,
	@strtables VARCHAR(MAX)
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @testid INT 
	INSERT INTO dbo.Tests(Name) VALUES(@testname)
	SELECT TOP 1 @testid=TestID FROM [Tests] WHERE name=@testname ORDER BY TestID DESC

	DECLARE @id INT

	DECLARE @tablename NVARCHAR(50)
	DECLARE cursorCreate CURSOR FOR
		SELECT value FROM string_split(@strtables, ' ') WHERE RTRIM(value) <> ''
	OPEN cursorCreate
	FETCH NEXT FROM cursorCreate INTO @tablename
	WHILE @@FETCH_STATUS=0
	BEGIN
		IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.VIEWS WHERE TABLE_NAME=@tablename)
		BEGIN
			EXEC AddView @tablename, @id OUTPUT
			INSERT INTO TestViews(TestID, ViewID) VALUES(@testid, @id)
		END
		ELSE IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME=@tablename)
		BEGIN
			EXEC AddTable @tablename, @id OUTPUT
			DECLARE @Pos INT = dbo.udf_GetNrFK(@tablename)
			INSERT INTO TestTables(TestID, TableID, Position, NoOfRows) VALUES(@testid, @id, @Pos, @NrRows)
		END
		ELSE
		BEGIN
			DECLARE @Error VARCHAR(100) = @tablename+' nu e nici View nici Tabel!'
			RAISERROR(@Error, 17, 1)
			BREAK
		END
		FETCH NEXT FROM cursorCreate INTO @tablename
	END
	CLOSE cursorCreate
	DEALLOCATE cursorCreate
END

GO
CREATE PROCEDURE CreateTest2
	@testname NVARCHAR(50), 
	@strtables VARCHAR(MAX)
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @testid INT 
	INSERT INTO dbo.Tests(Name) VALUES(@testname)
	SELECT TOP 1 @testid=TestID FROM [Tests] WHERE name=@testname ORDER BY TestID DESC

	DECLARE @id INT

	DECLARE @tablename NVARCHAR(50)
	DECLARE cursorCreate CURSOR FOR
		SELECT value FROM string_split(@strtables, ' ') WHERE RTRIM(value) <> ''
	OPEN cursorCreate
	FETCH NEXT FROM cursorCreate INTO @tablename
	WHILE @@FETCH_STATUS=0
	BEGIN
		IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.VIEWS WHERE TABLE_NAME=@tablename)
		BEGIN
			EXEC AddView @tablename, @id OUTPUT
			INSERT INTO TestViews(TestID, ViewID) VALUES(@testid, @id)
		END
		ELSE IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME=@tablename)
		BEGIN
			EXEC AddTable @tablename, @id OUTPUT
			DECLARE @Pos INT = dbo.udf_GetNrFK(@tablename)
			DECLARE @NrRows INT
			FETCH NEXT FROM cursorCreate INTO @NrRows
			INSERT INTO TestTables(TestID, TableID, Position, NoOfRows) VALUES(@testid, @id, @Pos, @NrRows)
		END
		ELSE
		BEGIN
			DECLARE @Error VARCHAR(100) = @tablename+' nu e nici View nici Tabel!'
			RAISERROR(@Error, 17, 1)
			BREAK
		END
		FETCH NEXT FROM cursorCreate INTO @tablename
	END
	CLOSE cursorCreate
	DEALLOCATE cursorCreate
END

GO
CREATE PROCEDURE RunTest 
	@testname String
AS
BEGIN
	SET NOCOUNT ON
	DECLARE @testid INT = -1
	DECLARE @testrunid INT = -1
	SELECT @testid=TestID FROM Tests WHERE Name=@testname
	IF @testid=-1
	BEGIN
		RAISERROR('Test not found!', 17, 1)
		RETURN 1
	END

	-- create test run
	INSERT INTO TestRuns(Description, StartAt, EndAt)
		VALUES(@testname, GETDATE(), GETDATE())
	SELECT @testrunid=TestRunID FROM TestRuns WHERE Description=@testname
	
	DECLARE @tableid INT
	DECLARE @viewid INT
	DECLARE @tablename String
	DECLARE @viewname String
	DECLARE @NrRows INT
	DECLARE @StartAt DATETIME
	DECLARE @EndAt DATETIME
	DECLARE @SQL VARCHAR(MAX)

	-- delete old data
	DECLARE cursorTablesDel CURSOR FOR
		SELECT Tables.Name FROM TestTables 
		INNER JOIN Tests ON Tests.TestID = TestTables.TestID
		INNER JOIN Tables ON TestTables.TableID = Tables.TableID
		WHERE Tests.TestID = @testid
		ORDER BY Position DESC
	OPEN cursorTablesDel
	FETCH NEXT FROM cursorTablesDel INTO @tablename
	WHILE @@FETCH_STATUS=0
	BEGIN
		EXEC('DELETE FROM '+@tablename)
		FETCH NEXT FROM cursorTablesDel INTO @tablename
	END
	CLOSE cursorTablesDel
	DEALLOCATE cursorTablesDel

	-- insert new data
	DECLARE cursorTablesIns CURSOR FOR
		SELECT Tables.TableID, Tables.Name, TestTables.NoOfRows FROM TestTables 
		INNER JOIN Tables ON TestTables.TableID = Tables.TableID 
		WHERE TestTables.TestID = @testid
		ORDER BY TestTables.Position
	OPEN cursorTablesIns
	FETCH NEXT FROM cursorTablesIns INTO @tableid, @tablename, @NrRows
	WHILE @@FETCH_STATUS=0
	BEGIN
		--PRINT CAST(@tableid as VARCHAR)+', '+@tablename+', '+CAST(@NrRows as VARCHAR)
		-- execute inserts
		EXEC CreateInserts @tablename, @NrRows, @SQL OUTPUT
		SET @StartAt = GETDATE()
		EXEC(@SQL)
		SET @EndAt = GETDATE()
		-- store result
		--PRINT 'TestRunID: '+CAST(@testrunid as VARCHAR)+', TableID: '+CAST(@tableid as VARCHAR)
		INSERT INTO TestRunTables(TestRunID, TableID, StartAt, EndAt)
		VALUES(@testrunid, @tableid, @StartAt, @EndAt)
		PRINT 'Test for Table '+@tablename+': '+dbo.udf_DateToStr(@StartAt)+' - '+dbo.udf_DateToStr(@EndAt)
		FETCH NEXT FROM cursorTablesIns INTO @tableid, @tablename, @NrRows
	END
	CLOSE cursorTablesIns
	DEALLOCATE cursorTablesIns

	-- test views
	DECLARE cursorViews CURSOR FOR
		SELECT TestViews.ViewID, V.Name FROM TestViews 
		INNER JOIN Tests ON TestViews.TestID = Tests.TestID
		INNER JOIN Views V ON V.ViewID = TestViews.ViewID
		WHERE Tests.TestID = @testid
	OPEN cursorViews
	FETCH NEXT FROM cursorViews INTO @viewid, @viewname
	WHILE @@FETCH_STATUS=0
	BEGIN
		SET @StartAt = GETDATE()
		EXEC('SELECT * FROM '+@viewname)
		SET @EndAt = GETDATE()
		-- store result
		--PRINT 'TestRunID: '+CAST(@testrunid as VARCHAR)+', ViewID: '+CAST(@viewid as VARCHAR)
		INSERT INTO TestRunViews(TestRunID, ViewID, StartAt, EndAt)
		VALUES(@testrunid, @viewid, @StartAt, @EndAt)
		PRINT 'Test for View '+@viewname+': '+dbo.udf_DateToStr(@StartAt)+' - '+dbo.udf_DateToStr(@EndAt)
		FETCH NEXT FROM cursorViews INTO @viewid, @viewname
	END
	CLOSE cursorViews
	DEALLOCATE cursorViews

	UPDATE TestRuns SET EndAt=GETDATE() WHERE TestRunID=@testrunid
	SELECT @StartAt=StartAt, @EndAt=EndAt FROM TestRuns WHERE TestRunID=@testrunid
	PRINT 'Test All: '+dbo.udf_DateToStr(@StartAt)+' - '+dbo.udf_DateToStr(@EndAt)
END