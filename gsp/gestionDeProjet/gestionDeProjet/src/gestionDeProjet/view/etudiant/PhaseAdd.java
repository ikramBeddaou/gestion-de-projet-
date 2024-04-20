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

public class PhaseAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField phaseNom;
	private JDateChooser phaseDd; 
	private JDateChooser phaseDf;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhaseAdd frame = new PhaseAdd(projetId,equipeId);
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
	public PhaseAdd(int projetId, int equipeId) {
		this.projetId=projetId;
		this.equipeId=equipeId;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 656, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(62, 68, 268, 14);
		contentPane.add(lblNewLabel);
		
		phaseNom = new JTextField();
		phaseNom.setBounds(62, 102, 526, 35);
		contentPane.add(phaseNom);
		phaseNom.setColumns(10);
		
		JLabel lblDateDebut = new JLabel("Date Debut");
		lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateDebut.setBounds(62, 164, 268, 25);
		contentPane.add(lblDateDebut);
		
	    phaseDd = new JDateChooser();
		phaseDd.setBounds(62, 203, 526, 35);
		contentPane.add(phaseDd);
		
		JLabel lblDateFin = new JLabel("Date Fin");
		lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateFin.setBounds(62, 260, 268, 25);
		contentPane.add(lblDateFin);
		
		phaseDf = new JDateChooser();
		phaseDf.setBounds(62, 299, 526, 35);
		contentPane.add(phaseDf);
		
		JButton btnNewButton = new JButton("Sauvegarder");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   
			        phaseDao=new PhasesDao();
			        if(!formValide()) {
			        	JOptionPane.showMessageDialog(null, "Forme Invalide");
			        	return;
			        }
			        Phase phase=new Phase();
			        Projet projet =new Projet();
			        projet.setId(projetId);
			        phase.setNom(phaseNom.getText());
			        phase.setDateDebut(phaseDd.getDate());
			        phase.setDateFin(phaseDf.getDate());
			        phase.setEtat("En Cours");
			        phase.setProjet(projet);
			        phaseId=phaseDao.add(phase);
			        
			        Object[] options = {"Sauvegarder et ajouter des taches", "Sauvegarder sans taches"};
			        int choice = JOptionPane.showOptionDialog(null, "Ajouter Des Taches Pour Cette Phase",
			                "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);   
			        
			        if(choice==JOptionPane.OK_OPTION) {
			        	
			        	TacheAdd tacheAdd=new TacheAdd(phaseId,equipeId);
			        	tacheAdd.setVisible(true);
			        }
			        else {
			        	JOptionPane.showMessageDialog(null, "Ajouter Avec Succées");
			        	dispose();
			        }
			        	
			}
		});
		btnNewButton.setBounds(62, 367, 371, 34);
		contentPane.add(btnNewButton);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(444, 367, 144, 34);
		contentPane.add(btnAnnuler);
	}
	public boolean formValide() {
		if(phaseNom.getText().trim().equals(" ") || phaseDd.getDate()==null || phaseDf.getDate()==null )
			return false;
		return true;
	}
	private static int projetId;
	private static int equipeId;
	private PhasesDao phaseDao;
	private int phaseId;
}
