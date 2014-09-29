DROP PROCEDURE IF EXISTS eccezione;
DELIMITER |
CREATE PROCEDURE eccezione()
UPDATE violazione_vincolo
SET X=1;
|
DELIMITER ;


/***************************************************************

	VINCOLI DI GENERALIZZAZIONE DI OGGETTO (ENTRAMBI)
	E DI OPERAARTE

****************************************************************/


DROP VIEW IF EXISTS tutti_oggetti_categoria;
CREATE VIEW tutti_oggetti_categoria(codice)
AS
SELECT codice
FROM RepertoArcheologico
UNION ALL
SELECT codice
FROM Fotografia
UNION ALL
SELECT codice
FROM OperaArte
;


DROP VIEW IF EXISTS tutti_oggetti_collezione;
CREATE VIEW tutti_oggetti_collezione(codice)
AS
SELECT codice
FROM OggettoTemporaneo
UNION ALL
SELECT codice
FROM OggettoPermanente
;


DROP VIEW IF EXISTS tutte_opere;
CREATE VIEW tutte_opere(codice)
AS
SELECT codice
FROM Quadro
UNION ALL
SELECT codice
FROM Statua
;




-- Funzione che verifica le premesse per la disgiuntezza di Reperto...
DROP FUNCTION IF EXISTS disgiuntezza_oggetti_categoria;
DELIMITER |
CREATE FUNCTION disgiuntezza_oggetti_categoria(codice CHAR(16)) RETURNS BOOL
BEGIN
	DECLARE num_ogg INTEGER;
	
	SELECT count(*)
	INTO num_ogg
	FROM tutti_oggetti_categoria t
	WHERE t.codice = codice;
	
	IF num_ogg = 0 THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
|
DELIMITER ;

-- Funzione che verifica la completezza di
-- RepertoArcheologico, ecc.
DROP FUNCTION IF EXISTS completezza_oggetti_categoria;
DELIMITER |
CREATE FUNCTION completezza_oggetti_categoria(codice CHAR(16)) RETURNS BOOL
BEGIN
	DECLARE num_ogg INTEGER;
	
	SELECT count(*)
	INTO num_ogg
	FROM tutti_oggetti_categoria t
	WHERE t.codice = codice;
	
	IF num_ogg >= 1 THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
|
DELIMITER ;








-- Funzione che verifica le premesse per la disgiuntezza di OggettoTemporaneo...
DROP FUNCTION IF EXISTS disgiuntezza_oggetti_collezione;
DELIMITER |
CREATE FUNCTION disgiuntezza_oggetti_collezione(codice CHAR(16)) RETURNS BOOL
BEGIN
	DECLARE num_ogg INTEGER;
	
	SELECT count(*)
	INTO num_ogg
	FROM tutti_oggetti_collezione t
	WHERE t.codice = codice;
	
	IF num_ogg = 0 THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
|
DELIMITER ;

-- Funzione che verifica la completezza di
-- OggettoTemporaneo, ecc.
DROP FUNCTION IF EXISTS completezza_oggetti_collezione;
DELIMITER |
CREATE FUNCTION completezza_oggetti_collezione(codice CHAR(16)) RETURNS BOOL
BEGIN
	DECLARE num_ogg INTEGER;
	
	SELECT count(*)
	INTO num_ogg
	FROM tutti_oggetti_collezione t
	WHERE t.codice = codice;
	
	IF num_ogg >= 1 THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
|
DELIMITER ;







-- Funzione che verifica le premesse per la disgiuntezza di Quadro e Statua
DROP FUNCTION IF EXISTS disgiuntezza_opere;
DELIMITER |
CREATE FUNCTION disgiuntezza_opere(codice CHAR(16)) RETURNS BOOL
BEGIN
	DECLARE num_ogg INTEGER;
	
	SELECT count(*)
	INTO num_ogg
	FROM tutte_opere t
	WHERE t.codice = codice;
	
	IF num_ogg = 0 THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
|
DELIMITER ;

