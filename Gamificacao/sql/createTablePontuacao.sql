USE sakila;
CREATE TABLE pontuacao(
    regId       INT UNSIGNED NOT NULL  AUTO_INCREMENT,
    usuario     CHAR(25)     NOT NULL, 
    tipoPontos  CHAR(25)     NOT NULL,
    numPontos   CHAR(10)     NOT NULL, 
    PRIMARY KEY (regId),     -- Index built automatically
    UNIQUE  KEY (regId)      -- Build INDEX on this unique-value column
);
INSERT	INTO pontuacao (usuario, tipoPontos, numPontos) VALUES ("maria","estrela","50.00");
INSERT	INTO pontuacao (usuario, tipoPontos, numPontos) VALUES ("maria","vida","150.00");
INSERT	INTO pontuacao (usuario, tipoPontos, numPontos) VALUES ("maria","sobrevida","30.00");