-- SECTION SPORTURI

CREATE TABLE Categorii(
	id INT PRIMARY KEY IDENTITY,
	denumire VARCHAR(20) NOT NULL,
	heart_rate VARCHAR(7),
	blood_preasure VARCHAR(7),
	cardiac_output VARCHAR(7),
	training VARCHAR(7),
	cardiac_remodeling VARCHAR(7),
)

CREATE TABLE TipuriSporturi(
	id INT PRIMARY KEY IDENTITY,
	allow_single BIT NOT NULL DEFAULT 1,
	allow_dual BIT NOT NULL DEFAULT 1,
	allow_multiple BIT NOT NULL DEFAULT 1, --se poate juca indiferent de nr?
	nr_players TINYINT NOT NULL DEFAULT 1,
	categorie_id INT NOT NULL,
	CONSTRAINT FK_TipuriSporturi_Categorie 
		FOREIGN KEY (categorie_id) REFERENCES Categorii(id),
)

CREATE TABLE Sporturi(
	id INT PRIMARY KEY IDENTITY,
	denumire VARCHAR(20) NOT NULL,
	tip_id INT NOT NULL,
	CONSTRAINT FK_Sporturi_Tip
		FOREIGN KEY (tip_id) REFERENCES TipuriSporturi(id),
)

-- END SECTION

CREATE TABLE Cluburi(
	id INT PRIMARY KEY IDENTITY,
	denumire VARCHAR(50) NOT NULL UNIQUE,
	descriere TEXT,
)

CREATE TABLE DetaliiPersoane(
	id INT PRIMARY KEY IDENTITY,
	nume VARCHAR(50) NOT NULL,
	data_nasterii DATE,
	email VARCHAR(50) NOT NULL,
	CONSTRAINT NumeOk CHECK (nume NOT LIKE '%[^A-Za-z ]%'),
)

CREATE TABLE Persoane(
	id INT PRIMARY KEY IDENTITY,
	detalii_id INT NOT NULL,
	sport_fav_id INT NOT NULL,
	UNIQUE(detalii_id),
	CONSTRAINT FK_Persoana_Detaliu
		FOREIGN KEY (detalii_id)
		REFERENCES DetaliiPersoane(id),
	CONSTRAINT FK_Persoana_Sport
		FOREIGN KEY (sport_fav_id)
		REFERENCES Sporturi(id),
)

CREATE TABLE PostariClub(
	id INT PRIMARY KEY IDENTITY,
	club_id INT NOT NULL,
	persoana_id INT NOT NULL,
	titlu VARCHAR(30) NOT NULL,
	moment DATETIME NOT NULL,
	continut TEXT NOT NULL,
	likeuri INT NOT NULL DEFAULT 0,
	CONSTRAINT FK_PostariClub_Club
		FOREIGN KEY (club_id)
		REFERENCES Cluburi(id),
	CONSTRAINT FK_PostariClub_Persoana
		FOREIGN KEY (persoana_id)
		REFERENCES Persoane(id),
)

CREATE TABLE ComentariiClub(
	id INT PRIMARY KEY IDENTITY,
	postare_id INT NOT NULL,
	persoana_id INT NOT NULL,
	moment DATETIME NOT NULL,
	continut TEXT NOT NULL,
	likeuri INT NOT NULL DEFAULT 0,
	CONSTRAINT FK_ComentariiClub_Postare
		FOREIGN KEY (postare_id)
		REFERENCES PostariClub(id),
	CONSTRAINT FK_ComentariiClub_Persoana
		FOREIGN KEY (persoana_id)
		REFERENCES Persoane(id),
)


CREATE TABLE Activitati(
	id INT PRIMARY KEY IDENTITY,
	sport_id INT NOT NULL,
	moment DATETIME NOT NULL, 
	durata TIME(0) NOT NULL,
	CONSTRAINT FK_Activitati_Sport
		FOREIGN KEY (sport_id)
		REFERENCES Sporturi(id),
)

CREATE TABLE ActivitatiPersoane(
	persoana_id INT NOT NULL,
	activitate_id INT NOT NULL,
	CONSTRAINT FK_ActPers_Persoana
		FOREIGN KEY(persoana_id)
		REFERENCES Persoane(id),
	CONSTRAINT FK_ActPers_Activitate
		FOREIGN KEY(activitate_id)
		REFERENCES Activitati(id),
	CONSTRAINT PK_ActPers PRIMARY KEY(persoana_id, activitate_id),
)

CREATE TABLE PersoaneCluburi(
	persoana_id INT NOT NULL,
	club_id INT NOT NULL,
	moment_join DATETIME NOT NULL,
	CONSTRAINT FK_PersClb_Persoana
		FOREIGN KEY(persoana_id)
		REFERENCES Persoane(id),
	CONSTRAINT FK_PersClb_Club
		FOREIGN KEY(club_id)
		REFERENCES Cluburi(id),
	CONSTRAINT PK_PersClb PRIMARY KEY(persoana_id, club_id),
)

