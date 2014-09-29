package controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import utility.EccezioneMuseo;

import DCS.QuadroDCS;

import dominio.EccezioneCardMin;
import dominio.TipoLinkCollocazione;
import dominio.Oggetto.Oggetto;
import dominio.OperaArte.OperaArte;
import dominio.Quadro.Quadro;

public class QuadroController {
	
	private QuadroController() {}
	
	public static Collection<String> getCodiceTutti() {
		
		try {
			Collection<Quadro> quadri = QuadroDCS.tuttiQuadro();
			Collection<String> quadri_codici = new LinkedList<String>();
			Iterator<Quadro> it = quadri.iterator();
			while (it.hasNext()) {
				quadri_codici.add(it.next().getCodice());
			}
			return quadri_codici;
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	
	public static String getQuadroGenerali(String codice) {
		
		try {
			Quadro quadro = new Quadro(codice);
			quadro.leggidaDB();
			quadro.leggiGeneralidaDB();
			return quadro.toStringGenerali();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	public static String getQuadroParticolari(String codice) {
		
		try {
			Quadro quadro = new Quadro(codice);
			quadro.leggidaDB();
			quadro.leggiParticolaridaDB();
			return quadro.toStringParticolari();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	
	public static String getCodiceArtista(String codice) {
		
		try {
			OperaArte opera = new Quadro(codice);
			opera.leggidaDB();
			return opera.getLinkRealizzazione().getArtista().getCodice();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		catch (EccezioneCardMin ecm) {
			System.out.println(ecm.toString());
		}
		return null;
		
	}
	
	
	public static String getCollocazione(String codice) {
		
		try {
			Oggetto oggetto = new Quadro(codice);
			oggetto.leggiCollocazionedaDB();
			TipoLinkCollocazione link = oggetto.getLinkCollocazione();
			return "NUMERO SALA: " + link.getSala().getNumero() + "\n" +
					"PIANO: " + link.getSala().getPiano();
			
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		catch (EccezioneCardMin ecm) {
			System.out.println(ecm.toString());
		}
		return null;
		
	}


}
