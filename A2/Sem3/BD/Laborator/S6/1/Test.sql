--EXEC AddStatie 'Suceava - Cluj', 'Clujana', '00:01', '00:02'

--EXEC AddStatie 'Nostalgia copilariei', 'Suceava Nord', '00:00', '00:00'

CREATE OR ALTER FUNCTION GetRutaNume(@id INT) RETURNS VARCHAR(50)
AS
BEGIN
	DECLARE @nume VARCHAR(50)
	SELECT TOP 1 @nume=nume FROM Ruta WHERE id=@id
	RETURN @nume
END

GO
CREATE OR ALTER FUNCTION GetStatieNume(@id INT) RETURNS VARCHAR(50)
AS
BEGIN
	DECLARE @nume VARCHAR(50)
	SELECT TOP 1 @nume=nume FROM Statie WHERE id=@id
	RETURN @nume
END

GO
DECLARE @i INT = 2
WHILE @i <= 6
BEGIN
	DECLARE @numest VARCHAR(50) = dbo.GetRutaNume(3)
	DECLARE @numer VARCHAR(50) = dbo.GetStatieNume(@i)
	EXEC AddStatie @numest, @numer, '20:02', '10:13'
	SET @i += 1
END