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