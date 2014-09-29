package dominio;

@SuppressWarnings("serial")
public class EccezioneCardMin extends Exception {
	
private String messaggio;
	
	public EccezioneCardMin(String messaggio) {
		this.messaggio = messaggio;
	}
	
	public String toString() {
		return messaggio;
	}

}
