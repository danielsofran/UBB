-- 1st view
DROP INDEX IX_SP_DEN ON Sporturi
DROP INDEX IX_P_SpId ON Persoane
DROP INDEX IX_DP_DN ON DetaliiPersoane
DROP INDEX IX_DP_NDN ON DetaliiPersoane

CREATE INDEX IX_DP_DN ON DetaliiPersoane -- query 2
	(data_nast ASC) INCLUDE(email)

CREATE INDEX IX_SP_DEN ON Sporturi -- query 1
	(denumire ASC)

CREATE INDEX IX_P_SpId ON Persoane -- query 1
	(sport_fav_id ASC)

CREATE INDEX IX_DP_NDN ON DetaliiPersoane -- query 3
	(nume ASC, data_nast ASC)

-- 2nd view
DROP INDEX IX_DP_N ON DetaliiPersoane
DROP INDEX IX_C_DEN ON Cluburi

CREATE INDEX IX_DP_N ON DetaliiPersoane
	(nume ASC) 

CREATE INDEX IX_C_DEN ON Cluburi
	(denumire ASC)
