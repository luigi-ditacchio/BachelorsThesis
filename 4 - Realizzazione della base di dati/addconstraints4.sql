DROP PROCEDURE IF EXISTS eccezione;
DELIMITER |
CREATE PROCEDURE eccezione()
UPDATE violazione_vincolo SET X=1;
|
DELIMITER ;


/*********************************************

	VINCOLI ESTERNI SU DATE DI
	PRESTITORICEVUTO, PRESTITOEFFETTUATO
	E RESTAURO

**********************************************/


-- Funzione che verifica la non sovrapposizione di un
-- intervallo temporale in PrestitoRicevuto
DROP FUNCTION IF EXISTS non_sovrapp_prRic;
DELIMITER |
CREATE FUNCTION non_sovrapp_prRic(dataInizio DATE, dataFine DATE) RETURNS BOOL
BEGIN
	DECLARE done INTEGER;
	DECLARE inizio, fine DATE;
	DECLARE ptr CURSOR FOR SELECT dataInizio, dataFine FROM PrestitoRicevuto;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
	
	IF dataInizio > dataFine THEN
		RETURN FALSE;
	END IF;
	
	SET done = 0;
	OPEN ptr;
	
	REPEAT
		FETCH ptr INTO inizio, fine;
		IF NOT done THEN
		BEGIN
			IF dataInizio < fine AND dataFine >= fine THEN
				RETURN FALSE;
			END IF;
			IF dataInizio <= inizio AND dataFine > inizio THEN
				RETURN FALSE;
			END IF;
		END;
		END IF;
	UNTIL done
	END REPEAT;
	
	CLOSE ptr;
	
	RETURN TRUE;

END;
|
DELIMITER ;


-- Funzione che verifica la non sovrapposizione di un
-- intervallo temporale in PrestitoEffettuato
DROP FUNCTION IF EXISTS non_sovrapp_prEff;
DELIMITER |
CREATE FUNCTION non_sovrapp_prEff(dataInizio DATE, dataFine DATE) RETURNS BOOL
BEGIN
	DECLARE done INTEGER;
	DECLARE inizio, fine DATE;
	DECLARE ptr CURSOR FOR SELECT dataInizio, dataFine FROM PrestitoEffettuato;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
	
	IF dataInizio > dataFine THEN
		RETURN FALSE;
	END IF;
	
	SET done = 0;
	OPEN ptr;
	
	REPEAT
		FETCH ptr INTO inizio, fine;
		IF NOT done THEN
		BEGIN
			IF dataInizio < fine AND dataFine >= fine THEN
				RETURN FALSE;
			END IF;
			IF dataInizio <= inizio AND dataFine > inizio THEN
				RETURN FALSE;
			END IF;
		END;
		END IF;
	UNTIL done
	END REPEAT;
	
	CLOSE ptr;
	
	RETURN TRUE;

END;
|
DELIMITER ;



-- Funzione che verifica la non sovrapposizione di un
-- intervallo temporale in Restauro
DROP FUNCTION IF EXISTS non_sovrapp_rest;
DELIMITER |
CREATE FUNCTION non_sovrapp_rest(dataInizio DATE, dataFine DATE) RETURNS BOOL
BEGIN
	DECLARE done INTEGER;
	DECLARE inizio, fine DATE;
	DECLARE ptr CURSOR FOR SELECT dataInizio, dataFine FROM Restauro;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
	
	IF dataInizio > dataFine THEN
		RETURN FALSE;
	END IF;
	
	SET done = 0;
	OPEN ptr;
	
	REPEAT
		FETCH ptr INTO inizio, fine;
		IF NOT done THEN
		BEGIN
			IF dataInizio < fine AND dataFine >= fine THEN
				RETURN FALSE;
			END IF;
			IF dataInizio <= inizio AND dataFine > inizio THEN
				RETURN FALSE;
			END IF;
		END;
		END IF;
	UNTIL done
	END REPEAT;
	
	CLOSE ptr;
	
	RETURN TRUE;

END;
|
DELIMITER ;








