package GUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	static final String titolo = "Applicazione per la visita personalizzata di un museo";
	static final int larghezza = 1024;
	static final int altezza = 768;
	
	
	//PANNELLO SUPERIORE
	static JPanel pannello_superiore = new JPanel();

	
	//PANNELLO INFERIORE
	static JPanel pannello_inferiore = new JPanel();
	
	
	//PANNELLO REPERTI
	static JPanel pannello_reperti = new JPanel();
	static TitledBorder bordo_reperti = BorderFactory.createTitledBorder("REPERTI");
	static JButton tasto_reperti = new JButton("Reperti Archeologici");
	
	
	//PANNELLO FOTOGRAFIE
	static JPanel pannello_fotografie = new JPanel();
	static TitledBorder bordo_fotografie = BorderFactory.createTitledBorder("FOTOGRAFIE");
	static JButton tasto_fotografie = new JButton("Fotografie");
	
	
	//PANNELLO OPERE D'ARTE
	static JPanel pannello_opere = new JPanel();
	static TitledBorder bordo_opere = BorderFactory.createTitledBorder("OPERE D'ARTE");
	static JButton tasto_quadri= new JButton("Quadri");
	static JButton tasto_statue = new JButton("Statue");
	
	
	//PANNELLO VISITA
	static JPanel pannello_visita = new JPanel();
	static TitledBorder bordo_visita = BorderFactory.createTitledBorder("VISITA");
	static JButton tasto_sale = new JButton("Sale");
	
	
	//PANNELLO PERSONAGGI
	static JPanel pannello_personaggi = new JPanel();
	static TitledBorder bordo_personaggi = BorderFactory.createTitledBorder("PERSONAGGI");
	static JButton tasto_artisti = new JButton("Artisti");
	
	

	
	
	//LISTENER
	RepertoArcheologicoListener reperto_listener = new RepertoArcheologicoListener(this);
	FotografiaListener fotografia_listener = new FotografiaListener(this);
	QuadroListener quadro_listener = new QuadroListener(this);
	StatuaListener statua_listener = new StatuaListener(this);
	ArtistaListener artista_listener = new ArtistaListener(this);
	SalaListener sala_listener = new SalaListener(this);
	

	
	
	
	public MainFrame() {
		
		super(titolo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(larghezza,altezza);
		
		
		//PANNELLO REPERTI
		bordo_reperti.setTitleJustification(TitledBorder.CENTER);
		pannello_reperti.setLayout(new BoxLayout(pannello_reperti, BoxLayout.Y_AXIS));
		pannello_reperti.setBorder(new CompoundBorder(bordo_reperti, new EmptyBorder(5, 20, 10, 20)));
		tasto_reperti.setAlignmentX(CENTER_ALIGNMENT);
		
		tasto_reperti.addActionListener(reperto_listener);
		tasto_reperti.setActionCommand(RepertoArcheologicoListener.TASTO_PRINCIPALE_REPERTO);
		
		pannello_reperti.add(tasto_reperti);

		
		//PANNELLO FOTOGRAFIE
		bordo_fotografie.setTitleJustification(TitledBorder.CENTER);
		pannello_fotografie.setLayout(new BoxLayout(pannello_fotografie, BoxLayout.Y_AXIS));
		pannello_fotografie.setBorder(new CompoundBorder(bordo_fotografie, new EmptyBorder(5, 20, 10, 20)));
		tasto_fotografie.setAlignmentX(CENTER_ALIGNMENT);
		
		tasto_fotografie.addActionListener(fotografia_listener);
		tasto_fotografie.setActionCommand(FotografiaListener.TASTO_PRINCIPALE_FOTOGRAFIA);
		
		pannello_fotografie.add(tasto_fotografie);
		
		
		//PANNELLO OPERE D'ARTE
		bordo_opere.setTitleJustification(TitledBorder.CENTER);
		pannello_opere.setLayout(new BoxLayout(pannello_opere, BoxLayout.Y_AXIS));
		pannello_opere.setBorder(new CompoundBorder(bordo_opere, new EmptyBorder(5, 20, 10, 20)));
		tasto_quadri.setAlignmentX(CENTER_ALIGNMENT);
		tasto_statue.setAlignmentX(CENTER_ALIGNMENT);
		
		tasto_quadri.addActionListener(quadro_listener);
		tasto_quadri.setActionCommand(QuadroListener.TASTO_PRINCIPALE_QUADRO);
		tasto_statue.addActionListener(statua_listener);
		tasto_statue.setActionCommand(StatuaListener.TASTO_PRINCIPALE_STATUA);
		
		pannello_opere.add(tasto_quadri);
		pannello_opere.add(tasto_statue);
		
		
		//PANNELLO VISITA
		bordo_visita.setTitleJustification(TitledBorder.CENTER);
		pannello_visita.setLayout(new BoxLayout(pannello_visita, BoxLayout.Y_AXIS));
		pannello_visita.setBorder(new CompoundBorder(bordo_visita, new EmptyBorder(5, 20, 10, 20)));
		tasto_sale.setAlignmentX(CENTER_ALIGNMENT);
		
		tasto_sale.addActionListener(sala_listener);
		tasto_sale.setActionCommand(SalaListener.TASTO_PRINCIPALE_SALA);
		
		pannello_visita.add(tasto_sale);

		
		
		//PANNELLO PERSONAGGI
		bordo_personaggi.setTitleJustification(TitledBorder.CENTER);
		pannello_personaggi.setLayout(new BoxLayout(pannello_personaggi, BoxLayout.Y_AXIS));
		pannello_personaggi.setBorder(new CompoundBorder(bordo_personaggi, new EmptyBorder(5, 20, 10, 20)));
		tasto_artisti.setAlignmentX(CENTER_ALIGNMENT);
		
		tasto_artisti.addActionListener(artista_listener);
		tasto_artisti.setActionCommand(ArtistaListener.TASTO_PRINCIPALE_ARTISTA);
		
		pannello_personaggi.add(tasto_artisti);
		
		

		
		
		
		
		//PANNELLO INFERIORE
		pannello_inferiore.setLayout(new BoxLayout(pannello_inferiore, BoxLayout.X_AXIS));
		pannello_inferiore.add(pannello_reperti);
		pannello_inferiore.add(pannello_fotografie);
		pannello_inferiore.add(pannello_opere);
		pannello_inferiore.add(pannello_visita);
		pannello_inferiore.add(pannello_personaggi);
		
		
		
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(pannello_inferiore, BorderLayout.SOUTH);
		contentPane.add(pannello_superiore, BorderLayout.CENTER);
		
		
		setVisible(true);
	}

}
