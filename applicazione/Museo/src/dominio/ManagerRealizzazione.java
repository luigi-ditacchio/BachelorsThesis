package dominio;

public final class ManagerRealizzazione {
	
	private TipoLinkRealizzazione link;
	
	private ManagerRealizzazione(TipoLinkRealizzazione link) {
		this.link = link;
	}
	
	public TipoLinkRealizzazione getLink() {
		return link;
	}
	
	public static void add(TipoLinkRealizzazione link) {
		if (link != null && link.getOpera().quantiRealizzazione() == 0) {
			ManagerRealizzazione man = new ManagerRealizzazione(link);
			link.getOpera().addForManagerRealizzazione(man);
			link.getArtista().addForManagerRealizzazione(man);
		}
	}
	
	public static void remove(TipoLinkRealizzazione link) {
		try {
			if (link != null && link.getOpera().getLinkRealizzazione().equals(link)) {
				ManagerRealizzazione man = new ManagerRealizzazione(link);
				link.getOpera().removeForManagerRealizzazione(man);
				link.getArtista().removeForManagerRealizzazione(man);
			}
		}
		catch (EccezioneCardMin e) {
			System.out.println(e);
		}
	}

}
