DECLARE @msg VARCHAR(MAX) = ''

BEGIN TRAN
	UPDATE DetaliiPersoane SET nume = 'Noob 2' WHERE id = 347749
	SET @msg += 'UPDATE DetaliiPersoane SET nume = Noob 2 WHERE id = 347749;'
	SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'
	WAITFOR DELAY '00:00:10'
	UPDATE Categorii SET heart_rate = '++' WHERE id = 41
	SET @msg += 'UPDATE Categorii SET heart_rate = ++ WHERE id = 41;'
	SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'
COMMIT TRAN

EXEC AddLog @msg