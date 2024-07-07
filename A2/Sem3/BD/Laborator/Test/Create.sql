DROP TABLE ActoriReplici
DROP TABLE Vizionari
DROP TABLE Replici
DROP TABLE Episoade
DROP TABLE Utilizatori
DROP TABLE Actori


CREATE TABLE Episoade(
	id INT PRIMARY KEY IDENTITY,
	nume NVARCHAR(100),
	durata TIME,
	ord_cron INT, -- nr de ordine
)

CREATE TABLE Utilizatori(
	id INT PRIMARY KEY IDENTITY,
	nume NVARCHAR(100),
	email NVARCHAR(100),
)

CREATE TABLE Vizionari(
	utilizator_id INT FOREIGN KEY REFERENCES Utilizatori(id),
	episod_id INT FOREIGN KEY REFERENCES Episoade(id),
	nr_ord INT, -- al catelea episod e pe lista utilizatorului
	CONSTRAINT PK_Vizionari PRIMARY KEY(utilizator_id, episod_id)
)

CREATE TABLE Actori(
	id INT PRIMARY KEY IDENTITY,
	nume NVARCHAR(100),
)

CREATE TABLE Replici(
	id INT PRIMARY KEY IDENTITY,
	textr NVARCHAR(MAX),
	moment TIME,
	episod_id INT FOREIGN KEY REFERENCES Episoade(id),
)

CREATE TABLE ActoriReplici(
	actor_id INT FOREIGN KEY REFERENCES Actori(id),
	replica_id INT FOREIGN KEY REFERENCES Replici(id),
	CONSTRAINT PK_ActoriReplici PRIMARY KEY(actor_id, replica_id),
)


