SET foreign_key_checks = 0;

DROP DATABASE IF EXISTS LaureaDB;
CREATE DATABASE LaureaDB;
USE LaureaDB;


DROP TABLE IF EXISTS OggettoGenerali;
CREATE TABLE OggettoGenerali (
	codice CHAR(16) PRIMARY KEY,
	nome VARCHAR(64) NOT NULL,
	anno_periodo VARCHAR(64) NOT NULL,
	descrizione VARCHAR(2048) NOT NULL
	
) ENGINE = INNODB;


DROP TABLE IF EXISTS OggettoParticolari;
CREATE TABLE OggettoParticolari (
	codice CHAR(16) PRIMARY KEY,
	lunghezza REAL NOT NULL,
	larghezza REAL NOT NULL,
	profondita REAL NOT NULL,
	provenienza VARCHAR(64) DEFAULT 'SCONOSCIUTA',
	curiosita VARCHAR(2048) DEFAULT 'NESSUNA'
) ENGINE = INNODB;


DROP TABLE IF EXISTS RepertoArcheologico;
CREATE TABLE RepertoArcheologico (
	codice CHAR(16) PRIMARY KEY
) ENGINE = INNODB;


DROP TABLE IF EXISTS Scavo;
CREATE TABLE Scavo (
	nome VARCHAR(64),
	luogo VARCHAR(64),
	PRIMARY KEY (nome, luogo)
) ENGINE = INNODB;


DROP TABLE IF EXISTS Ritrovamento;
CREATE TABLE Ritrovamento (
	codiceReperto CHAR(16) PRIMARY KEY,
	nomeScavo VARCHAR(64) NOT NULL,
	luogoScavo VARCHAR(64) NOT NULL,
	data DATE
) ENGINE = INNODB;


DROP TABLE IF EXISTS Fotografia;
CREATE TABLE Fotografia (
	codice CHAR(16) PRIMARY KEY,
	tipo ENUM('B/N','COLORI') NOT NULL
) ENGINE = INNODB;


DROP TABLE IF EXISTS OperaArte;
CREATE TABLE OperaArte (
	codice CHAR(16) PRIMARY KEY,
	codiceArtista CHAR(16) NOT NULL
) ENGINE = INNODB;


DROP TABLE IF EXISTS Quadro;
CREATE TABLE Quadro (
	codice CHAR(16) PRIMARY KEY,
	tecnica VARCHAR(64) DEFAULT 'SCONOSCIUTA'
) ENGINE = INNODB;


DROP TABLE IF EXISTS Statua;
CREATE TABLE Statua (
	codice CHAR(16) PRIMARY KEY,
	materiale VARCHAR(64)
) ENGINE = INNODB;


DROP TABLE IF EXISTS ArtistaGenerali;
CREATE TABLE ArtistaGenerali (
	codice CHAR(16) PRIMARY KEY,
	nome VARCHAR(64) NOT NULL,
	cognome VARCHAR(64) NOT NULL,
	nomeArte VARCHAR(64) DEFAULT 'NESSUNO',
	dataNascita DATE NOT NULL,
	dataMorte DATE
) ENGINE = INNODB;


DROP TABLE IF EXISTS ArtistaParticolari;
CREATE TABLE ArtistaParticolari (
	codice CHAR(16) PRIMARY KEY,
	luogoNascita VARCHAR(64) NOT NULL,
	luogoMorte VARCHAR(64),
	vita VARCHAR(2048) NOT NULL,
	stile VARCHAR(2048) NOT NULL
) ENGINE = INNODB;


DROP TABLE IF EXISTS MovimentoArtistico;
CREATE TABLE MovimentoArtistico (
	nome VARCHAR(64) PRIMARY KEY,
	periodoStorico VARCHAR(64) NOT NULL,
	descrizione VARCHAR(2048) NOT NULL
) ENGINE = INNODB;


