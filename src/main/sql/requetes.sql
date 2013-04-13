SELECT_CONNEXION_CLIENT=

SELECT Prenom, Nom
FROM Utilisateur
WHERE Login = ?
AND MotDePasse = ?

----------------
INSERT_CLIENT=

INSERT INTO Utilisateur ('Login','Nom','Prenom','Mail','MotDePasse')
VALUES (?, ?, ?, ?, ?)

-----------------
SELECT_LISTE_SPECTACLES_A_VENIR=

SELECT s.NoSpectacle, Nom
FROM Spectacle s, Representation r
WHERE s.NoSpectacle = r.NoSpectacle
AND DateRepresentation > CURRENT_DATE()

-----------------
SELECT_LISTE_REPRESENTATIONS_SPECTACLE_A_VENIR=

SELECT NoRepresentation, DateRepresentation
FROM Representation
WHERE DateRepresentation > CURRENT_DATE()
AND NoSpectacle = ?

----------------

SELECT_PLACES_RESERVEES_NON_PAYEES_CLIENT=

SELECT NoSpectacle, NoRepresentation, NoZone, NoRang, NoPlace
FROM AReserveNPlaces
WHERE Login = ?

-----------------

SELECT_LISTE_RESERVATIONS_NON_PAYEES_REPRESENTATION=

SELECT *
FROM AReserveNPlaces
WHERE NoSpectacle = ?
AND NoRepresentation = ?

------------------

SELECT_LISTE_RESERVATIONS_CLIENT_REPRESENTATION=

SELECT *
FROM AReserveNPlaces
WHERE Login = ?
AND NoSpectacle = ?
AND NoRepresentation = ?

-------------------

SELECT_LISTE_RESERVATIONS_CLIENT=

SELECT *
FROM AReserveNPlaces 
WHERE Login = ?

-----------------


SELECT_PRIX_ZONE_REPRESENTATION=

SELECT Prix
FROM PrixRepresentation
WHERE NoSpectacle = ?
AND NoRepresentation = ?
AND NoZone = ?

--------------------

SELECT_EXISTENCE_CLIENT=

SELECT Login
FROM Utilisateur
WHERE Login = ?


----------------------

SELECT_LISTE_REPRESENTATIONS_A_VENIR=

SELECT r.*, s.Nom, s.Image, pr.Prix as PrixMin
FROM Representation r,Spectacle s, PrixRepresentation pr
WHERE DateRepresentation > CURRENT_DATE() 
AND r.NoSpectacle = s.NoSpectacle
AND pr.NoSpectacle = s.NoSpectacle
AND pr.NoRepresentation = r.NoRepresentation
AND Prix = (SELECT  min(pr.Prix)
FROM Representation r2,Spectacle s, PrixRepresentation pr
WHERE DateRepresentation > CURRENT_DATE() 
AND r.NoSpectacle = s.NoSpectacle
AND pr.NoSpectacle = s.NoSpectacle
AND r.NoRepresentation = r2.NoRepresentation
AND pr.NoRepresentation = r.NoRepresentation
GROUP BY r.NoRepresentation) 
GROUP BY r.NoRepresentation
UNION
SELECT r.*, s.Nom, s.Image, pr.Prix as PrixMax
FROM Representation r,Spectacle s, PrixRepresentation pr
WHERE DateRepresentation > CURRENT_DATE() 
AND r.NoSpectacle = s.NoSpectacle
AND pr.NoSpectacle = s.NoSpectacle
AND pr.NoRepresentation = r.NoRepresentation
AND Prix = (SELECT  max(pr.Prix)
FROM Representation r2,Spectacle s, PrixRepresentation pr
WHERE DateRepresentation > CURRENT_DATE() 
AND r.NoSpectacle = s.NoSpectacle
AND pr.NoSpectacle = s.NoSpectacle
AND r.NoRepresentation = r2.NoRepresentation
AND pr.NoRepresentation = r.NoRepresentation
GROUP BY r.NoRepresentation) 
GROUP BY r.NoRepresentation)

----------------------

SELECT_LISTE_REPRESENTATIONS_DU_SPECTACLE =

SELECT r.*, s.Nom, s.Image
FROM Representation r, Spectacle s
WHERE r.NoSpectacle = ? 
AND r.NoSPectacle = s.NoSpectacle
AND DateRepresentation > CURRENT_DATE();





select liste places pas encore reservees (pour tout, par zone)
select verif que representation a plus de 70 places




