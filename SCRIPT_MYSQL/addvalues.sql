SET foreign_key_checks = 0;
SET @dis_trig = 1;

DELETE FROM OggettoGenerali;
DELETE FROM OggettoParticolari;
DELETE FROM RepertoArcheologico;
DELETE FROM Scavo;
DELETE FROM Ritrovamento;
DELETE FROM Fotografia;
DELETE FROM OperaArte;
DELETE FROM Quadro;
DELETE FROM Statua;
DELETE FROM ArtistaGenerali;
DELETE FROM ArtistaParticolari;
DELETE FROM MovimentoArtistico;
DELETE FROM Adesione;
DELETE FROM Sala;
DELETE FROM Collocazione;
DELETE FROM Sezione;
DELETE FROM Appartenenza;
DELETE FROM OggettoPermanente;
DELETE FROM OggettoTemporaneo;
DELETE FROM Struttura;
DELETE FROM TelefonoStruttura;
DELETE FROM Museo_Ente;
DELETE FROM CentroRestauro;
DELETE FROM PrestitoRicevuto;
DELETE FROM PrestitoEffettuato;
DELETE FROM Restauro;

SET foreign_key_checks = 1;
SET @dis_trig = NULL;


INSERT INTO MovimentoArtistico VALUES ('Impressionismo', 'Seconda meta\' Ottocento',
										'L\'Impressionismo e\' un movimento artistico nato in Francia...');
								
INSERT INTO MovimentoArtistico VALUES ('Cubismo', 'Prima meta\' del Novecento',
										'Cubismo e\' un\'espressione con cui si e\' soliti designare una corrente artistica...');
								

CALL inserisci_artista('PICASSO', 'Pablo', 'Picasso', '1881-10-25', 'Malaga',
						'Pablo Picasso nacque nel 1881 a Malaga, in Spagna,...',
						'Il lavoro di Picasso e\' spesso categorizzato in periodi.');
						
CALL inserisci_artista('MONET', 'Claude', 'Monet', '1840-11-14', 'Parigi',
						'Claude Monet nacque a Parigi, in rue Laffitte 45-47...', 
						'Monet non amava e non s\'interessava ai classici esempi della pittura, tanto da non entrare quasi mai al Louvre');
						
call inserisci_artista('DEGAS', 'Edgar', 'Degas', '1834-07-19', 'Parigi',
						'Figlio del ricco banchiere di nobile famiglia Auguste De Gas...', 
						'Durante i primi vent\'anni della sua carriera, Degas sperimenta tutti i generi.');
						
						
INSERT INTO Adesione VALUES ('PICASSO', 'Cubismo');
INSERT INTO Adesione VALUES ('MONET', 'Impressionismo');
INSERT INTO Adesione VALUES ('DEGAS', 'Impressionismo');



INSERT INTO Sala VALUES (1 ,0 ,'Sala numero 1', 0);
INSERT INTO Sala VALUES (2 ,0 ,'Sala numero 2', 0);
INSERT INTO Sala VALUES (3 ,0 ,'Sala numero 3', 0);
INSERT INTO Sala VALUES (101 ,1 ,'Sala numero 101', 0);
INSERT INTO Sala VALUES (103 ,1 ,'Sala numero 103', 0);

INSERT INTO Scavo VALUES ('SCAVO DELFI', 'DELFI');
INSERT INTO Scavo VALUES ('SCAVO POMPEI', 'POMPEI');



CALL inserisci_quadro_permanente('BALLERINE', 'Ballerine', '1884', 'La peculiarita\' del dipinto e\' il momento della rappresentazione scelto da Degas.',
								64.8, 50.8, 0.1,
								'PRESENTE',
								1, 0,
								'DEGAS');
									
CALL inserisci_reperto_permanente('VASO1', 'Vaso greco', 'IV secolo a.C.', 'Vaso raffigurante diverse divinita\' greche',
									30.2, 30.2, 44.4,
									'PRESENTE',
									1, 1, 'SCAVO DELFI', 'DELFI');
									
CALL inserisci_fotografia_permanente('FOTO1', 'Paesaggio', '1970', 'Foto di un paesaggio', 15.0, 20.0, 0.1,
										'B/N', 'PRESENTE', 3, 0);


