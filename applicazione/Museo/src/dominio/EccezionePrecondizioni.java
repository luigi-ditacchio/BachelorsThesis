package dominio;

@SuppressWarnings("serial")
public class EccezionePrecondizioni extends Exception {
	
	private String messaggio;
	
	public EccezionePrecondizioni(String messaggio) {
		this.messaggio = messaggio;
	}
	
	public String toString() {
		return messaggio;
	}

}
