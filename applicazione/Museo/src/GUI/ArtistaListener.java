package GUI;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.ArtistaController;
import controller.MovimentoArtisticoController;
import controller.QuadroController;
import controller.StatuaController;

import GUI.MainFrame;


public class ArtistaListener implements ActionListener {
	
	
	public static final String TASTO_PRINCIPALE_ARTISTA = "PRINCIPALE ARTISTA";
	public static final String TASTO_ARTISTA = "ARTISTA";
	public static final String TASTO_PIU_ARTISTA = "PIU ARTISTA";
	public static final String TASTO_OPERA = "OPERA";
	public static final String TASTO_PIU_OPERA = "PIU OPERA";
	public static final String TASTO_MOVIMENTO = "MOVIMENTO";
	
	private MainFrame frame;
	private String artista_selezionato;
	private String opera_selezionata;
	
	
	private JPanel pannello_sinistro = new JPanel();
	private JPanel pannello_sinistro_artisti = new JPanel();
	private JPanel pannello_sinistro_artista_gen = new JPanel();
	private JPanel pannello_sinistro_artista_par = new JPanel();
	
	private JPanel pannello_centrale = new JPanel();
	private JPanel pannello_centrale_opere = new JPanel();
	private JPanel pannello_centrale_opera_gen = new JPanel();
	private JPanel pannello_centrale_opera_par = new JPanel();
	
