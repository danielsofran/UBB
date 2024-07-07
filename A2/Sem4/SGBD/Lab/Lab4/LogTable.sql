DROP TABLE TransactionLog

CREATE TABLE TransactionLog
(
	id INT PRIMARY KEY IDENTITY,
	log_message VARCHAR(MAX),
	moment DATETIME,
)

GO
CREATE OR ALTER PROCEDURE AddLog
	@message VARCHAR(MAX)
AS
BEGIN
	DECLARE @msg VARCHAR(MAX), @date_str VARCHAR(MAX), @date DATETIME

	DECLARE cursor_tokens CURSOR FOR 
		SELECT * FROM string_split(@message, ';')

	OPEN cursor_tokens
	FETCH NEXT FROM cursor_tokens INTO @msg
	FETCH NEXT FROM cursor_tokens INTO @date_str
	WHILE @@FETCH_STATUS = 0
	BEGIN
		SET @date = TRY_CONVERT(DATETIME, @date_str, 20)
		INSERT INTO TransactionLog(log_message, moment) VALUES(@msg, @date)
		FETCH NEXT FROM cursor_tokens INTO @msg
		FETCH NEXT FROM cursor_tokens INTO @date_str
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
GO

CREATE OR ALTER PROCEDURE ShowLog AS
	SELECT * FROM TransactionLog ORDER BY moment
GO