-- Funzione che verifica la completezza di
-- Quadro e Statua
DROP FUNCTION IF EXISTS completezza_opere;
DELIMITER |
CREATE FUNCTION completezza_opere(codice CHAR(16)) RETURNS BOOL
BEGIN
	DECLARE num_ogg INTEGER;
	
	SELECT count(*)
	INTO num_ogg
	FROM tutte_opere t
	WHERE t.codice = codice;
	
	IF num_ogg >= 1 THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
|
DELIMITER ;






-- Funzione che verifica la presenza di un oggetto
DROP FUNCTION IF EXISTS presenza_oggetto;
DELIMITER |
CREATE FUNCTION presenza_oggetto(codice CHAR(16)) RETURNS BOOL
BEGIN
	DECLARE num_ogg INTEGER;
	
	SELECT count(*)
	INTO num_ogg
	FROM OggettoGenerali o
	WHERE o.codice = codice;
	
	IF num_ogg >= 1 THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
|
DELIMITER ;


-- Funzione che verifica la presenza di un'opera
DROP FUNCTION IF EXISTS presenza_opera;
DELIMITER |
CREATE FUNCTION presenza_opera(codice CHAR(16)) RETURNS BOOL
BEGIN
	DECLARE num_opere INTEGER;
	
	SELECT count(*)
	INTO num_opere
	FROM OperaArte o
	WHERE o.codice = codice;
	
	IF num_opere >= 1 THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
|
DELIMITER ;
















-- Inserimento in OggettoParticolari
DROP TRIGGER IF EXISTS oggettopar_ins;
DELIMITER |
CREATE TRIGGER oggettopar_ins
BEFORE INSERT ON OggettoParticolari
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL THEN
	BEGIN
		IF NEW.lunghezza <= 0 OR NEW.larghezza <= 0 OR NEW.profondita <= 0 THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;



-- Aggiornamento in OggettoParticolari
DROP TRIGGER IF EXISTS oggettopar_mod;
DELIMITER |
CREATE TRIGGER oggettopar_mod
BEFORE UPDATE ON OggettoParticolari
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL THEN
	BEGIN
		IF OLD.lunghezza <> NEW.lunghezza OR OLD.larghezza <> NEW.larghezza OR OLD.profondita <> NEW.profondita THEN
		BEGIN
			IF NEW.lunghezza <= 0 OR NEW.larghezza <= 0 OR NEW.profondita <= 0 THEN
				CALL eccezione();
			END IF;
		END;
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;





-- Inserimento in Oggetto
DROP TRIGGER IF EXISTS oggetto_ins;
DELIMITER |
CREATE TRIGGER oggetto_ins
BEFORE INSERT ON OggettoGenerali
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL THEN
	BEGIN
		IF completezza_oggetti_categoria(NEW.codice)=FALSE OR completezza_oggetti_collezione(NEW.codice)=FALSE THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;



-- Aggiornamento in Oggetto
DROP TRIGGER IF EXISTS oggetto_mod;
DELIMITER |
CREATE TRIGGER oggetto_mod
BEFORE UPDATE ON OggettoGenerali
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL THEN
	BEGIN
		IF OLD.codice <> NEW.codice THEN
		BEGIN
			IF completezza_oggetti_categoria(NEW.codice)=FALSE OR completezza_oggetti_collezione(NEW.codice)=FALSE THEN
				CALL eccezione();
			END IF;
		END;
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;





-- Inserimento in RepertoArcheologico
DROP TRIGGER IF EXISTS reperto_ins;
DELIMITER |
CREATE TRIGGER reperto_ins
BEFORE INSERT ON RepertoArcheologico
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL THEN
	BEGIN
		IF disgiuntezza_oggetti_categoria(NEW.codice)=FALSE THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in RepertoArcheologico
DROP TRIGGER IF EXISTS reperto_mod;
DELIMITER |
CREATE TRIGGER reperto_mod
BEFORE UPDATE ON RepertoArcheologico
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND OLD.codice <> NEW.codice THEN
	BEGIN
		IF disgiuntezza_oggetti_categoria(NEW.codice)=FALSE OR presenza_oggetto(OLD.codice) THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazione in RepertoArcheologico
DROP TRIGGER IF EXISTS reperto_canc;
DELIMITER |
CREATE TRIGGER reperto_canc
BEFORE DELETE ON RepertoArcheologico
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND presenza_oggetto(OLD.codice) THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;








