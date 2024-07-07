CREATE OR ALTER PROCEDURE Deadlock1 AS
BEGIN
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
END

GO
CREATE OR ALTER PROCEDURE Deadlock2 AS
BEGIN
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
END
GO

--EXEC Deadlock1
--WAITFOR DELAY '00:00:03'
--EXEC Deadlock2


