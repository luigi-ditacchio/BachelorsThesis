package dominio.CentroRestauro;

import dominio.Struttura.Struttura;


public class CentroRestauro extends Struttura {
	
	public CentroRestauro(String codice) {
		super(codice);
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			CentroRestauro cr = (CentroRestauro) o;
			return super.equals(cr);
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
