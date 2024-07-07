DROP TABLE TransactionLog

CREATE TABLE TransactionLog
(
	id INT PRIMARY KEY IDENTITY,
	log_message VARCHAR(MAX)
)

GO
CREATE OR ALTER PROCEDURE AddLog
	@message VARCHAR(MAX)
AS
BEGIN
	DECLARE @msg VARCHAR(MAX)

	DECLARE cursor_tokens CURSOR FOR 
		SELECT * FROM string_split(@message, ';')

	OPEN cursor_tokens
	FETCH NEXT FROM cursor_tokens INTO @msg
	WHILE @@FETCH_STATUS = 0
	BEGIN
		INSERT INTO TransactionLog(log_message) VALUES(@msg)
		PRINT @msg
		FETCH NEXT FROM cursor_tokens INTO @msg
	END
	CLOSE cursor_tokens
	DEALLOCATE cursor_tokens

	--INSERT INTO TransactionLog(log_message)
	--	VALUES(@message)
	--PRINT @message
END

GO
CREATE OR ALTER PROCEDURE ClearLog
AS
	DELETE FROM TransactionLog
