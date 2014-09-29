package dominio;

import java.util.HashSet;

import utility.EccezioneMuseo;

import DAO.SezioneDAO;

public class Sezione {
	
	private final String nome;
	private String descrizione;
	private int numSale;
	
	private HashSet<TipoLinkAppartenenza> sale;
	
	public static final int MIN_LINK_APPARTENENZA = 1;
	
	public Sezione(String nome) {
		this.nome = nome;
		numSale = 0;
		sale = new HashSet<TipoLinkAppartenenza>();
	}
	
	//nome
	public String getNome() {
		return nome;
	}
	
	//descrizione
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	//numSale
	public int getNumSale() {
		return numSale;
	}
	
	public void setNumSale(int numSale) {
		this.numSale = numSale;
	}
	
	//Appartenenza
	@SuppressWarnings("unchecked")
	public HashSet<TipoLinkAppartenenza> getLinkAppartenenza() throws EccezioneCardMin {
		if (quantiAppartenenza() < MIN_LINK_APPARTENENZA) {
			throw new EccezioneCardMin("Violazione cardinalita' minima Appartenenza");
		}
		else {
			return (HashSet<TipoLinkAppartenenza>)sale.clone();
		}
	}
	
	public void addLinkAppartenenza(TipoLinkAppartenenza link) {
		if (link != null && link.getSezione() == this) {
			ManagerAppartenenza.add(link);
		}
	}
	
	public void removeLinkAppartenenza(TipoLinkAppartenenza link) {
		if (link != null && link.getSezione() == this) {
			ManagerAppartenenza.remove(link);
		}
	} 
	
	public void addForManagerAppartenenza(ManagerAppartenenza man) {
		if (man != null) {
			sale.add(man.getLink());
		}
	}
	
	public void removeForManagerAppartenenza(ManagerAppartenenza man) {
		if (man != null) {
			sale.remove(man.getLink());
		}
	}
	
	public int quantiAppartenenza() {
		return sale.size();
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			Sezione s = (Sezione) o;
			return this.nome.equals(s.nome);
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return nome.hashCode();
	}
	
	
	public String toString() {
		return "NOME: " + nome + "\n" +
				"DESCRIZIONE: " + descrizione + "\n" +
				"NUMERO SALE: " + numSale + "\n";
	}

	
	
	
	
	public void leggidaDB() throws EccezioneMuseo {
		SezioneDAO.load(this);
	}
	
	public void leggiAppartenenzadaDB() throws EccezioneMuseo {
		SezioneDAO.load_appartenenza(this);
	}
	

}
