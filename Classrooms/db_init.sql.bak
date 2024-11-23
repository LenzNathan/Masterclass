CREATE DATABASE IF NOT EXISTS classrooms;

DROP TABLE IF EXISTS  abteilung ;
CREATE TABLE  abteilung 
(
     a_id       int NOT NULL AUTO_INCREMENT,
     a_kuerzel  char(4),
     a_name     varchar(50),
    PRIMARY KEY ( a_id )
); 

DROP TABLE IF EXISTS  lehrperson ;
CREATE TABLE  lehrperson 
(
     l_id        int NOT NULL AUTO_INCREMENT,
     l_vorname   varchar(50),
     l_nachname  varchar(50),
    PRIMARY KEY ( l_id )
);

DROP TABLE IF EXISTS  tag ;
CREATE TABLE  tag 
(
     tag_id    int NOT NULL AUTO_INCREMENT,
     tag_name  varchar(50),
    PRIMARY KEY ( tag_id )
);

/*Zwischentablelle zwischen Tags und Lehrer*/
DROP TABLE IF EXISTS  l_tag ;
CREATE TABLE  l_tag 
(
     l_id    int NOT NULL,
     tag_id  int NOT NULL,
    PRIMARY KEY ( l_id ,  tag_id ),
    FOREIGN KEY ( l_id ) REFERENCES  lehrperson  ( l_id ),
    FOREIGN KEY ( tag_id ) REFERENCES  tag  ( tag_id )
);

DROP TABLE IF EXISTS  schulklasse ;
CREATE TABLE  schulklasse 
(
     sk_id       int NOT NULL AUTO_INCREMENT,
     a_id        int DEFAULT NULL,
     jahrgang    int DEFAULT NULL,
     klassen_id  int DEFAULT NULL,
    PRIMARY KEY ( sk_id ),
    FOREIGN KEY ( a_id ) REFERENCES  abteilung  ( a_id )
);

/*Zwischentabelle zwischen Schulklassen und Tag*/
DROP TABLE IF EXISTS klasse_tag;
CREATE TABLE klasse_tag(
    tag_id INT NOT NULL,
    sk_id INT NOT NULL,
    PRIMARY KEY (tag_id, sk_id),
    FOREIGN KEY (tag_id) REFERENCES tag(tag_id),
    FOREIGN KEY (sk_id) REFERENCES schulklasse(sk_id)
);

/*habe gebäude in haus umgenannt, weil gebaeude isch a bissi weird*/
DROP TABLE IF EXISTS haus;
CREATE TABLE haus(
    h_id INT NOT NULL AUTO_INCREMENT,
    h_kuerzel char(1),
    h_name varchar(50),
    PRIMARY KEY(h_id)
);

DROP TABLE IF EXISTS raum;
CREATE TABLE raum(
    r_id INT NOT NULL AUTO_INCREMENT,
    h_id INT NOT NULL,
    r_nummer INT NOT NULL,
    r_kapazität INT NOT NULL,
    PRIMARY KEY(r_id),
    FOREIGN KEY (h_id) REFERENCES haus(h_id)
);

/*Zwischentabelle Raum und Tag*/
DROP TABLE IF EXISTS r_tag;
CREATE TABLE r_tag(
    r_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (r_id, tag_id),
    FOREIGN KEY (tag_id) REFERENCES tag(tag_id),
    FOREIGN KEY (r_id) REFERENCES raum(r_id)
);

DROP TABLE IF EXISTS  gruppe ;
CREATE TABLE  gruppe 
(
     g_id  int NOT NULL,
     sk_id int NOT NULL,
     g_name varchar(50),
    FOREIGN KEY (sk_id) REFERENCES schulklasse(sk_id),
    PRIMARY KEY ( g_id )
);

DROP TABLE IF EXISTS  schueler ;
CREATE TABLE  schueler 
(
     s_id   int NOT NULL,
     sk_id  int DEFAULT NULL,
    s_vorname varchar(50),
    s_nachname varchar(50),
    PRIMARY KEY ( s_id ),
    FOREIGN KEY ( sk_id ) REFERENCES  schulklasse  ( sk_id )
);

/*Zwischentabelle schueler und gruppe*/
DROP TABLE IF EXISTS s_gruppe;
CREATE TABLE s_gruppe(
    s_id INT NOT NULL,
    g_id INT NOT NULL,
    PRIMARY KEY(s_id, g_id),
    FOREIGN KEY (s_id) REFERENCES schueler (s_id),
    FOREIGN KEY (g_id) REFERENCES gruppe (g_id)
);

DROP TABLE IF EXISTS schulstunden;
CREATE TABLE schulstunden(
    st_id INT NOT NULL,
    st_beginn TIME NOT NULL,
    st_end TIME NOT NULL,
    PRIMARY KEY (st_id)
);

/*wann welche gruppe den raum besezt*/
DROP TABLE IF EXISTS zuteilung;
CREATE TABLE zuteilung(
    z_id int NOT NULL AUTO_INCREMENT,
    r_id INT NOT NULL,
    z_beginn INT NOT NULL,
    z_ende INT NOT NULL,
    z_datum DATE NOT NULL,
    PRIMARY KEY (z_id),
    FOREIGN KEY (r_id) REFERENCES raum (r_id),
    FOREIGN KEY (z_beginn) REFERENCES schulstunden (st_id),
    FOREIGN KEY (z_ende) REFERENCES schulstunden(st_id)
);