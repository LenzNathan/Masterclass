DELETE
FROM gebaeude;
INSERT INTO gebaeude (geb_kuerzel, geb_name)
VALUES ('A', 'Anichstrasse'),
       ('H', 'Hofueberbau'),
       ('I', 'Innrain'),
       ('S', 'Stoeckelgebaeude');

DELETE
FROM stunden_start;
INSERT INTO stunden_start(start_id, start_zeit)
VALUES (1, '08:00'),
       (2, '08:50'),
       (3, '09:55'),
       (4, '10:45'),
       (5, '11:40'),
       (6, '12:30'),
       (7, '13:20'),
       (8, '14:10'),
       (9, '15:15'),
       (10, '16:05'),
       (11, '16:55'),
       (12, '18:00'),
       (13, '18:45'),
       (14, '19:45'),
       (15, '20:30'),
       (16, '21:15');

DELETE
FROM stunden_ende;
INSERT INTO stunden_ende(ende_id, ende_zeit)
VALUES (1, '08:50'),
       (2, '09:40'),
       (3, '10:45'),
       (4, '11:35'),
       (5, '12:30'),
       (6, '13:20'),
       (7, '14:10'),
       (8, '15:00'),
       (9, '16:05'),
       (10, '16:55'),
       (11, '17:45'),
       (12, '18:45'),
       (13, '19:30'),
       (14, '20:30'),
       (15, '21:15'),
       (16, '22:00');

DELETE
FROM abteilung;
INSERT INTO abteilung(abt_kuerzel, abt_name)
VALUES ('HWII', 'Höhere Abteilung Wirtschaftsingenieure und Betriebsinformatik'),
       ('HBG', 'Höhere Abteilung Biomedizin und Gesundheitstechnik'),
       ('HEL', 'Höhere Abteilung Elektronik und Technische Informatik'),
       ('HET', 'Höhere Abteilung Elektrotechnik und Prozessinformatik'),
       ('HMBT', 'Höhere Abteilung Maschinenbau und Robotic Centre'),
       ('FELC', 'Fachschule Elektronik und Technische Informatik'),
       ('FET', 'Fachschule Elektrotechnik und Prozessinformatik');