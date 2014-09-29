package dominio;

public final class ManagerPermanenteRestauro {
	
	private ManagerPermanenteRestauro(TipoLinkPermanenteRestauro link) {
		this.link = link;
	}
	
	private TipoLinkPermanenteRestauro link;
	
	public TipoLinkPermanenteRestauro getLink() {
		return link;
	}
	
	
	public static void add(TipoLinkPermanenteRestauro link) {
		if (link != null && link.getRestauro().quantiPermanenteRestauro() == 0) {
			ManagerPermanenteRestauro man = new ManagerPermanenteRestauro(link);
			link.getOggetto().addForManagerPermanenteRestauro(man);
			link.getRestauro().addForManagerPermanenteRestauro(man);
		}
	}
	
	public static void remove(TipoLinkPermanenteRestauro link) {
		try {
			if (link != null && link.getRestauro().getLinkPermanenteRestauro().equals(link)) {
				ManagerPermanenteRestauro man = new ManagerPermanenteRestauro(link);
				link.getOggetto().removeForManagerPermanenteRestauro(man);
				link.getRestauro().removeForManagerPermanenteRestauro(man);
			}
		}
		catch (EccezioneCardMin e) {
			System.out.println(e);
		}
	}

}
