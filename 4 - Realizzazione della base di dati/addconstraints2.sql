DROP PROCEDURE IF EXISTS eccezione;
DELIMITER |
CREATE PROCEDURE eccezione()
UPDATE violazione_vincolo SET X=1;
|
DELIMITER ;




/****************************************************************

	VINCOLO DI INCLUSIONE SEZIONE[..] IN APPARTENENZA[..]

	VINCOLO SU NUMSALE IN SEZIONE

*****************************************************************/


-- Funzione che calcola il numero di sale contenute in una sezione
DROP FUNCTION IF EXISTS num_sale_sezione;
DELIMITER |
CREATE FUNCTION num_sale_sezione(nome VARCHAR(64)) RETURNS INTEGER
BEGIN
	DECLARE num_sale INTEGER;
	SELECT count(*)
	INTO num_sale
	FROM Appartenenza a
	WHERE a.sezione=nome;
	RETURN num_sale;
END;
|
DELIMITER ;


-- Funzione che verifica il vincolo di inclusione Sezione in Appartenenza
DROP FUNCTION IF EXISTS inclusione_sezione;
DELIMITER |
CREATE FUNCTION inclusione_sezione(nome VARCHAR(64)) RETURNS BOOL
BEGIN
	DECLARE num_sale INTEGER;
	SET num_sale = num_sale_sezione(nome);
	IF num_sale >= 1 THEN
		RETURN TRUE;
	ELSE 
		RETURN FALSE;
	END IF;
END;
|
DELIMITER ;



-- Inserimenti in Sezione
DROP TRIGGER IF EXISTS sezione_ins;
DELIMITER |
CREATE TRIGGER sezione_ins
BEFORE INSERT ON Sezione
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND (inclusione_sezione(NEW.nome)=FALSE OR NEW.numSale <> 0) THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;


-- Aggiornamenti in Sezione
DROP TRIGGER IF EXISTS sezione_mod;
DELIMITER |
CREATE TRIGGER sezione_mod
BEFORE UPDATE ON Sezione
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND OLD.numSale <> NEW.numSale THEN
		CALL eccezione();
	ELSEIF @DIS_TRIG IS NULL AND OLD.nome <> NEW.nome THEN
	BEGIN
		IF inclusione_sezione(NEW.nome)=FALSE OR OLD.numSale <> 0 THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazioni in Sezione
DROP TRIGGER IF EXISTS sezione_canc;
DELIMITER |
CREATE TRIGGER sezione_canc
BEFORE DELETE ON Sezione
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND OLD.numSale <> 0 THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;



-- Inserimenti in Appartenenza
DROP TRIGGER IF EXISTS app_ins;
DELIMITER |
CREATE TRIGGER app_ins
BEFORE INSERT ON Appartenenza
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL THEN
		UPDATE Sezione s
		SET s.numSale = s.numSale + 1
		WHERE s.nome = NEW.sezione;
	END IF;
END;
|
DELIMITER ;



-- Aggiornamenti in Appartenenza
DROP TRIGGER IF EXISTS app_mod;
DELIMITER |
CREATE TRIGGER app_mod
BEFORE UPDATE ON Appartenenza
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND OLD.sezione <> NEW.sezione THEN
	BEGIN
		DECLARE num_sale INTEGER;
		SET num_sale = num_sale_sezione(OLD.sezione);
		IF num_sale < 2 THEN
			CALL eccezione();
		ELSE
		BEGIN
			UPDATE Sezione s
			SET s.numSale = s.numSale - 1
			WHERE s.nome = OLD.sezione;
			UPDATE Sezione s
			SET s.numSale = s.numSale + 1
			WHERE s.nome = NEW.sezione;
		END;
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazioni in Appartenenza
DROP TRIGGER IF EXISTS app_canc;
DELIMITER |
CREATE TRIGGER app_canc
BEFORE DELETE ON Appartenenza
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL THEN
	BEGIN
		DECLARE num_sale INTEGER;
		SET num_sale = num_sale_sezione(OLD.sezione);
		IF num_sale < 2 THEN
			CALL eccezione();
		ELSE
			UPDATE Sezione s
			SET s.numSale = s.numsale - 1
			WHERE s.nome = OLD.sezione;
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;






/******************************************************************

	VINCOLO DI INCLUSIONE STRUTTURA[..] IN TELEFONOSTRUTTURA[..]

*******************************************************************/



-- Funzione che calcola i numeri di telefono di una struttura
DROP FUNCTION IF EXISTS num_telefoni_struttura;
DELIMITER |
CREATE FUNCTION num_telefoni_struttura(codice VARCHAR(16)) RETURNS INTEGER
BEGIN
	DECLARE num_tel INTEGER;
	SELECT count(*)
	INTO num_tel
	FROM TelefonoStruttura ts
	WHERE ts.codiceStruttura = codice;
	RETURN num_tel;
