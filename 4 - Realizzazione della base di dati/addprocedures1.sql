/******************************************

			REPERTO ARCHEOLOGICO
		
******************************************/



-- Procedura per inserire un RepertoArcheologico permanente
DROP PROCEDURE IF EXISTS inserisci_reperto_permanente;
DELIMITER |
CREATE PROCEDURE inserisci_reperto_permanente(
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64),
			descrizione VARCHAR(2048), lunghezza REAL, larghezza REAL, profondita REAL,
			numSala INTEGER, pianoSala INTEGER, stato ENUM('DISPONIBILE','PRESTITO','RESTAURO'),
			nomeScavo VARCHAR(64), luogoScavo VARCHAR(64))
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
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO RepertoArcheologico VALUES (codice);
	INSERT INTO Ritrovamento VALUES (codice, nomeScavo, luogoScavo);
	INSERT INTO OggettoPermanente VALUES (codice, stato);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per inserire un RepertoArcheologico temporaneo
DROP PROCEDURE IF EXISTS inserisci_reperto_temporaneo;
DELIMITER |
CREATE PROCEDURE inserisci_reperto_temporaneo(
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64),
			descrizione VARCHAR(2048), lunghezza REAL, larghezza REAL, profondita REAL,
			numSala INTEGER, pianoSala INTEGER,
			nomeScavo VARCHAR(64), luogoScavo VARCHAR(64),
			dataInizio DATE, dataFine DATE, codiceMuseo CHAR(16))
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
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO RepertoArcheologico VALUES (codice);
	INSERT INTO Ritrovamento VALUES (codice, nomeScavo, luogoScavo);
	INSERT INTO OggettoTemporaneo VALUES (codice);
	INSERT INTO PrestitoRicevuto VALUES (codice, dataInizio, dataFine, codiceMuseo);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;


-- Procedura per aggiornare un RepertoArcheologico permanente
DROP PROCEDURE IF EXISTS aggiorna_reperto_permanente;
DELIMITER |
CREATE PROCEDURE aggiorna_reperto_permanente(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
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
	
	UPDATE OggettoGenerali og SET og.codice = nuovo_codice WHERE og.codice = vecchio_codice;
	UPDATE OggettoParticolari og SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE RepertoArcheologico ra SET ra.codice = nuovo_codice WHERE ra.codice = vecchio_codice;
	UPDATE Ritrovamento r SET r.codice = nuovo_codice WHERE r.codice = vecchio_codice;
	UPDATE OggettoPermanente op SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE Collocazione c SET c.codiceOggetto = nuovo_codice WHERE c.codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per aggiornare un RepertoArcheologico temporaneo
DROP PROCEDURE IF EXISTS aggiorna_reperto_temporaneo;
DELIMITER |
CREATE PROCEDURE aggiorna_reperto_temporaneo(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
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
	
	UPDATE OggettoGenerali og SET og.codice = nuovo_codice WHERE og.codice = vecchio_codice;
	UPDATE OggettoParticolari og SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE RepertoArcheologico ra SET ra.codice = nuovo_codice WHERE ra.codice = vecchio_codice;
	UPDATE Ritrovamento r SET r.codice = nuovo_codice WHERE r.codice = vecchio_codice;
	UPDATE OggettoTemporaneo ot SET ot.codice = nuovo_codice WHERE ot.codice = vecchio_codice;
	UPDATE PrestitoRicevuto pr SET pr.codice = nuovo_codice WHERE pr.codice = vecchio_codice;
	UPDATE Collocazione c SET c.codiceOggetto = nuovo_codice WHERE c.codiceOggetto = vecchio_codice;

	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare un RepertoArcheologico permanente
DROP PROCEDURE IF EXISTS cancella_reperto_permanente;
DELIMITER |
CREATE PROCEDURE cancella_reperto_permanente(codice CHAR(16))
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
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM RepertoArcheologico WHERE RepertoArcheologico.codice = codice;
	DELETE FROM Ritrovamento WHERE Ritrovamento.codiceReperto = codice;
	DELETE FROM OggettoPermanente WHERE OggettoPermanente.codice = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare un RepertoArcheologico temporaneo
DROP PROCEDURE IF EXISTS cancella_reperto_temporaneo;
DELIMITER |
CREATE PROCEDURE cancella_reperto_temporaneo(codice CHAR(16))
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
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM RepertoArcheologico WHERE RepertoArcheologico.codice = codice;
	DELETE FROM Ritrovamento WHERE Ritrovamento.codiceReperto = codice;
	DELETE FROM OggettoTemporaneo WHERE OggettoTemporaneo.codice = codice;
	DELETE FROM PrestitoRicevuto WHERE PrestitoRicevuto.codiceOggetto = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;













/******************************************

			FOTOGRAFIA
		
******************************************/



-- Procedura per inserire una Fotografia permanente
DROP PROCEDURE IF EXISTS inserisci_fotografia_permanente;
DELIMITER |
CREATE PROCEDURE inserisci_fotografia_permanente(
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64),
			descrizione VARCHAR(2048), lunghezza REAL, larghezza REAL, profondita REAL,
			numSala INTEGER, pianoSala INTEGER, stato ENUM('DISPONIBILE','PRESTITO','RESTAURO'),
			tipo ENUM('B/N', 'COLORI'))
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
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO Fotografia VALUES (codice, tipo);
	INSERT INTO OggettoPermanente VALUES (codice, stato);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per inserire un Fotografia temporanea
DROP PROCEDURE IF EXISTS inserisci_fotografia_temporanea;
DELIMITER |
CREATE PROCEDURE inserisci_fotografia_temporanea(
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64),
			descrizione VARCHAR(2048), lunghezza REAL, larghezza REAL, profondita REAL,
			numSala INTEGER, pianoSala INTEGER,
			tipo ENUM('B/N', 'COLORI'),
			dataInizio DATE, dataFine DATE, codiceMuseo CHAR(16))
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
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO Fotografia VALUES (codice, tipo);
	INSERT INTO OggettoTemporaneo VALUES (codice);
	INSERT INTO PrestitoRicevuto VALUES (codice, dataInizio, dataFine, codiceMuseo);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;


