INSERT INTO Tipuri VALUES
	('tip1'),
	('tip2'),
	('tip3')

INSERT INTO Trenuri VALUES
	('tren11', 1),
	('tren21', 1),
	('tren31', 1),
	('tren12', 2),
	('tren22', 2),
	('tren32', 2),
	('tren13', 3),
	('tren23', 3),
	('tren33', 3)

INSERT INTO Rute VALUES
	('ruta1', 3), -- 1 3 5
	('ruta2', 5), -- 2 1 3
	('ruta3', 7)  -- 3 4 1

INSERT INTO Statii VALUES
	('statia1'),
	('statia2'),
	('statia3'),
	('statia4'),
	('statia5')

INSERT INTO StatiiRute VALUES
	(1, 4, '09:50', '10:10'),

	(1, 1, '10:00', '10:30'),
	(1, 3, '12:00', '12:20'),
	(1, 5, '22:00', '22:10'),
	(2, 2, '09:00', '09:40'),
	(2, 1, '09:50', '10:10'),
	(2, 3, '12:10', '12:15'), 
	(3, 3, '07:00', '07:20'),
	(3, 4, '10:00', '10:30'),
	(3, 1, '21:50', '22:20')
	


