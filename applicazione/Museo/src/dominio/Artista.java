package dominio;

import java.sql.Date;
import java.util.HashSet;

import utility.EccezioneMuseo;

import DAO.ArtistaDAO;

public class Artista {
	
	private final String codice;
	private String nome;
	private String cognome;
	private String nomeArte;
	private Date dataNascita;
	private Date dataMorte;
	private String luogoNascita;
	private String luogoMorte;
	private String vita;
	private String stile;
	
	private HashSet<TipoLinkRealizzazione> opere;
	private HashSet<TipoLinkAdesione> movimenti;
	
	public Artista(String codice) {
		this.codice = codice;
		
		this.opere = new HashSet<TipoLinkRealizzazione>();
		this.movimenti = new HashSet<TipoLinkAdesione>();
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
	
	//cognome
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	//nomeArte
	public String getNomeArte() {
		return nomeArte;
	}
	
	public void setNomeArte(String nomeArte) {
		this.nomeArte = nomeArte;
	}
	
	//dataNascita
	public Date getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	//dataMorte
	public Date getDataMorte() {
		return dataMorte;
	}
	
	public void setDataMorte(Date dataMorte) {
		this.dataMorte = dataMorte;
	}
	
	//luogoNascita
	public String getLuogoNascita() {
		return luogoNascita;
	}
	
	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}
	
	//luogoMorte
	public String getLuogoMorte() {
		return luogoMorte;
	}
	
	public void setLuogoMorte(String luogoMorte) {
		this.luogoMorte = luogoMorte;
	}
	
	//vita
	public String getVita() {
		return vita;
	}
	
	public void setVita(String vita) {
		this.vita = vita;
	}
	
	//stile
	public String getStile() {
		return stile;
	}
	
	public void setStile(String stile) {
		this.stile = stile;
	}
	
	//Realizzazione
	@SuppressWarnings("unchecked")
	public HashSet<TipoLinkRealizzazione> getLinkRealizzazione() {
		return (HashSet<TipoLinkRealizzazione>)opere.clone();
	}
	
	public void addLinkRealizzazione(TipoLinkRealizzazione link) {
		if (link != null && link.getArtista() == this) {
			ManagerRealizzazione.add(link);
		}
	}
	
	public void removeLinkRealizzazione(TipoLinkRealizzazione link) {
		if (link != null && link.getArtista() == this) {
			ManagerRealizzazione.remove(link);
		}
	}
	
	public void addForManagerRealizzazione(ManagerRealizzazione man) {
		if (man != null) {
			opere.add(man.getLink());
		}
	}
	
	public void removeForManagerRealizzazione(ManagerRealizzazione man) {
		if (man != null) {
			opere.remove(man.getLink());
		}
	}
	
	
	//Adesione
	@SuppressWarnings("unchecked")
	public HashSet<TipoLinkAdesione> getLinkAdesione() {
		return (HashSet<TipoLinkAdesione>)movimenti.clone();
	}
	
	public void addLinkAdesione(TipoLinkAdesione link) {
		if (link != null && link.getArtista() == this) {
			ManagerAdesione.add(link);
		}
	}
	
	public void removeLinkAdesione(TipoLinkAdesione link) {
		if (link != null && link.getArtista() == this) {
			ManagerAdesione.remove(link);
		}
	}
	
	public void addForManagerAdesione(ManagerAdesione man) {
		if (man != null) {
			movimenti.add(man.getLink());
		}
	}
	
	public void removeForManagerAdesione(ManagerAdesione man) {
		if (man != null) {
			movimenti.remove(man.getLink());
		}
	}
	
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			Artista a = (Artista) o;
			return this.codice.equals(a.codice);
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
				"COGNOME: " + cognome + "\n" +
				"NOME D'ARTE: " + nomeArte + "\n" +
				"DATA NASCITA: " + dataNascita + "\n" +
				"DATA MORTE: " + dataMorte + "\n";
	}
	
	public String toStringParticolari() {
		return "CODICE: " + codice + "\n" +
				"LUOGO NASCITA: " + luogoNascita + "\n" +
				"LUOGO MORTE: " + luogoMorte + "\n" +
				"VITA: " + vita + "\n" +
				"STILE: " + stile + "\n";
	}
	
	
	
	
	public void leggiGeneralidaDB() throws EccezioneMuseo {
		ArtistaDAO.load_generali(this);
	}
	
	public void leggiParticolaridaDB() throws EccezioneMuseo {
		ArtistaDAO.load_particolari(this);
	}
	
	public void leggiAdesionedaDB() throws EccezioneMuseo {
		ArtistaDAO.load_adesione(this);
	}
	
	public void leggiRealizzazionedaDB() throws EccezioneMuseo {
		ArtistaDAO.load_realizzazione(this);
	}


}
