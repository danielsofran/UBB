----SELECT Sporturi.id, Sporturi.denumire as 'Sport', Categorii.denumire as 'Categorie' FROM Sporturi 
----LEFT JOIN Categorii ON Sporturi.categorie_id = Categorii.id
----SELECT Persoane.id, DetaliiPersoane.nume, Sporturi.denumire FROM Persoane LEFT JOIN Sporturi ON Persoane.sport_fav_id = Sporturi.id LEFT JOIN DetaliiPersoane ON DetaliiPersoane.id = Persoane.detalii_id
----SELECT DetaliiPersoane.nume, Sporturi.denumire FROM Persoane 
----	INNER JOIN DetaliiPersoane ON Persoane.detalii_id = DetaliiPersoane.id
----	LEFT JOIN Sporturi ON Sporturi.id = Persoane.sport_fav_id 
----	ORDER BY Persoane.id DESC
--SELECT DetaliiPersoane.nume, MesajeData.continut, MesajeData.moment FROM MesajeData
--	LEFT JOIN Persoane ON Persoane.id = MesajeData.persoana_id
--	INNER JOIN DetaliiPersoane ON Persoane.detalii_id = DetaliiPersoane.id


--SELECT Activitati.id, Sporturi.denumire, Activitati.moment_start, Activitati.durata FROM Activitati 
--	INNER JOIN Sporturi ON Sporturi.id = Activitati.sport_id

----SELECT Persoane.id, DetaliiPersoane.nume FROM Persoane
----	INNER JOIN DetaliiPersoane ON DetaliiPersoane.id = Persoane.detalii_id

--SELECT * FROM Sporturi

----DELETE FROM ActivitatiPersoane

--SELECT * FROM ActivitatiPersoane ORDER BY activitate_id
----SELECT * from Activitati
----DELETE FROM Activitati
SELECT * FROM PersoaneCluburi

--SELECT * FROM Activitati
--SELECT * FROM ActivitatiPersoane