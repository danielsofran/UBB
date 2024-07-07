--ALTER TABLE TestTable ALTER COLUMN data nvarchar(20)
--EXEC ModifyColumn 'TestTable', 'data', 'nvarchar(20)';

--DECLARE @type VARCHAR(100);
--EXEC GetColumnType 'TestTable', 'data', @type OUTPUT;
--SELECT @type

--SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='TestTable'

--ALTER TABLE TestTable ADD CONSTRAINT df_TestTable_data DEFAULT 'abc' FOR data
--ALTER TABLE TestTable DROP CONSTRAINT df_TestTable_data;


--EXEC CreateTable 'Tests', 'id INT PRIMARY KEY IDENTITY, name TEXT'
--EXEC AddColumn 'TestTable', 'test_id', 'INT'
--EXEC AddFK 'TestTable', 'test_id', 'Tests', 'id'
--EXEC RemoveFK 'TestTable', 'test_id', 'Tests', 'id'

--EXEC CreateTable 'CreatedTable', 'id INT PRIMARY KEY, nume TEXT'
--EXEC DropTable 'CreatedTable'
--EXEC AddDefault 'TestTable', 'data', '---';
--EXEC RemoveDefault 'TestTable', 'data', '---';
--EXEC AddColumn 'Tests', 'descr', 'VARCHAR(10)'
--EXEC RemoveColumn 'Tests', 'descr'
--EXEC RemoveFK 'TestTable', 'test_id', 'Tests', 'id'

--DELETE FROM VersiuniBD WHERE id=(SELECT TOP 1 id FROM VersiuniBD ORDER BY id DESC)
--EXEC ClearVersionCache
SELECT * FROM VersiuniBD
SELECT * FROM ComenziSQL

--ALTER TABLE TestTable DROP CONSTRAINT df
--ALTER TABLE TestTable ADD CONSTRAINT df DEFAULT '2' FOR own_id
--SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='TestTable'

--DECLARE @versiune INT
--DECLARE @vid INT
--EXEC GetCurrentVersion @versiune OUTPUT, @vid OUTPUT
--SELECT @versiune, @vid

--INSERT INTO TestTable(own_id) VALUES(1)
--SELECT * FROM TestTable

--DECLARE @fields VARCHAR(300)
--EXEC GetTableFields 'TestTable', @fields
--SELECT @fields