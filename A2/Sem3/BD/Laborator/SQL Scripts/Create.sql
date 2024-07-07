--CREATE DATABASE Sporturi
--GO
--USE Sporturi
--GO

CREATE TABLE Categorii(
	id INT PRIMARY KEY IDENTITY,
	denumire NVARCHAR(20) NOT NULL,
	heart_rate NVARCHAR(10) NOT NULL DEFAULT '',
	blood_preasure NVARCHAR(10) NOT NULL DEFAULT '',
	cardiac_output NVARCHAR(10) NOT NULL DEFAULT '',
	cardiac_remodeling NVARCHAR(10) NOT NULL DEFAULT '',
	training NVARCHAR(10) NOT NULL DEFAULT '',
)

--CREATE TABLE TipuriSporturi(
--	id INT PRIMARY KEY IDENTITY,
--	allow_single BIT NOT NULL DEFAULT 1,
--	allow_dual BIT NOT NULL DEFAULT 0,
--	allow_multi BIT NOT NULL DEFAULT 0,
--	numplayers TINYINT NOT NULL DEFAULT 1,
--	categorie_id INT FOREIGN KEY REFERENCES Categorii(id),
--)

CREATE TABLE Sporturi(
	id INT PRIMARY KEY IDENTITY,
	denumire NVARCHAR(30) NOT NULL,
	categorie_id INT FOREIGN KEY REFERENCES Categorii(id),
)

--------------------------------------------------------

CREATE TABLE DetaliiPersoane(
	id INT PRIMARY KEY IDENTITY,
	nume NVARCHAR(50) NOT NULL,
	email NVARCHAR(50) NOT NULL UNIQUE,
	data_nast DATE NOT NULL,
)

CREATE TABLE Cluburi(
	id INT PRIMARY KEY IDENTITY,
	denumire NVARCHAR(50) NOT NULL,
	descriere TEXT,
)

CREATE TABLE Persoane(
	id INT PRIMARY KEY IDENTITY,
	detalii_id INT FOREIGN KEY REFERENCES DetaliiPersoane(id) ON DELETE CASCADE ON UPDATE CASCADE UNIQUE,
	sport_fav_id INT FOREIGN KEY REFERENCES Sporturi(id),
)

CREATE TABLE PersoaneCluburi(
	persoana_id INT FOREIGN KEY REFERENCES Persoane(id),
	club_id INT FOREIGN KEY REFERENCES Cluburi(id),
	CONSTRAINT PK_PersoaneCluburi PRIMARY KEY(persoana_id, club_id),
	moment_join DATETIME NOT NULL,
)

CREATE TABLE MesajeData(
	id INT PRIMARY KEY IDENTITY,
	persoana_id INT FOREIGN KEY REFERENCES Persoane(id),
	moment DATETIME NOT NULL,
	continut TEXT NOT NULL,
	likeuri INT NOT NULL DEFAULT 0,
)

CREATE TABLE PostariClub(
	id INT PRIMARY KEY IDENTITY,
	mesaj_id INT FOREIGN KEY REFERENCES MesajeData(id)  ON DELETE CASCADE ON UPDATE CASCADE UNIQUE,
	titlu NVARCHAR(30) NOT NULL DEFAULT 'Titlu',
)

CREATE TABLE PostariCluburi(
	postare_id INT FOREIGN KEY REFERENCES PostariClub(id) UNIQUE,
	club_id int FOREIGN KEY REFERENCES Cluburi(id),
	CONSTRAINT PK_PostariCluburi PRIMARY KEY (postare_id, club_id)
)

CREATE TABLE ComentariiClub(
	id INT PRIMARY KEY IDENTITY,
	postare_id INT FOREIGN KEY REFERENCES PostariClub(id),
	mesaj_id INT FOREIGN KEY REFERENCES MesajeData(id) ON DELETE CASCADE ON UPDATE CASCADE UNIQUE,
)

--------------------------------------------------------

CREATE TABLE Activitati(
	id INT PRIMARY KEY IDENTITY,
	sport_id INT FOREIGN KEY REFERENCES Sporturi(id),
	moment_start DATETIME NOT NULL,
	durata TIME(0) NOT NULL,
)

CREATE TABLE ActivitatiPersoane(
	persoana_id INT FOREIGN KEY REFERENCES Persoane(id),
	activitate_id INT FOREIGN KEY REFERENCES Activitati(id),
	CONSTRAINT PK_ActivitatiPersoane PRIMARY KEY(persoana_id, activitate_id),
)

--CREATE TABLE Postari(
--	id INT PRIMARY KEY IDENTITY,
--	mesaj_id INT FOREIGN KEY REFERENCES MesajeData(id),
--	titlu NVARCHAR(30) NOT NULL DEFAULT 'Titlu',
--)