-- Inserimento in PrestitoRicevuto
DROP TRIGGER IF EXISTS prRic_ins;
DELIMITER |
CREATE TRIGGER prRic_ins
BEFORE INSERT ON PrestitoRicevuto
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND 
		(NEW.dataInizio > NEW.dataFine
		OR non_sovrapp_prRic(NEW.dataInizio, NEW.dataFine)=FALSE
		OR non_sovrapp_prEff(NEW.dataInizio, NEW.dataFine)=FALSE
		OR non_sovrapp_rest(NEW.dataInizio, NEW.dataFine)=FALSE) THEN
			CALL eccezione();
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in PrestitoRicevuto
DROP TRIGGER IF EXISTS prRic_mod;
DELIMITER |
CREATE TRIGGER prRic_mod
BEFORE UPDATE ON PrestitoRicevuto
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND (OLD.dataInizio <> NEW.dataInizio OR OLD.dataFine <> NEW.dataFine) THEN
	BEGIN
		IF NEW.dataInizio > NEW.dataFine
			OR non_sovrapp_prRic(NEW.dataInizio, NEW.dataFine)=FALSE
			OR non_sovrapp_prEff(NEW.dataInizio, NEW.dataFine)=FALSE
			OR non_sovrapp_rest(NEW.dataInizio, NEW.dataFine)=FALSE THEN
				CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;



-- Inserimento in PrestitoEffettuato
DROP TRIGGER IF EXISTS prEff_ins;
DELIMITER |
CREATE TRIGGER prEff_ins
BEFORE INSERT ON PrestitoEffettuato
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND 
		(NEW.dataInizio > NEW.dataFine
		OR non_sovrapp_prEff(NEW.dataInizio, NEW.dataFine)=FALSE
		OR non_sovrapp_prRic(NEW.dataInizio, NEW.dataFine)=FALSE
		OR non_sovrapp_rest(NEW.dataInizio, NEW.dataFine)=FALSE) THEN
			CALL eccezione();
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in PrestitoEffettuato
DROP TRIGGER IF EXISTS prEff_mod;
DELIMITER |
CREATE TRIGGER prEff_mod
BEFORE UPDATE ON PrestitoEffettuato
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND (OLD.dataInizio <> NEW.dataInizio OR OLD.dataFine <> NEW.dataFine) THEN
	BEGIN
		IF NEW.dataInizio > NEW.dataFine
			OR non_sovrapp_prEff(NEW.dataInizio, NEW.dataFine)=FALSE
			OR non_sovrapp_prRic(NEW.dataInizio, NEW.dataFine)=FALSE
			OR non_sovrapp_rest(NEW.dataInizio, NEW.dataFine)=FALSE THEN
				CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;



-- Inserimento in Restauro
DROP TRIGGER IF EXISTS rest_ins;
DELIMITER |
CREATE TRIGGER rest_ins
BEFORE INSERT ON Restauro
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND 
		(NEW.dataInizio > NEW.dataFine
		OR non_sovrapp_rest(NEW.dataInizio, NEW.dataFine)=FALSE
		OR non_sovrapp_prRic(NEW.dataInizio, NEW.dataFine)=FALSE
		OR non_sovrapp_prEff(NEW.dataInizio, NEW.dataFine)=FALSE) THEN
			CALL eccezione();
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in PrestitoEffettuato
DROP TRIGGER IF EXISTS rest_mod;
DELIMITER |
CREATE TRIGGER rest_mod
BEFORE UPDATE ON Restauro
FOR EACH ROW
BEGIN
	IF @DIS_TRIG IS NULL AND (OLD.dataInizio <> NEW.dataInizio OR OLD.dataFine <> NEW.dataFine) THEN
	BEGIN
		IF NEW.dataInizio > NEW.dataFine
			OR non_sovrapp_rest(NEW.dataInizio, NEW.dataFine)=FALSE
			OR non_sovrapp_prRic(NEW.dataInizio, NEW.dataFine)=FALSE
			OR non_sovrapp_oreEff(NEW.dataInizio, NEW.dataFine)=FALSE THEN
				CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;
