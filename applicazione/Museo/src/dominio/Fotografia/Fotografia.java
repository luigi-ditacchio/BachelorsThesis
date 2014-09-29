package dominio.Fotografia;


import utility.EccezioneMuseo;
import DAO.FotografiaDAO;
import dominio.Oggetto.Oggetto;


public class Fotografia extends Oggetto {
	
	protected String tipo;
	
	public Fotografia(String codice) {
		super(codice);
	}
	
	//tipo
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	public String toStringGenerali() {
		return super.toStringGenerali() + "\n" +
				"TIPO: " + tipo;
	}
	
	public String toStringParticolari() {
		return super.toStringParticolari() + "\n" +
				"TIPO: " + tipo;
	}
	
	
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			Fotografia s = (Fotografia) o;
			return super.equals(s);
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	
	
	
	public void leggidaDB() throws EccezioneMuseo {
		FotografiaDAO.load(this);
	}

}
