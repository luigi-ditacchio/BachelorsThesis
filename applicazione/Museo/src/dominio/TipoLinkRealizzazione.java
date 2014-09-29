package dominio;

import dominio.OperaArte.OperaArte;

public class TipoLinkRealizzazione {
	
	private final OperaArte opera;
	private final Artista artista;
	
	public TipoLinkRealizzazione(OperaArte opera, Artista artista) throws EccezionePrecondizioni {
		if (opera == null || artista == null) {
			throw new EccezionePrecondizioni("Opera e Artista vanno inizializzati");
		}
		else {
			this.opera = opera;
			this.artista = artista;
		}
	}
	
	public OperaArte getOpera() {
		return opera;
	}
	
	public Artista getArtista() {
		return artista;
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			TipoLinkRealizzazione link = (TipoLinkRealizzazione) o;
			return this.opera.equals(link.getOpera()) && this.artista.equals(link.getArtista());
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return opera.hashCode() + artista.hashCode();
	}

}
