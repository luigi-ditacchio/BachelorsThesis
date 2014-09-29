/******************************************

			REPERTO ARCHEOLOGICO
		
******************************************/



-- Procedura per inserire un RepertoArcheologico permanente
DROP PROCEDURE IF EXISTS inserisci_reperto_permanente;
DELIMITER |
CREATE PROCEDURE inserisci_reperto_permanente(
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64), descrizione VARCHAR(2048),
			lunghezza REAL, larghezza REAL, profondita REAL,
			stato ENUM('PRESENTE','PRESTITO','RESTAURO'),
			numSala INTEGER, pianoSala INTEGER,
			nomeScavo VARCHAR(64), luogoScavo VARCHAR(64))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari(codice, lunghezza, larghezza, profondita) VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO RepertoArcheologico VALUES (codice);
	INSERT INTO Ritrovamento(codiceReperto, nomeScavo, luogoScavo) VALUES (codice, nomeScavo, luogoScavo);
	INSERT INTO OggettoPermanente VALUES (codice, stato);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	UPDATE Sala SET numOggetti = numOggetti+1 WHERE numero = numSala and piano = pianoSala;
	
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64), descrizione VARCHAR(2048),
			lunghezza REAL, larghezza REAL, profondita REAL,
			numSala INTEGER, pianoSala INTEGER,
			nomeScavo VARCHAR(64), luogoScavo VARCHAR(64),
			dataInizio DATE, dataFine DATE, codiceMuseo CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari(codice, lunghezza, larghezza, profondita) VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO RepertoArcheologico VALUES (codice);
	INSERT INTO Ritrovamento(codiceReperto, nomeScavo, luogoScavo) VALUES (codice, nomeScavo, luogoScavo);
	INSERT INTO OggettoTemporaneo VALUES (codice);
	INSERT INTO PrestitoRicevuto VALUES (codice, dataInizio, dataFine, codiceMuseo);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	UPDATE Sala SET numOggetti = numOggetti+1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	UPDATE OggettoGenerali SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoParticolari SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE RepertoArcheologico SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Ritrovamento SET codiceReperto = nuovo_codice WHERE codiceReperto = vecchio_codice;
	UPDATE OggettoPermanente SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Collocazione SET codiceOggetto = nuovo_codice WHERE codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	UPDATE OggettoGenerali SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoParticolari SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE RepertoArcheologico SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Ritrovamento SET codiceReperto = nuovo_codice WHERE codiceReperto = vecchio_codice;
	UPDATE OggettoTemporaneo SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE PrestitoRicevuto SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Collocazione SET codiceOggetto = nuovo_codice WHERE codiceOggetto = vecchio_codice;

	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM RepertoArcheologico WHERE RepertoArcheologico.codice = codice;
	DELETE FROM Ritrovamento WHERE Ritrovamento.codiceReperto = codice;
	DELETE FROM OggettoPermanente WHERE OggettoPermanente.codice = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	UPDATE Sala SET numOggetti = numOggetti-1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM RepertoArcheologico WHERE RepertoArcheologico.codice = codice;
	DELETE FROM Ritrovamento WHERE Ritrovamento.codiceReperto = codice;
	DELETE FROM OggettoTemporaneo WHERE OggettoTemporaneo.codice = codice;
	DELETE FROM PrestitoRicevuto WHERE PrestitoRicevuto.codiceOggetto = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	UPDATE Sala SET numOggetti = numOggetti-1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64), descrizione VARCHAR(2048),
			lunghezza REAL, larghezza REAL, profondita REAL,
			tipo ENUM('B/N', 'COLORI'),
			stato ENUM('PRESENTE','PRESTITO','RESTAURO'),
			numSala INTEGER, pianoSala INTEGER)
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari(codice, lunghezza, larghezza, profondita) VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO Fotografia VALUES (codice, tipo);
	INSERT INTO OggettoPermanente VALUES (codice, stato);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	UPDATE Sala SET numOggetti = numOggetti+1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64), descrizione VARCHAR(2048), 
			lunghezza REAL, larghezza REAL, profondita REAL,
			tipo ENUM('B/N', 'COLORI'),
			numSala INTEGER, pianoSala INTEGER,
			dataInizio DATE, dataFine DATE, codiceMuseo CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari(codice, lunghezza, larghezza, profondita) VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO Fotografia VALUES (codice, tipo);
	INSERT INTO OggettoTemporaneo VALUES (codice);
	INSERT INTO PrestitoRicevuto VALUES (codice, dataInizio, dataFine, codiceMuseo);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	UPDATE Sala SET numOggetti = numOggetti+1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	UPDATE OggettoGenerali SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoParticolari SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Fotografia SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoPermanente SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Collocazione SET codiceOggetto = nuovo_codice WHERE codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	UPDATE OggettoGenerali SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoParticolari SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Fotografia SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoTemporaneo SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE PrestitoRicevuto SET codiceOggetto = nuovo_codice WHERE codiceOggetto = vecchio_codice;
	UPDATE Collocazione SET codiceOggetto = nuovo_codice WHERE codiceOggetto = vecchio_codice;

	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM Fotografia WHERE Fotografia.codice = codice;
	DELETE FROM OggettoPermanente WHERE OggettoPermanente.codice = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	UPDATE Sala SET numOggetti = numOggetti-1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM Fotografia WHERE Fotografia.codice = codice;
	DELETE FROM OggettoTemporaneo WHERE OggettoTemporaneo.codice = codice;
	DELETE FROM PrestitoRicevuto WHERE PrestitoRicevuto.codiceOggetto = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	UPDATE Sala SET numOggetti = numOggetti-1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;








