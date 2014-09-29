package dominio;

public class TipoLinkAdesione {
	
	private final Artista artista;
	private final MovimentoArtistico movimento;
	
	public TipoLinkAdesione(Artista artista, MovimentoArtistico movimento) throws EccezionePrecondizioni {
		if (artista == null || movimento == null) {
			throw new EccezionePrecondizioni("Artista e Movimento vanno inizializzati");
		}
		else {
			this.artista = artista;
			this.movimento = movimento;
		}
	}
	
	public Artista getArtista() {
		return artista;
	}
	
	public MovimentoArtistico getMovimento() {
		return movimento;
	}
	
	public boolean equals(Object o) {
		if (o != null && o.getClass().equals(this.getClass())) {
			TipoLinkAdesione link = (TipoLinkAdesione) o;
			return this.artista.equals(link.getArtista()) && this.movimento.equals(link.getMovimento());
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return artista.hashCode() + movimento.hashCode();
	}

}
