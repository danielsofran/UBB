INSERT INTO TipTren(descriere) VALUES
	('InterRegio'), -- 1
	('CFR'),		-- 2
	('CFR Calatori'),-- 3
	('CFR Marfa'),   -- 4
	('De Jucarie'),  -- 5
	('CiuCiu')		 -- 6

INSERT INTO Tren(nume, id_tip) VALUES
	('Thomas', 5), -- 1
	('IR1962', 1),
	('IR1462', 1), -- 3
	('IR1952', 1),
	('IC54563', 3),
	('IC54263', 3), -- 6
	('IM5633', 4),
	('IM5233', 4),
	('IM5631', 4), -- 9
	('IM5993', 4)

INSERT INTO Ruta(nume, id_tren) VALUES
	('Nostalgia copilariei', 1), -- 1
	('Suceava - Cluj', 3),
	('Baia Mare - Cluj', 4), -- 3
	('Suceava - Bucuresti', 6),
	('Iasi - Satu Mare', 9),
	('Oradea - Neamt', 10) -- 6

INSERT INTO Statie(nume) VALUES
	('Suceava Nord'), 
	('Cluj Est'),
	('Clujana'), -- 3
	('Targu Mures'),
	('Oradea Sud'),
	('Gara de Nord'), -- 6
	('Baia Mare')

INSERT INTO StatieRuta(idStatie, idRuta, OraP, OraS) VALUES
	(1, 3, '10:10', '07:00'),
	(7, 3, '12:00', '15:00'),
	(1, 4, '11:00', '14:00'),
	(2, 4, '16:00', '07:13'),
	(6, 6, '14:10', '19:19'),
	(3, 2, '20:20', '04:32')