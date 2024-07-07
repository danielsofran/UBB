CREATE OR ALTER PROCEDURE RandInt @a INT, @b INT, @rez INT OUTPUT
AS BEGIN
	SET @rez = FLOOR(RAND()*(@b-@a+1)+@a)
END

GO
CREATE OR ALTER FUNCTION ValidDate(@d INT, @m INT, @y INT, @h INT, @min INT) RETURNS VARCHAR(MAX) AS
BEGIN
	DECLARE @rez VARCHAR(MAX) = FORMAT(@d, '00') + '.' + FORMAT(@m, '00') + '.' + FORMAT(@y, '0000') + ' ' +
								FORMAT(@h, '00') + ':' + FORMAT(@min, '00')
	IF TRY_CONVERT(DATETIME, @rez, 104) IS NULL
		RETURN NULL
	RETURN @rez
END

GO
CREATE OR ALTER FUNCTION StrToDate(@str VARCHAR(MAX)) RETURNS DATETIME AS
BEGIN
	RETURN CONVERT(DATETIME, @str, 104)
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