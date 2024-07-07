INSERT INTO PostariClub(mesaj_id, titlu) VALUES (12, 'Run Cluj 1')
INSERT INTO PostariClub(mesaj_id, titlu) VALUES (15, 'PingPong Bistrita')
INSERT INTO PostariClub(mesaj_id, titlu) VALUES (17, 'Fotbal Gheorgheni')
INSERT INTO PostariClub(mesaj_id, titlu) VALUES (19, 'SM_S04_E01')
INSERT INTO PostariClub(mesaj_id, titlu) VALUES (21, 'Workout')

INSERT INTO ComentariiClub(postare_id, mesaj_id) VALUES(1, 13)
INSERT INTO ComentariiClub(postare_id, mesaj_id) VALUES(1, 14)
INSERT INTO ComentariiClub(postare_id, mesaj_id) VALUES(2, 16)
INSERT INTO ComentariiClub(postare_id, mesaj_id) VALUES(3, 18)
INSERT INTO ComentariiClub(postare_id, mesaj_id) VALUES(4, 20)

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