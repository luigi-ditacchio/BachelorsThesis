package dominio;

import java.util.HashSet;

import utility.EccezioneMuseo;

import DAO.SalaDAO;

public class Sala {

	private final int numero;
	private final int piano;
	private String descrizione;
	private int numOggetti;
	
	private HashSet<TipoLinkCollocazione> oggetti;
	private HashSet<TipoLinkAppartenenza> sezioni;
	
	public Sala(int numero, int piano) {
		this.numero = numero;
		this.piano = piano;
		oggetti = new HashSet<TipoLinkCollocazione>();
		sezioni = new HashSet<TipoLinkAppartenenza>();
	}
	
	//nome
	public int getNumero() {
		return numero;
	}
	
	//piano
	public int getPiano() {
		return piano;
	}
	
	//descrizione
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	//numOggetti
	public int getNumOggetti() {
		return numOggetti;
	}
	
	public void setNumOggetti(int numOggetti) {
		this.numOggetti = numOggetti;
	}
	
	//Collocazione
	@SuppressWarnings("unchecked")
	public HashSet<TipoLinkCollocazione> getLinkCollocazione() {
		return (HashSet<TipoLinkCollocazione>)oggetti.clone();
	}
	
	public void addLinkCollocazione(TipoLinkCollocazione link) {
		if (link != null && link.getSala() == this) {
			ManagerCollocazione.add(link);
		}
	}
	
	public void removeLinkCollocazione(TipoLinkCollocazione link) {
		if (link != null && link.getSala() == this) {
			ManagerCollocazione.remove(link);
		}
	}
	
	public void addForManagerCollocazione(ManagerCollocazione man) {
		if (man != null) {
			oggetti.add(man.getLink());
		}
	}
	
	public void removeForManagerCollocazione(ManagerCollocazione man) {
		if (man != null) {
			oggetti.remove(man.getLink());
		}
	}
	
	//Appartenenza
	@SuppressWarnings("unchecked")
	public HashSet<TipoLinkAppartenenza> getLinkAppartenenza() {
		return (HashSet<TipoLinkAppartenenza>)sezioni.clone();
	}
	
	public void addLinkAppartenenza(TipoLinkAppartenenza link) {
		if (link != null && link.getSala() == this) {
			ManagerAppartenenza.add(link);
		}
	}
	
	public void removeLinkAppartenenza(TipoLinkAppartenenza link) {
		if (link != null && link.getSala() == this) {
			ManagerAppartenenza.remove(link);
		}
	}
	
	public void addForManagerAppartenenza(ManagerAppartenenza man) {
		if (man != null) {
			sezioni.add(man.getLink());
		}
	}
	
	public void removeForManagerAppartenenza(ManagerAppartenenza man) {
		if (man != null) {
			sezioni.remove(man.getLink());
		}
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			Sala s = (Sala) o;
			return this.numero == s.numero && this.piano == s.piano;
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return new Integer(numero).hashCode() + new Integer(piano).hashCode();
	}
	
	
	
	public String toString() {
		return "NUMERO: " + numero + "\n" +
				"PIANO: " + piano + "\n" +
				"DESCRIZIONE: " + descrizione + "\n" +
				"NUMERO OGGETTI: " + numOggetti;
	}
	
	
	
	
	
	public void leggidaDB() throws EccezioneMuseo {
		SalaDAO.load(this);
	}
	
	public void leggiAppartenenzadaDB() throws EccezioneMuseo {
		SalaDAO.load_appartenenza(this);
	}
	
	public void leggiCollocazionedaDB() throws EccezioneMuseo {
		SalaDAO.load_collocazione(this);
	}
	
}
