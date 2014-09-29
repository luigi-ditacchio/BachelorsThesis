package dominio;

import java.util.HashSet;

import utility.EccezioneMuseo;

import DAO.MovimentoArtisticoDAO;

public class MovimentoArtistico {
	
	private final String nome;
	private String periodoStorico;
	private String descrizione;
	
	private HashSet<TipoLinkAdesione> artisti;
	
	public MovimentoArtistico(String nome) {
		this.nome = nome;
		
		this.artisti = new HashSet<TipoLinkAdesione>();
	}
	
	//nome
	public String getNome() {
		return nome;
	}
	
	//periodoStorico
	public String getPeriodo() {
		return periodoStorico;
	}
	
	public void setPeriodo(String periodoStorico) {
		this.periodoStorico = periodoStorico;
	}
	
	//descrizione
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	//Adesione
	@SuppressWarnings("unchecked")
	public HashSet<TipoLinkAdesione> getLinkAdesione() {
		return (HashSet<TipoLinkAdesione>)artisti.clone();
	}
	
	public void addLinkAdesione(TipoLinkAdesione link) {
		if (link != null && link.getMovimento() == this) {
			ManagerAdesione.add(link);
		}
	}
	
	public void removeLinkAdesione(TipoLinkAdesione link) {
		if (link != null && link.getMovimento() == this) {
			ManagerAdesione.remove(link);
		}
	}
	
	public void addForManagerAdesione(ManagerAdesione man) {
		if (man != null) {
			artisti.add(man.getLink());
		}
	}
	
	public void removeForManagerAdesione(ManagerAdesione man) {
		if (man != null) {
			artisti.remove(man.getLink());
		}
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			MovimentoArtistico ma = (MovimentoArtistico) o;
			return this.nome.equals(ma.nome);
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
				"PERIODO STORICO: " + periodoStorico + "\n" +
				"DESCRIZIONE: " + descrizione + "\n";
	}
	
	
	public void leggidaDB() throws EccezioneMuseo {
		MovimentoArtisticoDAO.load(this);
	}
	
	public void leggiAdesionedaDB() throws EccezioneMuseo {
		MovimentoArtisticoDAO.load_adesione(this);
	}
	

}
