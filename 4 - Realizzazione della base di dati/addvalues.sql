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
DELETE FROM Aderimento;
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







INSERT INTO Scavo('Scavo1', 'Luogo1');
INSERT INTO Scavo('Scavo2', 'Luogo2');

INSERT INTO Sala(1 ,0 ,'Sala molto ampia', 0);
INSERT INTO Sala(2 ,0 ,'Sala antica', 0);
INSERT INTO Sala(3 ,0 ,'Sala semivuota', 0);
INSERT INTO Sala(1 ,1 ,'Sala piena di opere', 0);
INSERT INTO Sala(3 ,1 ,'Sala normale', 0);

CALL inserisci_reperto_permanente('REP1', 'Reperto 1', 'Preistoria', 'Ottimo stato', '10', '10', '10',
									1, 1, 'DISPONIBILE', 'Scavo1', 'Luogo1');
CALL inserisci_reperto_permanente('REP2', 'Reperto 2', 'Preistoria', 'Ottimo stato', '10', '10', '10',
									1, 1, 'DISPONIBILE', 'Scavo1', 'Luogo1');
CALL inserisci_reperto_permanente('REP3', 'Reperto 3', 'Preistoria', 'Ottimo stato', '10', '10', '10',
									3, 1, 'DISPONIBILE', 'Scavo2', 'Luogo2');
									
CALL inserisci_fotografia_permanente('FOT1', 'Fotografia1', '1960', 'Ottimo stato', '10', '10', '0.1',
									1, 1, 'DISPONIBILE', 'B/N');
																		
CALL inserisci_fotografia_permanente('FOT2', 'Fotografia2', '1960', 'Ottimo stato', '10', '10', '0.1',
									1, 0, 'DISPONIBILE', 'B/N');
									




CALL inserisci_artista('DALI\'', 'Salvador', 'Dali\'', '2011-12-6', 'Roma', 'vita', 'stile');
CALL inserisci_artista('MERISI', 'Michelangelo', 'Merisi', '2011-12-6', 'Roma', 'vita', 'stile');
CALL inserisci_artista('DE CHIRICO', 'Giorgio', 'De Chirico', '2011-12-6', 'Roma', 'vita', 'stile');
CALL inserisci_artista('PICASSO', 'Pablo', 'Picasso', '2011-12-6', 'Roma', 'vita', 'stile');
