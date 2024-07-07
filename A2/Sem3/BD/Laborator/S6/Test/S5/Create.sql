CREATE TABLE Clienti(
	id INT PRIMARY KEY IDENTITY,
	nume NVARCHAR(MAX),
	prenume NVARCHAR(MAX),
)

CREATE TABLE Angajati(
	id INT PRIMARY KEY IDENTITY,
	nume NVARCHAR(MAX),
	prenume NVARCHAR(MAX),
) -- polimorfism din persoana?

CREATE TABLE Marci(
	id INT PRIMARY KEY IDENTITY,
	denumire NVARCHAR(MAX),
)

CREATE TABLE Autovehicule(
	id INT PRIMARY KEY IDENTITY,
	nr_inm NVARCHAR(8),
	tip_comb NVARCHAR(20),
	marca_id INT FOREIGN KEY REFERENCES Marci(id),
)

CREATE TABLE Inchirieri(
	id INT PRIMARY KEY IDENTITY,
	angajat_id INT FOREIGN KEY REFERENCES Angajati(id),
	client_id INT FOREIGN KEY REFERENCES Clienti(id),
	autovehicul_id INT FOREIGN KEY REFERENCES Autovehicule(id),
	dataInc DATETIME,
	dataRet DATETIME,
)

--SELECT * FROM INFORMATION_SCHEMA.TABLES
SELECT autovehicul_id FROM Inchirieri
	WHERE '1/16/2023 12:00' NOT BETWEEN dataInc AND dataRet