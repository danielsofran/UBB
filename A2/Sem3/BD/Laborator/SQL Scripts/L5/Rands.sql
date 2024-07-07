CREATE OR ALTER PROCEDURE RandInt @a INT, @b INT, @rez INT OUTPUT
AS BEGIN
	SET @rez = FLOOR(RAND()*(@b-@a+1)+@a)
END

GO
CREATE OR ALTER PROCEDURE RandStr @l INT, @rez VARCHAR(MAX) OUTPUT AS BEGIN
	SET @rez = ''
	DECLARE @i INT = (@l+1) * 6 / 36 + 1
	WHILE @i > 0
	BEGIN
		SET @rez += CAST(NEWID() AS VARCHAR(MAX))
		SET @i -= 1
	END
	SET @i = 1
	DECLARE @digits VARCHAR(20) = '1234567890-'
	WHILE @i <= LEN(@digits)
	BEGIN
		SET @rez = REPLACE(@rez, SUBSTRING(@digits, @i, 1), '')
		SET @i += 1
	END
	SET @rez = LEFT(@rez, @l)
END

GO
CREATE OR ALTER PROCEDURE RandNume @rez VARCHAR(MAX) OUTPUT AS BEGIN
	EXEC RandStr 10, @rez OUTPUT
	SET @rez = SUBSTRING(@rez, 1, 1) + LOWER(SUBSTRING(@rez, 2, LEN(@rez)-1))
END

GO
CREATE OR ALTER PROCEDURE RandEmail @rez VARCHAR(MAX) OUTPUT AS BEGIN
	DECLARE @nume VARCHAR(MAX)
	DECLARE @posta VARCHAR(MAX)
	DECLARE @dom VARCHAR(MAX) = 'com'
	DECLARE @nr INT
	EXEC RandInt 0, 1, @nr OUTPUT
	IF @nr = 0
		SET @dom = 'ro'
	EXEC RandNume @nume OUTPUT
	EXEC RandStr 4, @posta OUTPUT
	SET @posta = LOWER(@posta)
	SET @rez = @nume + '@' + @posta + '.' + @dom
END

GO
CREATE OR ALTER PROCEDURE RandData @rez DATETIME OUTPUT, @ys INT = 2010, @ye INT = 2022 AS BEGIN
	DECLARE @valida INT = 0
	DECLARE @d INT, @m INT, @y INT, @h INT, @min INT
	WHILE @valida = 0
	BEGIN
		EXEC RandInt 1, 31, @d OUTPUT
		EXEC RandInt 1, 12, @m OUTPUT
		EXEC RandInt @ys, @ye, @y OUTPUT
		EXEC RandInt 0, 23, @h OUTPUT
		EXEC RandInt 0, 60, @min OUTPUT
		DECLARE @str VARCHAR(MAX)
		SET @str = dbo.ValidDate(@d, @m, @y, @h, @min)
		IF @str IS NOT NULL
		BEGIN
			SET @valida = 1
			SET @rez = dbo.StrToDate(@str)
		END
	END
END

GO
CREATE OR ALTER PROCEDURE RandId @tablename VARCHAR(MAX), @id INT OUTPUT AS
BEGIN
	DECLARE @SQL NVARCHAR(500) = 'SELECT TOP 1 @rid=id FROM '+@tablename+' ORDER BY NEWID()'
	DECLARE @ParamDef NVARCHAR(500) = '@rid INT OUTPUT'
	EXECUTE sp_executesql
		@SQL, @ParamDef, @rid = @id OUTPUT

END

GO
CREATE OR ALTER PROCEDURE RandText @rezultat TEXT OUTPUT AS
BEGIN
	
	DECLARE @rez VARCHAR(MAX)
	DECLARE @cv INT, @str VARCHAR(MAX), @l INT
	EXEC RandInt 5, 20, @cv OUTPUT
	EXEC RandNume @rez OUTPUT
	WHILE @cv > 0
	BEGIN
		EXEC RandInt 5, 10, @l OUTPUT
		EXEC RandStr @l, @str OUTPUT
		SET @rez += ' ' + LOWER(@str)
		SET @cv -= 1
	END
	SET @rez += '.'
	SET @rezultat = CAST(@rez as TEXT)
END
