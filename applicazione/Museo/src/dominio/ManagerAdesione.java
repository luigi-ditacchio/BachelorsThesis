package dominio;

public final class ManagerAdesione {
	
	private TipoLinkAdesione link;
	
	private ManagerAdesione(TipoLinkAdesione link) {
		this.link = link;
	}
	
	public TipoLinkAdesione getLink() {
		return link;
	}
	
	public static void add(TipoLinkAdesione link) {
		if (link != null) {
			ManagerAdesione man = new ManagerAdesione(link);
			link.getArtista().addForManagerAdesione(man);
			link.getMovimento().addForManagerAdesione(man);
		}
	}
	
	public static void remove(TipoLinkAdesione link) {
		if (link != null) {
			ManagerAdesione man = new ManagerAdesione(link);
			link.getArtista().removeForManagerAdesione(man);
			link.getMovimento().removeForManagerAdesione(man);
		}
	}

}
