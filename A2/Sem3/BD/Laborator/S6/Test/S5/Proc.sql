CREATE OR ALTER PROCEDURE AddInchiriere
	@angajat_id INT,
	@autovehicul_id INT,
	@client_id INT,
	@dataInc DATETIME,
	@dataRet DATETIME,
	@op BIT
AS
BEGIN
	IF @op<>0
	BEGIN
		INSERT INTO Inchirieri(angajat_id, autovehicul_id, client_id, dataInc, dataRet)
			VALUES(@angajat_id, @autovehicul_id, @client_id, @dataInc, @dataRet)
		PRINT 'Inchiriere adaugata!'
	END
	ELSE
	BEGIN
		UPDATE Inchirieri SET dataInc=@dataInc, dataRet=@dataRet
			WHERE angajat_id=@angajat_id AND autovehicul_id=@autovehicul_id AND client_id=@client_id
		PRINT 'Inchiriere actualizata!'
	END
END

GO
CREATE OR ALTER VIEW AngajatiDacia AS
	SELECT Ang.id, Ang.nume, Ang.prenume, COUNT(*) as Nr FROM Angajati Ang
		INNER JOIN Inchirieri I ON I.angajat_id=Ang.id
		INNER JOIN Autovehicule Mas ON Mas.id=I.autovehicul_id
		INNER JOIN Marci ON Marci.id = Mas.marca_id
		WHERE Marci.denumire='Dacia' AND MONTH(I.dataInc)=MONTH(GETDATE())
		GROUP BY Ang.id, Ang.nume, Ang.prenume
		HAVING COUNT(*) > 0
		--ORDER BY Ang.nume, Ang.prenume

GO
SELECT * FROM AngajatiDacia ORDER BY nume, prenume

GO
CREATE OR ALTER FUNCTION udf_MasiniLibere(@data DATETIME)
	RETURNS TABLE 
AS 
	RETURN SELECT A.nr_inm as numar, M.denumire as marca, A.tip_comb 
		FROM Autovehicule A
		INNER JOIN Marci M ON A.marca_id=M.id
		WHERE @data NOT BETWEEN 
			(SELECT MIN(dataInc) FROM Inchirieri WHERE autovehicul_id=A.id)
		AND 
			(SELECT MAX(dataRet) FROM Inchirieri WHERE autovehicul_id=A.id)

GO
SELECT * FROM dbo.udf_MasiniLibere('1/10/2023 10:30') -- ocupate sunt 3, 5, 1, 2, 4
SELECT * FROM dbo.udf_MasiniLibere('1/09/2023 17:00') -- ocupata e 2
SELECT * FROM dbo.udf_MasiniLibere('1/16/2023 10:30') -- libera e 2



		