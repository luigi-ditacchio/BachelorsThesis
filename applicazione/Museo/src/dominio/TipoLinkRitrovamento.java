package dominio;

import java.sql.Date;

import dominio.RepertoArcheologico.RepertoArcheologico;


public class TipoLinkRitrovamento {
	
	private final RepertoArcheologico reperto;
	private final Scavo scavo;
	private final Date data;
	
	public TipoLinkRitrovamento(RepertoArcheologico reperto, Scavo scavo, Date data) throws EccezionePrecondizioni {
		if (reperto == null || scavo == null) {
			throw new EccezionePrecondizioni("Reperto e Scavo vanno inizializzati");
		}
		else {
			this.reperto = reperto;
			this.scavo = scavo;
			this.data = data;
		}
	}
	
	public RepertoArcheologico getReperto() {
		return reperto;
	}
	
	public Scavo getScavo() {
		return scavo;
	}
	
	public Date getData() {
		return data;
	}
	
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			TipoLinkRitrovamento link = (TipoLinkRitrovamento) o;
			return this.reperto.equals(link.getReperto()) && this.scavo.equals(link.getScavo());
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return reperto.hashCode() + scavo.hashCode();
	}

}
