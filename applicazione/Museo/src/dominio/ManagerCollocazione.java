package dominio;

public final class ManagerCollocazione {
	
	private TipoLinkCollocazione link;
	
	private ManagerCollocazione(TipoLinkCollocazione link) {
		this.link = link;
	}
	
	public TipoLinkCollocazione getLink() {
		return link;
	}
	
	public static void add(TipoLinkCollocazione link) {
		if (link != null && link.getOggetto().quantiCollocazione() == 0) {
			ManagerCollocazione man = new ManagerCollocazione(link);
			link.getOggetto().addForManagerCollocazione(man);
			link.getSala().addForManagerCollocazione(man);
		}
	}
	
	public static void remove(TipoLinkCollocazione link) {
		try {
			if (link != null && link.getOggetto().getLinkCollocazione().equals(link)) {
				ManagerCollocazione man = new ManagerCollocazione(link);
				link.getOggetto().removeForManagerCollocazione(man);
				link.getSala().removeForManagerCollocazione(man);
			}
		}
		catch (EccezioneCardMin e) {
			System.out.println(e);
		}
	}

}
