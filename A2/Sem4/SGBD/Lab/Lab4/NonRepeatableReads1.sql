-- solve => REPEATABLE READ 
DECLARE @msg VARCHAR(MAX) = ''

BEGIN TRAN
WAITFOR DELAY '00:00:05'
UPDATE DetaliiPersoane SET email = 'email updated' WHERE id=347712
SET @msg += 'UPDATE DetaliiPersoane SET email = email updated;'
SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'
COMMIT TRAN
SET @msg += 'COMMIT UPDATE;'
SET @msg += CONVERT(VARCHAR, GETDATE(), 20)+';'

EXEC AddLog @msg

--UPDATE DetaliiPersoane SET email = 'email' WHERE id=347712