END;
|
DELIMITER ;


-- Funzione che verifica il vincolo di inclusione Struttura - TelefonoStruttura
DROP FUNCTION IF EXISTS inclusione_struttura;
DELIMITER |
CREATE FUNCTION inclusione_struttura(codice VARCHAR(16)) RETURNS BOOL
BEGIN
	DECLARE num_tel INTEGER;
	SET num_tel = num_telefoni_struttura(codice);
	IF num_tel >= 1 THEN
		RETURN TRUE;
	ELSE 
		RETURN FALSE;
	END IF;
END;
|
DELIMITER ;



-- Inserimenti in Struttura
DROP TRIGGER IF EXISTS struttura_ins;
DELIMITER |
CREATE TRIGGER struttura_ins
BEFORE INSERT ON Struttura
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND inclusione_struttura(NEW.codice)=FALSE THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;


-- Aggiornamenti in Struttura
DROP TRIGGER IF EXISTS struttura_mod;
DELIMITER |
CREATE TRIGGER struttura_mod
BEFORE UPDATE ON Struttura
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND OLD.codice <> NEW.codice THEN
	BEGIN
		IF inclusione_struttura(NEW.codice)=FALSE THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;



-- Aggiornamenti in TelefonoStruttura
DROP TRIGGER IF EXISTS tel_mod;
DELIMITER |
CREATE TRIGGER tel_mod
BEFORE UPDATE ON TelefonoStruttura
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND OLD.codiceStruttura <> NEW.codiceStruttura THEN
	BEGIN
		DECLARE num_tel INTEGER;
		SET num_tel = num_telefoni_struttura(OLD.codiceStruttura);
		IF num_tel < 2 THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazioni in TelefonoStruttura
DROP TRIGGER IF EXISTS tel_canc;
DELIMITER |
CREATE TRIGGER tel_canc
BEFORE DELETE ON TelefonoStruttura
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL THEN
	BEGIN
		DECLARE num_tel INTEGER;
		SET num_tel = num_telefoni_struttura(OLD.codiceStruttura);
		IF (num_tel < 2) THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;







/****************************************************

	VINCOLO SU NUMOGGETTI IN SALA

*****************************************************/



-- Inserimenti in Sala
DROP TRIGGER IF EXISTS sala_ins;
DELIMITER |
CREATE TRIGGER sala_ins
BEFORE INSERT ON Sala
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND NEW.numOggetti <> 0 THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;


-- Aggiornamenti in Sala
DROP TRIGGER IF EXISTS sala_mod;
DELIMITER |
CREATE TRIGGER sala_mod
BEFORE UPDATE ON Sala
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND OLD.numOggetti <> NEW.numOggetti THEN
		CALL eccezione();
	ELSEIF @DIS_TRIG IS NULL AND (OLD.numero <> NEW.numero OR OLD.piano <> NEW.piano) THEN
	BEGIN
		IF OLD.numOggetti <> 0 THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazioni in Sala
DROP TRIGGER IF EXISTS sala_canc;
DELIMITER |
CREATE TRIGGER sala_canc
BEFORE DELETE ON Sala
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND OLD.numOggetti <> 0 THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;



-- Inserimenti in Collocazione
DROP TRIGGER IF EXISTS coll_ins;
DELIMITER |
CREATE TRIGGER coll_ins
BEFORE INSERT ON Collocazione
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL THEN
		UPDATE Sala s
		SET s.numOggetti = s.numOggetti + 1
		WHERE s.numero = NEW.numSala AND s.piano = NEW.pianoSala;
	END IF;
END;
|
DELIMITER ;



-- Aggiornamenti in Collocazione
DROP TRIGGER IF EXISTS coll_mod;
DELIMITER |
CREATE TRIGGER coll_mod
BEFORE UPDATE ON Collocazione
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND (OLD.numSala <> NEW.numSala OR OLD.pianoSala <> NEW.pianoSala) THEN
	BEGIN
		UPDATE Sala s
		SET s.numOggetti = s.numOggetti - 1
		WHERE s.numero = OLD.numSala AND s.piano = OLD.pianoSala;
		UPDATE Sala s
		SET s.numOggetti = s.numOggetti + 1
		WHERE s.numero = NEW.numSala AND s.piano = NEW.pianoSala;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazioni in Collocazione
DROP TRIGGER IF EXISTS coll_canc;
DELIMITER |
CREATE TRIGGER coll_canc
BEFORE DELETE ON Collocazione
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL THEN
		UPDATE Sala s
		SET s.numOggetti = s.numOggetti - 1
		WHERE s.numero = OLD.numSala AND s.piano = OLD.pianoSala;
	END IF;
END;
|
DELIMITER ;

