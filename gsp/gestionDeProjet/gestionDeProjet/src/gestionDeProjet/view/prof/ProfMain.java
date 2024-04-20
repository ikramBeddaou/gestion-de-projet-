package gestionDeProjet.view.prof;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;

public class ProfMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfMain frame = new ProfMain(profId);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProfMain(int id) {
		profId=id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1107, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane container = new JDesktopPane();
		container.setBounds(235, 0, 868, 564);
		contentPane.add(container);
		container.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 236, 564);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Projet");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjetIHM projet=new ProjetIHM(profId);
				projet.setVisible(true);
				container.removeAll();
				container.add(projet);
			}
		});
		btnNewButton.setBounds(38, 66, 160, 23);
		panel.add(btnNewButton);
		
		JButton av_projet = new JButton("Anacement des Projets");
		av_projet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AvancementProjet avcProjetIHM=new AvancementProjet(profId);
				avcProjetIHM.setVisible(true);
				container.removeAll();				
				container.add(avcProjetIHM);
			}
		});
		av_projet.setBackground(Color.WHITE);
		av_projet.setBounds(38, 285, 160, 23);
		panel.add(av_projet);
	}
	private static int profId ;
}
