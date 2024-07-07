-- numele care participa la activitati, ordonate descrescator dupa nr act si cresc dupa nume
SELECT DetaliiPersoane.nume, COUNT(*) as 'Nr Act' FROM Persoane 
	LEFT JOIN ActivitatiPersoane ON ActivitatiPersoane.persoana_id = Persoane.id
	INNER JOIN DetaliiPersoane ON DetaliiPersoane.id = Persoane.detalii_id
	GROUP BY DetaliiPersoane.nume
	ORDER BY COUNT(*) DESC, DetaliiPersoane.nume

-- cluburile care au mai mult de 3 postari
SELECT Cluburi.denumire FROM Cluburi
	LEFT JOIN PostariCluburi ON PostariCluburi.club_id = Cluburi.id
	GROUP BY Cluburi.id, Cluburi.denumire
	HAVING COUNT(*) > 3

-- persoanele nascute dupa 2000 care sunt inscrise la mai mult de un club
SELECT DetaliiPersoane.nume, COUNT(*) as 'Nr Cluburi' FROM Persoane
	LEFT JOIN PersoaneCluburi ON PersoaneCluburi.persoana_id = Persoane.id
	INNER JOIN DetaliiPersoane ON DetaliiPersoane.id = Persoane.detalii_id
	WHERE YEAR(DetaliiPersoane.data_nast) > 2000
	GROUP BY DetaliiPersoane.nume
	HAVING COUNT(*) >= 2

-- numele persoanelor cu activitati in luna noiembrie
SELECT DISTINCT DetaliiPersoane.nume FROM Activitati
	INNER JOIN ActivitatiPersoane ON ActivitatiPersoane.activitate_id = Activitati.id
	INNER JOIN Persoane ON Persoane.id = ActivitatiPersoane.persoana_id
	INNER JOIN DetaliiPersoane ON DetaliiPersoane.id = Persoane.id
	WHERE MONTH(Activitati.moment_start) = 11

-- data, numele pers si numele sportului pentru fiecare activitate
SELECT CONVERT(DATE, Activitati.moment_start), DetaliiPersoane.nume, Sporturi.denumire 
	FROM Activitati
	INNER JOIN Sporturi ON Sporturi.id = Activitati.sport_id
	INNER JOIN ActivitatiPersoane ON Activitati.id = ActivitatiPersoane.activitate_id
	INNER JOIN Persoane ON Persoane.id = ActivitatiPersoane.persoana_id
	INNER JOIN DetaliiPersoane ON Persoane.detalii_id = DetaliiPersoane.id

-- cluburile care contin jucatori de basket
SELECT DISTINCT Cluburi.denumire FROM Cluburi
	INNER JOIN PersoaneCluburi ON PersoaneCluburi.club_id = Cluburi.id
	INNER JOIN ActivitatiPersoane ON ActivitatiPersoane.persoana_id = PersoaneCluburi.persoana_id
	INNER JOIN Activitati ON Activitati.id = ActivitatiPersoane.activitate_id
	INNER JOIN Sporturi ON Sporturi.id = Activitati.sport_id
	WHERE Sporturi.denumire LIKE 'basket'

-- numele persoanelor care joaca basket
SELECT DetaliiPersoane.nume FROM Persoane
	INNER JOIN DetaliiPersoane ON DetaliiPersoane.id = Persoane.detalii_id
	INNER JOIN ActivitatiPersoane ON Persoane.id = ActivitatiPersoane.persoana_id
	INNER JOIN Activitati ON Activitati.id = ActivitatiPersoane.activitate_id
	INNER JOIN Sporturi ON Sporturi.id = Activitati.sport_id
	WHERE Sporturi.denumire LIKE 'basket'

-- titlul, continutul si numele autorului postarilor de la ora 10 AM fix
SELECT PostariClub.titlu, DetaliiPersoane.nume, MesajeData.continut, MesajeData.moment FROM PostariClub
	INNER JOIN MesajeData ON PostariClub.mesaj_id = MesajeData.id
	LEFT JOIN Persoane ON Persoane.id = MesajeData.persoana_id
	INNER JOIN DetaliiPersoane ON Persoane.detalii_id = DetaliiPersoane.id
	WHERE DATEPART(HOUR, MesajeData.moment) = 10
	AND DATEPART(MINUTE, MesajeData.moment) = 0
	AND DATEPART(SECOND, MesajeData.moment) = 0

-- anul in care cei mai multi utilizatori s-au alaturat unui club
SELECT TOP 1 YEAR(moment_join) as 'An' FROM PersoaneCluburi
	GROUP BY YEAR(moment_join)
	ORDER BY COUNT(*) DESC

-- cea mai practicata categorie de sport
SELECT TOP 1 Categorii.denumire FROM Categorii
	INNER JOIN Sporturi ON Sporturi.categorie_id = Categorii.id
	INNER JOIN Activitati ON Activitati.sport_id = Sporturi.id
	GROUP BY Categorii.denumire
	ORDER BY COUNT(*) DESC