-- Procedura per aggiornare una Fotografia permanente
DROP PROCEDURE IF EXISTS aggiorna_fotografia_permanente;
DELIMITER |
CREATE PROCEDURE aggiorna_fotografia_permanente(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
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
	
	UPDATE OggettoGenerali og SET og.codice = nuovo_codice WHERE og.codice = vecchio_codice;
	UPDATE OggettoParticolari og SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE Fotografia f SET f.codice = nuovo_codice WHERE f.codice = vecchio_codice;
	UPDATE OggettoPermanente op SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE Collocazione c SET c.codiceOggetto = nuovo_codice WHERE c.codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per aggiornare una Fotografia temporanea
DROP PROCEDURE IF EXISTS aggiorna_fotografia_temporanea;
DELIMITER |
CREATE PROCEDURE aggiorna_fotografia_temporanea(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
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
	
	UPDATE OggettoGenerali og SET og.codice = nuovo_codice WHERE og.codice = vecchio_codice;
	UPDATE OggettoParticolari og SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE Fotografia f SET f.codice = nuovo_codice WHERE f.codice = vecchio_codice;
	UPDATE OggettoTemporaneo ot SET ot.codice = nuovo_codice WHERE ot.codice = vecchio_codice;
	UPDATE PrestitoRicevuto pr SET pr.codice = nuovo_codice WHERE pr.codice = vecchio_codice;
	UPDATE Collocazione c SET c.codiceOggetto = nuovo_codice WHERE c.codiceOggetto = vecchio_codice;

	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare una Fotografia permanente
DROP PROCEDURE IF EXISTS cancella_fotografia_permanente;
DELIMITER |
CREATE PROCEDURE cancella_fotografia_permanente(codice CHAR(16))
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
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM Fotografia WHERE Fotografia.codice = codice;
	DELETE FROM OggettoPermanente WHERE OggettoPermanente.codice = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare una Fotografia temporanea
DROP PROCEDURE IF EXISTS cancella_fotografia_temporanea;
DELIMITER |
CREATE PROCEDURE cancella_fotografia_temporanea(codice CHAR(16))
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
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM Fotografia WHERE Fotografia.codice = codice;
	DELETE FROM OggettoTemporaneo WHERE OggettoTemporaneo.codice = codice;
	DELETE FROM PrestitoRicevuto WHERE PrestitoRicevuto.codiceOggetto = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;








/******************************************

			QUADRO
		
******************************************/



-- Procedura per inserire una Quadro permanente
DROP PROCEDURE IF EXISTS inserisci_quadro_permanente;
DELIMITER |
CREATE PROCEDURE inserisci_quadro_permanente(
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64),
			descrizione VARCHAR(2048), lunghezza REAL, larghezza REAL, profondita REAL,
			numSala INTEGER, pianoSala INTEGER, stato ENUM('DISPONIBILE','PRESTITO','RESTAURO'),
			codiceArtista CHAR(16))
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
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO OperaArte VALUES (codice, codiceArtista);
	INSERT INTO Quadro VALUES (codice);
	INSERT INTO OggettoPermanente VALUES (codice, stato);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per inserire un Quadro temporaneo
DROP PROCEDURE IF EXISTS inserisci_quadro_temporaneo;
DELIMITER |
CREATE PROCEDURE inserisci_quadro_temporaneo(
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64),
			descrizione VARCHAR(2048), lunghezza REAL, larghezza REAL, profondita REAL,
			numSala INTEGER, pianoSala INTEGER,
			codiceArtista CHAR(16), dataInizio DATE, dataFine DATE, codiceMuseo CHAR(16))
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
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO OperaArte VALUES (codice, codiceArtista);
	INSERT INTO Quadro VALUES (codice);
	INSERT INTO OggettoTemporaneo VALUES (codice);
	INSERT INTO PrestitoRicevuto VALUES (codice, dataInizio, dataFine, codiceMuseo);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;


