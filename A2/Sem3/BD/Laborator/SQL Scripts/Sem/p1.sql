CREATE PROCEDURE InsertSectiune @nume VARCHAR(30), @descriere VARCHAR(200) 
AS
BEGIN
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO Sectiuni(nume, descriere) VALUES(@nume, @descriere) 
END
