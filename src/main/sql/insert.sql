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
insert into Place values (1,2,2);
insert into Place values (2,2,2);
insert into Place values (3,2,2);
insert into Place values (4,2,2);
insert into Place values (5,2,2);
insert into Place values (6,2,2);
insert into Place values (7,2,2);
insert into Place values (8,2,2);
insert into Place values (9,2,2);
insert into Place values (10,2,2);
insert into Place values (1,3,3);
insert into Place values (2,3,3);
insert into Place values (3,3,3);
insert into Place values (4,3,3);
insert into Place values (5,3,3);
insert into Place values (6,3,3);
insert into Place values (7,3,3);
insert into Place values (8,3,3);
insert into Place values (9,3,3);
insert into Place values (10,3,3);

-- AReserveNPlaces
insert into AReserveNPlaces values ('vernadat',1,1,1,1,1);
insert into AReserveNPlaces values ('vernadat',1,1,8,3,3);


-- AAcheteNPlaces
insert into AAcheteNPlaces values ('vernadat',1,1,1,1,1,1,1,20130102);

-- Les Clients
-- Les Responsables

