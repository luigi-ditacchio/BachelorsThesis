package controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import utility.EccezioneMuseo;



import dominio.Artista;
import dominio.TipoLinkAdesione;
import dominio.TipoLinkRealizzazione;


import DCS.ArtistaDCS;

public class ArtistaController {
	
	private ArtistaController() {}
	
	public static Collection<String> getCodiceTutti() {
		
		try {
			Collection<Artista> artisti = ArtistaDCS.tuttiArtistaGenerali();
			Collection<String> artisti_codici = new LinkedList<String>();
			Iterator<Artista> it = artisti.iterator();
			while (it.hasNext()) {
				artisti_codici.add(it.next().getCodice());
			}
			return artisti_codici;
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	
	public static String getArtistaGenerali(String codice) {
		
		try {
			Artista artista = new Artista(codice);
			artista.leggiGeneralidaDB();
			return artista.toStringGenerali();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	
	public static String getArtistaParticolari(String codice) {
		
		try {
			Artista artista = new Artista(codice);
			artista.leggiParticolaridaDB();
			return artista.toStringParticolari();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	
	public static Collection<String> getMovimenti(String codice) {
		
		try {
			Collection<String> list = new LinkedList<String>();
			Artista artista = new Artista(codice);
			artista.leggiAdesionedaDB();
			Set<TipoLinkAdesione> set = artista.getLinkAdesione();
			Iterator<TipoLinkAdesione> it = set.iterator();
			while (it.hasNext()) {
				list.add(it.next().getMovimento().getNome());
			}
			return list;
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	public static Collection<String> getOpere(String codice) {
		
		try {
			Collection<String> list = new LinkedList<String>();
			Artista artista = new Artista(codice);
			artista.leggiRealizzazionedaDB();
			Set<TipoLinkRealizzazione> set = artista.getLinkRealizzazione();
			Iterator<TipoLinkRealizzazione> it = set.iterator();
			while (it.hasNext()) {
				list.add(it.next().getOpera().getCodice());
			}
			return list;
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}

}
