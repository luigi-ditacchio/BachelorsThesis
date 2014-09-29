package dominio;


import dominio.OggettoPermanente.OggettoPermanente;


public class TipoLinkPermanentePrestito {
	
	private final OggettoPermanente oggetto;
	private final PrestitoEffettuato prestito;
	
	public TipoLinkPermanentePrestito(OggettoPermanente oggetto, PrestitoEffettuato prestito) throws EccezionePrecondizioni {
		if (oggetto == null || prestito == null) {
			throw new EccezionePrecondizioni("OggettoPermanente e PrestitoEffettuato vanno inizializzati");
		}
		else {
			this.oggetto = oggetto;
			this.prestito = prestito;
		}
	}
	
	public OggettoPermanente getOggetto() {
		return oggetto;
	}
	
	public PrestitoEffettuato getPrestito() {
		return prestito;
	}
	
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			TipoLinkPermanentePrestito link = (TipoLinkPermanentePrestito) o;
			return this.oggetto.equals(link.getOggetto()) && this.prestito.equals(link.getPrestito());
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return oggetto.hashCode() + prestito.hashCode();
	}

}
