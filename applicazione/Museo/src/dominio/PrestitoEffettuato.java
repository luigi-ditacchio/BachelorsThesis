package dominio;

import java.sql.Date;



import dominio.Museo_Ente.Museo_Ente;


public class PrestitoEffettuato {
	
	private Date dataInizio;
	private Date dataFine;
	
	private TipoLinkPermanentePrestito oggetto;
	private Museo_Ente museo;
	
	public static final int MIN_LINK_PERMANENTE_PRESTITO = 1;
	public static final int MIN_ENTE_PRESTITO = 1;
	
	
	public PrestitoEffettuato(Date dataInizio) {
		this.dataInizio = dataInizio;
		
	}
	
	//dataInizio
	public Date getDataInizio() {
		return dataInizio;
	}
	
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	//dataFine
	public Date getDataFine() {
		return dataFine;
	}
	
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	
	
	//PermanentePrestito
	public TipoLinkPermanentePrestito getLinkPermanentePrestito() throws EccezioneCardMin {
		if (oggetto == null) {
			throw new EccezioneCardMin("Violazione cardinalita' minima PermanentePrestito");
		}
		else {
			return oggetto;
		}
	}
	
	public void addLinkPermanentePrestito(TipoLinkPermanentePrestito link) {
		if (link != null && link.getPrestito() == this) {
			ManagerPermanentePrestito.add(link);
		}
	}
	
	public void removeLinkPermanentePrestito(TipoLinkPermanentePrestito link) {
		if (link != null && link.getPrestito() == this) {
			ManagerPermanentePrestito.remove(link);
		}
	}
	
	public void addForManagerPermanentePrestito(ManagerPermanentePrestito man) {
		if (man != null) {
			oggetto = man.getLink();
		}
	}
	
	public void removeForManagerPermanentePrestito(ManagerPermanentePrestito man) {
		if (man != null) {
			oggetto = null;
		}
	}
	
	
	
	//EntePrestito
	public Museo_Ente getEntePrestito() throws EccezioneCardMin {
		if (museo == null) {
			throw new EccezioneCardMin("Violazione cardinalita' minima EntePrestito");
		}
		else {
			return museo;
		}
	}
	
	public void addEntePrestito(Museo_Ente museo) {
		if (museo != null && this.museo == null) {
			this.museo = museo;
		}
	}
	
	public void removeEntePrestito() {
		museo = null;
	}
	
	
	
	
	
	public int quantiPermanentePrestito() {
		if (oggetto == null) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public int quantiEntePrestito() {
		if (museo == null) {
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
