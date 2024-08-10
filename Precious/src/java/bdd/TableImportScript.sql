/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ETU1886-Fanirina
 * Created: 29 juil. 2024
 */

CREATE TABLE NoteEtudiant (
    idNoteEtudiant SERIAL,
    numETU VARCHAR(500) NOT NULL,
    nom VARCHAR(500) NOT NULL,
    prenom VARCHAR(500) NOT NULL,
    genre VARCHAR(500) NOT NULL,
    dateNaissance DATE NOT NULL,
    promotion VARCHAR(500) NOT NULL,
    codeMatiere VARCHAR(500) NOT NULL,
    semestre VARCHAR(500) NOT NULL,
    note DECIMAL(15,2) NOT NULL,
    PRIMARY KEY (idNoteEtudiant)
);

CREATE TABLE ConfigurationNote (
    idConfigurationNote SERIAL,
    code VARCHAR(500) NOT NULL,
    config VARCHAR(500) NOT NULL,
    valeur INT NOT NULL,
    PRIMARY KEY (idConfigurationNote)
);
