package controller;

import java.util.Collection;

import utility.EccezioneMuseo;

import DCS.SalaDCS;
import dominio.Sala;


public class SalaController {
	
	private SalaController() {}
	
	
	
	public static Collection<Integer> getPianoTutti() {
		
		try {
			return SalaDCS.tuttiPiani();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	
	public static Collection<Integer> getSalesuPiano(int piano) {
		
		try {
			return SalaDCS.tuttiSalasuPiano(piano);
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	
	public static String getSala(int numero, int piano) {
		
		try {
			Sala sala = new Sala(numero, piano);
			sala.leggidaDB();
			return sala.toString();
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	public static Collection<String> getOpereinSala(int numero, int piano) {
		try {
			return SalaDCS.tutteOpereinSala(numero, piano);
		}
		catch (EccezioneMuseo e) {
			System.out.println(e.toString());
		}
		return null;
	}

}
