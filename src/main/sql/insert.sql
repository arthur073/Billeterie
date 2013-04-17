source creation.sql;

-- Les spectacles

insert into Spectacle values(1, '-M- Première partie', 'defaultSpectacle.jpg');
insert into Spectacle values(2, 'Urban peace 3', 'urbanpeace.jpg');
insert into Spectacle values(3, 'La Fouine', 'defaultSpectacle.jpg');
insert into Spectacle values(4, 'Patrick Sébastien', 'defaultSpectacle.jpg');
insert into Spectacle values(5, 'Le Moulin Rouge - Le dessous des choses', 'defaultSpectacle.jpg');
insert into Spectacle values(6, 'Gad Elmaleh', 'defaultSpectacle.jpg');
insert into Spectacle values(7, 'Coder en JSP facilement par Michel Kuhm', 'defaultSpectacle.jpg');
insert into Spectacle values(8, 'Le TP web pour les nuls - Thibault Vernadat', 'defaultSpectacle.jpg');



-- Les Réservations

insert into Representation values(1, 1, '2013-05-01');
insert into Representation values(1, 2, '2013-05-02');
insert into Representation values(1, 3, '2013-05-04');
insert into Representation values(2, 3, '2013-05-11');
insert into Representation values(2, 4, '2013-05-10');
insert into Representation values(3, 5, '2013-05-12');
insert into Representation values(4, 6, '2013-05-01');
insert into Representation values(5, 7, '2013-04-20');
insert into Representation values(6, 8, '2013-02-10');
insert into Representation values(7, 9, '2013-01-10');
insert into Representation values(8, 10, '2014-05-10');


-- Les Zones
insert into Zone values(1, 'Poulailler', 16);
insert into Zone values(2, 'Orchestre', 56);
insert into Zone values(3, 'Balcon', 66);
insert into Zone values(4, 'Loge', 120);

-- Les Utilisateurs

insert into Utilisateur values ('vernadat','Vernadat','Thibault', 'titi@ensimag.fr','5d933eef19aee7da192608de61b6c23d','Client');
insert into Utilisateur values ('Michel57390', 'Haha', 'Michel', 'michel@haha.fr', '63a9f0ea7bb98050796b649e85481845', 'Responsable');

