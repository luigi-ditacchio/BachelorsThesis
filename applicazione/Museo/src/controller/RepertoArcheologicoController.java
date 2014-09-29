package controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import utility.EccezioneMuseo;

import DCS.RepertoArcheologicoDCS;

import dominio.EccezioneCardMin;
import dominio.TipoLinkCollocazione;
import dominio.TipoLinkRitrovamento;
import dominio.Oggetto.Oggetto;

import dominio.RepertoArcheologico.RepertoArcheologico;


public class RepertoArcheologicoController {
	
	private RepertoArcheologicoController() {}
	
	
	public static Collection<String> getCodiceTutti() {
		
		try {
			Collection<RepertoArcheologico> reperti = RepertoArcheologicoDCS.tuttiRepertoArcheologico();
			Collection<String> reperti_codici = new LinkedList<String>();
			Iterator<RepertoArcheologico> it = reperti.iterator();
			while (it.hasNext()) {
				reperti_codici.add(it.next().getCodice());
			}
			return reperti_codici;
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	
	public static String getRepertoGenerali(String codice) {
		
		try {
			RepertoArcheologico reperto = new RepertoArcheologico(codice);
			reperto.leggidaDB();
			reperto.leggiGeneralidaDB();
			return reperto.toStringGenerali();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	public static String getRepertoParticolari(String codice) {
		
		try {
			RepertoArcheologico reperto = new RepertoArcheologico(codice);
			reperto.leggidaDB();
			reperto.leggiParticolaridaDB();
			return reperto.toStringParticolari();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	public static String getScavo(String codice) {
		
		try {
			RepertoArcheologico reperto = new RepertoArcheologico(codice);
			reperto.leggiRitrovamentodaDB();
			TipoLinkRitrovamento link = reperto.getLinkRitrovamento();
			return link.getScavo().toString() + "\n" +
					"DATA RITROVAMENTO: " + link.getData();
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
			Oggetto oggetto = new RepertoArcheologico(codice);
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
