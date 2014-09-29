package dominio;

public class TipoLinkAppartenenza {
	
	private final Sala sala;
	private final Sezione sezione;
	
	public TipoLinkAppartenenza(Sala sala, Sezione sezione) throws EccezionePrecondizioni {
		if (sala == null || sezione == null) {
			throw new EccezionePrecondizioni("Sala e Sezione vanno inizializzati");
		}
		else {
			this.sala = sala;
			this.sezione = sezione;
		}
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public Sezione getSezione() {
		return sezione;
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			TipoLinkAppartenenza link = (TipoLinkAppartenenza) o;
			return this.sala.equals(link.getSala()) && this.sezione.equals(link.getSezione());
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return sala.hashCode() + sezione.hashCode();
	}

}
