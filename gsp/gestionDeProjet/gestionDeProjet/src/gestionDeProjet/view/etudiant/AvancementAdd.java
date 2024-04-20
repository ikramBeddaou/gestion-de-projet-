package gestionDeProjet.view.etudiant;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestionDeProjet.dao.AvancementDao;
import gestionDeProjet.dao.IndicateurDao;
import gestionDeProjet.dao.TacheDao;
import gestionDeProjet.model.Avancement;
import gestionDeProjet.model.Indicateur;
import gestionDeProjet.model.Tache;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AvancementAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField avancementTp;
	private JTextField AvancementRf;
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvancementAdd frame = new AvancementAdd(tacheId);
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
	public AvancementAdd(int id) {
		this.tacheId=id;
		setFont(new Font("Times New Roman", Font.BOLD, 20));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, -37, 646, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTempsPass = new JLabel("Temps passé");
		lblTempsPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTempsPass.setBounds(50, 47, 268, 23);
		contentPane.add(lblTempsPass);
		
		avancementTp = new JTextField();
		avancementTp.setColumns(10);
		avancementTp.setBounds(50, 81, 526, 35);
		contentPane.add(avancementTp);
		
		JLabel lblResteFaire = new JLabel("Reste à faire");
		lblResteFaire.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblResteFaire.setBounds(50, 142, 268, 23);
		contentPane.add(lblResteFaire);
		
		AvancementRf = new JTextField();
		AvancementRf.setColumns(10);
		AvancementRf.setBounds(50, 176, 526, 35);
		contentPane.add(AvancementRf);
		
		JButton btnNewButton = new JButton("Sauvegarder");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!formeValide()) {
					JOptionPane.showMessageDialog(null, "Forme Invalide!!!");
				return;
			}
				Avancement avc=new Avancement();
				TacheDao tacheDao=new TacheDao();
				AvancementDao avcDao=new AvancementDao();
				IndicateurDao indicateurDao=new IndicateurDao();
				Tache tache=tacheDao.getTacheById(tacheId);
				Date datePhaseDebut=tache.getPhase().getDateDebut();
				Date datePhaseFin=tache.getPhase().getDateFin();
				long differenceInMillisecondsPhase=datePhaseFin.getTime()-datePhaseDebut.getTime();
				int temps_total_phase=(int) (differenceInMillisecondsPhase / (1000 * 60 * 60 * 24));
				Date dateDbTache =tache.getDateDebut(); 
			    Date dateDfTache =tache.getDateFin();
      
			    
			    long differenceInMillisecondsTache = dateDfTache.getTime() - dateDbTache.getTime();
				double temps_total_tache=(differenceInMillisecondsTache / (1000 * 60 * 60 * 24));
				System.out.println("temps_total_tache :"+temps_total_tache);
				System.out.println("temps_total_phase :"+temps_total_phase);
				
				try {
					
					String avcTp=avancementTp.getText();
				    String avcRf=AvancementRf.getText();
				    double avc_A=(Double.parseDouble(avcTp)/temps_total_tache)*100;
				    double result=temps_total_tache-Integer.parseInt(avcTp);
				    if(result<=0) {
				    	tacheDao.updateTacheEtatById(tache.getId());
				    	JOptionPane.showMessageDialog(null,"La Tache est Terminée");
				    }
					SimpleDateFormat f=new SimpleDateFormat("yyyy/MM/dd");
					avc.setTache(tache);
					avc.setDateAvanc(f.parse(f.format(new Date())));
					avc.setTempPasse_T(avcTp);
					avc.setResteAfaire_R(avcRf);
					avc.setAvancement_A(String.valueOf(avc_A)+"%");
					int avcId=avcDao.add(avc);
					avc.setId(avcId);
					
					// Calculer les indicateurs
		            double productivite = Double.parseDouble(avcTp) / temps_total_tache;
		            double performance = (Double.parseDouble(avcTp) / temps_total_tache)*100;
		            double tauxOccupation = (temps_total_tache / temps_total_phase)*100;
		            System.out.println("tauxOccupation :"+tauxOccupation);
		           
		            Indicateur indicateur = new Indicateur();
		            indicateur.setAvancement(avc);
		            indicateur.setResponsable(tache.getRespo());
		            indicateur.setProductivite(String.valueOf(productivite));
		            indicateur.setPerformance(String.valueOf(performance)+"%");
		            indicateur.setTauxDoccupation(String.valueOf(tauxOccupation)+"%");
					indicateurDao.add(indicateur);
					JOptionPane.showMessageDialog(null, "Ajouté Avec succées");
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}

			
		});
		btnNewButton.setBounds(50, 249, 371, 34);
		contentPane.add(btnNewButton);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(432, 249, 144, 34);
		contentPane.add(btnAnnuler);
	}
	private boolean formeValide() {
		try {
			Integer.parseInt(avancementTp.getText().trim());
			Integer.parseInt(AvancementRf.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "please enter numbers representing days");
			return false;
		}
		if(avancementTp.getText().toString().equals(" ") || AvancementRf.getText().toString().equals(" "))
		return false;
		return true;
	}
	private static int tacheId;

}
