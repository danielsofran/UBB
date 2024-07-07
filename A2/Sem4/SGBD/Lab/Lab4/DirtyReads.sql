--SELECT * FROM DetaliiPersoane
--EXEC DeleteDetaliiPersoane
--INSERT INTO DetaliiPersoane(nume, email, data_nast)
--	VALUES('Nume', 'email', '2003-05-09')
--GO

-- solve => READ COMMITTED
DECLARE @msg VARCHAR(MAX) = ''

BEGIN TRAN
UPDATE DetaliiPersoane SET nume='Nume Updated'
SET @msg += 'UPDATE DetaliiPersoane SET nume=Nume Updated;'
SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'
WAITFOR DELAY '00:00:05'
ROLLBACK
SET @msg += 'UPDATE ROLLBACK;'
SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'

-- SELECT * FROM DetaliiPersoane
-- UPDATE DetaliiPersoane SET nume='Nume'
EXEC AddLog @msg