-- Inserimento in Fotografia
DROP TRIGGER IF EXISTS fotografia_ins;
DELIMITER |
CREATE TRIGGER fotografia_ins
BEFORE INSERT ON Fotografia
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL THEN
	BEGIN
		IF disgiuntezza_oggetti_categoria(NEW.codice)=FALSE THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in Fotografia
DROP TRIGGER IF EXISTS fotografia_mod;
DELIMITER |
CREATE TRIGGER fotografia_mod
BEFORE UPDATE ON Fotografia
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND OLD.codice <> NEW.codice THEN
	BEGIN
		IF disgiuntezza_oggetti_categoria(NEW.codice)=FALSE OR presenza_oggetto(OLD.codice) THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazione in Fotografia
DROP TRIGGER IF EXISTS fotografia_canc;
DELIMITER |
CREATE TRIGGER fotografia_canc
BEFORE DELETE ON Fotografia
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND presenza_oggetto(OLD.codice) THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;








-- Inserimento in OperaArte
DROP TRIGGER IF EXISTS opera_ins;
DELIMITER |
CREATE TRIGGER opera_ins
BEFORE INSERT ON OperaArte
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL THEN
	BEGIN
		IF disgiuntezza_oggetti_categoria(NEW.codice)=FALSE OR completezza_opere(NEW.codice)=FALSE THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in OperaArte
DROP TRIGGER IF EXISTS opera_mod;
DELIMITER |
CREATE TRIGGER opera_mod
BEFORE UPDATE ON OperaArte
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND OLD.codice <> NEW.codice THEN
	BEGIN
		IF disgiuntezza_oggetti_categoria(NEW.codice)=FALSE OR presenza_oggetto(OLD.codice) OR completezza_opere(NEW.codice)=FALSE THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazione in OperaArte
DROP TRIGGER IF EXISTS opera_canc;
DELIMITER |
CREATE TRIGGER opera_canc
BEFORE DELETE ON OperaArte
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND presenza_oggetto(OLD.codice) THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;







-- Inserimento in OggettoTemporaneo
DROP TRIGGER IF EXISTS oggtemp_ins;
DELIMITER |
CREATE TRIGGER oggtemp_ins
BEFORE INSERT ON OggettoTemporaneo
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL THEN 
	BEGIN
		IF disgiuntezza_oggetti_collezione(NEW.codice)=FALSE THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in OggettoTemporaneo
DROP TRIGGER IF EXISTS oggtemp_mod;
DELIMITER |
CREATE TRIGGER oggtemp_mod
BEFORE UPDATE ON OggettoTemporaneo
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND OLD.codice <> NEW.codice THEN
	BEGIN
		IF disgiuntezza_oggetti_collezione(NEW.codice)=FALSE OR presenza_oggetto(OLD.codice) THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazione in OggettoTemporaneo
DROP TRIGGER IF EXISTS oggtemp_canc;
DELIMITER |
CREATE TRIGGER oggtemp_canc
BEFORE DELETE ON OggettoTemporaneo
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND presenza_oggetto(OLD.codice) THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;







-- Inserimento in OggettoPermanente
DROP TRIGGER IF EXISTS oggperm_ins;
DELIMITER |
CREATE TRIGGER oggperm_ins
BEFORE INSERT ON OggettoPermanente
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL THEN 
	BEGIN
		IF disgiuntezza_oggetti_collezione(NEW.codice)=FALSE THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in OggettoPermanente
DROP TRIGGER IF EXISTS oggperm_mod;
DELIMITER |
CREATE TRIGGER oggperm_mod
BEFORE UPDATE ON OggettoPermanente
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND OLD.codice <> NEW.codice THEN
	BEGIN
		IF disgiuntezza_oggetti_collezione(NEW.codice)=FALSE OR presenza_oggetto(OLD.codice) THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazione in OggettoPermanente
DROP TRIGGER IF EXISTS oggperm_canc;
DELIMITER |
CREATE TRIGGER oggperm_canc
BEFORE DELETE ON OggettoPermanente
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND presenza_oggetto(OLD.codice) THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;











