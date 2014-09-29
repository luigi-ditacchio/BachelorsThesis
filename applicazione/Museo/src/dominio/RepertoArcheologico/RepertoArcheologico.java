package dominio.RepertoArcheologico;

import utility.EccezioneMuseo;
import DAO.RepertoArcheologicoDAO;
import dominio.*;
import dominio.Oggetto.Oggetto;

public class RepertoArcheologico extends Oggetto {
	
	protected TipoLinkRitrovamento scavo;
	
	public static final int MIN_LINK_RITROVAMENTO = 1;
	
	public RepertoArcheologico(String codice) {
		super(codice);
	}
	
	//Ritrovamento
	public TipoLinkRitrovamento getLinkRitrovamento() throws EccezioneCardMin {
		if (scavo == null) {
			throw new EccezioneCardMin("Violazione cardinalita' minima ritrovamento");
		}
		else {
			return scavo;
		}
	}
	
	public void addLinkRitrovamento(TipoLinkRitrovamento link) {
		if (link != null && link.getReperto() == this && scavo == null) {
			scavo = link;
		}
	}
	
	public void removeLinkRitrovamento() {
		scavo = null;
	}
	
	
	public int quantiRitrovamento() {
		if (scavo == null) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			RepertoArcheologico s = (RepertoArcheologico) o;
			return super.equals(s);
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public String toStringGenerali() {
		return super.toStringGenerali();
	}
	
	public String toStringParticolari() {
		return super.toStringParticolari();
	}
	
	
	
	public void leggidaDB() throws EccezioneMuseo {
		RepertoArcheologicoDAO.load(this);
	}
	
	public void leggiRitrovamentodaDB() throws EccezioneMuseo {
		RepertoArcheologicoDAO.load_ritrovamento(this);
	}
	
	

}
