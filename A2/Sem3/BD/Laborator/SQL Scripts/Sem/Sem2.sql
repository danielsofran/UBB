--CREATE DATABASE ParcDeDistractii
--GO
--USE ParcDeDistractii
--GO

--CREATE TABLE Sectiuni(
--	cod_s INT PRIMARY KEY IDENTITY,
--	nume VARCHAR(30),
--	descriere VARCHAR(200),
--)

--CREATE TABLE Categorii(
--	cod_c INT PRIMARY KEY IDENTITY,
--	nume VARCHAR(30),
--)

--CREATE TABLE Vizitatori(
--	cod_v INT PRIMARY KEY IDENTITY,
--	nume VARCHAR(30),
--	email VARCHAR(30),
--	cod_c INT FOREIGN KEY REFERENCES Categorii(cod_c),
--)

--CREATE TABLE Atractii(
--	cod_a INT PRIMARY KEY IDENTITY,
--	nume VARCHAR(30),
--	descriere VARCHAR(200),
--	varsta_min TINYINT,
--	cod_s INT FOREIGN KEY REFERENCES Sectiuni(cod_s),
--)

--CREATE TABLE Note(
--	cod_a INT FOREIGN KEY REFERENCES Atractii(cod_a),
--	cod_v INT FOREIGN KEY REFERENCES Vizitatori(cod_v),
--	CONSTRAINT PK_Note PRIMARY KEY(cod_a, cod_v),
--	nota FLOAT,
--)

