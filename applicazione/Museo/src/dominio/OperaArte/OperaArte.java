package dominio.OperaArte;


import utility.EccezioneMuseo;
import DAO.OperaArteDAO;
import dominio.Oggetto.Oggetto;
import dominio.*;

public abstract class OperaArte extends Oggetto {
	
	protected TipoLinkRealizzazione artista;
	
	public static final int MIN_LINK_REALIZZAZIONE = 1;
	
	public OperaArte(String codice) {
		super(codice);
	}
	
	//Realizzazione
	public TipoLinkRealizzazione getLinkRealizzazione() throws EccezioneCardMin {
		if (artista == null) {
			throw new EccezioneCardMin("Violazione cardinalita' minima Realizzazione");
		}
		else {
			return artista;
		}
	}
	
	public void addLinkRealizzazione(TipoLinkRealizzazione link) {
		if (link != null && link.getOpera() == this) {
			ManagerRealizzazione.add(link);
		}
	}
	
	public void removeLinkRealizzazione(TipoLinkRealizzazione link) {
		if (link != null && link.getOpera() == this) {
			ManagerRealizzazione.remove(link);
		}
	}
	
	public void addForManagerRealizzazione(ManagerRealizzazione man) {
		if (man != null) {
			artista = man.getLink();
		}
	}
	
	public void removeForManagerRealizzazione(ManagerRealizzazione man) {
		if (man != null) {
			artista = null;
		}
	}
	
	public int quantiRealizzazione() {
		if (artista == null) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			OperaArte oa = (OperaArte) o;
			return super.equals(oa);
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
		OperaArteDAO.load(this);
	}

}
