package utility;

@SuppressWarnings("serial")
public class EccezioneMuseo extends Exception {
	
	private String messaggio;
	
	public EccezioneMuseo(String messaggio) {
		this.messaggio = messaggio;
	}
	
	public String toString() {
		return messaggio;
	}

}