-- Les Places
insert into Place values (1,1,1);
insert into Place values (2,1,1);
insert into Place values (3,1,1);
insert into Place values (4,1,1);
insert into Place values (5,1,1);
insert into Place values (6,1,1);
insert into Place values (7,1,1);
insert into Place values (8,1,1);
insert into Place values (9,1,1);
insert into Place values (10,1,1);
insert into Place values (1,2,1);
insert into Place values (2,2,1);
insert into Place values (3,2,1);
insert into Place values (4,2,1);
insert into Place values (5,2,1);
insert into Place values (6,2,1);
insert into Place values (7,2,1);
insert into Place values (8,2,1);
insert into Place values (9,2,1);
insert into Place values (10,2,1);
insert into Place values (1,3,1);
insert into Place values (2,3,1);
insert into Place values (3,3,1);
insert into Place values (4,3,1);
insert into Place values (5,3,1);
insert into Place values (6,3,1);
insert into Place values (7,3,1);
insert into Place values (8,3,1);
insert into Place values (9,3,1);
insert into Place values (10,3,1);
insert into Place values (1,4,1);
insert into Place values (2,4,1);
insert into Place values (3,4,1);
insert into Place values (4,4,1);
insert into Place values (5,4,1);
insert into Place values (6,4,1);
insert into Place values (7,4,1);
insert into Place values (8,4,1);
insert into Place values (9,4,1);
insert into Place values (10,4,1);
insert into Place values (1,5,1);
insert into Place values (2,5,1);
insert into Place values (3,5,1);
insert into Place values (4,5,1);
insert into Place values (5,5,1);
insert into Place values (6,5,1);
insert into Place values (7,5,1);
insert into Place values (8,5,1);
insert into Place values (9,5,1);
insert into Place values (10,5,1);
insert into Place values (1,6,1);
insert into Place values (2,6,1);
insert into Place values (3,6,1);
insert into Place values (4,6,1);
insert into Place values (5,6,1);
insert into Place values (6,6,1);
insert into Place values (7,6,1);
insert into Place values (8,6,1);
insert into Place values (9,6,1);
insert into Place values (10,6,1);
insert into Place values (1,7,1);
insert into Place values (2,7,1);
insert into Place values (3,7,1);
insert into Place values (4,7,1);
insert into Place values (5,7,1);
insert into Place values (6,7,1);
insert into Place values (7,7,1);
insert into Place values (8,7,1);
insert into Place values (9,7,1);
insert into Place values (10,7,1);
insert into Place values (1,8,1);
insert into Place values (2,8,1);
insert into Place values (3,8,1);
insert into Place values (4,8,1);
insert into Place values (5,8,1);
insert into Place values (6,8,1);
insert into Place values (7,8,1);
insert into Place values (8,8,1);
insert into Place values (9,8,1);
insert into Place values (10,8,1);
insert into Place values (1,9,1);
insert into Place values (2,9,1);
insert into Place values (3,9,1);
insert into Place values (4,9,1);
insert into Place values (5,9,1);
insert into Place values (6,9,1);
insert into Place values (7,9,1);
insert into Place values (8,9,1);
insert into Place values (9,9,1);
insert into Place values (10,9,1);
insert into Place values (1,10,1);
insert into Place values (2,10,1);
insert into Place values (3,10,1);
insert into Place values (4,10,1);
insert into Place values (5,10,1);
insert into Place values (6,10,1);
insert into Place values (7,10,1);
insert into Place values (8,10,1);
insert into Place values (9,10,1);
insert into Place values (10,10,1);
insert into Place values (1,11,1);
insert into Place values (2,11,1);
insert into Place values (3,11,1);
insert into Place values (4,11,1);
insert into Place values (5,11,1);
insert into Place values (6,11,1);
insert into Place values (7,11,1);
insert into Place values (8,11,1);
insert into Place values (9,11,1);
insert into Place values (10,11,1);
insert into Place values (1,12,1);
insert into Place values (2,12,1);
insert into Place values (3,12,1);
insert into Place values (4,12,1);
insert into Place values (5,12,1);
insert into Place values (6,12,1);
insert into Place values (7,12,1);
insert into Place values (8,12,1);
insert into Place values (9,12,1);
insert into Place values (10,12,1);
insert into Place values (1,13,2);
insert into Place values (2,13,2);
insert into Place values (3,13,2);
insert into Place values (4,13,2);
insert into Place values (5,13,2);
insert into Place values (6,13,2);
insert into Place values (7,13,2);
insert into Place values (8,13,2);
insert into Place values (9,13,2);
insert into Place values (10,13,2);
insert into Place values (1,14,2);
insert into Place values (2,14,2);
insert into Place values (3,14,2);
insert into Place values (4,14,2);
insert into Place values (5,14,2);
insert into Place values (6,14,2);
insert into Place values (7,14,2);
insert into Place values (8,14,2);
insert into Place values (9,14,2);
insert into Place values (10,14,2);
insert into Place values (1,15,2);
insert into Place values (2,15,2);
insert into Place values (3,15,2);
insert into Place values (4,15,2);
insert into Place values (5,15,2);
insert into Place values (6,15,2);
insert into Place values (7,15,2);
insert into Place values (8,15,2);
insert into Place values (9,15,2);
insert into Place values (10,15,2);
insert into Place values (1,16,2);
insert into Place values (2,16,2);
insert into Place values (3,16,2);
insert into Place values (4,16,2);
insert into Place values (5,16,2);
insert into Place values (6,16,2);
insert into Place values (7,16,2);
insert into Place values (8,16,2);
insert into Place values (9,16,2);
insert into Place values (10,16,2);
insert into Place values (1,17,2);
insert into Place values (2,17,2);
insert into Place values (3,17,2);
insert into Place values (4,17,2);
insert into Place values (5,17,2);
insert into Place values (6,17,2);
insert into Place values (7,17,2);
insert into Place values (8,17,2);
insert into Place values (9,17,2);
insert into Place values (10,17,2);
insert into Place values (1,18,2);
insert into Place values (2,18,2);
insert into Place values (3,18,2);
insert into Place values (4,18,2);
insert into Place values (5,18,2);
insert into Place values (6,18,2);
insert into Place values (7,18,2);
insert into Place values (8,18,2);
insert into Place values (9,18,2);
insert into Place values (10,18,2);
insert into Place values (1,19,2);
insert into Place values (2,19,2);
insert into Place values (3,19,2);
insert into Place values (4,19,2);
insert into Place values (5,19,2);
insert into Place values (6,19,2);
insert into Place values (7,19,2);
insert into Place values (8,19,2);
insert into Place values (9,19,2);
insert into Place values (10,19,2);
insert into Place values (1,20,2);
insert into Place values (2,20,2);
insert into Place values (3,20,2);
insert into Place values (4,20,2);
insert into Place values (5,20,2);
insert into Place values (6,20,2);
insert into Place values (7,20,2);
insert into Place values (8,20,2);
insert into Place values (9,20,2);
insert into Place values (10,20,2);
insert into Place values (1,21,2);
insert into Place values (2,21,2);
insert into Place values (3,21,2);
insert into Place values (4,21,2);
insert into Place values (5,21,2);
insert into Place values (6,21,2);
insert into Place values (7,21,2);
insert into Place values (8,21,2);
insert into Place values (9,21,2);
insert into Place values (10,21,2);
insert into Place values (1,22,4);
insert into Place values (2,22,4);
insert into Place values (3,22,4);
insert into Place values (4,22,4);
insert into Place values (5,22,4);
insert into Place values (6,22,4);
insert into Place values (7,22,4);
insert into Place values (8,22,4);
insert into Place values (9,22,4);
insert into Place values (10,22,4);
insert into Place values (1,23,4);
insert into Place values (2,23,4);
insert into Place values (3,23,4);
insert into Place values (4,23,4);
insert into Place values (5,23,4);
insert into Place values (6,23,4);
insert into Place values (7,23,4);
insert into Place values (8,23,4);
insert into Place values (9,23,4);
insert into Place values (10,23,4);
insert into Place values (1,24,4);
insert into Place values (2,24,4);
insert into Place values (3,24,4);
insert into Place values (4,24,4);
insert into Place values (5,24,4);
insert into Place values (6,24,4);
insert into Place values (7,24,4);
insert into Place values (8,24,4);
insert into Place values (9,24,4);
insert into Place values (10,24,4);
insert into Place values (1,25,4);
insert into Place values (2,25,4);
insert into Place values (3,25,4);
insert into Place values (4,25,4);
insert into Place values (5,25,4);
insert into Place values (6,25,4);
insert into Place values (7,25,4);
insert into Place values (8,25,4);
insert into Place values (9,25,4);
insert into Place values (10,25,4);
insert into Place values (1,26,4);
insert into Place values (2,26,4);
insert into Place values (3,26,4);
insert into Place values (4,26,4);
insert into Place values (5,26,4);
insert into Place values (6,26,4);
insert into Place values (7,26,4);
insert into Place values (8,26,4);
insert into Place values (9,26,4);
insert into Place values (10,26,4);
insert into Place values (1,27,4);
insert into Place values (2,27,4);
insert into Place values (3,27,4);
insert into Place values (4,27,4);
insert into Place values (5,27,4);
insert into Place values (6,27,4);
insert into Place values (7,27,4);
insert into Place values (8,27,4);
insert into Place values (9,27,4);
insert into Place values (10,27,4);


-- AReserveNPlaces
insert into AReserveNPlaces values ('vernadat',1,1,1,1,1);
insert into AReserveNPlaces values ('vernadat',1,1,3,25,4);

-- AAcheteNPlaces
insert into AAcheteNPlaces values ('vernadat',1,1,1,1,1,1,1,20130102);
insert into AAcheteNPlaces values ('Michel57390', 2, 3, 1, 1, 1, 1, 1, 20130102);
insert into AAcheteNPlaces values ('Michel57390', 6, 8, 1, 1, 1, 2, 2, 20130102);

-- Les Clients
-- Les Responsables