-- Procedura per aggiornare un Quadro permanente
DROP PROCEDURE IF EXISTS aggiorna_quadro_permanente;
DELIMITER |
CREATE PROCEDURE aggiorna_quadro_permanente(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
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
	
	UPDATE OggettoGenerali og SET og.codice = nuovo_codice WHERE og.codice = vecchio_codice;
	UPDATE OggettoParticolari og SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE OperaArte oa SET oa.codice = nuovo_codice WHERE oa.codice = vecchio_codice;
	UPDATE Quadro q SET q.codice = nuovo_codice WHERE q.codice = vecchio_codice;
	UPDATE OggettoPermanente op SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE Collocazione c SET c.codiceOggetto = nuovo_codice WHERE c.codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per aggiornare un quadro temporaneo
DROP PROCEDURE IF EXISTS aggiorna_quadro_temporaneo;
DELIMITER |
CREATE PROCEDURE aggiorna_quadro_temporaneo(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
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
	
	UPDATE OggettoGenerali og SET og.codice = nuovo_codice WHERE og.codice = vecchio_codice;
	UPDATE OggettoParticolari og SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE OperaArte oa SET oa.codice = nuovo_codice WHERE oa.codice = vecchio_codice;
	UPDATE Quadro q SET q.codice = nuovo_codice WHERE q.codice = vecchio_codice;
	UPDATE OggettoTemporaneo ot SET ot.codice = nuovo_codice WHERE ot.codice = vecchio_codice;
	UPDATE PrestitoRicevuto pr SET pr.codice = nuovo_codice WHERE pr.codice = vecchio_codice;
	UPDATE Collocazione c SET c.codiceOggetto = nuovo_codice WHERE c.codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare un quadro permanente
DROP PROCEDURE IF EXISTS cancella_quadro_permanente;
DELIMITER |
CREATE PROCEDURE cancella_quadro_permanente(codice CHAR(16))
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
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM OperaArte WHERE OperaArte.codice = codice;
	DELETE FROM Quadro WHERE Quadro.codice = codice;
	DELETE FROM OggettoPermanente WHERE OggettoPermanente.codice = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare un Quadro temporaneo
DROP PROCEDURE IF EXISTS cancella_quadro_temporaneo;
DELIMITER |
CREATE PROCEDURE cancella_quadro_temporaneo(codice CHAR(16))
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
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM OperaArte WHERE OperaArte.codice = codice;
	DELETE FROM Quadro WHERE Quadro.codice = codice;
	DELETE FROM OggettoTemporaneo WHERE OggettoTemporaneo.codice = codice;
	DELETE FROM PrestitoRicevuto WHERE PrestitoRicevuto.codiceOggetto = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;









/******************************************

			STATUA
		
******************************************/



-- Procedura per inserire una Statua permanente
DROP PROCEDURE IF EXISTS inserisci_statua_permanente;
DELIMITER |
CREATE PROCEDURE inserisci_statua_permanente(
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64),
			descrizione VARCHAR(2048), lunghezza REAL, larghezza REAL, profondita REAL,
			numSala INTEGER, pianoSala INTEGER, stato ENUM('DISPONIBILE','PRESTITO','RESTAURO'),
			materiale VARCHAR(64), codiceArtista CHAR(16))
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
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO OperaArte VALUES (codice, codiceArtista);
	INSERT INTO Statua VALUES (codice, materiale);
	INSERT INTO OggettoPermanente VALUES (codice, stato);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per inserire una Statua temporanea
DROP PROCEDURE IF EXISTS inserisci_statua_temporanea;
DELIMITER |
CREATE PROCEDURE inserisci_statua_temporanea(
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64),
			descrizione VARCHAR(2048), lunghezza REAL, larghezza REAL, profondita REAL,
			numSala INTEGER, pianoSala INTEGER,
			materiale VARCHAR(64), codiceArtista CHAR(16),
			dataInizio DATE, dataFine DATE, codiceMuseo CHAR(16))
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
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO OperaArte VALUES (codice, codiceArtista);
	INSERT INTO Statua VALUES (codice, materiale);
	INSERT INTO OggettoTemporaneo VALUES (codice);
	INSERT INTO PrestitoRicevuto VALUES (codice, dataInizio, dataFine, codiceMuseo);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;


