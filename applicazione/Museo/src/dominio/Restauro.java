package dominio;

import java.sql.Date;



import dominio.CentroRestauro.CentroRestauro;


public class Restauro {
	
	private Date dataInizio;
	private Date dataFine;
	
	private TipoLinkPermanenteRestauro oggetto;
	private CentroRestauro centro_restauro;
	
	public static final int MIN_LINK_PERMANENTE_RESTAURO = 1;
	public static final int MIN_LOCAZIONE_RESTAURO = 1;
	
	
	public Restauro(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	//dataInizio
	public Date getDataInizio() {
		return dataInizio;
	}
	
	
	//dataFine
	public Date getDataFine() {
		return dataFine;
	}
	
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	
	
	//PermanenteRestauro
	public TipoLinkPermanenteRestauro getLinkPermanenteRestauro() throws EccezioneCardMin {
		if (oggetto == null) {
			throw new EccezioneCardMin("Violazione cardinalita' minima PermanenteRestauro");
		}
		else {
			return oggetto;
		}
	}
	
	public void addLinkPermanenteRestauro(TipoLinkPermanenteRestauro link) {
		if (link != null && link.getRestauro() == this) {
			ManagerPermanenteRestauro.add(link);
		}
	}
	
	public void removeLinkPermanenteRestauro(TipoLinkPermanenteRestauro link) {
		if (link != null && link.getRestauro() == this) {
			ManagerPermanenteRestauro.remove(link);
		}
	}
	
	public void addForManagerPermanenteRestauro(ManagerPermanenteRestauro man) {
		if (man != null) {
			oggetto = man.getLink();
		}
	}
	
	public void removeForManagerPermanenteRestauro(ManagerPermanenteRestauro man) {
		if (man != null) {
			oggetto = null;
		}
	}
	
	
	
	//EntePrestito
	public CentroRestauro getLocazioneRestauro() throws EccezioneCardMin {
		if (centro_restauro == null) {
			throw new EccezioneCardMin("Violazione cardinalita' minima LocazioneRestauro");
		}
		else {
			return centro_restauro;
		}
	}
	
	public void addLocazioneRestauro(CentroRestauro centro_restauro) {
		if (centro_restauro != null && this.centro_restauro == null) {
			this.centro_restauro = centro_restauro;
		}
	}
	
	public void removeLocazioneRestauro() {
		centro_restauro = null;
	}
	
	
	
	
	
	public int quantiPermanenteRestauro() {
		if (oggetto == null) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public int quantiLocazioneRestauro() {
		if (centro_restauro == null) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	
	
	public String toString() {
		return "DATA INIZIO: " + dataInizio + "\n" +
				"DATA FINE: " + dataFine + "\n";
	}

}
