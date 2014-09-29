package dominio.OggettoPermanente;

import java.util.HashSet;

import dominio.ManagerPermanentePrestito;
import dominio.ManagerPermanenteRestauro;
import dominio.TipoLinkPermanentePrestito;
import dominio.TipoLinkPermanenteRestauro;
import dominio.Oggetto.Oggetto;


public class OggettoPermanente extends Oggetto {
	
	protected String stato;
	
	protected HashSet<TipoLinkPermanentePrestito> prestito;
	protected HashSet<TipoLinkPermanenteRestauro> restauro;
	
	public OggettoPermanente(String codice) {
		super(codice);
		
		prestito = new HashSet<TipoLinkPermanentePrestito>();
		restauro = new HashSet<TipoLinkPermanenteRestauro>();
	}
	
	//stato
	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
	//PermanentePrestito
	@SuppressWarnings("unchecked")
	public HashSet<TipoLinkPermanentePrestito> getLinkPermanentePrestito() {
		return (HashSet<TipoLinkPermanentePrestito>)prestito.clone();
	}
	
	public void addLinkPermanentePrestito(TipoLinkPermanentePrestito link) {
		if (link != null && link.getOggetto() == this) {
			ManagerPermanentePrestito.add(link);
		}
	}
	
	public void removeLinkPermanentePrestito(TipoLinkPermanentePrestito link) {
		if (link != null && link.getOggetto() == this) {
			ManagerPermanentePrestito.remove(link);
		}
	}
	
	public void addForManagerPermanentePrestito(ManagerPermanentePrestito man) {
		if (man != null) {
			prestito.add(man.getLink());
		}
	}
	
	public void removeForManagerPermanentePrestito(ManagerPermanentePrestito man) {
		if (man != null) {
			prestito.remove(man.getLink());
		}
	}
	
	
	//PermanenteRestauro
	@SuppressWarnings("unchecked")
	public HashSet<TipoLinkPermanenteRestauro> getLinkPermanenteRestauro() {
		return (HashSet<TipoLinkPermanenteRestauro>)restauro.clone();
	}
	
	public void addLinkPermanenteRestauro(TipoLinkPermanenteRestauro link) {
		if (link != null && link.getOggetto() == this) {
			ManagerPermanenteRestauro.add(link);
		}
	}
	
	public void removeLinkPermanenteRestauro(TipoLinkPermanenteRestauro link) {
		if (link != null && link.getOggetto() == this) {
			ManagerPermanenteRestauro.remove(link);
		}
	}
	
	public void addForManagerPermanenteRestauro(ManagerPermanenteRestauro man) {
		if (man != null) {
			restauro.add(man.getLink());
		}
	}
	
	public void removeForManagerPermanenteRestauro(ManagerPermanenteRestauro man) {
		if (man != null) {
			restauro.remove(man.getLink());
		}
	}
	
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			OggettoPermanente op = (OggettoPermanente) o;
			return super.equals(op);
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	
	public String toString() {
		return super.toString() + "\n" +
				"STATO: " + stato + "\n";
	}

}