-- Procedura per aggiornare una Statua permanente
DROP PROCEDURE IF EXISTS aggiorna_statua_permanente;
DELIMITER |
CREATE PROCEDURE aggiorna_statua_permanente(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
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
	
	UPDATE OggettoGenerali og SET og.codice = nuovo_codice WHERE og.codice = vecchio_codice;
	UPDATE OggettoParticolari og SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE OperaArte oa SET oa.codice = nuovo_codice WHERE oa.codice = vecchio_codice;
	UPDATE Statua s SET s.codice = nuovo_codice WHERE s.codice = vecchio_codice;
	UPDATE OggettoPermanente op SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE Collocazione c SET c.codiceOggetto = nuovo_codice WHERE c.codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per aggiornare una Statua temporanea
DROP PROCEDURE IF EXISTS aggiorna_statua_temporaneo;
DELIMITER |
CREATE PROCEDURE aggiorna_statua_temporaneo(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
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
	
	UPDATE OggettoGenerali og SET og.codice = nuovo_codice WHERE og.codice = vecchio_codice;
	UPDATE OggettoParticolari og SET op.codice = nuovo_codice WHERE op.codice = vecchio_codice;
	UPDATE OperaArte oa SET oa.codice = nuovo_codice WHERE oa.codice = vecchio_codice;
	UPDATE Statua s SET s.codice = nuovo_codice WHERE s.codice = vecchio_codice;
	UPDATE OggettoTemporaneo ot SET ot.codice = nuovo_codice WHERE ot.codice = vecchio_codice;
	UPDATE PrestitoRicevuto pr SET pr.codice = nuovo_codice WHERE pr.codice = vecchio_codice;
	UPDATE Collocazione c SET c.codiceOggetto = nuovo_codice WHERE c.codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare una Statua permanente
DROP PROCEDURE IF EXISTS cancella_statua_permanente;
DELIMITER |
CREATE PROCEDURE cancella_statua_permanente(codice CHAR(16))
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
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM OperaArte WHERE OperaArte.codice = codice;
	DELETE FROM Statua WHERE Statua.codice = codice;
	DELETE FROM OggettoPermanente WHERE OggettoPermanente.codice = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare una Statua temporanea
DROP PROCEDURE IF EXISTS cancella_statua_temporaneo;
DELIMITER |
CREATE PROCEDURE cancella_statua_temporaneo(codice CHAR(16))
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
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM OperaArte WHERE OperaArte.codice = codice;
	DELETE FROM Statua WHERE Statua.codice = codice;
	DELETE FROM OggettoTemporaneo WHERE OggettoTemporaneo.codice = codice;
	DELETE FROM PrestitoRicevuto WHERE PrestitoRicevuto.codiceOggetto = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	
	SET foreign_key_checks = 1;
	SET @DIS_TRIG = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;

