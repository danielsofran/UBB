CREATE PROCEDURE InsertAtractie 
	@nume VARCHAR(30),
	@descriere VARCHAR(200),
	@varsta_min INT,
	@sectiune VARCHAR(30)
AS
BEGIN
	IF (NOT EXISTS(SELECT nume FROM Sectiuni WHERE nume = @sectiune))
		EXEC InsertSectiune @sectiune, '';
	DECLARE @id_sectiune INT
	SELECT TOP 1 @id_sectiune=cod_s FROM Sectiuni WHERE nume=@sectiune
	INSERT INTO Atractii(nume, descriere, varsta_min, cod_s)
		VALUES(@nume, @descriere, @varsta_min, @id_sectiune)
END