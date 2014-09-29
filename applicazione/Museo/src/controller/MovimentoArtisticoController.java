package controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import utility.EccezioneMuseo;
import DCS.MovimentoArtisticoDCS;

import dominio.MovimentoArtistico;


public class MovimentoArtisticoController {
	
	
	private MovimentoArtisticoController() {}
	
	public static Collection<String> getNomeTutti() {
		
		try {
			Collection<MovimentoArtistico> movimenti = MovimentoArtisticoDCS.tuttiMovimentoArtistico();
			Collection<String> movimenti_nomi = new LinkedList<String>();
			Iterator<MovimentoArtistico> it = movimenti.iterator();
			while (it.hasNext()) {
				movimenti_nomi.add(it.next().getNome());
			}
			return movimenti_nomi;
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	
	public static String getMovimento(String nome) {
		
		try {
			MovimentoArtistico movimento = new MovimentoArtistico(nome);
			movimento.leggidaDB();
			return movimento.toString();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}


}
