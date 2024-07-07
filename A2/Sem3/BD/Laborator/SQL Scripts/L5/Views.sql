DROP VIEW PersoaneView
DROP VIEW PersoaneCluburiView

GO
CREATE VIEW PersoaneView AS SELECT D.nume, D.email, D.data_nast as [Data Nasterii], S.denumire as [Sport Fav]
	FROM Persoane P
	INNER JOIN DetaliiPersoane D ON D.id = P.detalii_id
	INNER JOIN Sporturi S ON S.id = P.sport_fav_id

GO
CREATE VIEW PersoaneCluburiView AS 
	SELECT D.nume as Persoana, C.denumire as Club
	FROM DetaliiPersoane D
	INNER JOIN Persoane P ON P.detalii_id = D.id
	INNER JOIN PersoaneCluburi PC ON PC.persoana_id = P.id
	INNER JOIN Cluburi C ON PC.club_id = C.id
