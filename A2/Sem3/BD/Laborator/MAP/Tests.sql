--EXEC CreateForPair 'UBB', 'mateInfo'

SELECT * FROM Echipe
SELECT * FROM Elev
SELECT E.nume, E.scoala, Ech.nume FROM Jucator J
	INNER JOIN Elev E ON E.id = J.idElev
	INNER JOIN Echipe Ech ON Ech.id = J.idEchipa


--EXEC CreateMeciuri 50
SELECT DISTINCT Meci.id, E1.nume, E2.nume FROM Meci 
	INNER JOIN Echipe E1 ON E1.id = Meci.idEchipa1
	INNER JOIN Echipe E2 ON E2.id = Meci.idEchipa2

--EXEC CreatePlayersForMeci 401, 44
--EXEC CreateActivePlayers
SELECT * FROM JucatorActiv JA

SELECT nume FROM Echipe GROUP BY nume HAVING COUNT(*) > 1

SELECT YEAR(data), count(*) FROM Meci GROUP BY YEAR(data)

SELECT * FROM Echipe WHERE nume LIKE 'I%'
SELECT * FROM JucatorActiv JA
	INNER JOIN Jucator J ON J.idElev = JA.idJucator
	WHERE idMeci = 401 AND idEchipa = 52

SELECT * FROM Jucator WHERE idElev NOT IN (SELECT idJucator FROM JucatorActiv)

