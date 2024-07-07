CREATE INDEX IX_Comenzi_data_asc ON Comenzi(data_comanda ASC)
--DROP INDEX IX_Comenzi_data_asc ON Comenzi
GO

SELECT data_comanda FROM Comenzi WHERE data_comanda > '2023-04-25' 