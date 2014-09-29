package dominio;

public final class ManagerPermanentePrestito {
	
	private ManagerPermanentePrestito(TipoLinkPermanentePrestito link) {
		this.link = link;
	}
	
	private TipoLinkPermanentePrestito link;
	
	public TipoLinkPermanentePrestito getLink() {
		return link;
	}
	
	
	public static void add(TipoLinkPermanentePrestito link) {
		if (link != null && link.getPrestito().quantiPermanentePrestito() == 0) {
			ManagerPermanentePrestito man = new ManagerPermanentePrestito(link);
			link.getOggetto().addForManagerPermanentePrestito(man);
			link.getPrestito().addForManagerPermanentePrestito(man);
		}
	}
	
	public static void remove(TipoLinkPermanentePrestito link) {
		try {
			if (link != null && link.getPrestito().getLinkPermanentePrestito().equals(link)) {
				ManagerPermanentePrestito man = new ManagerPermanentePrestito(link);
				link.getOggetto().removeForManagerPermanentePrestito(man);
				link.getPrestito().removeForManagerPermanentePrestito(man);
			}
		}
		catch (EccezioneCardMin e) {
			System.out.println(e);
		}
	}

}
