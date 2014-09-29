package dominio.Statua;

import utility.EccezioneMuseo;
import DAO.StatuaDAO;
import dominio.OperaArte.OperaArte;

public class Statua extends OperaArte {
	
	protected String materiale;
	
	public Statua(String codice) {
		super(codice);
	}
	
	//materiale
	public String getMateriale() {
		return materiale;
	}
	
	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			Statua s = (Statua) o;
			return super.equals(s);
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	
	public String toStringGenerali() {
		return super.toStringGenerali() + "\n" +
				"MATERIALE: " + materiale;
	}
	
	public String toStringParticolari() {
		return super.toStringParticolari() + "\n" +
				"MATERIALE: " + materiale;
	}
	
	
	
	
	public void leggiStatuadaDB() throws EccezioneMuseo {
		StatuaDAO.load(this);
	}


}
