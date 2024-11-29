DROP DATABASE IF EXISTS classrooms;
CREATE DATABASE classrooms;
USE classrooms;

DROP TABLE IF EXISTS tag;
CREATE TABLE tag
(
    tag_id   int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tag_name varchar(50)
);

DROP TABLE IF EXISTS abteilung;
CREATE TABLE abteilung
(
    abt_id      int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    abt_kuerzel char(4),
    abt_name    varchar(50)
);

DROP TABLE IF EXISTS abteilungs_stufe;
CREATE TABLE abteilungs_stufe
(
    stufe_id  int PRIMARY KEY,
    stufe     int,
    abteilung int,
    FOREIGN KEY (abteilung) REFERENCES abteilung (abt_id)
);

DROP TABLE IF EXISTS stufe_tag;
CREATE TABLE stufe_tag
(
    stufe_id int,
    tag_id   int,
    PRIMARY KEY (stufe_id, tag_id),
    FOREIGN KEY (stufe_id) REFERENCES abteilungs_stufe (stufe_id),
    FOREIGN KEY (tag_id) REFERENCES tag (tag_id)
);

DROP TABLE IF EXISTS lehrperson;
CREATE TABLE lehrperson
(
    lehrer_id       int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    lehrer_vorname  varchar(50),
    lehrer_nachname varchar(50)
);

/*Zwischentablelle zwischen Tags und Lehrer*/
DROP TABLE IF EXISTS lehrer_tag;
CREATE TABLE lehrer_tag
(
    lehrer_id int NOT NULL,
    tag_id    int NOT NULL,
    PRIMARY KEY (lehrer_id, tag_id),
    FOREIGN KEY (lehrer_id) REFERENCES lehrperson (lehrer_id),
    FOREIGN KEY (tag_id) REFERENCES tag (tag_id)
);

DROP TABLE IF EXISTS schulklasse;
CREATE TABLE schulklasse
(
    schulklasse_id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    klassen_id     int DEFAULT NULL,
    jahrgang       int             NOT NULL,
    abteilung      int             NOT NULL,
    FOREIGN KEY (abteilung) REFERENCES abteilung (abt_id)
);

/*habe gebäude in haus umgenannt, weil gebaeude isch a bissi weird*/
DROP TABLE IF EXISTS gebaeude;
CREATE TABLE gebaeude
(
    geb_id      int NOT NULL AUTO_INCREMENT,
    geb_kuerzel char(1),
    geb_name    varchar(50),
    PRIMARY KEY (geb_id)
);

DROP TABLE IF EXISTS raum;
CREATE TABLE raum
(
    raum_id        int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    geb_id         int             NOT NULL,
    raum_nummer    int             NOT NULL,
    raum_kapazität int             NOT NULL,
    FOREIGN KEY (geb_id) REFERENCES gebaeude (geb_id)
);

/*Zwischentabelle Raum und Tag*/
DROP TABLE IF EXISTS raum_tag;
CREATE TABLE raum_tag
(
    raum_id int NOT NULL,
    tag_id  int NOT NULL,
    PRIMARY KEY (raum_id, tag_id),
    FOREIGN KEY (tag_id) REFERENCES tag (tag_id),
    FOREIGN KEY (raum_id) REFERENCES raum (raum_id)
);

DROP TABLE IF EXISTS gruppe;
CREATE TABLE gruppe
(
    gruppe_id      int PRIMARY KEY NOT NULL,
    gruppe_name    varchar(50),
    schulklasse_id int             NOT NULL,
    FOREIGN KEY (schulklasse_id) REFERENCES schulklasse (schulklasse_id)
);

DROP TABLE IF EXISTS schueler;
CREATE TABLE schueler
(
    schueler_id       int PRIMARY KEY NOT NULL,
    schulklasse_id    int DEFAULT NULL,
    schueler_vorname  varchar(50),
    schueler_nachname varchar(50),
    FOREIGN KEY (schulklasse_id) REFERENCES schulklasse (schulklasse_id)
);

/*Zwischentabelle schueler und gruppe*/
DROP TABLE IF EXISTS schueler_gruppe;
CREATE TABLE schueler_gruppe
(
    schueler_id int NOT NULL,
    group_id    int NOT NULL,
    PRIMARY KEY (schueler_id, group_id),
    FOREIGN KEY (schueler_id) REFERENCES schueler (schueler_id),
    FOREIGN KEY (group_id) REFERENCES gruppe (gruppe_id)
);

DROP TABLE IF EXISTS stunden_start;
CREATE TABLE stunden_start
(
    start_id   int PRIMARY KEY NOT NULL,
    start_zeit time            NOT NULL
);

DROP TABLE IF EXISTS stunden_ende;
CREATE TABLE stunden_ende
(
    ende_id   int PRIMARY KEY NOT NULL,
    ende_zeit time            NOT NULL
);

/*wann welche gruppe den raum besezt*/
DROP TABLE IF EXISTS stundenblock;
CREATE TABLE stundenblock
(
    stundenblock_id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    raum_id         int             NOT NULL,
    gruppe_id       int             NOT NULL,
    start_id        int             NOT NULL,
    ende_id         int             NOT NULL,
    stundenblock    date            NOT NULL,
    FOREIGN KEY (raum_id) REFERENCES raum (raum_id),
    FOREIGN KEY (start_id) REFERENCES stunden_start (start_id),
    FOREIGN KEY (ende_id) REFERENCES stunden_ende (ende_id),
    FOREIGN KEY (gruppe_id) REFERENCES gruppe (gruppe_id)
);

