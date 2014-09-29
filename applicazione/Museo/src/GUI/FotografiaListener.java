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

import controller.FotografiaController;



public class FotografiaListener implements ActionListener {
	
	
	public static final String TASTO_PRINCIPALE_FOTOGRAFIA = "PRINCIPALE FOTOGRAFIA";
	public static final String TASTO_FOTOGRAFIA = "FOTOGRAFIA";
	public static final String TASTO_PIU_FOTOGRAFIA = "PIU FOTOGRAFIA";
	
	
	private JPanel pannello_sinistro = new JPanel();
	private JPanel pannello_sinistro_fotografie = new JPanel();
	private JPanel pannello_sinistro_collocazione = new JPanel();
	
	private JPanel pannello_destro = new JPanel();
	private JPanel pannello_destro_generali = new JPanel();
	private JPanel pannello_destro_particolari = new JPanel();
	
	private MainFrame frame;
	private String fotografia_selezionata;
	
	
	public FotografiaListener(MainFrame frame) {
		this.frame = frame;
	}


	
	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent event) {
		
		String action = event.getActionCommand();
		
		
		
		if (action == TASTO_PRINCIPALE_FOTOGRAFIA) {
			
			frame.pannello_superiore.removeAll();
			
			pannello_sinistro.removeAll();
			pannello_sinistro_fotografie.removeAll();
			pannello_sinistro_collocazione.removeAll();
			
			pannello_destro.removeAll();
			pannello_destro_generali.removeAll();
			pannello_destro_particolari.removeAll();
			
			//Costruzione scheletro finestra
			
			
			
			//FOTOGRAFIE
			pannello_sinistro_fotografie.setLayout(new BoxLayout(pannello_sinistro_fotografie, BoxLayout.Y_AXIS));
			TitledBorder title_fotografie = BorderFactory.createTitledBorder("FOTOGRAFIE");
			pannello_sinistro_fotografie.setBorder(new CompoundBorder(title_fotografie, new EmptyBorder(5, 20, 10, 20)));
			
			//COLLOCAZIONE
			pannello_sinistro_collocazione.setLayout(new BoxLayout(pannello_sinistro_collocazione, BoxLayout.Y_AXIS));
			TitledBorder title_collocazione = BorderFactory.createTitledBorder("COLLOCAZIONE");
			pannello_sinistro_collocazione.setBorder(new CompoundBorder(title_collocazione, new EmptyBorder(5, 20, 10, 20)));
			
			//INFORMAZIONI GENERALI
			pannello_destro_generali.setLayout(new BoxLayout(pannello_destro_generali, BoxLayout.Y_AXIS));
			TitledBorder title_generali = BorderFactory.createTitledBorder("INFORMAZIONI GENERALI");
			pannello_destro_generali.setBorder(new CompoundBorder(title_generali, new EmptyBorder(5, 20, 10, 20)));
			
			//INFORMAZIONI PARTICOLARI
			pannello_destro_particolari.setLayout(new BoxLayout(pannello_destro_particolari, BoxLayout.Y_AXIS));
			TitledBorder title_particolari = BorderFactory.createTitledBorder("INFORMAZIONI PARTICOLARI");
			pannello_destro_particolari.setBorder(new CompoundBorder(title_particolari, new EmptyBorder(5, 20, 10, 20)));
			
			
			//PANNELLO SINISTRO
			pannello_sinistro.setLayout(new BorderLayout());
			pannello_sinistro.add(pannello_sinistro_fotografie, BorderLayout.CENTER);
			pannello_sinistro.add(pannello_sinistro_collocazione, BorderLayout.SOUTH);
			
			//PANNELLO DESTRO
			pannello_destro.setLayout(new GridLayout(2,1));
			pannello_destro.add(pannello_destro_generali);
			pannello_destro.add(pannello_destro_particolari);
			
			//PANNELLO COMPLETO
			frame.pannello_superiore.setLayout(new GridLayout(1, 2));
			frame.pannello_superiore.add(pannello_sinistro);
			frame.pannello_superiore.add(pannello_destro);
			
			
			Collection<String> fotografie = FotografiaController.getCodiceTutti();
			if (fotografie == null) return;
			Iterator<String> it = fotografie.iterator();
			while (it.hasNext()) {
				JButton button = new JButton(String.valueOf(it.next()));
				button.addActionListener(frame.fotografia_listener);
				button.setActionCommand(FotografiaListener.TASTO_FOTOGRAFIA);
				pannello_sinistro_fotografie.add(button);
			}
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
			
		}
		
		else if (action == TASTO_FOTOGRAFIA) {
			
			
			pannello_destro_generali.removeAll();
			pannello_destro_particolari.removeAll();
			
			
			JButton button = (JButton)event.getSource();
			fotografia_selezionata = button.getText();
			
			JTextArea text_area = new JTextArea(FotografiaController.getFotografiaGenerali(fotografia_selezionata));
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_destro_generali.add(scroll);
			
			JButton butt_par = new JButton("Piu' informazioni");
			butt_par.addActionListener(frame.fotografia_listener);
			butt_par.setActionCommand(FotografiaListener.TASTO_PIU_FOTOGRAFIA);
			pannello_destro_particolari.add(butt_par);
			
			JTextArea text_area_sala = new JTextArea(FotografiaController.getCollocazione(fotografia_selezionata));
			text_area_sala.setEditable(false);
			JScrollPane scroll_sala = new JScrollPane(text_area_sala);
			pannello_sinistro_collocazione.add(scroll_sala);
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		else if (action == TASTO_PIU_FOTOGRAFIA) {
			
			pannello_destro_particolari.removeAll();
			

			JTextArea text_area = new JTextArea(FotografiaController.getFotografiaParticolari(fotografia_selezionata));
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_destro_particolari.add(scroll);
			
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
			
			
			
		
		
	}

}
