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

import controller.FotografiaController;
import controller.QuadroController;
import controller.RepertoArcheologicoController;
import controller.SalaController;
import controller.StatuaController;

public class SalaListener implements ActionListener {
	
	public static final String TASTO_PRINCIPALE_SALA = "PRINCIPALE SALA";
	public static final String TASTO_PIANO = "PIANO";
	public static final String TASTO_SALA = "SALA";
	public static final String TASTO_OPERA = "OPERA";
	public static final String TASTO_PIU_OPERA = "PIU OPERA";
	
	
	private MainFrame frame;
	private int piano_selezionato;
	private int numero_selezionato;
	private String opera_selezionata;
	
	
	private JPanel pannello_sinistro = new JPanel();
	private JPanel pannello_sinistro_piani = new JPanel();
	private JPanel pannello_sinistro_sale = new JPanel();
	
	private JPanel pannello_centrale = new JPanel();
	private JPanel pannello_centrale_informazioni = new JPanel();
	private JPanel pannello_centrale_opere = new JPanel();
	
	private JPanel pannello_destro = new JPanel();
	private JPanel pannello_destro_generali = new JPanel();
	private JPanel pannello_destro_particolari = new JPanel();
	
	
	public SalaListener(MainFrame frame) {
		this.frame = frame;
	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent event) {
		
		String action = event.getActionCommand();
		
		
		if (action == TASTO_PRINCIPALE_SALA) {
			
			//Costruisce lo scheletro della finestra
			
			frame.pannello_superiore.removeAll();
			
			pannello_sinistro.removeAll();
			pannello_sinistro_piani.removeAll();
			pannello_sinistro_sale.removeAll();
			
			pannello_centrale.removeAll();
			pannello_centrale_informazioni.removeAll();
			pannello_centrale_opere.removeAll();
			
			pannello_destro.removeAll();
			pannello_destro_generali.removeAll();
			pannello_destro_particolari.removeAll();
			
			
			
			//PIANI
			pannello_sinistro_piani.setLayout(new BoxLayout(pannello_sinistro_piani, BoxLayout.Y_AXIS));
			TitledBorder title_piani = BorderFactory.createTitledBorder("PIANI");
			pannello_sinistro_piani.setBorder(new CompoundBorder(title_piani, new EmptyBorder(5, 20, 10, 20)));
			
			//SALE
			pannello_sinistro_sale.setLayout(new BoxLayout(pannello_sinistro_sale, BoxLayout.Y_AXIS));
			TitledBorder title_sale = BorderFactory.createTitledBorder("SALE");
			pannello_sinistro_sale.setBorder(new CompoundBorder(title_sale, new EmptyBorder(5, 20, 10, 20)));
			
			//INFORMAZIONI
			pannello_centrale_informazioni.setLayout(new BoxLayout(pannello_centrale_informazioni, BoxLayout.Y_AXIS));
			TitledBorder title_informazioni = BorderFactory.createTitledBorder("INFORMAZIONI");
			pannello_centrale_informazioni.setBorder(new CompoundBorder(title_informazioni, new EmptyBorder(5, 20, 10, 20)));
			
			//OPERE
			pannello_centrale_opere.setLayout(new BoxLayout(pannello_centrale_opere, BoxLayout.Y_AXIS));
			TitledBorder title_opere = BorderFactory.createTitledBorder("OPERE");
			pannello_centrale_opere.setBorder(new CompoundBorder(title_opere, new EmptyBorder(5, 20, 10, 20)));
			
			//INFORMAZIONI GENERALI OPERA
			pannello_destro_generali.setLayout(new BoxLayout(pannello_destro_generali, BoxLayout.Y_AXIS));
			TitledBorder title_generali = BorderFactory.createTitledBorder("INFORMAZIONI GENERALI OPERA");
			pannello_destro_generali.setBorder(new CompoundBorder(title_generali, new EmptyBorder(5, 20, 10, 20)));
			
			//INFORMAZIONI PARTICOLARI OPERA
			pannello_destro_particolari.setLayout(new BoxLayout(pannello_destro_particolari, BoxLayout.Y_AXIS));
			TitledBorder title_particolari = BorderFactory.createTitledBorder("INFORMAZIONI PARTICOLARI OPERA");
			pannello_destro_particolari.setBorder(new CompoundBorder(title_particolari, new EmptyBorder(5, 20, 10, 20)));
			
			
			
			//PANNELLO SINISTRO
			pannello_sinistro.setLayout(new GridLayout(2,1));
			pannello_sinistro.add(pannello_sinistro_piani);
			pannello_sinistro.add(pannello_sinistro_sale);
			
			//PANNELLO CENTRALE
			pannello_centrale.setLayout(new GridLayout(2,1));
			pannello_centrale.add(pannello_centrale_informazioni);
			pannello_centrale.add(pannello_centrale_opere);
			
			//PANNELLO DESTRO
			pannello_destro.setLayout(new GridLayout(2,1));
			pannello_destro.add(pannello_destro_generali);
			pannello_destro.add(pannello_destro_particolari);
			
			
			//PANNELLO COMPLETO
			frame.pannello_superiore.setLayout(new GridLayout(1, 3));
			frame.pannello_superiore.add(pannello_sinistro);
			frame.pannello_superiore.add(pannello_centrale);
			frame.pannello_superiore.add(pannello_destro);
			
			
			Collection<Integer> piani = SalaController.getPianoTutti();
			if (piani == null) return;
			Iterator<Integer> it = piani.iterator();
			while (it.hasNext()) {
				JButton button = new JButton(String.valueOf(it.next()));
				button.addActionListener(frame.sala_listener);
				button.setActionCommand(SalaListener.TASTO_PIANO);
				pannello_sinistro_piani.add(button);
			}
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		
		
		else if (action == TASTO_PIANO) {
			
			
			pannello_sinistro_sale.removeAll();
			
			pannello_centrale_informazioni.removeAll();
			pannello_centrale_opere.removeAll();
			
			pannello_destro_generali.removeAll();
			pannello_destro_particolari.removeAll();
			
			
			JButton premuto = (JButton)event.getSource();
			piano_selezionato = Integer.parseInt(premuto.getText());
			
			Collection<Integer> sale = SalaController.getSalesuPiano(piano_selezionato);
			if (sale == null) return;
			Iterator<Integer> it = sale.iterator();
			while (it.hasNext()) {
				JButton button = new JButton(String.valueOf(it.next()));
				button.addActionListener(frame.sala_listener);
				button.setActionCommand(SalaListener.TASTO_SALA);
				pannello_sinistro_sale.add(button);
			}

			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		
		else if (action == TASTO_SALA) {
			
			pannello_centrale_informazioni.removeAll();
			pannello_centrale_opere.removeAll();
			
			pannello_destro_generali.removeAll();
			pannello_destro_particolari.removeAll();
			
			
			JButton premuto = (JButton)event.getSource();
			numero_selezionato = Integer.parseInt(premuto.getText());
			
			//Aggiunge informazioni
			JTextArea text_area = new JTextArea(SalaController.getSala(numero_selezionato, piano_selezionato));
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_centrale_informazioni.add(scroll);
			
			//Aggiunge opere
			Collection<String> opere = SalaController.getOpereinSala(numero_selezionato, piano_selezionato);
			if (opere == null) return;
			Iterator<String> it = opere.iterator();
			
			while (it.hasNext()) {
				JButton butt = new JButton(it.next());
				butt.addActionListener(frame.sala_listener);
				butt.setActionCommand(SalaListener.TASTO_OPERA);
				pannello_centrale_opere.add(butt);
			}
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
			
		
		}
		
		
		else if (action == TASTO_OPERA) {
			
			pannello_destro_generali.removeAll();
			pannello_destro_particolari.removeAll();
			
			JButton button = (JButton)event.getSource();
			opera_selezionata = button.getText();
			
			String opera_generali = null;
			opera_generali = QuadroController.getQuadroGenerali(opera_selezionata);
			if (opera_generali == null) {
				opera_generali = StatuaController.getStatuaGenerali(opera_selezionata);
				if (opera_generali == null) {
					opera_generali = FotografiaController.getFotografiaGenerali(opera_selezionata);
					if (opera_generali == null) {
						opera_generali = RepertoArcheologicoController.getRepertoGenerali(opera_selezionata);
						if (opera_generali == null) {
							return;
						}
					}
				}
			}
			JTextArea text_area = new JTextArea(opera_generali);
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_destro_generali.add(scroll);
			
			JButton butt_par = new JButton("Piu' informazioni");
			butt_par.addActionListener(frame.sala_listener);
			butt_par.setActionCommand(SalaListener.TASTO_PIU_OPERA);
			pannello_destro_particolari.add(butt_par);
			
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}
		
		
		
		else if (action == TASTO_PIU_OPERA) {
			
			pannello_destro_particolari.removeAll();
			
			String opera_particolari = null;
			opera_particolari = QuadroController.getQuadroParticolari(opera_selezionata);
			if (opera_particolari == null) {
				opera_particolari = StatuaController.getStatuaParticolari(opera_selezionata);
				if (opera_particolari == null) {
					opera_particolari = FotografiaController.getFotografiaParticolari(opera_selezionata);
					if (opera_particolari == null) {
						opera_particolari = RepertoArcheologicoController.getRepertoParticolari(opera_selezionata);
						if (opera_particolari == null) {
							return;
						}
					}
				}
			}
			JTextArea text_area = new JTextArea(opera_particolari);
			text_area.setEditable(false);
			JScrollPane scroll = new JScrollPane(text_area);
			pannello_destro_particolari.add(scroll);
			
			
			frame.pannello_superiore.revalidate();
			frame.pannello_superiore.repaint();
		}

			
			
	}

}