-- Inserimento in Quadro
DROP TRIGGER IF EXISTS quadro_ins;
DELIMITER |
CREATE TRIGGER quadro_ins
BEFORE INSERT ON Quadro
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL THEN 
	BEGIN
		IF disgiuntezza_opere(NEW.codice)=FALSE THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in Quadro
DROP TRIGGER IF EXISTS quadro_mod;
DELIMITER |
CREATE TRIGGER quadro_mod
BEFORE UPDATE ON Quadro
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND OLD.codice <> NEW.codice THEN
	BEGIN
		IF disgiuntezza_opere(NEW.codice)=FALSE OR presenza_opera(OLD.codice) THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazione in Quadro
DROP TRIGGER IF EXISTS quadro_canc;
DELIMITER |
CREATE TRIGGER quadro_canc
BEFORE DELETE ON Quadro
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND presenza_opera(OLD.codice) THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;







-- Inserimento in Statua
DROP TRIGGER IF EXISTS statua_ins;
DELIMITER |
CREATE TRIGGER statua_ins
BEFORE INSERT ON Statua
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL THEN 
	BEGIN
		IF disgiuntezza_opere(NEW.codice)=FALSE THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in Statua
DROP TRIGGER IF EXISTS statua_mod;
DELIMITER |
CREATE TRIGGER statua_mod
BEFORE UPDATE ON Statua
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND OLD.codice <> NEW.codice THEN
	BEGIN
		IF disgiuntezza_opere(NEW.codice)=FALSE OR presenza_opera(OLD.codice) THEN
			CALL eccezione();
		END IF;
	END;
	END IF;
END;
|
DELIMITER ;


-- Cancellazione in Statua
DROP TRIGGER IF EXISTS statua_canc;
DELIMITER |
CREATE TRIGGER statua_canc
BEFORE DELETE ON Statua
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND presenza_opera(OLD.codice) THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;








/***************************************************

	VINCOLO DI GENERALIZZAZIONE DI STRUTTURA

****************************************************/

DROP VIEW IF EXISTS tutte_strutture;
CREATE VIEW tutte_strutture(codice)
AS
SELECT codice
FROM Museo_Ente
UNION ALL
SELECT codice
FROM CentroRestauro
;


-- Funzione che verifica le premesse per la disgiuntezza di
-- Museo_Ente e CentroRestauro
DROP FUNCTION IF EXISTS disgiuntezza_strutture;
DELIMITER |
CREATE FUNCTION disgiuntezza_strutture(codice CHAR(16)) RETURNS BOOL
BEGIN
	DECLARE num_str INTEGER;
	
	SELECT count(*)
	INTO num_str
	FROM tutte_strutture t
	WHERE t.codice = codice;
	
	IF num_ogg = 0 THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;
|
DELIMITER ;









-- Inserimento in Museo_Ente
DROP TRIGGER IF EXISTS museo_ins;
DELIMITER |
CREATE TRIGGER museo_ins
BEFORE INSERT ON Museo_Ente
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND disgiuntezza_strutture(NEW.codice)=FALSE THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in Museo_Ente
DROP TRIGGER IF EXISTS museo_mod;
DELIMITER |
CREATE TRIGGER museo_mod
BEFORE UPDATE ON Museo_Ente
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND OLD.codice <> NEW.codice AND disgiuntezza_strutture(NEW.codice)=FALSE THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;





-- Inserimento in CentroRestauro
DROP TRIGGER IF EXISTS centro_ins;
DELIMITER |
CREATE TRIGGER centro_ins
BEFORE INSERT ON CentroRestauro
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND disgiuntezza_strutture(NEW.codice)=FALSE THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;


-- Aggiornamento in CentroRestauro
DROP TRIGGER IF EXISTS centro_mod;
DELIMITER |
CREATE TRIGGER centro_mod
BEFORE UPDATE ON CentroRestauro
FOR EACH ROW
BEGIN
	IF @dis_trig IS NULL AND OLD.codice <> NEW.codice AND disgiuntezza_strutture(NEW.codice)=FALSE THEN
		CALL eccezione();
	END IF;
END;
|
DELIMITER ;
