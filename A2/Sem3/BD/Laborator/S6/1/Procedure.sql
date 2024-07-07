
CREATE OR ALTER PROCEDURE AddStatie
	-- ruta, statie, ora sosirii, ora plecarii -> adauga in many to many
	@ruta VARCHAR(50),
	@statie VARCHAR(50),
	@ora_plecarii TIME,
	@ora_sosirii TIME
AS
BEGIN
	DECLARE @idruta INT = NULL, @idstatie INT = NULL
	SELECT TOP 1 @idruta=id FROM Ruta WHERE nume = @ruta
	SELECT TOP 1 @idstatie=id FROM Statie WHERE nume = @statie
	IF @idruta IS NULL OR @idstatie IS NULL
		THROW 50010, 'Ruta sau statia nu a fost gasita!', 1
	IF (NOT EXISTS(SELECT * FROM StatieRuta WHERE idRuta=@idruta AND idStatie=@idstatie))
	BEGIN
		INSERT INTO StatieRuta (idRuta, idStatie, OraP, OraS) 
		VALUES(@idruta, @idstatie, @ora_plecarii, @ora_sosirii)
		PRINT 'RutaStatie inserata!'
	END
	ELSE
	BEGIN
		UPDATE StatieRuta SET OraP=@ora_plecarii, OraS=@ora_sosirii
			WHERE idRuta=@idruta AND idStatie=@idstatie 
		PRINT 'RutaStatie actualizata!'
	END
END
