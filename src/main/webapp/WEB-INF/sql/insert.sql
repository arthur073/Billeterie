-- Les spectacles

insert into Spectacle values(1, '-M- Je viens du Sud', 'defaultSpectacle.jpg');
insert into Spectacle values(2, 'Urban peace 3', 'urbanpeace.jpg');
insert into Spectacle values(3, 'La Fouine', 'la-fouine.jpg');
insert into Spectacle values(4, 'Patrick S. - Patoche et ses bretelles', 'patrick-sebastien.jpg');
insert into Spectacle values(5, 'Le Moulin Rouge - Le dessous des choses', 'moulin-rouge.jpg');
insert into Spectacle values(6, 'Gad Elmaleh', 'gad-el.jpg');
insert into Spectacle values(7, 'Coder en JSP facilement par Michel Kuhm', 'michel.jpg');
insert into Spectacle values(8, 'Le TP web pour les nuls - Thibault Vernadat', 'vernadat.jpg');



-- Les Réservations

insert into Representation values(1, 1, '2013-04-01 20:00:00', 0);
insert into Representation values(1, 2, '2013-05-01 01:00:00', 0);
insert into Representation values(1, 3, '2013-05-02 20:00:00', 0);
insert into Representation values(1, 4, '2013-05-04 17:00:00', 0);
insert into Representation values(2, 3, '2013-05-11 17:00:00', 0);
insert into Representation values(2, 4, '2013-05-10 20:00:00', 0);
insert into Representation values(3, 5, '2013-05-12 17:00:00', 0);
insert into Representation values(3, 2, '2013-05-12 20:00:00', 0);
insert into Representation values(4, 6, '2013-05-01 17:00:00', 0);
insert into Representation values(5, 7, '2013-04-20 20:00:00', 0);
insert into Representation values(6, 8, '2013-02-10 20:00:00', 0);
insert into Representation values(7, 9, '2013-01-10 18:00:00', 0);
insert into Representation values(8, 10, '2014-05-10 20:00:00', 0);
insert into Representation values(8, 6, '2014-05-11 19:30:00', 0);
insert into Representation values(8, 7, '2014-05-01 11:54:00', 0);


-- Les Zones
insert into Zone values(1, 'Poulailler', 16);
insert into Zone values(2, 'Orchestre', 56);
insert into Zone values(3, 'Balcon', 66);
insert into Zone values(4, 'Loge', 120);

-- Les Utilisateurs

insert into Utilisateur values ('vernadat','Vernadat','Thibault', 'titi@ensimag.fr','5d933eef19aee7da192608de61b6c23d','Client');
insert into Utilisateur values ('root', 'Israteur', 'Admin', 'admin@mes-billets.com', '63a9f0ea7bb98050796b649e85481845', 'Responsable');
insert into Utilisateur values ('user','Kuhm','Michel','Michel@ensimag.fr','f71dbe52628a3f83a77ab494817525c6','Client');

-- Les Places

