ALTER TABLE OggettoGenerali
ADD CONSTRAINT Foreign_OggettoGen_Collocazione
FOREIGN KEY (codice) REFERENCES Collocazione(codiceOggetto);



ALTER TABLE OggettoGenerali
ADD CONSTRAINT Foreign_OggettoGen_OggettoPar
FOREIGN KEY (codice) REFERENCES OggettoParticolari(codice);


ALTER TABLE OggettoParticolari
ADD CONSTRAINT Foreign_OggettoPar_OggettoGen
FOREIGN KEY (codice) REFERENCES OggettoGenerali(codice);


ALTER TABLE RepertoArcheologico
ADD CONSTRAINT Foreign_Reperto_OggettoGen
FOREIGN KEY (codice) REFERENCES OggettoGenerali(codice);


ALTER TABLE RepertoArcheologico
ADD CONSTRAINT Foreign_Reperto_Ritrovamento
FOREIGN KEY (codice) REFERENCES Ritrovamento(codiceReperto);



ALTER TABLE Ritrovamento
ADD CONSTRAINT Foreign_Ritrovamento_Reperto
FOREIGN KEY (codiceReperto) REFERENCES RepertoArcheologico(codice);



ALTER TABLE Ritrovamento
ADD CONSTRAINT Foreign_Ritrovamento_Scavo
FOREIGN KEY (nomeScavo, luogoScavo) REFERENCES Scavo(nome, luogo);



ALTER TABLE Fotografia
ADD CONSTRAINT Foreign_Fotografia_OggettoGen
FOREIGN KEY (codice) REFERENCES OggettoGenerali(codice);



ALTER TABLE OperaArte
ADD CONSTRAINT Foreign_Opera_OggettoGen
FOREIGN KEY (codice) REFERENCES OggettoGenerali(codice);



ALTER TABLE OperaArte
ADD CONSTRAINT Foreign_Opera_ArtistaGen
FOREIGN KEY (codiceArtista) REFERENCES ArtistaGenerali(codice);



ALTER TABLE Quadro
ADD CONSTRAINT Foreign_Quadro_Opera
FOREIGN KEY (codice) REFERENCES OperaArte(codice);



ALTER TABLE Statua
ADD CONSTRAINT Foreign_Statua_Opera
FOREIGN KEY (codice) REFERENCES OperaArte(codice);



ALTER TABLE ArtistaGenerali
ADD CONSTRAINT Foreign_ArtistaGen_ArtistaPar
FOREIGN KEY (codice) REFERENCES ArtistaParticolari(codice);



ALTER TABLE ArtistaParticolari
ADD CONSTRAINT Foreign_ArtistaPar_ArtistaGen
FOREIGN KEY (codice) REFERENCES ArtistaGenerali(codice);



ALTER TABLE Adesione
ADD CONSTRAINT Foreign_Adesione_ArtistaGen
FOREIGN KEY (codiceArtista) REFERENCES ArtistaGenerali(codice);



ALTER TABLE Adesione
ADD CONSTRAINT Foreign_Adesione_Movimento
FOREIGN KEY (movimentoArtistico) REFERENCES MovimentoArtistico(nome);



ALTER TABLE Collocazione
ADD CONSTRAINT Foreign_Collocazione_OggettoGen
FOREIGN KEY (codiceOggetto) REFERENCES OggettoGenerali(codice);



ALTER TABLE Collocazione
ADD CONSTRAINT Foreign_Collocazione_Sala
FOREIGN KEY (numSala, pianoSala) REFERENCES Sala(numero, piano);



ALTER TABLE Appartenenza
ADD CONSTRAINT Foreign_Appartenenza_Sala
FOREIGN KEY (numSala, pianoSala) REFERENCES Sala(numero, piano);



ALTER TABLE Appartenenza
ADD CONSTRAINT Foreign_Appartenenza_Sezione
FOREIGN KEY (sezione) REFERENCES Sezione(nome);



ALTER TABLE OggettoPermanente
ADD CONSTRAINT Foreign_OggettoPerm_OggettoGen
FOREIGN KEY (codice) REFERENCES OggettoGenerali(codice);



ALTER TABLE OggettoTemporaneo
ADD CONSTRAINT Foreign_OggettoTemp_OggettoGen
FOREIGN KEY (codice) REFERENCES OggettoGenerali(codice);



ALTER TABLE OggettoTemporaneo
ADD CONSTRAINT Foreign_OggettoTemp_PrestitoRic
FOREIGN KEY (codice) REFERENCES PrestitoRicevuto(codiceOggetto);



ALTER TABLE TelefonoStruttura
ADD CONSTRAINT Foreign_Telefono_Struttura
FOREIGN KEY (codiceStruttura) REFERENCES Struttura(codice);



ALTER TABLE Museo_Ente
ADD CONSTRAINT Foreign_Museo_Struttura
FOREIGN KEY (codice) REFERENCES Struttura(codice);



ALTER TABLE CentroRestauro
ADD CONSTRAINT Foreign_Centro_Struttura
FOREIGN KEY (codice) REFERENCES Struttura(codice);



ALTER TABLE PrestitoRicevuto
ADD CONSTRAINT Foreign_PrestitoRic_OggettoTemp
FOREIGN KEY (codiceOggetto) REFERENCES OggettoTemporaneo(codice);



ALTER TABLE PrestitoRicevuto
ADD CONSTRAINT Foreign_PrestitoRic_Museo
FOREIGN KEY (codiceMuseo) REFERENCES Museo_Ente(codice);



ALTER TABLE PrestitoEffettuato
ADD CONSTRAINT Foreign_PrestitoEff_OggettoPerm
FOREIGN KEY (codiceOggetto) REFERENCES OggettoPermanente(codice);



ALTER TABLE PrestitoEffettuato
ADD CONSTRAINT Foreign_PrestitoEff_Museo
FOREIGN KEY (codiceMuseo) REFERENCES Museo_Ente(codice);



ALTER TABLE Restauro
ADD CONSTRAINT Foreign_Restauro_OggettoPerm
FOREIGN KEY (codiceOggetto) REFERENCES OggettoPermanente(codice);



ALTER TABLE Restauro
ADD CONSTRAINT Foreign_Restauro_Centro
FOREIGN KEY (codiceCentro) REFERENCES CentroRestauro(codice);