	private JPanel pannello_destro = new JPanel();
	private JPanel pannello_destro_movimenti = new JPanel();
	private JPanel pannello_destro_movimento_inf = new JPanel();
	
	
	public ArtistaListener(MainFrame frame) {
		this.frame = frame;
	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent event) {
		
		
		String action = event.getActionCommand();
		
		
		if (action == TASTO_PRINCIPALE_ARTISTA) {
			
			//Costruisce lo scheletro della finestra
			
			frame.pannello_superiore.removeAll();
			
			pannello_sinistro.removeAll();
			pannello_sinistro_artisti.removeAll();
			pannello_sinistro_artista_gen.removeAll();
			pannello_sinistro_artista_par.removeAll();
			
			pannello_centrale.removeAll();
			pannello_centrale_opere.removeAll();
			pannello_centrale_opera_gen.removeAll();
			pannello_centrale_opera_par.removeAll();
			
			pannello_destro.removeAll();
			pannello_destro_movimenti.removeAll();
			pannello_destro_movimento_inf.removeAll();
			
			
			
			//ARTISTA 
			pannello_sinistro_artisti.setLayout(new BoxLayout(pannello_sinistro_artisti, BoxLayout.Y_AXIS));
			TitledBorder title_artisti = BorderFactory.createTitledBorder("ARTISTI");
			pannello_sinistro_artisti.setBorder(new CompoundBorder(title_artisti, new EmptyBorder(5, 20, 10, 20)));
			
			//ARTISTA GENERALI
			pannello_sinistro_artista_gen.setLayout(new BoxLayout(pannello_sinistro_artista_gen, BoxLayout.Y_AXIS));
			TitledBorder title_artista_gen = BorderFactory.createTitledBorder("INFORMAZIONI GENERALI");
			pannello_sinistro_artista_gen.setBorder(new CompoundBorder(title_artista_gen, new EmptyBorder(5, 20, 10, 20)));
			
			//ARTISTA PARTICOLARI
			pannello_sinistro_artista_par.setLayout(new BoxLayout(pannello_sinistro_artista_par, BoxLayout.Y_AXIS));
			TitledBorder title_artista_par = BorderFactory.createTitledBorder("INFORMAZIONI PARTICOLARI");
			pannello_sinistro_artista_par.setBorder(new CompoundBorder(title_artista_par, new EmptyBorder(5, 20, 10, 20)));
			
			//OPERE
			pannello_centrale_opere.setLayout(new BoxLayout(pannello_centrale_opere, BoxLayout.Y_AXIS));
			TitledBorder title_opere = BorderFactory.createTitledBorder("OPERE");
			pannello_centrale_opere.setBorder(new CompoundBorder(title_opere, new EmptyBorder(5, 20, 10, 20)));
			
			//OPERA GENERALI
			pannello_centrale_opera_gen.setLayout(new BoxLayout(pannello_centrale_opera_gen, BoxLayout.Y_AXIS));
			TitledBorder title_opera_gen = BorderFactory.createTitledBorder("INFORMAZIONI GENERALI OPERA");
			pannello_centrale_opera_gen.setBorder(new CompoundBorder(title_opera_gen, new EmptyBorder(5, 20, 10, 20)));
			
			//OPERA PARTICOLARI
			pannello_centrale_opera_par.setLayout(new BoxLayout(pannello_centrale_opera_par, BoxLayout.Y_AXIS));
			TitledBorder title_opera_par = BorderFactory.createTitledBorder("INFORMAZIONI PARTICOLARI OPERA");
			pannello_centrale_opera_par.setBorder(new CompoundBorder(title_opera_par, new EmptyBorder(5, 20, 10, 20)));
			
			//MOVIMENTI
			pannello_destro_movimenti.setLayout(new BoxLayout(pannello_destro_movimenti, BoxLayout.Y_AXIS));
			TitledBorder title_movimenti = BorderFactory.createTitledBorder("MOVIMENTI");
			pannello_destro_movimenti.setBorder(new CompoundBorder(title_movimenti, new EmptyBorder(5, 20, 10, 20)));
			
			//MOVIMENTI INFO
			pannello_destro_movimento_inf.setLayout(new BoxLayout(pannello_destro_movimento_inf, BoxLayout.Y_AXIS));
			TitledBorder title_movimento_inf = BorderFactory.createTitledBorder("INFORMAZIONI MOVIMENTO");
			pannello_destro_movimento_inf.setBorder(new CompoundBorder(title_movimento_inf, new EmptyBorder(5, 20, 10, 20)));
			
			
			
			//PANNELLO SINISTRO
			pannello_sinistro.setLayout(new GridLayout(3,1));
			pannello_sinistro.add(pannello_sinistro_artisti);
			pannello_sinistro.add(pannello_sinistro_artista_gen);
			pannello_sinistro.add(pannello_sinistro_artista_par);
			
			//PANNELLO CENTRALE
			pannello_centrale.setLayout(new GridLayout(3,1));
			pannello_centrale.add(pannello_centrale_opere);
			pannello_centrale.add(pannello_centrale_opera_gen);
			pannello_centrale.add(pannello_centrale_opera_par);
			
			//PANNELLO DESTRO
			pannello_destro.setLayout(new GridLayout(2,1));
			pannello_destro.add(pannello_destro_movimenti);
			pannello_destro.add(pannello_destro_movimento_inf);
			
			
			//PANNELLO COMPLETO
			frame.pannello_superiore.setLayout(new GridLayout(1, 3));
			frame.pannello_superiore.add(pannello_sinistro);
			frame.pannello_superiore.add(pannello_centrale);
			frame.pannello_superiore.add(pannello_destro);
			
			
			
			
			Collection<String> artisti = ArtistaController.getCodiceTutti();
			if (artisti == null) return;
			Iterator<String> it = artisti.iterator();
			while (it.hasNext()) {
				JButton button = new JButton(String.valueOf(it.next()));
				button.addActionListener(frame.artista_listener);
				button.setActionCommand(ArtistaListener.TASTO_ARTISTA);
				pannello_sinistro_artisti.add(button);
			}
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		
		
		else if (action == TASTO_ARTISTA) {
			
			pannello_sinistro_artista_gen.removeAll();
			pannello_sinistro_artista_par.removeAll();
			
			pannello_centrale_opere.removeAll();
			pannello_centrale_opera_gen.removeAll();
			pannello_centrale_opera_par.removeAll();
			
			pannello_destro_movimenti.removeAll();
			pannello_destro_movimento_inf.removeAll();
			
			
			JButton button = (JButton)event.getSource();
			artista_selezionato = button.getText();
			
			JTextArea text_area = new JTextArea(ArtistaController.getArtistaGenerali(artista_selezionato));
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_sinistro_artista_gen.add(scroll);
			
			JButton butt_par = new JButton("Piu' informazioni");
			butt_par.addActionListener(frame.artista_listener);
			butt_par.setActionCommand(ArtistaListener.TASTO_PIU_ARTISTA);
			pannello_sinistro_artista_par.add(butt_par);
			
			
			Collection<String> opere = ArtistaController.getOpere(artista_selezionato);
			if (opere == null) return;
			Iterator<String> it = opere.iterator();
			while (it.hasNext()) {
				JButton b = new JButton(String.valueOf(it.next()));
				b.addActionListener(frame.artista_listener);
				b.setActionCommand(ArtistaListener.TASTO_OPERA);
				pannello_centrale_opere.add(b);
			}
			
			
			
			Collection<String> movimenti = ArtistaController.getMovimenti(artista_selezionato);
			if (movimenti == null) return;
			Iterator<String> i = movimenti.iterator();
			while (i.hasNext()) {
				JButton butt = new JButton(String.valueOf(i.next()));
				butt.addActionListener(frame.artista_listener);
				butt.setActionCommand(ArtistaListener.TASTO_MOVIMENTO);
				pannello_destro_movimenti.add(butt);
			}
			
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
			
		}
		
		else if (action == TASTO_PIU_ARTISTA) {
			
			pannello_sinistro_artista_par.removeAll();
			
			JTextArea text_area = new JTextArea(ArtistaController.getArtistaParticolari(artista_selezionato));
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_sinistro_artista_par.add(scroll);
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		
		else if (action == TASTO_OPERA) {
			
			pannello_centrale_opera_gen.removeAll();
			pannello_centrale_opera_par.removeAll();
			
			JButton button = (JButton)event.getSource();
			opera_selezionata = button.getText();
			
			String opera_generali = null;
			opera_generali = QuadroController.getQuadroGenerali(opera_selezionata);
			if (opera_generali == null) {
				opera_generali = StatuaController.getStatuaGenerali(opera_selezionata);
				if (opera_generali == null) {
					return;
				}
			}
			JTextArea text_area = new JTextArea(opera_generali);
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_centrale_opera_gen.add(scroll);
			
			JButton butt_par = new JButton("Piu' informazioni");
			butt_par.addActionListener(frame.artista_listener);
			butt_par.setActionCommand(ArtistaListener.TASTO_PIU_OPERA);
			pannello_centrale_opera_par.add(butt_par);
			
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		
		
		else if (action == TASTO_PIU_OPERA) {
			
			pannello_centrale_opera_par.removeAll();
			
			String opera_particolari = null;
			opera_particolari = QuadroController.getQuadroParticolari(opera_selezionata);
			if (opera_particolari == null) {
				opera_particolari = StatuaController.getStatuaParticolari(opera_selezionata);
				if (opera_particolari == null) {
					return;
				}
			}
			JTextArea text_area = new JTextArea(opera_particolari);
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_centrale_opera_par.add(scroll);
			
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		
		else if (action == TASTO_MOVIMENTO) {
			
			pannello_destro_movimento_inf.removeAll();
			
			JButton button = (JButton)event.getSource();
			
			JTextArea text_area = new JTextArea(MovimentoArtisticoController.getMovimento(button.getText()));
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_destro_movimento_inf.add(scroll);
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		
		
		
		
	}

}
