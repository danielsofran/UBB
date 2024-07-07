DECLARE @msg VARCHAR(MAX) = ''

BEGIN TRAN
	UPDATE Categorii SET blood_preasure = '++' WHERE id = 41
	SET @msg += 'UPDATE Categorii SET blood_preasure = ++ WHERE id = 41;'
	SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'
	WAITFOR DELAY '00:00:10'
	UPDATE DetaliiPersoane SET data_nast = '2009-01-01' WHERE id = 347749
	SET @msg += 'UPDATE DetaliiPersoane SET data_nast = 2009-01-01 WHERE id = 347749;'
	SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'
COMMIT TRAN

EXEC AddLog @msg