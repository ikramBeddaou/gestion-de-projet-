package gestionDeProjet.view.etudiant;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import gestionDeProjet.dao.PhasesDao;
import gestionDeProjet.model.Phase;
import gestionDeProjet.model.Projet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PhaseDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField phaseNom;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhaseDetails frame = new PhaseDetails(phaseId,equipeId);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param projetId 
	 * @param equipeId 
	 */
	public PhaseDetails(int phaseId, int equipeId) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				phaseDao=new PhasesDao();
				Phase phase=phaseDao.getPhaseById(phaseId);
				phaseNom.setText(phase.getNom());
				phaseDd.setText(phase.getDateDebut().toString());
				phaseDf.setText(phase.getDateFin().toString());
				phaseEtat.setText(phase.getEtat());
			}
		});
		this.phaseId=phaseId;
		this.equipeId=equipeId;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 656, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(62, 68, 268, 14);
		contentPane.add(lblNewLabel);
		
		phaseNom = new JTextField();
		phaseNom.setEditable(false);
		phaseNom.setBounds(62, 102, 526, 35);
		contentPane.add(phaseNom);
		phaseNom.setColumns(10);
		
		JLabel lblDateDebut = new JLabel("Date Debut");
		lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateDebut.setBounds(62, 164, 268, 25);
		contentPane.add(lblDateDebut);
		
		JLabel lblDateFin = new JLabel("Date Fin");
		lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateFin.setBounds(62, 260, 268, 25);
		contentPane.add(lblDateFin);
		
		JButton btnNewButton = new JButton("Ajouter Des Taches");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			        	TacheAdd tacheAdd=new TacheAdd(phaseId,equipeId);
			        	tacheAdd.setVisible(true);
			}
		});
		btnNewButton.setBounds(62, 476, 371, 34);
		contentPane.add(btnNewButton);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnuler.setBounds(444, 476, 144, 34);
		contentPane.add(btnAnnuler);
		
		phaseDd = new JTextField();
		phaseDd.setEditable(false);
		phaseDd.setColumns(10);
		phaseDd.setBounds(62, 200, 526, 35);
		contentPane.add(phaseDd);
		
		phaseDf = new JTextField();
		phaseDf.setEditable(false);
		phaseDf.setColumns(10);
		phaseDf.setBounds(62, 297, 526, 35);
		contentPane.add(phaseDf);
		
		JLabel lblEtat = new JLabel("Etat");
		lblEtat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEtat.setBounds(62, 353, 268, 25);
		contentPane.add(lblEtat);
		
		phaseEtat = new JTextField();
		phaseEtat.setEditable(false);
		phaseEtat.setColumns(10);
		phaseEtat.setBounds(62, 390, 526, 35);
		contentPane.add(phaseEtat);
	}

	private static int equipeId;
	private PhasesDao phaseDao;
	private static int phaseId;
	private JTextField phaseDd;
	private JTextField phaseDf;
	private JTextField phaseEtat;
}
