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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PhaseUpdate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField phaseNom;
	private JDateChooser phaseDd; 
	private JDateChooser phaseDf;
	private JComboBox cmbEtat ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhaseUpdate frame = new PhaseUpdate(phaseId);
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
	public PhaseUpdate(int phaseId) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				phaseDao=new PhasesDao();
				Phase phase=phaseDao.getPhaseById(phaseId);
				phaseNom.setText(phase.getNom());
				 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			        Date startDate=null;
			        Date endDate=null;
					try {
						startDate = dateFormat.parse(phase.getDateDebut().toString());
						endDate  = dateFormat.parse(phase.getDateFin().toString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			       

			        phaseDd.setDate(startDate);
			        phaseDf.setDate(endDate);
			        cmbEtat.setSelectedItem(phase.getEtat());
			}
		});
		this.phaseId=phaseId;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 656, 552);
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
			        phase.setId(phaseId);
			        phase.setNom(phaseNom.getText());
			        phase.setDateDebut(phaseDd.getDate());
			        phase.setDateFin(phaseDf.getDate());
			        phase.setEtat(cmbEtat.getSelectedItem().toString());
			        phaseDao.update(phase);
			        JOptionPane.showMessageDialog(btnNewButton,"Modifié Avec Succées");
			        dispose();
			}
		});
		btnNewButton.setBounds(62, 470, 371, 34);
		contentPane.add(btnNewButton);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(443, 470, 144, 34);
		contentPane.add(btnAnnuler);
		
		JLabel lblDateFin_1 = new JLabel("Date Fin");
		lblDateFin_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateFin_1.setBounds(62, 357, 268, 25);
		contentPane.add(lblDateFin_1);
		
		cmbEtat = new JComboBox();
		cmbEtat.setModel(new DefaultComboBoxModel(new String[] {"En Cours...", "Terminé", "Autre"}));
		cmbEtat.setBounds(62, 397, 526, 35);
		contentPane.add(cmbEtat);
	}
	public boolean formValide() {
		if(phaseNom.getText().trim().equals(" ") || phaseDd.getDate()==null || phaseDf.getDate()==null )
			return false;
		return true;
	}
	private PhasesDao phaseDao;
	private static int phaseId;
}
