-- Les check suivant sont faits au niveau applicatif :
-- - faire un check entre la date courante (pour reservation / achat ) et la date
--   de la représentation. Si < 1h, on refuse.
-- - faire un check qu’il reste plus de 70 places avant achat / réservation (et
--   après)
-- Pour client et responsable : une seule table car mêmes attributs, on rajoute un
-- champ pour les distinguer.
-- THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES,
-- INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
-- FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.

DROP TABLE AAcheteNPlaces;
DROP TABLE AReserveNPlaces;
DROP TABLE Place;
DROP TABLE Utilisateur;
DROP TABLE Zone;
DROP TABLE Representation;
DROP TABLE Spectacle;

CREATE TABLE Spectacle (
    NoSpectacle int,
    Nom varchar(100) NOT NULL,
    Image varchar(300),
    CONSTRAINT pk_Spectacle PRIMARY KEY (NoSpectacle)
);

CREATE TABLE Representation (
    NoSpectacle int,
    NoRepresentation int,
    DateRepresentation datetime NOT NULL,
    Annule bool NOT NULL default 0,
    CONSTRAINT pk_Representation PRIMARY KEY (NoSpectacle, NoRepresentation),
    CONSTRAINT fk_Representation FOREIGN KEY (NoSpectacle) REFERENCES Spectacle (NoSpectacle)
);

CREATE TABLE Utilisateur (
    Login varchar(30),
    Nom varchar(50) NOT NULL,
    Prenom varchar(50) NOT NULL,
    Mail varchar(100),
    MotDePasse varchar(128) NOT NULL,
    Type varchar(11) NOT NULL,
    CONSTRAINT pk_Client PRIMARY KEY (Login)
);

CREATE TABLE Zone (
    NoZone int,
    Categorie varchar(20) NOT NULL,
    TarifBase double(6,2) NOT NULL,
    CONSTRAINT pk_Zone PRIMARY KEY (NoZone)
);

CREATE TABLE Place (
    NoPlace int,
    NoRang int,
    NoZone int,
    CONSTRAINT pk_Place PRIMARY KEY (NoPlace, NoRang, NoZone),
    CONSTRAINT fk_Place FOREIGN KEY (NoZone) REFERENCES Zone (NoZone)
);

CREATE TABLE AAcheteNPlaces (
    Login varchar(30),
    NoSpectacle int,
    NoRepresentation int,
    NoPlace int,
    NoRang int,
    NoZone int,
    NoDossier int,
    NoSerie int,
    DateAchat datetime,
    CONSTRAINT pk_AAcheteNPlaces PRIMARY KEY (Login, NoSpectacle, NoRepresentation, NoZone, NoRang, NoPlace, NoDossier, NoSerie),
    CONSTRAINT uq_AAcheteNPlaces UNIQUE (Login, NoSpectacle, NoRepresentation, NoZone, NoRang, NoPlace),
    -- CONSTRAINT uq_AAcheteNPlaces_NoSer UNIQUE (NoSerie),
    CONSTRAINT fk_AAcheteNPlaces_Place FOREIGN KEY (NoPlace, NoRang, NoZone) REFERENCES Place (NoPlace, NoRang, NoZone),
    CONSTRAINT fk_AAcheteNPlaces_Representation FOREIGN KEY (NoSpectacle, NoRepresentation) REFERENCES Representation (NoSpectacle, NoRepresentation),
    CONSTRAINT fk_AAcheteNPlaces_Client FOREIGN KEY (Login) REFERENCES Utilisateur (Login)
);

CREATE TABLE AReserveNPlaces (
    Login varchar(30),
    NoSpectacle int,
    NoRepresentation int,
    NoPlace int,
    NoRang int,
    NoZone int,
    CONSTRAINT pk_AReserveNPlaces PRIMARY KEY (Login, NoSpectacle, NoRepresentation, NoZone, NoRang, NoPlace),
    CONSTRAINT fk_AReserveNPlaces_Place FOREIGN KEY (NoPlace, NoRang, NoZone) REFERENCES Place (NoPlace, NoRang, NoZone),
    CONSTRAINT fk_AReserveNPlaces_Representation FOREIGN KEY (NoSpectacle, NoRepresentation) REFERENCES Representation (NoSpectacle, NoRepresentation),
    CONSTRAINT fk_AReserveNPlaces_Client FOREIGN KEY (Login) REFERENCES Utilisateur (Login)
);


