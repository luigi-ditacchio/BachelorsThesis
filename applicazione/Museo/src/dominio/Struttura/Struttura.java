package dominio.Struttura;

import java.util.HashSet;

import dominio.EccezioneCardMin;

public class Struttura {
	
	protected final String codice;
	protected String nome;
	protected String citta;
	protected String via;
	protected int numero;
	protected String CAP;
	protected HashSet<String> telefono;
	
	
	public static final int MIN_TELEFONO = 1;
	
	public Struttura(String codice) {
		this.codice = codice;

		this.telefono = new HashSet<String>();
		
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
	
	//citt�
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	//via
	public String getVia() {
		return via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	//numero
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	//CAP
	public String getCAP() {
		return CAP;
	}
	
	public void setCAP(String CAP) {
		this.CAP = CAP;
	}
	
	//telefono
	@SuppressWarnings("unchecked")
	public HashSet<String> getTelefono() throws EccezioneCardMin {
		if (quantiTelefono() < MIN_TELEFONO) {
			throw new EccezioneCardMin("Violazione cardinalita' minima telefono");
		}
		else {
			return (HashSet<String>)telefono.clone();
		}
	}
	
	public void addTelefono(String numero) {
		if (numero != null) {
			telefono.add(numero);
		}
	}
	
	public void removeTelefono(String numero) {
		if (numero != null) {
			telefono.remove(numero);
		}
	}
	
	
	public int quantiTelefono() {
		return telefono.size();
	}
	
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			Struttura s = (Struttura) o;
			return this.codice.equals(s.codice);
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return codice.hashCode();
	}
	
	
	public String toString() {
		return "CODICE: " + codice + "\n" +
				"NOME: " + nome + "\n" +
				"CITTA': " + citta + "\n" +
				"VIA: " + via + "\n" +
				"NUMERO: " + numero + "\n" +
				"CAP: " + CAP + "\n";
	}

}
