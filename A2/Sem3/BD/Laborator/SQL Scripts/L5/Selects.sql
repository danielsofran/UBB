SELECT * FROM PersoaneView
SELECT * FROM PersoaneCluburiView

GO
CREATE OR ALTER PROCEDURE SelectPersoaneView AS
BEGIN
	SELECT * FROM PersoaneView WHERE [Sport Fav]='Basket' 
	SELECT email FROM PersoaneView WHERE YEAR([Data Nasterii]) > 2018
	SELECT nume, [Data Nasterii] FROM PersoaneView WHERE nume LIKE '[A-M]%' ORDER BY nume, [Data Nasterii]
END

GO
CREATE OR ALTER PROCEDURE SelectPersoaneCluburiView AS
BEGIN
	SELECT * FROM PersoaneCluburiView ORDER BY Persoana
	SELECT * FROM PersoaneCluburiView ORDER BY Club, Persoana DESC
END