package dominio;

import utility.EccezioneMuseo;
import DAO.ScavoDAO;

public class Scavo {

	private final String nome;
	private final String luogo;
	
	public Scavo(String nome, String luogo) {
		this.nome = nome;
		this.luogo = luogo;
	}
	
	//nome
	public String getNome() {
		return nome;
	}
	
	//luogo
	public String getLuogo() {
		return luogo;
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			Scavo s = (Scavo) o;
			return this.nome.equals(s.nome) && this.luogo.equals(s.luogo);
		}
		else {
			return false;
		}
	}
	
	
	
	public String toString() {
		return "NOME: " + nome + "\n" +
				"LUOGO: " + luogo;
	}
	
	
	
	
	public int hashCode() {
		return nome.hashCode() + luogo.hashCode();
	}
	
	
	
	public void leggidaDB() throws EccezioneMuseo {
		ScavoDAO.load(this);
	}
	
}