/******************************************

			QUADRO
		
******************************************/



-- Procedura per inserire un Quadro permanente
DROP PROCEDURE IF EXISTS inserisci_quadro_permanente;
DELIMITER |
CREATE PROCEDURE inserisci_quadro_permanente(
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64), descrizione VARCHAR(2048), 
			lunghezza REAL, larghezza REAL, profondita REAL,
			stato ENUM('PRESENTE','PRESTITO','RESTAURO'),
			numSala INTEGER, pianoSala INTEGER,
			codiceArtista CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari(codice, lunghezza, larghezza, profondita) VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO OperaArte VALUES (codice, codiceArtista);
	INSERT INTO Quadro(codice) VALUES (codice);
	INSERT INTO OggettoPermanente VALUES (codice, stato);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	UPDATE Sala SET numOggetti = numOggetti+1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64), descrizione VARCHAR(2048), 
			lunghezza REAL, larghezza REAL, profondita REAL,
			numSala INTEGER, pianoSala INTEGER,
			codiceArtista CHAR(16),
			dataInizio DATE, dataFine DATE, codiceMuseo CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari(codice, lunghezza, larghezza, profondita) VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO OperaArte VALUES (codice, codiceArtista);
	INSERT INTO Quadro(codice) VALUES (codice);
	INSERT INTO OggettoTemporaneo VALUES (codice);
	INSERT INTO PrestitoRicevuto VALUES (codice, dataInizio, dataFine, codiceMuseo);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	UPDATE Sala SET numOggetti = numOggetti+1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	UPDATE OggettoGenerali SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoParticolari SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OperaArte SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Quadro SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoPermanente SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Collocazione SET codiceOggetto = nuovo_codice WHERE codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	UPDATE OggettoGenerali SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoParticolari SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OperaArte SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Quadro SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoTemporaneo SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE PrestitoRicevuto SET codiceOggetto = nuovo_codice WHERE codiceOggetto = vecchio_codice;
	UPDATE Collocazione SET codiceOggetto = nuovo_codice WHERE codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM OperaArte WHERE OperaArte.codice = codice;
	DELETE FROM Quadro WHERE Quadro.codice = codice;
	DELETE FROM OggettoPermanente WHERE OggettoPermanente.codice = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	UPDATE Sala SET numOggetti = numOggetti-1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM OperaArte WHERE OperaArte.codice = codice;
	DELETE FROM Quadro WHERE Quadro.codice = codice;
	DELETE FROM OggettoTemporaneo WHERE OggettoTemporaneo.codice = codice;
	DELETE FROM PrestitoRicevuto WHERE PrestitoRicevuto.codiceOggetto = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	UPDATE Sala SET numOggetti = numOggetti-1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64), descrizione VARCHAR(2048), 
			lunghezza REAL, larghezza REAL, profondita REAL,
			materiale VARCHAR(64),
			stato ENUM('PRESENTE','PRESTITO','RESTAURO'),
			numSala INTEGER, pianoSala INTEGER, 
			codiceArtista CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari(codice, lunghezza, larghezza, profondita) VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO OperaArte VALUES (codice, codiceArtista);
	INSERT INTO Statua VALUES (codice, materiale);
	INSERT INTO OggettoPermanente VALUES (codice, stato);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	UPDATE Sala SET numOggetti = numOggetti+1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
			codice CHAR(16), nome VARCHAR(64), anno_periodo VARCHAR(64), descrizione VARCHAR(2048), 
			lunghezza REAL, larghezza REAL, profondita REAL,
			materiale VARCHAR(64), 
			numSala INTEGER, pianoSala INTEGER,
			codiceArtista CHAR(16),
			dataInizio DATE, dataFine DATE, codiceMuseo CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	INSERT INTO OggettoGenerali VALUES (codice, nome, anno_periodo, descrizione);
	INSERT INTO OggettoParticolari(codice, lunghezza, larghezza, profondita) VALUES (codice, lunghezza, larghezza, profondita);
	INSERT INTO OperaArte VALUES (codice, codiceArtista);
	INSERT INTO Statua VALUES (codice, materiale);
	INSERT INTO OggettoTemporaneo VALUES (codice);
	INSERT INTO PrestitoRicevuto VALUES (codice, dataInizio, dataFine, codiceMuseo);
	INSERT INTO Collocazione VALUES (codice, numSala, pianoSala);
	UPDATE Sala SET numOggetti = numOggetti+1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	UPDATE OggettoGenerali SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoParticolari SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OperaArte SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Statua SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoPermanente SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Collocazione c SET c.codiceOggetto = nuovo_codice WHERE c.codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per aggiornare una Statua temporanea
