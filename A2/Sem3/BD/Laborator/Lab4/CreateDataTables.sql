ALTER TABLE Studenti DROP CONSTRAINT FK_Studenti_Persoane
ALTER TABLE ProfesoriStudenti DROP CONSTRAINT FK_PS_P
ALTER TABLE ProfesoriStudenti DROP CONSTRAINT FK_PS_S
DROP TABLE Persoane;
DROP TABLE Studenti;
DROP TABLE ProfesoriStudenti

DROP VIEW NumePersoane
DROP VIEW NumeStudenti
DROP VIEW ClaseProfesori
GO

CREATE TABLE Persoane(
	id INT PRIMARY KEY IDENTITY,
	nume String NOT NULL DEFAULT 'Nume',
	an INT NOT NULL DEFAULT 2022,
)

CREATE TABLE Studenti(
	id INT PRIMARY KEY IDENTITY,
	persoana_id INT NOT NULL,
	facultate String NOT NULL DEFAULT 'UBB',
	CONSTRAINT FK_Studenti_Persoane FOREIGN KEY(persoana_id) REFERENCES Persoane(id),
)

CREATE TABLE ProfesoriStudenti(
	prof_id INT NOT NULL,
	stud_id INT NOT NULL,
	CONSTRAINT FK_PS_P FOREIGN KEY(prof_id) REFERENCES Persoane(id),
	CONSTRAINT FK_PS_S FOREIGN KEY(stud_id) REFERENCES Studenti(id),
	CONSTRAINT PK_Prof_Stud PRIMARY KEY(prof_id, stud_id),
)

GO
CREATE VIEW NumePersoane AS SELECT nume FROM Persoane

GO
CREATE VIEW NumeStudenti AS SELECT Persoane.nume, Studenti.facultate
	FROM Studenti INNER JOIN Persoane ON Studenti.persoana_id = Persoane.id

GO
CREATE VIEW ClaseProfesori AS SELECT Profi.nume as Prof, COUNT(*) as NrStudenti 
	FROM ProfesoriStudenti PS 
	INNER JOIN Persoane Profi ON Profi.id = PS.prof_id
	INNER JOIN Studenti ON Studenti.id = PS.stud_id
	GROUP BY Profi.nume
GO

INSERT INTO Persoane(nume) VALUES('Istvan')		--1
INSERT INTO Persoane(nume) VALUES('Gabi')		--2
INSERT INTO Persoane(nume) VALUES('Suciu')		--3

INSERT INTO Persoane(nume) VALUES('Daniel')		--4 1
INSERT INTO Persoane(nume) VALUES('Catalin')	--5 2
INSERT INTO Persoane(nume) VALUES('Cristi')		--6 3
INSERT INTO Persoane(nume) VALUES('Alex')		--7 4
INSERT INTO Persoane(nume) VALUES('Leonardo')	--8 5

INSERT INTO Studenti(persoana_id) VALUES(4), (5), (6), (7), (8)
INSERT INTO ProfesoriStudenti(prof_id, stud_id) VALUES (1, 1), (1, 2), (1, 3), 
	(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
	(3, 1), (3, 3)
