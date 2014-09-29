package dominio;

import dominio.Oggetto.Oggetto;

public class TipoLinkCollocazione {
	
	private final Oggetto oggetto;
	private final Sala sala;
	
	public TipoLinkCollocazione(Oggetto oggetto, Sala sala) throws EccezionePrecondizioni {
		if (oggetto == null || sala == null) {
			throw new EccezionePrecondizioni("Oggetto e Sala vanno inizializzati");
		}
		else {
			this.oggetto = oggetto;
			this.sala = sala;
		}
	}
	
	public Oggetto getOggetto() {
		return oggetto;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			TipoLinkCollocazione link = (TipoLinkCollocazione) o;
			return this.oggetto.equals(link.getOggetto()) && this.sala.equals(link.getSala());
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return oggetto.hashCode() + sala.hashCode();
	}
	

}
