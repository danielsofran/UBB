INSERT INTO Categorii(denumire, heart_rate, blood_preasure, cardiac_output, training, cardiac_remodeling) VALUES('Skill', '+/++', '+', '+', '-', '-')
INSERT INTO Categorii(denumire, heart_rate, blood_preasure, cardiac_output, training, cardiac_remodeling) VALUES('Power', '++', '+++', '++', '+', '+')
INSERT INTO Categorii(denumire, heart_rate, blood_preasure, cardiac_output, training, cardiac_remodeling) VALUES('Mixed', '+++/++', '++', '+++/++', '++', '++')
INSERT INTO Categorii(denumire, heart_rate, blood_preasure, cardiac_output, training, cardiac_remodeling) VALUES('Endurance', '+++', '++', '+++', '+++', '+++')

INSERT INTO Cluburi(denumire, descriere) VALUES('Alergare Transilvania', 'Clubul alergatorilor din Ardeal')
INSERT INTO Cluburi(denumire, descriere) VALUES('Strava', 'Clubul oficial al utilizatorilor strava')
INSERT INTO Cluburi(denumire, descriere) VALUES('Cluj Sports', '#ClujulInMiscare')

INSERT INTO DetaliiPersoane(nume, email, data_nast) VALUES('Daniel', 'daniel@yahoo.com', '20030509')
INSERT INTO DetaliiPersoane(nume, email, data_nast) VALUES('Radu', 'vasi@gmail.com', '20020628')
INSERT INTO DetaliiPersoane(nume, email, data_nast) VALUES('Cipri', 'cpr@yahoo.com', '19501015')
INSERT INTO DetaliiPersoane(nume, email, data_nast) VALUES('Raul', 'raul@ubbcj.com', '20021123')
INSERT INTO DetaliiPersoane(nume, email, data_nast) VALUES('Cata', 'catalin@ubbcj.com', '20020706')

INSERT INTO Sporturi(denumire, categorie_id) VALUES('Tenis de masa', 1)
INSERT INTO Sporturi(denumire, categorie_id) VALUES('Tir cu arcul', 1)
INSERT INTO Sporturi(denumire, categorie_id) VALUES('Golf', 1)

INSERT INTO Sporturi(denumire, categorie_id) VALUES('Ski Alpin', 2)
INSERT INTO Sporturi(denumire, categorie_id) VALUES('Ski Nautic', 2)
INSERT INTO Sporturi(denumire, categorie_id) VALUES('Haltere', 2)

INSERT INTO Sporturi(denumire, categorie_id) VALUES('Fotbal', 3)
INSERT INTO Sporturi(denumire, categorie_id) VALUES('Basket', 3)
INSERT INTO Sporturi(denumire, categorie_id) VALUES('Volei', 3)

INSERT INTO Sporturi(denumire, categorie_id) VALUES('Alergare', 4)
INSERT INTO Sporturi(denumire, categorie_id) VALUES('Ciclism', 4)
INSERT INTO Sporturi(denumire, categorie_id) VALUES('Canoe', 4)


INSERT INTO Persoane(detalii_id, sport_fav_id) VALUES(1, 1)
INSERT INTO Persoane(detalii_id, sport_fav_id) VALUES(2, 8)
INSERT INTO Persoane(detalii_id, sport_fav_id) VALUES(3, 10)
INSERT INTO Persoane(detalii_id, sport_fav_id) VALUES(4, 7)
INSERT INTO Persoane(detalii_id, sport_fav_id) VALUES(5, 6)


-- D alergare
INSERT INTO MesajeData(persoana_id, moment, continut, likeuri)
	VALUES(1, CONVERT(DATETIME, '1.11.2022 10:00', 104), 
	'Prima alergare!', 3)

INSERT INTO MesajeData(persoana_id, moment, continut, likeuri)
	VALUES(2, CONVERT(DATETIME, '1.11.2022 11:30', 104), 'Bravo', 1)

