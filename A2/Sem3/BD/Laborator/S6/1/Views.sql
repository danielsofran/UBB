-- numele rutelor care contin toate statiile
CREATE OR ALTER VIEW RuteView AS
	SELECT R.nume FROM Ruta R
	INNER JOIN StatieRuta SR ON SR.idRuta = R.id
	GROUP BY SR.idRuta, R.nume
	HAVING COUNT(*) = (SELECT COUNT(*) FROM Statie)
	