DROP TABLE IF EXISTS Adesione;
CREATE TABLE Adesione (
	codiceArtista CHAR(16),
	movimentoArtistico VARCHAR(64),
	PRIMARY KEY (codiceArtista, movimentoArtistico)
) ENGINE = INNODB;


DROP TABLE IF EXISTS Sala;
CREATE TABLE Sala (
	numero INTEGER,
	piano INTEGER,
	descrizione VARCHAR(2048),
	numOggetti INTEGER NOT NULL,
	PRIMARY KEY (numero, piano)
) ENGINE = INNODB;


DROP TABLE IF EXISTS Collocazione;
CREATE TABLE Collocazione (
	codiceOggetto CHAR(16) PRIMARY KEY,
	numSala INTEGER NOT NULL,
	pianoSala INTEGER NOT NULL
) ENGINE = INNODB;


DROP TABLE IF EXISTS Sezione;
CREATE TABLE Sezione (
	nome VARCHAR(64) PRIMARY KEY,
	descrizione VARCHAR(2048) NOT NULL,
	numSale INTEGER NOT NULL
) ENGINE = INNODB;


DROP TABLE IF EXISTS Appartenenza;
CREATE TABLE Appartenenza (
	numSala INTEGER,
	pianoSala INTEGER,
	sezione VARCHAR(64),
	PRIMARY KEY (numSala, pianoSala, sezione)
) ENGINE = INNODB;


DROP TABLE IF EXISTS OggettoPermanente;
CREATE TABLE OggettoPermanente (
	codice CHAR(16) PRIMARY KEY,
	stato ENUM('PRESENTE','PRESTITO','RESTAURO') NOT NULL
) ENGINE = INNODB;


DROP TABLE IF EXISTS OggettoTemporaneo;
CREATE TABLE OggettoTemporaneo (
	codice CHAR(16) PRIMARY KEY
) ENGINE = INNODB;


DROP TABLE IF EXISTS Struttura;
CREATE TABLE Struttura (
	codice CHAR(16) PRIMARY KEY,
	nome VARCHAR(64) NOT NULL,
	citta VARCHAR(64) NOT NULL,
	via VARCHAR(64) NOT NULL,
	numero INTEGER NOT NULL,
	CAP CHAR(5) NOT NULL
) ENGINE = INNODB;


DROP TABLE IF EXISTS TelefonoStruttura;
CREATE TABLE TelefonoStruttura (
	telefono VARCHAR(16) PRIMARY KEY,
	codiceStruttura CHAR(16) NOT NULL
) ENGINE = INNODB;


DROP TABLE IF EXISTS Museo_Ente;
CREATE TABLE Museo_Ente (
	codice CHAR(16) PRIMARY KEY
) ENGINE = INNODB;


DROP TABLE IF EXISTS CentroRestauro;
CREATE TABLE CentroRestauro (
	codice CHAR(16) PRIMARY KEY
) ENGINE = INNODB;


DROP TABLE IF EXISTS PrestitoRicevuto;
CREATE TABLE PrestitoRicevuto (
	codiceOggetto CHAR(16) PRIMARY KEY,
	dataInizio DATE NOT NULL,
	dataFine DATE NOT NULL,
	codiceMuseo CHAR(16) NOT NULL
) ENGINE = INNODB;


DROP TABLE IF EXISTS PrestitoEffettuato;
CREATE TABLE PrestitoEffettuato (
	codiceOggetto CHAR(16),
	dataInizio DATE,
	dataFine DATE NOT NULL,
	codiceMuseo CHAR(16) NOT NULL,
	PRIMARY KEY (codiceOggetto, dataInizio)
) ENGINE = INNODB;


DROP TABLE IF EXISTS Restauro;
CREATE TABLE Restauro (
	codiceOggetto CHAR(16),
	dataInizio DATE,
	dataFine DATE NOT NULL,
	codiceCentro CHAR(16) NOT NULL,
	PRIMARY KEY (codiceOggetto, dataInizio)
) ENGINE = INNODB;


SET foreign_key_checks = 1;
