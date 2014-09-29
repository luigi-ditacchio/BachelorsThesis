/******************************************

			SEZIONE
		
******************************************/


-- Procedura per inserire una Sezione
DROP PROCEDURE IF EXISTS inserisci_sezione;
DELIMITER |
CREATE PROCEDURE inserisci_sezione(nome VARCHAR(64), descrizione VARCHAR(2048),
			numSala INTEGER, pianoSala INTEGER)
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	INSERT INTO Sezione VALUES (nome, descrizione, 1);
	INSERT INTO Appartenenza VALUES (numSala, pianoSala, nome);
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;


-- Procedura per aggiornare una Sezione
DROP PROCEDURE IF EXISTS aggiorna_sezione;
DELIMITER |
CREATE PROCEDURE aggiorna_sezione(vecchio_nome VARCHAR(64), nuovo_nome VARCHAR(64))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	UPDATE Sezione s SET s.nome = nuovo_nome WHERE s.nome = vecchio_nome;
	UPDATE Appartenenza SET a.sezione = nuovo_nome WHERE a.sezione = vecchio_nome;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare una Sezione
DROP PROCEDURE IF EXISTS cancella_sezione;
DELIMITER |
CREATE PROCEDURE cancella_sezione(nome VARCHAR(64))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	DELETE FROM Sezione WHERE Sezione.nome = nome;
	DELETE FROM Appartenenza WHERE Appartenenza.sezione = nome;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;










/******************************************

			STRUTTURA GENERICA
		
******************************************/


