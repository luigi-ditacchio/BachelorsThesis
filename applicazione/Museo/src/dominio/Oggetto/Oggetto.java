package dominio.Oggetto;

import utility.EccezioneMuseo;
import DAO.OggettoDAO;
import dominio.*;

public abstract class Oggetto {
	
	protected final String codice;
	protected String nome;
	protected String descrizione;
	protected float lunghezza;
	protected float larghezza;
	protected float profondita;
	protected String anno_periodo;
	protected String provenienza;
	protected String curiosita;
	protected TipoLinkCollocazione sala;
	
	
	public static final int MIN_LINK_COLLOCAZIONE = 1;
	
	public Oggetto(String codice) {
		this.codice = codice;
		
	}
	
	//codice
	public String getCodice() {
		return codice;
	}
	
	//nome
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//descrizione
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	//lunghezza
	public float getLunghezza() {
		return lunghezza;
	}
	
	public void setLunghezza(float lunghezza) {
		this.lunghezza = lunghezza;
	}
	
	//larghezza
	public float getLarghezza() {
		return larghezza;
	}
	
	public void setLarghezza(float larghezza) {
		this.larghezza = larghezza;
	}
	
	//profondita
	public float getProfondita() {
		return profondita;
	}
	
	public void setProfondita(float profondita) {
		this.profondita = profondita;
	}
	
	//anno_periodo
	public String getAnnoPeriodo() {
		return anno_periodo;
	}
	
	public void setAnnoPeriodo(String anno_periodo) {
		this.anno_periodo = anno_periodo;
	}
	
	//provenienza
	public String getProvenienza() {
		return provenienza;
	}
	
	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}
	
	//curiosita
	public String getCuriosita() {
		return curiosita;
	}
	
	public void setCuriosita(String curiosita) {
		this.curiosita = curiosita;
	}
	
	//Collocazione
	public TipoLinkCollocazione getLinkCollocazione() throws EccezioneCardMin {
		if (sala == null) {
			throw new EccezioneCardMin("Violazione cardinalita' minima Collocazione");
		}
		else {
			return sala;
		}
	}
	
	public void addLinkCollocazione(TipoLinkCollocazione link) {
		if (link != null && link.getOggetto() == this) {
			ManagerCollocazione.add(link);
		}
	}
	
	public void removeLinkCollocazione(TipoLinkCollocazione link) {
		if (link != null && link.getOggetto() == this) {
			ManagerCollocazione.remove(link);
		}
	}
	
	public void addForManagerCollocazione(ManagerCollocazione man) {
		if (man != null) {
			sala = man.getLink();
		}
	}
	
	public void removeForManagerCollocazione(ManagerCollocazione man) {
		if (man != null) {
			sala = null;
		}
	}
	
	public int quantiCollocazione() {
		if (sala == null) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			Oggetto s = (Oggetto) o;
			return this.codice.equals(s.codice);
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return codice.hashCode();
	}
	
	
	public String toStringGenerali() {
		return "CODICE: " + codice + "\n" +
				"NOME: " + nome + "\n" +
				"ANNO/PERIODO: " + anno_periodo + "\n" +
				"DESCRIZIONE: " + descrizione;
	}
	
	public String toStringParticolari() {
		return "CODICE: " + codice + "\n" +
				"LUNGHEZZA: " + lunghezza + "\n" +
				"LARGHEZZA: " + larghezza + "\n" +
				"PROFONDITA: " + profondita + "\n" +
				"PROVENIENZA: " + provenienza + "\n" +
				"CURIOSITA': " + curiosita;
	}
	
	
	
	public void leggiGeneralidaDB() throws EccezioneMuseo {
		OggettoDAO.load_generali(this);
	}
	
	public void leggiParticolaridaDB() throws EccezioneMuseo {
		OggettoDAO.load_particolari(this);
	}
	
	public void leggiCollocazionedaDB() throws EccezioneMuseo {
		OggettoDAO.load_collocazione(this);
	}

}
