DELETE FROM photos;
DELETE FROM comments;
DELETE FROM causes;
DELETE FROM users;
DELETE FROM causereactions;
DELETE FROM commentreactions;

INSERT INTO users (id, username, email, password)
VALUES (1, 'john', 'john@gmail.com', 'parola'),
       (2, 'jane', 'jane@gmail.com', 'parola'),
       (3, 'joe', 'joe@yahoo.com', 'parola'),
       (4, 'jim', 'jim@gmail.com', 'parola');


INSERT INTO causes(id, cause_type, date, deadline, description, location, money_obtained, money_target, name, score, user_id)
VALUES (1, 'Personal', '2023-10-10', '2023-10-21', 'I need urgent help for my kitty :(', 'Cluj-Napoca, Romania', 25, 500, 'Help Fluffy', 0, 2),
       (2, 'Organization', '2023-11-13', '2023-11-24', 'Ajuta cabinetul medical BestVet', 'Cluj-Napoca, Romania', 2500, 5000, 'BestVet', 100, 2),
       (3, 'Organization', '2022-02-01', '2022-02-27', 'Procurare echipament medical', 'Timsoara, Romania', 300, 10000, 'Clinica Veterinarius', 51, 3),
       (4, 'Personal', '2023-08-24', '2023-12-05', 'Help me fund my doggy''s new cage', 'Bern, Switzerland', 36000, 100000, 'John Doe''s cry for help', 10000, 1);

INSERT INTO photos (id, cause_id, path)
VALUES (1, 1, '/images/causes/1701010960061_cat11.jpg'),
       (2, 1, '/images/causes/1701010960085_cat12.webp'),
       (3, 2, '/images/causes/1701010963730_cat21.webp'),
       (4, 2, '/images/causes/1701010963733_cat22.avif'),
       (5, 3, '/images/causes/1701010966650_cat31.jpg'),
       (6, 3, '/images/causes/1701010966653_cat32.jpg'),
       (7, 3, '/images/causes/1701010966656_cat33.jpg'),
       (8, 4, '/images/causes/1701010969004_cat41.jpg'),
       (9, 4, '/images/causes/1701010969007_cat42.jpg');

INSERT INTO comments(id, date, message, cause_id, user_id)
VALUES (1, '2023-10-10 12:30:02.000000', 'What a cute kitty!!!', 1, 1),
       (2, '2023-10-10 16:45:12.000000', 'OMG!!! She is so cute!', 1, 2),
       (3, '2023-11-13 10:02:32.124334', 'Recomand cu incredere clinica BestVet! Am avut pisicuta la ei cu piciorul rupt! Cei mai tari din oras!', 2, 1),
       (4, '2023-11-13 15:12:32.000000', 'Super seriosi, ajut cu drag!', 2, 3),
       (5, '2023-10-10 12:50:02.000000', 'Thanks for your support!', 1, 2),
       (6, '2023-10-10 14:01:02.000000', 'We did it, thanks so much!', 1, 2),
       (7, '2023-10-10 16:30:02.000000', 'Your kitty is so cute, hope she gets well!', 1, 3),
       (8, '2023-10-10 17:30:02.000000', 'OMG, I am gonna cry!', 1, 4),
       (9, '2023-10-10 18:30:02.000000', '<3 <3 <3', 1, 4);

INSERT INTO causereactions(id, user_id, cause_id, reaction_type)
VALUES (1, 1, 1, 3),
       (2, 3, 1, 3),
       (3, 4, 1, 1),
       (4, 1, 3, 1),
       (5, 2, 3, 1),
       (6, 3, 3, 2),
       (7, 4, 3, 1),
       (8, 2, 4, 2),
       (10, 2, 1, 3),
       (11, 2, 2, 0);

INSERT INTO commentreactions(id, user_id, comment_id, reaction_type)
VALUES (1, 2, 1, 0),
       (2, 3, 1, 0),
       (3, 3, 3, 1),
       (4, 2, 4, 1),
       (5, 3, 9, 2),
       (6, 2, 9, 1);

SELECT setval('user_sequence', 4, true);
SELECT setval('cause_sequence', 4, true);
SELECT setval('photo_sequence', 9, true);
SELECT setval('comment_sequence', 9, true);
SELECT setval('causereaction_sequence', 11, true);
SELECT setval('commentreaction_sequence', 6, true);
