CREATE OR ALTER PROCEDURE PlayNext
	@utilizator_id INT
AS
BEGIN
	DECLARE @epid INT
	DECLARE @ult_ep INT
	SELECT @ult_ep=MAX(ord_cron) FROM Episoade
	SELECT TOP 1 @epid=id FROM Episoade WHERE id NOT IN
		(SELECT episod_id FROM Vizionari WHERE utilizator_id=@utilizator_id)
		AND ord_cron <> @ult_ep -- nu e ep 9
		ORDER BY NEWID()
	IF @epid IS NULL -- poate ca nu a vazut ult
	BEGIN
		SELECT TOP 1 @epid=id FROM Episoade WHERE id NOT IN
		(SELECT episod_id FROM Vizionari WHERE utilizator_id=@utilizator_id)
		--AND ord_cron <> @ult_ep => se poate ep 9
		ORDER BY NEWID()

		IF @epid IS NULL
			RETURN -- nu fac nmk daca am vzt tot
	END
	DECLARE @ord_ult_ep INT
	SELECT TOP 1 @ord_ult_ep=nr_ord FROM Vizionari WHERE utilizator_id=@utilizator_id AND episod_id=@epid
	INSERT INTO Vizionari(utilizator_id, episod_id, nr_ord)
		VALUES(@utilizator_id, @epid, @ord_ult_ep+1)
	PRINT 'Am dat play'
END