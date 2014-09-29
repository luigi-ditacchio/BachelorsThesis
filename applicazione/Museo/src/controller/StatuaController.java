package controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import utility.EccezioneMuseo;

import DCS.StatuaDCS;


import dominio.EccezioneCardMin;
import dominio.TipoLinkCollocazione;
import dominio.Oggetto.Oggetto;
import dominio.OperaArte.OperaArte;

import dominio.Statua.Statua;

public class StatuaController {
	
	private StatuaController() {}
	
	public static Collection<String> getCodiceTutti() {
		
		try {
			Collection<Statua> statue = StatuaDCS.tuttiStatua();
			Collection<String> statue_codici = new LinkedList<String>();
			Iterator<Statua> it = statue.iterator();
			while (it.hasNext()) {
				statue_codici.add(it.next().getCodice());
			}
			return statue_codici;
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	
	public static String getStatuaGenerali(String codice) {
		
		try {
			Statua statua = new Statua(codice);
			statua.leggidaDB();
			statua.leggiGeneralidaDB();
			return statua.toStringGenerali();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	public static String getStatuaParticolari(String codice) {
		
		try {
			Statua statua = new Statua(codice);
			statua.leggidaDB();
			statua.leggiParticolaridaDB();
			return statua.toStringParticolari();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	
	public static String getCodiceArtista(String codice) {
		
		try {
			OperaArte opera = new Statua(codice);
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
			Oggetto oggetto = new Statua(codice);
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
