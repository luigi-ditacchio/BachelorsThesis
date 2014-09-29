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


import controller.RepertoArcheologicoController;


public class RepertoArcheologicoListener implements ActionListener {
	
	
	public static final String TASTO_PRINCIPALE_REPERTO = "PRINCIPALE REPERTO";
	public static final String TASTO_REPERTO = "REPERTO";
	public static final String TASTO_PIU_REPERTO = "PIU REPERTO";
	
	
	private JPanel pannello_sinistro = new JPanel();
	private JPanel pannello_sinistro_reperti = new JPanel();
	private JPanel pannello_sinistro_collocazione = new JPanel();
	
	private JPanel pannello_centrale = new JPanel();
	private JPanel pannello_centrale_generali = new JPanel();
	private JPanel pannello_centrale_particolari = new JPanel();
	
	private JPanel pannello_destro = new JPanel();
	
	private MainFrame frame;
	private String reperto_selezionato;
	
	
	public RepertoArcheologicoListener(MainFrame frame) {
		this.frame = frame;
	}


	
	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent event) {
		
		String action = event.getActionCommand();
		
		
		
		if (action == TASTO_PRINCIPALE_REPERTO) {
			
			frame.pannello_superiore.removeAll();
			
			pannello_sinistro.removeAll();
			pannello_sinistro_reperti.removeAll();
			pannello_sinistro_collocazione.removeAll();
			
			pannello_centrale.removeAll();
			pannello_centrale_generali.removeAll();
			pannello_centrale_particolari.removeAll();
			
			pannello_destro.removeAll();
			
			//Costruzione scheletro finestra
			
			
			
			//REPERTI
			pannello_sinistro_reperti.setLayout(new BoxLayout(pannello_sinistro_reperti, BoxLayout.Y_AXIS));
			TitledBorder title_reperti = BorderFactory.createTitledBorder("REPERTI ARCHEOLOGICI");
			pannello_sinistro_reperti.setBorder(new CompoundBorder(title_reperti, new EmptyBorder(5, 20, 10, 20)));
			
			//COLLOCAZIONE
			pannello_sinistro_collocazione.setLayout(new BoxLayout(pannello_sinistro_collocazione, BoxLayout.Y_AXIS));
			TitledBorder title_collocazione = BorderFactory.createTitledBorder("COLLOCAZIONE");
			pannello_sinistro_collocazione.setBorder(new CompoundBorder(title_collocazione, new EmptyBorder(5, 20, 10, 20)));
			
			
			//INFORMAZIONI GENERALI
			pannello_centrale_generali.setLayout(new BoxLayout(			pannello_centrale_generali, BoxLayout.Y_AXIS));
			TitledBorder title_generali = BorderFactory.createTitledBorder("INFORMAZIONI GENERALI");
			pannello_centrale_generali.setBorder(new CompoundBorder(title_generali, new EmptyBorder(5, 20, 10, 20)));
			
			//INFORMAZIONI PARTICOLARI
			pannello_centrale_particolari.setLayout(new BoxLayout(pannello_centrale_particolari, BoxLayout.Y_AXIS));
			TitledBorder title_particolari = BorderFactory.createTitledBorder("INFORMAZIONI PARTICOLARI");
			pannello_centrale_particolari.setBorder(new CompoundBorder(title_particolari, new EmptyBorder(5, 20, 10, 20)));
			
			//SCAVO
			pannello_destro.setLayout(new BoxLayout(pannello_destro, BoxLayout.Y_AXIS));
			TitledBorder title_scavo = BorderFactory.createTitledBorder("SCAVO");
			pannello_destro.setBorder(new CompoundBorder(title_scavo, new EmptyBorder(5, 20, 10, 20)));
			
			
			
			//PANNELLO SINISTRO
			pannello_sinistro.setLayout(new BorderLayout());
			pannello_sinistro.add(pannello_sinistro_reperti, BorderLayout.CENTER);
			pannello_sinistro.add(pannello_sinistro_collocazione, BorderLayout.SOUTH);
			
			//PANNELLO CENTRALE
			pannello_centrale.setLayout(new GridLayout(2,1));
			pannello_centrale.add(pannello_centrale_generali);
			pannello_centrale.add(pannello_centrale_particolari);
			
			
			
			//PANNELLO COMPLETO
			frame.pannello_superiore.setLayout(new GridLayout(1, 3));
			frame.pannello_superiore.add(pannello_sinistro);
			frame.pannello_superiore.add(pannello_centrale);
			frame.pannello_superiore.add(pannello_destro);
			
			
			Collection<String> reperti = RepertoArcheologicoController.getCodiceTutti();
			if (reperti == null) return;
			Iterator<String> it = reperti.iterator();
			while (it.hasNext()) {
				JButton button = new JButton(String.valueOf(it.next()));
				button.addActionListener(frame.reperto_listener);
				button.setActionCommand(RepertoArcheologicoListener.TASTO_REPERTO);
				pannello_sinistro_reperti.add(button);
			}
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
			
		}
		
		else if (action == TASTO_REPERTO) {
			
			
			pannello_sinistro_collocazione.removeAll();
			
			
			pannello_centrale_generali.removeAll();
			pannello_centrale_particolari.removeAll();
			
			pannello_destro.removeAll();
			
			
			JButton button = (JButton)event.getSource();
			reperto_selezionato = button.getText();
			
			JTextArea text_area = new JTextArea(RepertoArcheologicoController.getRepertoGenerali(reperto_selezionato));
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_centrale_generali.add(scroll);
			
			JButton butt_par = new JButton("Piu' informazioni");
			butt_par.addActionListener(frame.reperto_listener);
			butt_par.setActionCommand(RepertoArcheologicoListener.TASTO_PIU_REPERTO);
			pannello_centrale_particolari.add(butt_par);
			
			
			JTextArea text_area_scavo = new JTextArea(RepertoArcheologicoController.getScavo(reperto_selezionato));
			text_area_scavo.setEditable(false);
			JScrollPane scroll_scavo = new JScrollPane(text_area_scavo);
			pannello_destro.add(scroll_scavo);
			
			
			JTextArea text_area_collocazione = new JTextArea(RepertoArcheologicoController.getCollocazione(reperto_selezionato));
			text_area_collocazione.setEditable(false);
			JScrollPane scroll_collocazione = new JScrollPane(text_area_collocazione);
			pannello_sinistro_collocazione.add(scroll_collocazione);
			
			
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		else if (action == TASTO_PIU_REPERTO) {
			
			pannello_centrale_particolari.removeAll();
			

			JTextArea text_area = new JTextArea(RepertoArcheologicoController.getRepertoParticolari(reperto_selezionato));
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_centrale_particolari.add(scroll);
			
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
			
			
			
		
		
	}
	
	
	

}