INSERT INTO MesajeData(persoana_id, moment, continut, likeuri)
	VALUES(4, CONVERT(DATETIME, '2.11.2022 16:00', 104), 'Felicitari', 1)

-- D tenis de masa
INSERT INTO MesajeData(persoana_id, moment, continut, likeuri)
	VALUES(1, CONVERT(DATETIME, '2.10.2022 10:00', 104), 
	'Am castigat 11-2!', 3)
INSERT INTO MesajeData(persoana_id, moment, continut, likeuri)
	VALUES(5, CONVERT(DATETIME, '2.10.2022 10:30', 104), 'Te bat data viitoare', 1)

-- R fotbal
INSERT INTO MesajeData(persoana_id, moment, continut, likeuri)
	VALUES(4, CONVERT(DATETIME, '3.11.2022 10:00', 104), 
	'Primul fotbal castigat din sezon :D', 10)
INSERT INTO MesajeData(persoana_id, moment, continut, likeuri)
	VALUES(2, CONVERT(DATETIME, '3.11.2022 22:00', 104), 
	'Mama ca i-am spart', 5)

-- Cipri alergare
INSERT INTO MesajeData(persoana_id, moment, continut, likeuri)
	VALUES(3, CONVERT(DATETIME, '4.11.2022 12:00', 104), 
	'Semimaraton pe dealuri', 4)
INSERT INTO MesajeData(persoana_id, moment, continut, likeuri)
	VALUES(1, CONVERT(DATETIME, '4.11.2022 13:33', 104), 
	'Tot respectul, domn profesor', 0)

-- cata haltere
INSERT INTO MesajeData(persoana_id, moment, continut, likeuri)
	VALUES(5, CONVERT(DATETIME, '4.11.2022 08:35:41', 104), 
	'Leg Day', 0)

-- activitati postate
INSERT INTO Activitati(sport_id, moment_start, durata) VALUES
	(10, CONVERT(DATETIME, '01.11.2022 09:00', 104), '01:00')
INSERT INTO Activitati(sport_id, moment_start, durata) VALUES
	(1, CONVERT(DATETIME, '02.10.2022 08:00', 104), '02:00')
INSERT INTO Activitati(sport_id, moment_start, durata) VALUES
	(7, CONVERT(DATETIME, '03.11.2022 09:00', 104), '01:00')
INSERT INTO Activitati(sport_id, moment_start, durata) VALUES
	(10, CONVERT(DATETIME, '04.11.2022 10:00', 104), '02:00')
INSERT INTO Activitati(sport_id, moment_start, durata) VALUES
	(6, CONVERT(DATETIME, '04.11.2022 07:00', 104), '01:10')

-- activitati pt sporturi cu mai multe
INSERT INTO Activitati(sport_id, moment_start, durata) VALUES
	(1, CONVERT(DATETIME, '01.10.2022 16:00', 104), '03:00')

-- activitati pentru sporturi cu o sg act
INSERT INTO Activitati(sport_id, moment_start, durata) VALUES
	(8, CONVERT(DATETIME, '05.10.2022 17:00', 104), '00:50')
INSERT INTO Activitati(sport_id, moment_start, durata) VALUES
	(11, CONVERT(DATETIME, '05.10.2022 22:00', 104), '01:30')
INSERT INTO Activitati(sport_id, moment_start, durata) VALUES
	(3, CONVERT(DATETIME, '05.10.2022 10:00', 104), '01:00')

INSERT INTO Activitati(sport_id, moment_start, durata) VALUES
	(12, CONVERT(DATETIME, '06.10.2022 08:00', 104), '00:30')

INSERT INTO PostariClub(mesaj_id, titlu) VALUES (1, 'Run Cluj 1')
INSERT INTO PostariClub(mesaj_id, titlu) VALUES (4, 'PingPong Bistrita')
INSERT INTO PostariClub(mesaj_id, titlu) VALUES (6, 'Fotbal Gheorgheni')
INSERT INTO PostariClub(mesaj_id, titlu) VALUES (8, 'SM_S04_E01')
INSERT INTO PostariClub(mesaj_id, titlu) VALUES (10, 'Workout')

