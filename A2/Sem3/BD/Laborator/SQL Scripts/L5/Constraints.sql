ALTER TABLE DetaliiPersoane DROP CONSTRAINT ck_email
ALTER TABLE DetaliiPersoane DROP CONSTRAINT ck_nume
ALTER TABLE Cluburi DROP CONSTRAINT ck_den

ALTER TABLE DetaliiPersoane ADD CONSTRAINT 
	ck_email CHECK(
		LEN(email) > 5 AND 
		CHARINDEX('@', email)>0 AND 
		CHARINDEX('.', email)>0)

ALTER TABLE DetaliiPersoane ADD CONSTRAINT
	ck_nume CHECK(nume NOT LIKE '[^A-Za-z ]')

ALTER TABLE Cluburi ADD CONSTRAINT
	ck_den CHECK(denumire NOT LIKE '[^A-Za-z]')