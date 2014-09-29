package dominio;

import dominio.OggettoPermanente.OggettoPermanente;

public class TipoLinkPermanenteRestauro {
	
	private final OggettoPermanente oggetto;
	private final Restauro restauro;
	
	public TipoLinkPermanenteRestauro(OggettoPermanente oggetto, Restauro restauro) throws EccezionePrecondizioni {
		if (oggetto == null || restauro == null) {
			throw new EccezionePrecondizioni("OggettoPermanente e Restauro vanno inizializzati");
		}
		else {
			this.oggetto = oggetto;
			this.restauro = restauro;
		}
	}
	
	public OggettoPermanente getOggetto() {
		return oggetto;
	}
	
	public Restauro getRestauro() {
		return restauro;
	}
	
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			TipoLinkPermanenteRestauro link = (TipoLinkPermanenteRestauro) o;
			return this.oggetto.equals(link.getOggetto()) && this.restauro.equals(link.getRestauro());
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return oggetto.hashCode() + restauro.hashCode();
	}


}
