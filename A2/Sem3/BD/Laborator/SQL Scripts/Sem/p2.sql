CREATE PROCEDURE AdaugaCategorie @nume VARCHAR(50)
AS
BEGIN
	IF EXISTS(SELECT * FROM Categorii WHERE nume = @nume)
		PRINT 'Categorie deja existenta'
	ELSE 
		INSERT INTO Categorii(nume) VALUES(@nume)
END