-- Procedura per inserire una Struttura generica
DROP PROCEDURE IF EXISTS inserisci_struttura;
DELIMITER |
CREATE PROCEDURE inserisci_struttura(
			codice CHAR(16), nome VARCHAR(64), citta VARCHAR(64),
			via VARCHAR(64), numero INTEGER, CAP VARCHAR(5),
			telefono VARCHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	INSERT INTO Struttura VALUES (codice, nome, citta, via, numero, CAP);
	INSERT INTO TelefonoStruttura VALUES (telefono, codice);
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;


-- Procedura per aggiornare una Struttura generica
DROP PROCEDURE IF EXISTS aggiorna_struttura;
DELIMITER |
CREATE PROCEDURE aggiorna_struttura(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	UPDATE Struttura s SET s.codice = nuovo_codice WHERE s.codice = vecchio_codice;
	UPDATE TelefonoStruttura ts SET ts.codiceStruttura = nuovo_codice WHERE ts.codiceStruttura = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare una Struttura generica
DROP PROCEDURE IF EXISTS cancella_struttura;
DELIMITER |
CREATE PROCEDURE cancella_struttura(codice CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	DELETE FROM Struttura WHERE Struttura.codice = codice;
	DELETE FROM TelefonoStruttura WHERE TelefonoStruttura.codiceStruttura = codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;






/******************************************

			MUSEO/ENTE
		
******************************************/


-- Procedura per inserire un Museo/Ente
DROP PROCEDURE IF EXISTS inserisci_museo;
DELIMITER |
CREATE PROCEDURE inserisci_museo(
			codice CHAR(16), nome VARCHAR(64), citta VARCHAR(64),
			via VARCHAR(64), numero INTEGER, CAP VARCHAR(5),
			telefono VARCHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	INSERT INTO Struttura VALUES (codice, nome, citta, via, numero, CAP);
	INSERT INTO TelefonoStruttura VALUES (telefono, codice);
	INSERT INTO Museo_Ente VALUES (codice);
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;


-- Procedura per aggiornare un Museo/Ente
DROP PROCEDURE IF EXISTS aggiorna_museo;
DELIMITER |
CREATE PROCEDURE aggiorna_museo(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	UPDATE Struttura s SET s.codice = nuovo_codice WHERE s.codice = vecchio_codice;
	UPDATE TelefonoStruttura ts SET ts.codiceStruttura = nuovo_codice WHERE ts.codiceStruttura = vecchio_codice;
	UPDATE Museo_Ente m SET m.codice = nuovo_codice WHERE m.codice = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare un Museo/Ente
DROP PROCEDURE IF EXISTS cancella_museo;
DELIMITER |
CREATE PROCEDURE cancella_museo(codice CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	DELETE FROM Struttura WHERE Struttura.codice = codice;
	DELETE FROM TelefonoStruttura WHERE TelefonoStruttura.codiceStruttura = codice;
	DELETE FROM Museo_Ente WHERE Museo_Ente.codice = codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;




/******************************************

			CENTRO RESTAURO
		
******************************************/


-- Procedura per inserire un CentroRestauro
DROP PROCEDURE IF EXISTS inserisci_centro_restauro;
DELIMITER |
CREATE PROCEDURE inserisci_centro_restauro(
			codice CHAR(16), nome VARCHAR(64), citta VARCHAR(64),
			via VARCHAR(64), numero INTEGER, CAP VARCHAR(5),
			telefono VARCHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	INSERT INTO Struttura VALUES (codice, nome, citta, via, numero, CAP);
	INSERT INTO TelefonoStruttura VALUES (telefono, codice);
	INSERT INTO CentroRestauro VALUES (codice);
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;


-- Procedura per aggiornare un CentroRestauro
DROP PROCEDURE IF EXISTS aggiorna_centro_restauro;
DELIMITER |
CREATE PROCEDURE aggiorna_centro_restauro(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	UPDATE Struttura s SET s.codice = nuovo_codice WHERE s.codice = vecchio_codice;
	UPDATE TelefonoStruttura ts SET ts.codiceStruttura = nuovo_codice WHERE ts.codiceStruttura = vecchio_codice;
	UPDATE CentroRestauro cr SET cr.codice = nuovo_codice WHERE cr.codice = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare un CentroRestauro
DROP PROCEDURE IF EXISTS cancella_centro_restauro;
DELIMITER |
CREATE PROCEDURE cancella_centro_restauro(codice CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @DIS_TRIG = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @DIS_TRIG = 1;
	
	DELETE FROM Struttura WHERE Struttura.codice = codice;
	DELETE FROM TelefonoStruttura WHERE TelefonoStruttura.codiceStruttura = codice;
	DELETE FROM CentroRestauro WHERE CentroRestauro.codice = codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;









/******************************************

			ARTISTA
		
******************************************/


-- Procedura per inserire una Artista
DROP PROCEDURE IF EXISTS inserisci_artista;
DELIMITER |
CREATE PROCEDURE inserisci_artista(codice CHAR(16), nome VARCHAR(64), cognome VARCHAR(64),
			dataNascita DATE, luogoNascita VARCHAR(16), vita VARCHAR(2048), stile VARCHAR(2048))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	
	INSERT INTO ArtistaGenerali(codice, nome, cognome, dataNascita, dataMorte) VALUES (codice, nome, cognome, dataNascita, dataMorte);
	INSERT INTO ArtistaParticolari VALUES (codice, luogoNascita, luogoMorte, vita, stile);
	
	SET foreign_key_checks = 1;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;


-- Procedura per aggiornare un Artista
DROP PROCEDURE IF EXISTS aggiorna_artista;
DELIMITER |
CREATE PROCEDURE aggiorna_artista(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	
	UPDATE ArtistaGenerali SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE ArtistaParticolari SET codice = nuovo_codice WHERE codice = vecchio_codice;
	
	SET foreign_key_checks = 1;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare una Artista
DROP PROCEDURE IF EXISTS cancella_artista;
DELIMITER |
CREATE PROCEDURE cancella_artista(codice CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	
	DELETE FROM ArtistaGenerali WHERE ArtistaGenerali.codice = codice;
	DELETE FROM ArtistaParticolari WHERE ArtistaParticolari.codice = codice;
	
	SET foreign_key_checks = 1;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;
