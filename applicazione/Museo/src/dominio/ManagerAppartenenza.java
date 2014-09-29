package dominio;

public final class ManagerAppartenenza {
	
	private TipoLinkAppartenenza link;
	
	private ManagerAppartenenza(TipoLinkAppartenenza link) {
		this.link = link;
	}
	
	public TipoLinkAppartenenza getLink() {
		return link;
	}
	
	public static void add(TipoLinkAppartenenza link) {
		if (link != null) {
			ManagerAppartenenza man = new ManagerAppartenenza(link);
			link.getSala().addForManagerAppartenenza(man);
			link.getSezione().addForManagerAppartenenza(man);
		}
	}
	
	public static void remove(TipoLinkAppartenenza link) {
		if (link != null) {
			ManagerAppartenenza man = new ManagerAppartenenza(link);
			link.getSala().removeForManagerAppartenenza(man);
			link.getSezione().removeForManagerAppartenenza(man);
		}
	}
	
}
