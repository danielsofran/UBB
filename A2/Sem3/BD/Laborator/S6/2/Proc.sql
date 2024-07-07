CREATE OR ALTER PROCEDURE AddStatieRuta
	@ruta_id INT,
	@statie_id INT,
	@oraS TIME,
	@oraP TIME
AS
BEGIN
	-- check if ruta/statie exists
	IF EXISTS(SELECT * FROM StatiiRute WHERE ruta_id=@ruta_id AND statie_id=@statie_id)
		UPDATE StatiiRute SET oraS=@oraS, oraP=@oraP WHERE ruta_id=@ruta_id AND statie_id=@statie_id
	ELSE
		INSERT INTO StatiiRute(ruta_id, statie_id, oraS, oraP)
			VALUES(@ruta_id, @statie_id, @oraS, @oraP)
END