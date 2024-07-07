SET TRANSACTION ISOLATION LEVEL SERIALIZABLE

DECLARE @msg VARCHAR(MAX) = ''

BEGIN TRAN
	SELECT * FROM DetaliiPersoane
	SET @msg += 'SELECT * FROM DetaliiPersoane;'
	SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'
	WAITFOR DELAY '00:00:10'
	SELECT * FROM DetaliiPersoane
	SET @msg += 'SELECT * FROM DetaliiPersoane;'
	SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'
COMMIT TRAN

EXEC AddLog @msg

EXEC ShowLog
EXEC ClearLog

--DELETE FROM DetaliiPersoane WHERE nume LIKE 'n'
--INSERT INTO DetaliiPersoane(nume, email, data_nast)
--	VALUES('n', 'e', '2003-05-09')
--	SELECT * FROM DetaliiPersoane
