/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ETU1886-Fanirina
 * Created: 25 juil. 2024
 */

-- Insertion d'un utilisateur admin dans la table Profil
INSERT INTO Profil (username, pseudo, password, isAdmin) VALUES
('admin', 'Admin', '$2a$10$AObGy4vJTL9DeCfROE9eSen0M1AaBJBX6vgrMdxZRF82a7cY7JFQm', true);

INSERT INTO Semestre (nom) VALUES 
('S1'),
('S2'),
('S3'),
('S4'),
('S5'),
('S6');

INSERT INTO Matiere (code, credit, nom, optionnelle, idSemestre) VALUES 
('INF101', 7, 'Programmation procedurale', false, 1),
('INF104', 5, 'HTML et Introduction au Web', false, 1),
('INF107', 4, 'Informatique de Base', false, 1),
('MTH101', 4, 'Arithmetique et nombres', false, 1),
('MTH102', 6, 'Analyse mathematique', false, 1),
('ORG101', 4, 'Techniques de communication', false, 1),

('INF102', 5, 'Bases de donnees relationnelles', false, 2),
('INF103', 5, 'Bases de l''administration systeme', false, 2),
('INF105', 4, 'Maintenance materiel et logiciel', false, 2),
('INF106', 6, 'Complements de programmation', false, 2),
('MTH103', 6, 'Calcul Vectoriel et Matriciel', false, 2),
('MTH105', 4, 'Probabilite et Statistique', false, 2),

('INF201', 6, 'Programmation orientee objet', false, 3),
('INF202', 6, 'Bases de donnees objets', false, 3),
('INF203', 4, 'Programmation systeme', false, 3),
('INF208', 6, 'Reseaux informatiques', false, 3),
('MTH201', 4, 'Methodes numeriques', false, 3),
('ORG201', 4, 'Bases de gestion', false, 3),

('INF204', 6, 'Systeme d''information geographique', true, 4),
('INF205', 6, 'Systeme d''information', true, 4),
('INF206', 6, 'Interface Homme/Machine', true, 4),
('INF207', 6, 'Elements d''algorithmique', false, 4),
('INF210', 10, 'Mini-projet de developpement', false, 4),
('MTH204', 4, 'Geometrie', true, 4),
('MTH205', 4, 'Equations differentielles', true, 4),
('MTH206', 4, 'Optimisation', true, 4),
('MTH203', 4, 'MAO', false, 4),

('INF301', 6, 'Architecture logicielle', false, 5),
('INF304', 6, 'Developpement pour mobiles', false, 5),
('INF307', 6, 'Conception en modele oriente objet', false, 5),
('ORG301', 5, 'Gestion d''entreprise', false, 5),
('ORG302', 4, 'Gestion de projets', false, 5),
('ORG303', 3, 'Anglais pour les affaires', false, 5),

('INF310', 4, 'Codage', false, 6),
('INF313', 6, 'Programmation avancee, frameworks', false, 6),
('INF302', 6, 'Technologies d''acces aux reseaux', true, 6),
('INF303', 6, 'Multimedia', true, 6),
('INF316', 10, 'Projet de developpement', false, 6),
('ORG304', 4, 'Communication d''entreprise', false, 6);


------------------------------------------------------------------------------------
INSERT INTO Barem (noteEliminatoire) VALUES 
(6);

INSERT INTO promotion (nom) VALUES 
('P13'),
('P14'),
('P15');

INSERT INTO Etudiant (etu, nom, prenom, dtn, idPromotion, idSemestre) VALUES
('ETU001', 'Dupont', 'Jean', '2000-01-15', 1, 5),
('ETU002', 'Martin', 'Claire', '2000-02-20', 1, 5),
('ETU003', 'Durand', 'Paul', '2000-03-25', 1, 5),
('ETU004', 'Lefevre', 'Marie', '2000-04-30', 1, 5),
('ETU005', 'Moreau', 'Pierre', '2000-05-10', 1, 5),
('ETU006', 'Roux', 'Sophie', '2000-06-15', 1, 5),
('ETU007', 'Faure', 'Luc', '2000-07-20', 1, 5),
('ETU008', 'Giraud', 'Chloe', '2000-08-25', 1, 5),
('ETU009', 'Lemoine', 'Nicolas', '2000-09-30', 1, 5),
('ETU010', 'Blanc', 'Emma', '2000-10-10', 1, 5),
('ETU011', 'Lemoine', 'Julien', '2000-11-15', 1, 5),
('ETU012', 'Perrin', 'Laura', '2000-12-20', 1, 5),
('ETU013', 'Charpentier', 'Marc', '2001-01-10', 1, 5),
('ETU014', 'Leblanc', 'Anaïs', '2001-02-25', 1, 5),
('ETU015', 'Joly', 'Vincent', '2001-03-15', 1, 5),
('ETU016', 'Gautier', 'Pauline', '2000-02-01', 2, 3),
('ETU017', 'Martinez', 'Sebastien', '2000-05-14', 2, 3),
('ETU018', 'Vidal', 'Elise', '2000-08-21', 2, 3),
('ETU019', 'Morel', 'Jerôme', '2000-11-10', 2, 3),
('ETU020', 'Lemoine', 'Audrey', '2001-01-22', 2, 3),
('ETU021', 'Renaud', 'Thierry', '2001-03-12', 2, 3),
('ETU022', 'Colin', 'Celine', '2001-06-30', 2, 3),
('ETU023', 'Giraud', 'Nadine', '2000-07-04', 3, 1),
('ETU024', 'Roussel', 'Guillaume', '2000-09-17', 3, 1),
('ETU025', 'Deschamps', 'Juliette', '2000-11-25', 3, 1),
('ETU026', 'Koto', 'Jean', '2000-01-30', 4, 6);
