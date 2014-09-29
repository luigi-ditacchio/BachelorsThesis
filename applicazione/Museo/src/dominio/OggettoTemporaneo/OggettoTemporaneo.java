package dominio.OggettoTemporaneo;

import dominio.EccezioneCardMin;
import dominio.TipoLinkPrestitoRicevuto;
import dominio.Oggetto.Oggetto;

public class OggettoTemporaneo extends Oggetto {
	
	protected TipoLinkPrestitoRicevuto prestito;
	
	public static final int MIN_LINK_PRESTITO_RICEVUTO = 1;
	
	public OggettoTemporaneo(String codice) {
		super(codice);
	}
	
	//PrestitoRicevuto
	public TipoLinkPrestitoRicevuto getLinkPrestitoRicevuto() throws EccezioneCardMin {
		if (prestito == null) {
			throw new EccezioneCardMin("Violazione cardinalita' minima prestito ricevuto");
		}
		else {
			return prestito;
		}
	}
	
	public void addLinkPrestitoRicevuto(TipoLinkPrestitoRicevuto link) {
		if (link != null && link.getOggetto() == this && prestito == null) {
			prestito = link;
		}
	}
	
	public void removeLinkPrestitoRicevuto() {
		prestito = null;
	}
	
	
	public int quantiPrestitoRicevuto() {
		if (prestito == null) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			OggettoTemporaneo ot = (OggettoTemporaneo) o;
			return super.equals(ot);
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	
	public String toString() {
		return super.toString();
	}
	
	

}
