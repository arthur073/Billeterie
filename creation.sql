DROP TABLE AAcheteNPlaces;
DROP TABLE AReserveNPlaces;
DROP TABLE Place;
DROP TABLE Zone;
DROP TABLE CategorieTarifaire;
DROP TABLE Utilisateur;
DROP TABLE Spectacle;
DROP TABLE Representation;


CREATE TABLE Utilisateur (
	Login int,
	Nom varchar(50) NOT NULL,
	Prenom varchar(50) NOT NULL,
	Mail varchar(100),
	MotDePasse varchar(128) NOT NULL,
	Type varchar(11) NOT NULL CHECK(Type = ‘Responsable’ OR Type = ‘Client’),
	CONSTRAINT pk_Client PRIMARY KEY (Login)
);

CREATE TABLE CategorieTarifaire (
	Categorie varchar(20),
	TarifBase double(6,2) NOT NULL,
	NoZone int NOT NULL,
	CONSTRAINT pk_CatTarif PRIMARY KEY (Categorie),
	CONSTRAINT fk_CatTarif_NoZone FOREIGN KEY (NoZone) REFERENCES Zone (NoZone)
);





CREATE TABLE Zone (
	NoZone int,
	Categorie varchar(20),
	CONSTRAINT pk_Zone PRIMARY KEY (NoZone),
	CONSTRAINT fk_Zone_NoZone FOREIGN KEY (Categorie) REFERENCES CategorieTarifaire (Categorie)
);

CREATE TABLE Place (
	NoPlace int,
	NoRang int,
	NoZone int,
	CONSTRAINT pk_Place PRIMARY KEY (NoPlace, NoRang, NoZone),
	CONSTRAINT fk_Place FOREIGN KEY (NoZone) REFERENCES Zone (NoZone)
);
CREATE TABLE Spectacle (
	NoSpectacle int,
	Nom varchar(100),
	CONSTRAINT pk_Spectacle PRIMARY KEY (NoSpectacle)
);

CREATE TABLE Representation (
	NoSpectacle int,
	NoRepresentation int,
	Date date,
	CONSTRAINT pk_Representation PRIMARY KEY (NoSpectacle, NoRepresentation),
	CONSTRAINT fk_Representation FOREIGN KEY (NoSpectacle) REFERENCES Spectacle (NoSpectacle)
);

CREATE TABLE AAcheteNPlaces (
	Login int,
	NoRepresentation int,
	NoZone int,
	NoRang int,
	NoPlace int,
	NoDossier int,
	NoSerie int,
	DateAchat datetime,
	CONSTRAINT pk_AAcheteNPlaces PRIMARY KEY (Login, NoRepresentation, NoZone, NoRang, NoPlace, NoDossier, NoSerie),
	CONSTRAINT fk_AAcheteNPlaces_Place FOREIGN KEY (NoZone, NoRang, NoPlace) REFERENCES Place (NoZone, NoRang, NoPlace),
	CONSTRAINT fk_AAcheteNPlaces_Representation FOREIGN KEY (NoRepresentation) REFERENCES Representation (NoRepresentation),
	CONSTRAINT fk_AAcheteNPlaces_Client FOREIGN KEY (Login) REFERENCES Client (Login)
);

CREATE TABLE AReserveNPlaces (
	Login int,
	NoRepresentation int,
	NoZone int,
	NoRang int,
	NoPlace int,
	CONSTRAINT pk_AAcheteNPlaces PRIMARY KEY (Login, NoRepresentation, NoZone, NoRang, NoPlace),
	CONSTRAINT fk_AAcheteNPlaces_Place FOREIGN KEY (NoZone, NoRang, NoPlace) REFERENCES Place (NoZone, NoRang, NoPlace),
	CONSTRAINT fk_AAcheteNPlaces_Representation FOREIGN KEY (NoRepresentation) REFERENCES Representation (NoRepresentation),
	CONSTRAINT fk_AAcheteNPlaces_Client FOREIGN KEY (Login) REFERENCES Client (Login)
);
