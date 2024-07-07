--EXEC CreateTable 'Table1', 'id INT PRIMARY KEY, data1 TEXT'
--EXEC CreateTable 'Table2', 'id INT PRIMARY KEY, data2 INT'
--EXEC AddColumn 'Table1', 'table2_id', 'INT'
--EXEC AddFK 'Table1', 'table2_id', 'Table2', 'id'
--EXEC AddDefault 'Table2', 'data2', '---';
--EXEC RemoveDefault 'Table2', 'data2';
--EXEC ModifyColumn 'Table1', 'data1', 'NVARCHAR(10)'

--EXEC ClearVersionCache

EXEC GotoVersion 5

SELECT * FROM VersiuniBD
SELECT * FROM ComenziSQL

--ALTER TABLE Table2 ADD CONSTRAINT df_Table2_data2 DEFAULT '---' FOR data2

