-- solve: SERIALIZABLE

DECLARE @msg VARCHAR(MAX) = ''

BEGIN TRAN
WAITFOR DELAY '00:00:05'
INSERT INTO DetaliiPersoane(nume, email, data_nast)
	VALUES('n', 'e', '2003-05-09')
SET @msg += 'INSERT INTO DetaliiPersoane(nume, email, data_nast) VALUES(n, e, 2003-05-09);'
SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'
COMMIT TRAN
SET @msg += 'COMMIT INSERT;'
SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'

EXEC AddLog @msg

-- DELETE FROM DetaliiPersoane WHERE nume = 'n'