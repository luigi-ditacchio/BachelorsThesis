package GUI;

import java.awt.BorderLayout;
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
import controller.QuadroController;

public class QuadroListener implements ActionListener {
	
	
	public static final String TASTO_PRINCIPALE_QUADRO = "PRINCIPALE QUADRO";
	public static final String TASTO_QUADRO = "QUADRO";
	public static final String TASTO_PIU_QUADRO = "PIU QUADRO";
	public static final String TASTO_PIU_ARTISTA = "PIU ARTISTA";
	
	
	private JPanel pannello_sinistro = new JPanel();
	private JPanel pannello_sinistro_quadri = new JPanel();
	private JPanel pannello_sinistro_collocazione = new JPanel();
	
	private JPanel pannello_centrale = new JPanel();
	private JPanel pannello_centrale_generali = new JPanel();
	private JPanel pannello_centrale_particolari = new JPanel();
	
	private JPanel pannello_destro = new JPanel();
	private JPanel pannello_destro_generali = new JPanel();
	private JPanel pannello_destro_particolari = new JPanel();
	
	private MainFrame frame;
	private String quadro_selezionato;
	private String artista_selezionato;
	
	
	public QuadroListener(MainFrame frame) {
		this.frame = frame;
	}


	
	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent event) {
		
		String action = event.getActionCommand();
		
		
		
		if (action == TASTO_PRINCIPALE_QUADRO) {
			
			frame.pannello_superiore.removeAll();
			
			pannello_sinistro.removeAll();
			pannello_sinistro_quadri.removeAll();
			pannello_sinistro_collocazione.removeAll();
			
			pannello_centrale.removeAll();
			pannello_centrale_generali.removeAll();
			pannello_centrale_particolari.removeAll();
			
			pannello_destro.removeAll();
			pannello_destro_generali.removeAll();
			pannello_destro_particolari.removeAll();
			
			
			
			//Costruzione scheletro finestra
			
			
			
			//QUADRI
			pannello_sinistro_quadri.setLayout(new BoxLayout(pannello_sinistro_quadri, BoxLayout.Y_AXIS));
			TitledBorder title_quadri = BorderFactory.createTitledBorder("QUADRI");
			pannello_sinistro_quadri.setBorder(new CompoundBorder(title_quadri, new EmptyBorder(5, 20, 10, 20)));
			
			//COLLOCAZIONE
			pannello_sinistro_collocazione.setLayout(new BoxLayout(pannello_sinistro_collocazione, BoxLayout.Y_AXIS));
			TitledBorder title_collocazione = BorderFactory.createTitledBorder("COLLOCAZIONE");
			pannello_sinistro_collocazione.setBorder(new CompoundBorder(title_collocazione, new EmptyBorder(5, 20, 10, 20)));
			
			//INFORMAZIONI GENERALI
			pannello_centrale_generali.setLayout(new BoxLayout(pannello_centrale_generali, BoxLayout.Y_AXIS));
			TitledBorder title_generali = BorderFactory.createTitledBorder("INFORMAZIONI GENERALI");
			pannello_centrale_generali.setBorder(new CompoundBorder(title_generali, new EmptyBorder(5, 20, 10, 20)));
			
			//INFORMAZIONI PARTICOLARI
			pannello_centrale_particolari.setLayout(new BoxLayout(pannello_centrale_particolari, BoxLayout.Y_AXIS));
			TitledBorder title_particolari = BorderFactory.createTitledBorder("INFORMAZIONI PARTICOLARI");
			pannello_centrale_particolari.setBorder(new CompoundBorder(title_particolari, new EmptyBorder(5, 20, 10, 20)));
			
			//ARTISTA INFORMAZIONI GENERALI
			pannello_destro_generali.setLayout(new BoxLayout(pannello_destro_generali, BoxLayout.Y_AXIS));
			TitledBorder title_artista_generali = BorderFactory.createTitledBorder("ARTISTA INFORMAZIONI GENERALI");
			pannello_destro_generali.setBorder(new CompoundBorder(title_artista_generali, new EmptyBorder(5, 20, 10, 20)));
			
			//ARTISTA INFORMAZIONI PARTICOLARI
			pannello_destro_particolari.setLayout(new BoxLayout(pannello_destro_particolari, BoxLayout.Y_AXIS));
			TitledBorder title_artista_particolari = BorderFactory.createTitledBorder("ARTISTA INFORMAZIONI PARTICOLARI");
			pannello_destro_particolari.setBorder(new CompoundBorder(title_artista_particolari, new EmptyBorder(5, 20, 10, 20)));
			
			
			//PANNELLO SINISTRO
			pannello_sinistro.setLayout(new BorderLayout());
			pannello_sinistro.add(pannello_sinistro_quadri, BorderLayout.CENTER);
			pannello_sinistro.add(pannello_sinistro_collocazione, BorderLayout.SOUTH);
			
			//PANNELLO CENTRALE
			pannello_centrale.setLayout(new GridLayout(2,1));
			pannello_centrale.add(pannello_centrale_generali);
			pannello_centrale.add(pannello_centrale_particolari);
			
			//PANNELLO DESTRO
			pannello_destro.setLayout(new GridLayout(2,1));
			pannello_destro.add(pannello_destro_generali);
			pannello_destro.add(pannello_destro_particolari);
			
			//PANNELLO COMPLETO
			frame.pannello_superiore.setLayout(new GridLayout(1, 2));
			frame.pannello_superiore.add(pannello_sinistro);
			frame.pannello_superiore.add(pannello_centrale);
			frame.pannello_superiore.add(pannello_destro);
			
			
			Collection<String> quadri = QuadroController.getCodiceTutti();
			if (quadri == null) return;
			Iterator<String> it = quadri.iterator();
			while (it.hasNext()) {
				JButton button = new JButton(String.valueOf(it.next()));
				button.addActionListener(frame.quadro_listener);
				button.setActionCommand(QuadroListener.TASTO_QUADRO);
				pannello_sinistro_quadri.add(button);
			}
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
			
		}
		
		else if (action == TASTO_QUADRO) {
			
			pannello_sinistro_collocazione.removeAll();
			pannello_centrale_generali.removeAll();
			pannello_centrale_particolari.removeAll();
			pannello_destro_generali.removeAll();
			pannello_destro_particolari.removeAll();
			
			
			JButton button = (JButton)event.getSource();
			quadro_selezionato = button.getText();
			
			JTextArea text_area = new JTextArea(QuadroController.getQuadroGenerali(quadro_selezionato));
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_centrale_generali.add(scroll);
			
			JButton butt_par = new JButton("Piu' informazioni");
			butt_par.addActionListener(frame.quadro_listener);
			butt_par.setActionCommand(QuadroListener.TASTO_PIU_QUADRO);
			pannello_centrale_particolari.add(butt_par);
			
			
			artista_selezionato = QuadroController.getCodiceArtista(quadro_selezionato);
			
			JTextArea text_area_artista = new JTextArea(ArtistaController.getArtistaGenerali(artista_selezionato));
			text_area.setEditable(false);
			JScrollPane scroll_artista = new JScrollPane(text_area_artista);
			pannello_destro_generali.add(scroll_artista);
			
			JButton butt_par_artista = new JButton("Piu' informazioni");
			butt_par_artista.addActionListener(frame.quadro_listener);
			butt_par_artista.setActionCommand(QuadroListener.TASTO_PIU_ARTISTA);
			pannello_destro_particolari.add(butt_par_artista);
			
			
			JTextArea text_area_sala = new JTextArea(QuadroController.getCollocazione(quadro_selezionato));
			text_area_sala.setEditable(false);
			JScrollPane scroll_sala = new JScrollPane(text_area_sala);
			pannello_sinistro_collocazione.add(scroll_sala);
			
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		else if (action == TASTO_PIU_QUADRO) {
			
			pannello_centrale_particolari.removeAll();

			JTextArea text_area = new JTextArea(QuadroController.getQuadroParticolari(quadro_selezionato));
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_centrale_particolari.add(scroll);
			
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		else if (action == TASTO_PIU_ARTISTA) {
			
			pannello_destro_particolari.removeAll();

			JTextArea text_area = new JTextArea(ArtistaController.getArtistaParticolari(artista_selezionato));
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_destro_particolari.add(scroll);
			
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
			
			
			
		
		
	}


}
