/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ETU1886-Fanirina
 * Created: 27 juil. 2024
 */

CREATE OR REPLACE VIEW v_matiere AS (
    SELECT 
        mat.idMatiere,
        mat.code,
        mat.credit,
        mat.nom AS matierenom,
        sem.idsemestre,
        sem.nom AS semestrenom
    FROM matiere mat
    LEFT JOIN semestre sem on sem.idSemestre = mat.idSemestre
    ORDER BY idMatiere
);


CREATE OR REPLACE VIEW v_etudiant AS (
    SELECT 
        etu.idetudiant,
        etu.etu,
        etu.nom as etudiantnom,
        etu.prenom,
        etu.dtn,
        prom.idpromotion,
        prom.nom AS promotionnom
    FROM etudiant etu
    LEFT JOIN promotion prom ON prom.idpromotion = etu.idpromotion
    ORDER BY etu
);

CREATE OR REPLACE VIEW v_noteDefault AS (
    SELECT 
        m.idMatiere,
        e.etu,
        0 AS defaultNote
    FROM Matiere m
    CROSS JOIN Etudiant e
    ORDER BY etu, idMatiere
);

CREATE OR REPLACE VIEW v_note AS (
    SELECT 
        m.idMatiere,
        e.etu,
        COALESCE(n.note, nd.defaultNote) AS note,
        nd.defaultNote
    FROM Matiere m
    CROSS JOIN Etudiant e
    LEFT JOIN Note n ON m.idMatiere = n.idMatiere AND e.etu = n.etu
    LEFT JOIN v_noteDefault nd ON e.etu = nd.etu AND m.idMatiere = nd.idmatiere;
);

CREATE OR REPLACE VIEW v_note AS (
    SELECT
        mat.idMatiere,
        mat.code,
        mat.credit,
        mat.nom AS matierenom,
        mat.optionnelle,
        COALESCE(note.note, nd.defaultNote) AS note,
        nd.defaultNote,
        sem.idsemestre,
        sem.nom AS semestrenom,
        etu.idetudiant,
        etu.etu,
        prom.idpromotion,
        prom.nom AS promotionnom
    FROM Matiere mat
    CROSS JOIN Etudiant etu
    LEFT JOIN Note ON mat.idMatiere = note.idMatiere AND etu.etu = note.etu
    LEFT JOIN v_noteDefault nd ON etu.etu = nd.etu AND mat.idMatiere = nd.idmatiere
    LEFT JOIN semestre sem on sem.idSemestre = mat.idSemestre
    LEFT JOIN promotion prom ON prom.idpromotion = etu.idpromotion
    GROUP BY 
        etu.etu, 
        note.idnote,
        mat.idMatiere,
        nd.defaultNote,
        sem.idSemestre,
        prom.idpromotion
    ORDER BY idnote, etu
);

EXPLAIN ANALYZE 
SELECT *
FROM v_note
WHERE etu = 'ETU001304'
    AND  optionnelle = true
    AND code LIKE '%INF%'
    AND idsemestre = 4;

EXPLAIN ANALYZE 
SELECT *
FROM v_note
WHERE
    idsemestre = 4 AND
    etu = 'ETU001304' AND
    optionnelle = TRUE AND
    code LIKE '%INF%' 
ORDER BY note DESC
LIMIT 1;
