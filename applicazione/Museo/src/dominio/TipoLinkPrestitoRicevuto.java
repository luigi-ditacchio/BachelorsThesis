package dominio;

import java.sql.Date;

import dominio.Museo_Ente.Museo_Ente;
import dominio.OggettoTemporaneo.OggettoTemporaneo;

public class TipoLinkPrestitoRicevuto {
	
	private final OggettoTemporaneo oggetto;
	private final Museo_Ente museo;
	private final Date dataInizio;
	private final Date dataFine;
	
	public TipoLinkPrestitoRicevuto(OggettoTemporaneo oggetto, Museo_Ente museo, Date dataInizio, Date dataFine) throws EccezionePrecondizioni {
		if (oggetto == null || museo == null) {
			throw new EccezionePrecondizioni("Oggetto e Museo_Ente vanno inizializzati");
		}
		else {
			this.oggetto = oggetto;
			this.museo = museo;
			this.dataInizio = dataInizio;
			this.dataFine = dataFine;
		}
	}
	
	public OggettoTemporaneo getOggetto() {
		return oggetto;
	}
	
	public Museo_Ente getMuseo() {
		return museo;
	}
	
	public Date getDataInizio() {
		return dataInizio;
	}
	
	public Date getDataFine() {
		return dataFine;
	}
	
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			TipoLinkPrestitoRicevuto link = (TipoLinkPrestitoRicevuto) o;
			return this.oggetto.equals(link.getOggetto()) && this.museo.equals(link.getMuseo());
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return oggetto.hashCode() + museo.hashCode();
	}

}
