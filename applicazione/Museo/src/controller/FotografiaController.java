package controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import utility.EccezioneMuseo;
import DCS.FotografiaDCS;

import dominio.EccezioneCardMin;
import dominio.TipoLinkCollocazione;
import dominio.Fotografia.Fotografia;
import dominio.Oggetto.Oggetto;




public class FotografiaController {
	
	private FotografiaController() {}
	
	public static Collection<String> getCodiceTutti() {
		
		try {
			Collection<Fotografia> fotografie = FotografiaDCS.tuttiFotografia();
			Collection<String> fotografie_codici = new LinkedList<String>();
			Iterator<Fotografia> it = fotografie.iterator();
			while (it.hasNext()) {
				fotografie_codici.add(it.next().getCodice());
			}
			return fotografie_codici;
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	
	public static String getFotografiaGenerali(String codice) {
		
		try {
			Fotografia fotografia = new Fotografia(codice);
			fotografia.leggidaDB();
			fotografia.leggiGeneralidaDB();
			return fotografia.toStringGenerali();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	public static String getFotografiaParticolari(String codice) {
		
		try {
			Fotografia fotografia = new Fotografia(codice);
			fotografia.leggidaDB();
			fotografia.leggiParticolaridaDB();
			return fotografia.toStringParticolari();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	
	public static String getCollocazione(String codice) {
		
		try {
			Oggetto oggetto = new Fotografia(codice);
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
