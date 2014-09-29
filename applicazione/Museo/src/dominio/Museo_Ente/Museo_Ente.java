package dominio.Museo_Ente;

import dominio.Struttura.Struttura;

public class Museo_Ente extends Struttura {
	
	public Museo_Ente(String codice) {
		super(codice);
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			Museo_Ente m = (Museo_Ente) o;
			return super.equals(m);
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	
	public String toString() {
		return super.toString();
	}
	

}
