/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ETU1886-Fanirina
 * Created: 25 juil. 2024
 */

CREATE TABLE Profil(
   idProfil SERIAL,
   username VARCHAR(50) NOT NULL,
   pseudo VARCHAR(50) NOT NULL,
   password VARCHAR(500) NOT NULL,
   isAdmin BOOLEAN NOT NULL,
   PRIMARY KEY(idProfil),
   UNIQUE(username)
);

CREATE TABLE Semestre(
   idSemestre SERIAL,
   nom VARCHAR(500) NOT NULL,
   PRIMARY KEY(idSemestre)
); 

CREATE TABLE Matiere(
   idMatiere SERIAL,
   code VARCHAR(100) NOT NULL,
   credit DECIMAL(15,2) NOT NULL,
   nom VARCHAR(500) NOT NULL,
   optionnelle BOOLEAN NOT NULL,
   idSemestre INT NOT NULL,
   PRIMARY KEY(idMatiere),
   UNIQUE(code),
   FOREIGN KEY(idSemestre) REFERENCES Semestre(idSemestre)
);

CREATE TABLE Promotion(
   idPromotion SERIAL,
   nom VARCHAR(100) NOT NULL,
   PRIMARY KEY(idPromotion)
);

CREATE TABLE Genre (
    idGenre SERIAL,
    nom VARCHAR(100) NOT NULL,
    PRIMARY KEY(idGenre),
    UNIQUE(nom)
);

CREATE TABLE Etudiant(
   idEtudiant SERIAL,
   etu VARCHAR(500) NOT NULL,
   nom VARCHAR(500) NOT NULL,
   prenom VARCHAR(500),
   dtn DATE NOT NULL,
   idGenre INT NOT NULL,
   idPromotion INT NOT NULL,
   PRIMARY KEY(etu),
   UNIQUE(idEtudiant),
   FOREIGN KEY(idGenre) REFERENCES Genre(idGenre),
   FOREIGN KEY(idPromotion) REFERENCES Promotion(idPromotion)
);

CREATE TABLE Note(
   idNote SERIAL,
   note DECIMAL(15,2) NOT NULL,
   idMatiere INT NOT NULL,
   etu VARCHAR(50) NOT NULL,
   PRIMARY KEY(idNote),
   FOREIGN KEY(idMatiere) REFERENCES Matiere(idMatiere),
   FOREIGN KEY(etu) REFERENCES Etudiant(etu)
);

CREATE TABLE Barem(
   idBarem SERIAL,
   codeConfig VARCHAR(500) NOT NULL,
   nomConfig VARCHAR(500) NOT NULL,
   noteEliminatoire DECIMAL(15,2) NOT NULL,
   PRIMARY KEY(idBarem)
);

CREATE TABLE Compensation(
   idCompensation SERIAL,
   codeConfig VARCHAR(500) NOT NULL,
   nomConfig VARCHAR(500) NOT NULL,
   nombreMatiere INT NOT NULL,
   PRIMARY KEY(idCompensation)
);






















CREATE TABLE HistoriqueSemestreEtudiant(
   idHistorique SERIAL,
   datePassage DATE NOT NULL,
   idSemestre INT NOT NULL,
   etu VARCHAR(500) NOT NULL,
   PRIMARY KEY(idHistorique),
   FOREIGN KEY(idSemestre) REFERENCES Semestre(idSemestre),
   FOREIGN KEY(etu) REFERENCES Etudiant(etu)
);