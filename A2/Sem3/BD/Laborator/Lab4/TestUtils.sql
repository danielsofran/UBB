--DECLARE @fields VARCHAR(300)
--DECLARE @createfields VARCHAR(300)

--EXEC GetTableFields 'Studenti', @fields OUTPUT, @createfields OUTPUT

--SELECT @fields
--SELECT @createfields

--PRINT dbo.udf_GetNrFK('ProfesoriStudenti')

--SELECT * FROM INFORMATION_SCHEMA.VIEWS

--SELECT * FROM string_split(' Tabel Tabel2  Marfa ', ' ') WHERE RTRIM(value)!= ''

--DECLARE @rez VARCHAR(100)
--EXEC GetFKVal 'Studenti', 'persoana_id', @rez OUTPUT
--SELECT @rez

--DECLARE @fields VARCHAR(100)
--DECLARE @fieldvals VARCHAR(100)

--EXEC PrepareInsertProc 'ProfesoriStudenti', @fields OUTPUT, @fieldvals OUTPUT

--SELECT @fields, @fieldvals

--DECLARE @SQL VARCHAR(MAX)
--EXEC CreateInserts 'Studenti', 10, @SQL OUTPUT
--EXEC(@SQL)

--EXEC CreateTest 'Test1', 10, 'NumePersoane ProfesoriStudenti ClaseProfesori Studenti Persoane NumeStudenti'

--SELECT * FROM Tests
--SELECT * FROM [Tables]
--SELECT * FROM [Views]
--SELECT * FROM TestTables
--SELECT * FROM TestViews

--SELECT Tables.Name, TestTables.NoOfRows, TestTables.Position FROM TestTables 
--	INNER JOIN Tests ON Tests.TestID = 1
--	INNER JOIN Tables ON TestTables.TableID = Tables.TableID
--	ORDER BY Position DESC

--DECLARE @SQL VARCHAR(MAX)
--EXEC CreateInserts 'ProfesoriStudenti', 20, @SQL OUTPUT
--DELETE FROM ProfesoriStudenti
--EXEC(@SQL)
--SELECT P1.nume as 'prof', Persoane.nume as 'stud' FROM ProfesoriStudenti
--	INNER JOIN Persoane P1 ON P1.id = ProfesoriStudenti.prof_id
--	INNER JOIN Studenti ON Studenti.id = ProfesoriStudenti.stud_id
--	INNER JOIN Persoane ON Studenti.persoana_id = Persoane.id

--DECLARE @testid INT = 2

--SELECT Tables.Name FROM TestTables 
--		INNER JOIN Tests ON Tests.TestID = TestTables.TestID
--		INNER JOIN Tables ON TestTables.TableID = Tables.TableID
--		WHERE Tests.TestID = @testid
--		ORDER BY Position DESC

--SELECT Tables.TableID, Tables.Name, TestTables.NoOfRows FROM TestTables 
--		INNER JOIN Tables ON TestTables.TableID = Tables.TableID 
--		WHERE TestTables.TestID = @testid
--		ORDER BY TestTables.Position

--SELECT TestViews.ViewID, V.Name FROM TestViews 
--		INNER JOIN Tests ON TestViews.TestID = Tests.TestID
--		INNER JOIN Views V ON V.ViewID = TestViews.ViewID
--		WHERE Tests.TestID = @testid
		--ORDER BY V.ViewID

--DELETE FROM ProfesoriStudenti
--EXEC CreateTest 'Test3', 100, 'Studenti Persoane'
--EXEC RunTest 'Test3'

--SELECT * FROM CacheExcluded

--SELECT COUNT(*) FROM Persoane

--DECLARE @rez VARCHAR(100)
--EXEC GetFKVal 'Studenti', 'persoana_id', @rez OUTPUT
----DELETE FROM CacheExcluded
----INSERT INTO CacheExcluded(tablename, colname, colvals) VALUES('Persoane', 'id', '916,')
--SELECT @rez
--SELECT * From CacheExcluded

--EXEC CreateTest 'Test1', 100, 'NumePersoane ProfesoriStudenti ClaseProfesori Studenti Persoane NumeStudenti'
----EXEC CreateTest2 'Test2', 'NumePersoane ProfesoriStudenti 100 ClaseProfesori Studenti 100 Persoane 200 NumeStudenti'
--EXEC RunTest 'Test1'

--SELECT COUNT(*) FROM Persoane
--SELECT COUNT(*) FROM Studenti
--SELECT COUNT(*) FROM ProfesoriStudenti
--SELECT * FROM TestRuns
--SELECT * FROM TestRunTables
--SELECT * FROM TestRunViews