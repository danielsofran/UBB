--SELECT S.nume, R1.nume as R1, SR1.oraS, SR1.oraP, R2.nume, SR2.oraS, SR2.oraP as R2 FROM Statii S
SELECT DISTINCT S.nume FROM Statii S
	INNER JOIN StatiiRute SR1 ON SR1.statie_id = S.id
	INNER JOIN StatiiRute SR2 ON SR2.statie_id = S.id
	INNER JOIN Rute R1 ON SR1.ruta_id = R1.id
	INNER JOIN Rute R2 ON SR2.ruta_id = R2.id
	WHERE SR1.statie_id = SR2.statie_id
	AND R1.tren_id <> R2.tren_id AND 
	(SR1.oraP > SR2.oraS AND SR1.oraP < SR2.oraP OR
	 SR1.oraS > SR2.oraS AND SR1.oraS < SR2.oraP)

GO
CREATE OR ALTER FUNCTION udf_StatiiActive(@ora TIME)
RETURNS TABLE AS RETURN
	SELECT S.nume FROM Statii S
		INNER JOIN StatiiRute SR ON SR.statie_id = S.id
		WHERE SR.oraS < @ora AND SR.oraP > @ora
		GROUP BY S.id, S.nume
		HAVING COUNT(*)>1
		
GO

SELECT * FROM dbo.udf_StatiiActive('10:05')
SELECT * FROM StatiiRute WHERE statie_id = 2