INSERT INTO ComentariiClub(postare_id, mesaj_id) VALUES(1, 2)
INSERT INTO ComentariiClub(postare_id, mesaj_id) VALUES(1, 3)
INSERT INTO ComentariiClub(postare_id, mesaj_id) VALUES(2, 5)
INSERT INTO ComentariiClub(postare_id, mesaj_id) VALUES(3, 7)
INSERT INTO ComentariiClub(postare_id, mesaj_id) VALUES(4, 9)

INSERT INTO PostariCluburi(postare_id, club_id) VALUES(1, 1)
INSERT INTO PostariCluburi(postare_id, club_id) VALUES(1, 2)
INSERT INTO PostariCluburi(postare_id, club_id) VALUES(1, 3)
INSERT INTO PostariCluburi(postare_id, club_id) VALUES(2, 2)
INSERT INTO PostariCluburi(postare_id, club_id) VALUES(3, 2)
INSERT INTO PostariCluburi(postare_id, club_id) VALUES(3, 3)
INSERT INTO PostariCluburi(postare_id, club_id) VALUES(4, 1)
INSERT INTO PostariCluburi(postare_id, club_id) VALUES(4, 2)
INSERT INTO PostariCluburi(postare_id, club_id) VALUES(5, 2)

INSERT INTO PersoaneCluburi(persoana_id, club_id, moment_join) VALUES
	(1, 1, CONVERT(DATETIME, '01.01.2020', 104))

INSERT INTO PersoaneCluburi(persoana_id, club_id, moment_join) VALUES
	(1, 2, CONVERT(DATETIME, '01.03.2021', 104))

INSERT INTO PersoaneCluburi(persoana_id, club_id, moment_join) VALUES
	(1, 3, CONVERT(DATETIME, '11.11.2020', 104))

INSERT INTO PersoaneCluburi(persoana_id, club_id, moment_join) VALUES
	(2, 1, CONVERT(DATETIME, '03.02.2018 10:00', 104))

INSERT INTO PersoaneCluburi(persoana_id, club_id, moment_join) VALUES
	(2, 2, CONVERT(DATETIME, '10.11.2020', 104))

INSERT INTO PersoaneCluburi(persoana_id, club_id, moment_join) VALUES
	(3, 1, CONVERT(DATETIME, '03.05.2020', 104))

INSERT INTO PersoaneCluburi(persoana_id, club_id, moment_join) VALUES
	(3, 2, CONVERT(DATETIME, '03.06.2018', 104))

INSERT INTO PersoaneCluburi(persoana_id, club_id, moment_join) VALUES
	(4, 3, CONVERT(DATETIME, '05.06.2015', 104))

INSERT INTO PersoaneCluburi(persoana_id, club_id, moment_join) VALUES
	(5, 1, CONVERT(DATETIME, '04.01.2020', 104))

INSERT INTO PersoaneCluburi(persoana_id, club_id, moment_join) VALUES
	(5, 2, CONVERT(DATETIME, '03.01.2020', 104))

-- prima alergare
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (1, 2)

-- tenis ul de masa
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (2, 3)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (5, 3)

-- fotbalul
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (1, 4)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (2, 4)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (3, 4)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (4, 4)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (5, 4)

-- alergare Cipri
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (3, 5)

-- haltere cata
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (5, 6)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (4, 6)

-- tenis masa 2
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (1, 7)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (2, 7)

-- basket
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (1, 8)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (2, 8)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (4, 8)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (5, 8)

-- ciclism
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (1, 9)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (3, 9)

-- golf
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (1, 10)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (2, 10)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (3, 10)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (4, 10)
INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (5, 10)

-- canoe
--INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (2, 11)
--INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (3, 11)
--INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (4, 11)
--INSERT INTO ActivitatiPersoane(persoana_id, activitate_id) VALUES (5, 11)