insert into Place values (1,15,3);
insert into Place values (2,15,3);
insert into Place values (3,15,3);
insert into Place values (4,15,3);
insert into Place values (5,15,3);
insert into Place values (6,15,3);
insert into Place values (7,15,3);
insert into Place values (8,15,3);
insert into Place values (9,15,3);
insert into Place values (10,15,3);
insert into Place values (11,15,3);
insert into Place values (12,15,3);
insert into Place values (13,15,3);
insert into Place values (14,15,3);
insert into Place values (15,15,3);
insert into Place values (16,15,3);
insert into Place values (17,15,3);
insert into Place values (18,15,3);
insert into Place values (19,15,3);
insert into Place values (20,15,3);
insert into Place values (21,15,3);
insert into Place values (22,15,3);
insert into Place values (23,15,3);
insert into Place values (24,15,3);
insert into Place values (25,15,3);
insert into Place values (26,15,3);
insert into Place values (27,15,3);
insert into Place values (28,15,3);
insert into Place values (29,15,3);
insert into Place values (30,15,3);
insert into Place values (31,15,3);
insert into Place values (32,15,3);
insert into Place values (33,15,3);
insert into Place values (34,15,3);
insert into Place values (35,15,3);
insert into Place values (36,15,3);
insert into Place values (37,15,3);
insert into Place values (38,15,3);
insert into Place values (39,15,3);
insert into Place values (40,15,3);
insert into Place values (41,15,3);
insert into Place values (42,15,3);
insert into Place values (43,15,3);
insert into Place values (1,14,3);
insert into Place values (2,14,3);
insert into Place values (3,14,3);
insert into Place values (4,14,3);
insert into Place values (5,14,3);
insert into Place values (6,14,3);
insert into Place values (7,14,3);
insert into Place values (8,14,3);
insert into Place values (9,14,3);
insert into Place values (10,14,3);
insert into Place values (11,14,3);
insert into Place values (12,14,3);
insert into Place values (13,14,3);
insert into Place values (14,14,3);
insert into Place values (15,14,3);
insert into Place values (16,14,3);
insert into Place values (17,14,3);
insert into Place values (18,14,3);
insert into Place values (19,14,3);
insert into Place values (20,14,3);
insert into Place values (21,14,3);
insert into Place values (22,14,3);
insert into Place values (23,14,3);
insert into Place values (24,14,3);
insert into Place values (25,14,3);
insert into Place values (26,14,3);
insert into Place values (27,14,3);
insert into Place values (28,14,3);
insert into Place values (29,14,3);
insert into Place values (30,14,3);
insert into Place values (31,14,3);
insert into Place values (32,14,3);
insert into Place values (33,14,3);
insert into Place values (34,14,3);
insert into Place values (35,14,3);
insert into Place values (36,14,3);
insert into Place values (37,14,3);
insert into Place values (38,14,3);
insert into Place values (39,14,3);
insert into Place values (40,14,3);
insert into Place values (41,14,3);
insert into Place values (42,14,3);
insert into Place values (43,14,3);
insert into Place values (1,13,3);
insert into Place values (2,13,3);
insert into Place values (3,13,3);
insert into Place values (4,13,3);
insert into Place values (40,13,3);
insert into Place values (41,13,3);
insert into Place values (42,13,3);
insert into Place values (43,13,3);
insert into Place values (1,12,3);
insert into Place values (2,12,3);
insert into Place values (3,12,3);
insert into Place values (4,12,3);
insert into Place values (6,12,1);
insert into Place values (7,12,1);
insert into Place values (8,12,1);
insert into Place values (9,12,1);
insert into Place values (10,12,1);
insert into Place values (11,12,1);
insert into Place values (12,12,1);
insert into Place values (13,12,1);
insert into Place values (14,12,1);
insert into Place values (15,12,1);
insert into Place values (16,12,1);
insert into Place values (17,12,1);
insert into Place values (18,12,1);
insert into Place values (19,12,1);
insert into Place values (20,12,1);
insert into Place values (21,12,1);
insert into Place values (22,12,1);
insert into Place values (23,12,1);
insert into Place values (24,12,1);
insert into Place values (25,12,1);
insert into Place values (26,12,1);
insert into Place values (27,12,1);
insert into Place values (28,12,1);
insert into Place values (29,12,1);
insert into Place values (30,12,1);
insert into Place values (31,12,1);
insert into Place values (32,12,1);
insert into Place values (33,12,1);
insert into Place values (34,12,1);
insert into Place values (35,12,1);
insert into Place values (36,12,1);
insert into Place values (37,12,1);
insert into Place values (38,12,1);
insert into Place values (40,12,3);
insert into Place values (41,12,3);
insert into Place values (42,12,3);
insert into Place values (43,12,3);
insert into Place values (1,11,3);
insert into Place values (2,11,3);
insert into Place values (3,11,3);
insert into Place values (4,11,3);
insert into Place values (6,11,1);
insert into Place values (7,11,1);
insert into Place values (8,11,1);
insert into Place values (9,11,1);
insert into Place values (10,11,1);
insert into Place values (11,11,1);
insert into Place values (12,11,1);
insert into Place values (13,11,1);
insert into Place values (14,11,1);
insert into Place values (15,11,1);
insert into Place values (16,11,1);
insert into Place values (17,11,1);
insert into Place values (18,11,1);
insert into Place values (19,11,1);
insert into Place values (20,11,1);
insert into Place values (21,11,1);
insert into Place values (22,11,1);
insert into Place values (23,11,1);
insert into Place values (24,11,1);
insert into Place values (25,11,1);
insert into Place values (26,11,1);
insert into Place values (27,11,1);
insert into Place values (28,11,1);
insert into Place values (29,11,1);
insert into Place values (30,11,1);
insert into Place values (31,11,1);
insert into Place values (32,11,1);
insert into Place values (33,11,1);
insert into Place values (34,11,1);
insert into Place values (35,11,1);
insert into Place values (36,11,1);
insert into Place values (37,11,1);
insert into Place values (38,11,1);
insert into Place values (40,11,3);
insert into Place values (41,11,3);
insert into Place values (42,11,3);
insert into Place values (43,11,3);
insert into Place values (1,10,3);
insert into Place values (2,10,3);
insert into Place values (3,10,3);
insert into Place values (4,10,3);
insert into Place values (6,10,1);
insert into Place values (7,10,1);
insert into Place values (8,10,1);
insert into Place values (9,10,1);
insert into Place values (10,10,1);
insert into Place values (11,10,1);
insert into Place values (12,10,1);
insert into Place values (13,10,1);
insert into Place values (14,10,1);
insert into Place values (15,10,1);
insert into Place values (16,10,1);
insert into Place values (17,10,1);
insert into Place values (18,10,1);
insert into Place values (19,10,1);
insert into Place values (20,10,1);
insert into Place values (21,10,1);
insert into Place values (22,10,1);
insert into Place values (23,10,1);
insert into Place values (24,10,1);
insert into Place values (25,10,1);
insert into Place values (26,10,1);
insert into Place values (27,10,1);
insert into Place values (28,10,1);
insert into Place values (29,10,1);
insert into Place values (30,10,1);
insert into Place values (31,10,1);
insert into Place values (32,10,1);
insert into Place values (33,10,1);
insert into Place values (34,10,1);
insert into Place values (35,10,1);
insert into Place values (36,10,1);
insert into Place values (37,10,1);
insert into Place values (38,10,1);
insert into Place values (40,10,3);
insert into Place values (41,10,3);
insert into Place values (42,10,3);
insert into Place values (43,10,3);
insert into Place values (1,9,3);
insert into Place values (2,9,3);
insert into Place values (3,9,3);
insert into Place values (4,9,3);
insert into Place values (6,9,1);
insert into Place values (7,9,1);
insert into Place values (8,9,1);
insert into Place values (9,9,1);
insert into Place values (10,9,1);
insert into Place values (11,9,1);
insert into Place values (12,9,1);
insert into Place values (13,9,1);
insert into Place values (14,9,1);
insert into Place values (15,9,1);
insert into Place values (16,9,1);
insert into Place values (17,9,1);
insert into Place values (18,9,1);
insert into Place values (19,9,1);
insert into Place values (20,9,1);
insert into Place values (21,9,1);
insert into Place values (22,9,1);
insert into Place values (23,9,1);
insert into Place values (24,9,1);
insert into Place values (25,9,1);
insert into Place values (26,9,1);
insert into Place values (27,9,1);
insert into Place values (28,9,1);
insert into Place values (29,9,1);
insert into Place values (30,9,1);
insert into Place values (31,9,1);
insert into Place values (32,9,1);
insert into Place values (33,9,1);
insert into Place values (34,9,1);
insert into Place values (35,9,1);
insert into Place values (36,9,1);
insert into Place values (37,9,1);
insert into Place values (38,9,1);
insert into Place values (40,9,3);
insert into Place values (41,9,3);
insert into Place values (42,9,3);
insert into Place values (43,9,3);
insert into Place values (1,8,3);
insert into Place values (2,8,3);
insert into Place values (3,8,3);
insert into Place values (4,8,3);
insert into Place values (40,8,3);
insert into Place values (41,8,3);
insert into Place values (42,8,3);
insert into Place values (43,8,3);
insert into Place values (1,7,3);
insert into Place values (2,7,3);
insert into Place values (3,7,3);
insert into Place values (4,7,3);
insert into Place values (6,7,4);
insert into Place values (7,7,4);
insert into Place values (8,7,4);
insert into Place values (9,7,4);
insert into Place values (10,7,4);
insert into Place values (11,7,4);
insert into Place values (12,7,4);
insert into Place values (13,7,4);
insert into Place values (14,7,4);
insert into Place values (15,7,4);
insert into Place values (16,7,4);
insert into Place values (17,7,4);
insert into Place values (18,7,4);
insert into Place values (19,7,4);
insert into Place values (20,7,4);
insert into Place values (21,7,4);
insert into Place values (22,7,4);
insert into Place values (23,7,4);
insert into Place values (24,7,4);
insert into Place values (25,7,4);
insert into Place values (26,7,4);
insert into Place values (27,7,4);
insert into Place values (28,7,4);
insert into Place values (29,7,4);
insert into Place values (30,7,4);
insert into Place values (31,7,4);
insert into Place values (32,7,4);
insert into Place values (33,7,4);
insert into Place values (34,7,4);
insert into Place values (35,7,4);
insert into Place values (36,7,4);
insert into Place values (37,7,4);
insert into Place values (38,7,4);
insert into Place values (40,7,3);
insert into Place values (41,7,3);
insert into Place values (42,7,3);
insert into Place values (43,7,3);
insert into Place values (1,6,3);
insert into Place values (2,6,3);
insert into Place values (3,6,3);
insert into Place values (4,6,3);
insert into Place values (6,6,4);
insert into Place values (7,6,4);
insert into Place values (8,6,4);
insert into Place values (9,6,4);
insert into Place values (10,6,4);
insert into Place values (11,6,4);
insert into Place values (12,6,4);
insert into Place values (13,6,4);
insert into Place values (14,6,4);
insert into Place values (15,6,4);
insert into Place values (16,6,4);
insert into Place values (17,6,4);
insert into Place values (18,6,4);
insert into Place values (19,6,4);
insert into Place values (20,6,4);
insert into Place values (21,6,4);
insert into Place values (22,6,4);
insert into Place values (23,6,4);
insert into Place values (24,6,4);
insert into Place values (25,6,4);
insert into Place values (26,6,4);
insert into Place values (27,6,4);
insert into Place values (28,6,4);
insert into Place values (29,6,4);
insert into Place values (30,6,4);
insert into Place values (31,6,4);
insert into Place values (32,6,4);
insert into Place values (33,6,4);
insert into Place values (34,6,4);
insert into Place values (35,6,4);
insert into Place values (36,6,4);
insert into Place values (37,6,4);
insert into Place values (38,6,4);
insert into Place values (40,6,3);
insert into Place values (41,6,3);
insert into Place values (42,6,3);
insert into Place values (43,6,3);
insert into Place values (1,5,3);
insert into Place values (2,5,3);
insert into Place values (3,5,3);
insert into Place values (4,5,3);
insert into Place values (6,5,4);
insert into Place values (7,5,4);
insert into Place values (8,5,4);
insert into Place values (9,5,4);
insert into Place values (10,5,4);
insert into Place values (11,5,4);
insert into Place values (12,5,4);
insert into Place values (13,5,4);
insert into Place values (14,5,4);
insert into Place values (15,5,4);
insert into Place values (16,5,4);
insert into Place values (17,5,4);
insert into Place values (18,5,4);
insert into Place values (19,5,4);
insert into Place values (20,5,4);
insert into Place values (21,5,4);
insert into Place values (22,5,4);
insert into Place values (23,5,4);
insert into Place values (24,5,4);
insert into Place values (25,5,4);
insert into Place values (26,5,4);
insert into Place values (27,5,4);
insert into Place values (28,5,4);
insert into Place values (29,5,4);
insert into Place values (30,5,4);
insert into Place values (31,5,4);
insert into Place values (32,5,4);
insert into Place values (33,5,4);
insert into Place values (34,5,4);
insert into Place values (35,5,4);
insert into Place values (36,5,4);
insert into Place values (37,5,4);
insert into Place values (38,5,4);
insert into Place values (40,5,3);
insert into Place values (41,5,3);
insert into Place values (42,5,3);
insert into Place values (43,5,3);
insert into Place values (1,4,3);
insert into Place values (2,4,3);
insert into Place values (3,4,3);
insert into Place values (4,4,3);
insert into Place values (40,4,3);
insert into Place values (41,4,3);
insert into Place values (42,4,3);
insert into Place values (43,4,3);
insert into Place values (1,3,3);
insert into Place values (2,3,3);
insert into Place values (3,3,3);
insert into Place values (4,3,3);
insert into Place values (6,3,2);
insert into Place values (7,3,2);
insert into Place values (8,3,2);
insert into Place values (9,3,2);
insert into Place values (10,3,2);
insert into Place values (11,3,2);
insert into Place values (12,3,2);
insert into Place values (13,3,2);
insert into Place values (14,3,2);
insert into Place values (15,3,2);
insert into Place values (16,3,2);
insert into Place values (17,3,2);
insert into Place values (18,3,2);
insert into Place values (19,3,2);
insert into Place values (20,3,2);
insert into Place values (21,3,2);
insert into Place values (22,3,2);
insert into Place values (23,3,2);
insert into Place values (24,3,2);
insert into Place values (25,3,2);
insert into Place values (26,3,2);
insert into Place values (27,3,2);
insert into Place values (28,3,2);
insert into Place values (29,3,2);
insert into Place values (30,3,2);
insert into Place values (31,3,2);
insert into Place values (32,3,2);
insert into Place values (33,3,2);
insert into Place values (34,3,2);
insert into Place values (35,3,2);
insert into Place values (36,3,2);
insert into Place values (37,3,2);
insert into Place values (38,3,2);
insert into Place values (40,3,3);
insert into Place values (41,3,3);
insert into Place values (42,3,3);
insert into Place values (43,3,3);
insert into Place values (1,2,3);
insert into Place values (2,2,3);
insert into Place values (3,2,3);
insert into Place values (4,2,3);
insert into Place values (6,2,2);
insert into Place values (7,2,2);
insert into Place values (8,2,2);
insert into Place values (9,2,2);
insert into Place values (10,2,2);
insert into Place values (11,2,2);
insert into Place values (12,2,2);
insert into Place values (13,2,2);
insert into Place values (14,2,2);
insert into Place values (15,2,2);
insert into Place values (16,2,2);
insert into Place values (17,2,2);
insert into Place values (18,2,2);
insert into Place values (19,2,2);
insert into Place values (20,2,2);
insert into Place values (21,2,2);
insert into Place values (22,2,2);
insert into Place values (23,2,2);
insert into Place values (24,2,2);
insert into Place values (25,2,2);
insert into Place values (26,2,2);
insert into Place values (27,2,2);
insert into Place values (28,2,2);
insert into Place values (29,2,2);
insert into Place values (30,2,2);
insert into Place values (31,2,2);
insert into Place values (32,2,2);
insert into Place values (33,2,2);
insert into Place values (34,2,2);
insert into Place values (35,2,2);
insert into Place values (36,2,2);
insert into Place values (37,2,2);
insert into Place values (38,2,2);
insert into Place values (40,2,3);
insert into Place values (41,2,3);
insert into Place values (42,2,3);
insert into Place values (43,2,3);
insert into Place values (1,1,3);
insert into Place values (2,1,3);
insert into Place values (3,1,3);
insert into Place values (4,1,3);
insert into Place values (6,1,2);
insert into Place values (7,1,2);
insert into Place values (8,1,2);
insert into Place values (9,1,2);
insert into Place values (10,1,2);
insert into Place values (11,1,2);
insert into Place values (12,1,2);
insert into Place values (13,1,2);
insert into Place values (14,1,2);
insert into Place values (15,1,2);
insert into Place values (16,1,2);
insert into Place values (17,1,2);
insert into Place values (18,1,2);
insert into Place values (19,1,2);
insert into Place values (20,1,2);
insert into Place values (21,1,2);
insert into Place values (22,1,2);
insert into Place values (23,1,2);
insert into Place values (24,1,2);
insert into Place values (25,1,2);
insert into Place values (26,1,2);
insert into Place values (27,1,2);
insert into Place values (28,1,2);
insert into Place values (29,1,2);
insert into Place values (30,1,2);
insert into Place values (31,1,2);
insert into Place values (32,1,2);
insert into Place values (33,1,2);
insert into Place values (34,1,2);
insert into Place values (35,1,2);
insert into Place values (36,1,2);
insert into Place values (37,1,2);
insert into Place values (38,1,2);
insert into Place values (40,1,3);
insert into Place values (41,1,3);
insert into Place values (42,1,3);
insert into Place values (43,1,3);



-- AReserveNPlaces
-- insert into AReserveNPlaces values ('vernadat',1,1,1,1,1);
-- insert into AReserveNPlaces values ('vernadat',1,1,3,25,4);
insert into AReserveNPlaces values ('toto',1,1,1,1,3);

-- AAcheteNPlaces
-- insert into AAcheteNPlaces values ('vernadat',1,1,1,1,1,1,1,20130102);
insert into AAcheteNPlaces values ('toto', 1, 2, 1, 1, 3, 1, 1, 20130102);


