USE sakila;
CREATE TABLE pontuacao(
    regId       INT UNSIGNED NOT NULL  AUTO_INCREMENT,
    usuario     CHAR(25)     NOT NULL, 
    tipoPontos  CHAR(25)     NOT NULL,
    numPontos   CHAR(10)     NOT NULL, 
    PRIMARY KEY (regId),     -- Index built automatically
    UNIQUE  KEY (regId)      -- Build INDEX on this unique-value column
);
SELECT * FROM pontuacao;
INSERT	INTO pontuacao (usuario, tipoPontos, numPontos) VALUES ("maria","estrela","50");
INSERT	INTO pontuacao (usuario, tipoPontos, numPontos) VALUES ("maria","vida","150");
INSERT	INTO pontuacao (usuario, tipoPontos, numPontos) VALUES ("maria","sobrevida","30");
SELECT * FROM pontuacao;
CREATE TABLE IF NOT EXISTS pontuacao (
    regId       INT UNSIGNED NOT NULL  AUTO_INCREMENT,
    usuario     CHAR(25)     NOT NULL, 
    tipoPontos  CHAR(25)     NOT NULL,
    numPontos   CHAR(10)     NOT NULL, 
    PRIMARY KEY (regId),     -- Index built automatically
    UNIQUE  KEY (regId)      -- Build INDEX on this unique-value column
);
SHOW TABLES;
TRUNCATE TABLE pontuacao;
