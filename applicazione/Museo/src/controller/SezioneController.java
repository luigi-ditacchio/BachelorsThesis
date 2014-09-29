package controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import utility.EccezioneMuseo;

import DCS.SezioneDCS;

import dominio.Sezione;

public class SezioneController {
	
	
	private SezioneController() {}
	
	public static Collection<String> getNomeTutti() {
		
		try {
			Collection<Sezione> sezioni = SezioneDCS.tuttiSezione();
			Collection<String> sezioni_nomi = new LinkedList<String>();
			Iterator<Sezione> it = sezioni.iterator();
			while (it.hasNext()) {
				sezioni_nomi.add(it.next().getNome());
			}
			return sezioni_nomi;
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	
	public static String getSezione(String nome) {
		
		try {
			Sezione sezione = new Sezione(nome);
			sezione.leggidaDB();
			return sezione.toString();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}

}
