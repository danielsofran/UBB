CREATE PROCEDURE ExistaVizitator
	@mail VARCHAR(30)
AS
BEGIN
	DECLARE @cod INT
	SET @cod = NULL
	SELECT @cod=cod_v FROM Vizitatori WHERE
		email = @mail
	IF @cod IS NULL
		RAISERROR('Nu exista nici un vizitator cu acest email ', 17, 0)
	ELSE
		RETURN @cod
END