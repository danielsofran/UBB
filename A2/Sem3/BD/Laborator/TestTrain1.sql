DROP TABLE PiratiLovers
DROP TABLE PiratiMisiuni
DROP TABLE CorabiiPirati
DROP TABLE Corabii
DROP TABLE Pirati
DROP TABLE Lovers
DROP TABLE Misiuni

CREATE TABLE Corabii(
	id INT PRIMARY KEY IDENTITY,
	nume NVARCHAR(MAX),
	lemn BIT DEFAULT 1,
	capitan_id INT,
)

CREATE TABLE Pirati(
	id INT PRIMARY KEY IDENTITY,
	nume NVARCHAR(MAX),
	varsta INT,
	corabie_id INT,
)

CREATE TABLE CorabiiPirati(
	pirat_id INT FOREIGN KEY REFERENCES Pirati(id),
	corabie_id INT FOREIGN KEY REFERENCES Corabii(id),
	CONSTRAINT PK_CorabiiPirati PRIMARY KEY(pirat_id, corabie_id)
)

CREATE TABLE Lovers(
	id INT PRIMARY KEY IDENTITY,
	nume NVARCHAR(MAX),
	dimensiune_cupa NVARCHAR(MAX),
	varsta INT,
)

CREATE TABLE Misiuni(
	id  INT PRIMARY KEY IDENTITY,
	nume NVARCHAR(MAX),
	durata INT,
)

CREATE TABLE PiratiLovers(
	pirat_id INT FOREIGN KEY REFERENCES Pirati(id),
	lover_id INT FOREIGN KEY REFERENCES Lovers(id),
	CONSTRAINT PK_PiratiLovers PRIMARY KEY(pirat_id, lover_id)
)

CREATE TABLE PiratiMisiuni(
	pirat_id INT FOREIGN KEY REFERENCES Pirati(id),
	misiune_id INT FOREIGN KEY REFERENCES Misiuni(id),
	CONSTRAINT PK_PiratiMisiuni PRIMARY KEY(pirat_id, misiune_id)
)

--
SELECT DISTINCT COUNT(PL.lover_id) FROM Corabii C
	INNER JOIN Pirati P ON C.id = P.corabie_id
	INNER JOIN PiratiLovers PL ON PL.pirat_id = P.id
	WHERE C.capitan_id = 1