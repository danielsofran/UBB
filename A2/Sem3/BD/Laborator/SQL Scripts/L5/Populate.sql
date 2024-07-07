SET NOCOUNT ON

EXEC DeletePersoaneCluburi
EXEC DeletePersoane
EXEC DeleteCluburi
EXEC DeleteDetaliiPersoane

EXEC AddPersoane 10000
EXEC AddCluburi 10000
EXEC AddPersoaneCluburi 100000