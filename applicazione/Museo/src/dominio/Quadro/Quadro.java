package dominio.Quadro;

import utility.EccezioneMuseo;
import DAO.QuadroDAO;
import dominio.OperaArte.OperaArte;

public class Quadro extends OperaArte {
	
	protected String tecnica;
	
	public Quadro(String codice) {
		super(codice);
	}
	
	//tecnica
	public String getTecnica() {
		return tecnica;
	}
	
	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			Quadro q = (Quadro) o;
			return super.equals(q);
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
				"TECNICA: " + tecnica;
	}
	
	public String toStringParticolari() {
		return super.toStringParticolari() + "\n" +
				"TECNICA: " + tecnica;
	}
	
	
	
	public void leggiQuadrodaDB() throws EccezioneMuseo {
		QuadroDAO.load(this);
	}

}
