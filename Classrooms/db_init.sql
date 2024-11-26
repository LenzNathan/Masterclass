DROP DATABASE IF EXISTS classrooms;
CREATE DATABASE classrooms;
USE classrooms;

DROP TABLE IF EXISTS tag;
CREATE TABLE tag
(
    tag_id   int NOT NULL AUTO_INCREMENT,
    tag_name varchar(50),
    PRIMARY KEY (tag_id)
);

DROP TABLE IF EXISTS abteilung;
CREATE TABLE abteilung
(
    a_id      int NOT NULL AUTO_INCREMENT,
    a_kuerzel char(4),
    a_name    varchar(50),
    PRIMARY KEY (a_id)
);

DROP TABLE IF EXISTS abteilungs_stufe;
CREATE TABLE abteilungs_stufe
(
    id        int primary key,
    stufe     int,
    abteilung int,
    foreign key (abteilung) references abteilung (a_id)
);

DROP TABLE IF EXISTS stufe_tag;
CREATE TABLE stufe_tag
(
    stufe_tag_id int primary key,
    stufe        int,
    tag          int,
    foreign key (stufe) references abteilungs_stufe (id),
    foreign key (tag) references tag (tag_id)
);

DROP TABLE IF EXISTS lehrperson;
CREATE TABLE lehrperson
(
    l_id       int NOT NULL AUTO_INCREMENT,
    l_vorname  varchar(50),
    l_nachname varchar(50),
    PRIMARY KEY (l_id)
);

/*Zwischentablelle zwischen Tags und Lehrer*/
DROP TABLE IF EXISTS l_tag;
CREATE TABLE l_tag
(
    l_id   int NOT NULL,
    tag_id int NOT NULL,
    PRIMARY KEY (l_id, tag_id),
    FOREIGN KEY (l_id) REFERENCES lehrperson (l_id),
    FOREIGN KEY (tag_id) REFERENCES tag (tag_id)
);

DROP TABLE IF EXISTS schulklasse;
CREATE TABLE schulklasse
(
    sk_id      int NOT NULL AUTO_INCREMENT,
    klassen_id int DEFAULT NULL,
    jahrgang   int NOT NULL,
    abteilung  int not null,
    PRIMARY KEY (sk_id),
    foreign key (abteilung) references abteilung (a_id)
);

/*habe gebäude in haus umgenannt, weil gebaeude isch a bissi weird*/
DROP TABLE IF EXISTS gebaeude;
CREATE TABLE gebaeude
(
    g_id      INT NOT NULL AUTO_INCREMENT,
    g_kuerzel char(1),
    g_name    varchar(50),
    PRIMARY KEY (g_id)
);

DROP TABLE IF EXISTS raum;
CREATE TABLE raum
(
    r_id        INT NOT NULL AUTO_INCREMENT,
    h_id        INT NOT NULL,
    r_nummer    INT NOT NULL,
    r_kapazität INT NOT NULL,
    PRIMARY KEY (r_id),
    FOREIGN KEY (h_id) REFERENCES gebaeude (g_id)
);

/*Zwischentabelle Raum und Tag*/
DROP TABLE IF EXISTS r_tag;
CREATE TABLE r_tag
(
    r_id   INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (r_id, tag_id),
    FOREIGN KEY (tag_id) REFERENCES tag (tag_id),
    FOREIGN KEY (r_id) REFERENCES raum (r_id)
);

DROP TABLE IF EXISTS gruppe;
CREATE TABLE gruppe
(
    g_id   int NOT NULL,
    sk_id  int NOT NULL,
    g_name varchar(50),
    FOREIGN KEY (sk_id) REFERENCES schulklasse (sk_id),
    PRIMARY KEY (g_id)
);

DROP TABLE IF EXISTS schueler;
CREATE TABLE schueler
(
    s_id       int NOT NULL,
    sk_id      int DEFAULT NULL,
    s_vorname  varchar(50),
    s_nachname varchar(50),
    PRIMARY KEY (s_id),
    FOREIGN KEY (sk_id) REFERENCES schulklasse (sk_id)
);

/*Zwischentabelle schueler und gruppe*/
DROP TABLE IF EXISTS s_gruppe;
CREATE TABLE s_gruppe
(
    s_id INT NOT NULL,
    g_id INT NOT NULL,
    PRIMARY KEY (s_id, g_id),
    FOREIGN KEY (s_id) REFERENCES schueler (s_id),
    FOREIGN KEY (g_id) REFERENCES gruppe (g_id)
);

DROP TABLE IF EXISTS stunden_anfang;
CREATE TABLE stunden_anfang
(
    st_a_id    INT  NOT NULL,
    st_a_start TIME NOT NULL,
    PRIMARY KEY (st_a_id)
);

DROP TABLE IF EXISTS stunden_ende;
CREATE TABLE stunden_ende
(
    st_e_id    INT  NOT NULL,
    st_e_start TIME NOT NULL,
    PRIMARY KEY (st_e_id)
);

/*wann welche gruppe den raum besezt*/
DROP TABLE IF EXISTS zuteilung;
CREATE TABLE zuteilung
(
    z_id    int  NOT NULL AUTO_INCREMENT,
    r_id    INT  NOT NULL,
    g_id    INT  NOT NULL,
    st_a_id INT  NOT NULL,
    st_e_id INT  NOT NULL,
    z_datum DATE NOT NULL,
    PRIMARY KEY (z_id),
    FOREIGN KEY (r_id) REFERENCES raum (r_id),
    FOREIGN KEY (st_a_id) REFERENCES stunden_anfang (st_a_id),
    FOREIGN KEY (st_e_id) REFERENCES stunden_ende (st_e_id),
    FOREIGN KEY (g_id) REFERENCES gruppe (g_id)
);
