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
insert into Zone values(3, 'Zone !', 66);



-- Les prix des représentations 

insert into PrixRepresentation values(1, 1, 1, 15);
insert into PrixRepresentation values(1, 1, 2, 25);
insert into PrixRepresentation values(1, 1, 3, 55.55);

-- Les Utilisateurs
-- Les Clients
-- Les Responsables