DROP PROCEDURE IF EXISTS aggiorna_statua_temporanea;
DELIMITER |
CREATE PROCEDURE aggiorna_statua_temporanea(vecchio_codice CHAR(16), nuovo_codice CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	UPDATE OggettoGenerali SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoParticolari SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OperaArte SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE Statua SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE OggettoTemporaneo SET codice = nuovo_codice WHERE codice = vecchio_codice;
	UPDATE PrestitoRicevuto SET codiceOggetto = nuovo_codice WHERE codiceOggetto = vecchio_codice;
	UPDATE Collocazione SET codiceOggetto = nuovo_codice WHERE codiceOggetto = vecchio_codice;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
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
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM OperaArte WHERE OperaArte.codice = codice;
	DELETE FROM Statua WHERE Statua.codice = codice;
	DELETE FROM OggettoPermanente WHERE OggettoPermanente.codice = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	UPDATE Sala SET numOggetti = numOggetti-1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;



-- Procedura per cancellare una Statua temporanea
DROP PROCEDURE IF EXISTS cancella_statua_temporanea;
DELIMITER |
CREATE PROCEDURE cancella_statua_temporanea(codice CHAR(16))
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET foreign_key_checks = 1;
		SET @dis_trig = NULL;
		SET AUTOCOMMIT = 1;
		SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	END;
	
	SET AUTOCOMMIT = 0;
	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	SET foreign_key_checks = 0;
	SET @dis_trig = 1;
	
	DELETE FROM OggettoGenerali WHERE OggettoGenerali.codice = codice;
	DELETE FROM OggettoParticolari WHERE OggettoParticolari.codice = codice;
	DELETE FROM OperaArte WHERE OperaArte.codice = codice;
	DELETE FROM Statua WHERE Statua.codice = codice;
	DELETE FROM OggettoTemporaneo WHERE OggettoTemporaneo.codice = codice;
	DELETE FROM PrestitoRicevuto WHERE PrestitoRicevuto.codiceOggetto = codice;
	DELETE FROM Collocazione WHERE Collocazione.codiceOggetto = codice;
	UPDATE Sala SET numOggetti = numOggetti-1 WHERE numero = numSala and piano = pianoSala;
	
	SET foreign_key_checks = 1;
	SET @dis_trig = NULL;
	
	COMMIT;
	
	SET AUTOCOMMIT = 1;
	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

END;
|
DELIMITER ;

