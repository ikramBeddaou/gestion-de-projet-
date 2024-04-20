package gestionDeProjet.view.prof;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Panel;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import gestionDeProjet.dao.AvancementDao;
import gestionDeProjet.dao.ClasseDao;
import gestionDeProjet.dao.EquipeDao;
import gestionDeProjet.dao.IndicateurDao;
import gestionDeProjet.dao.PhasesDao;
import gestionDeProjet.dao.ProjetDao;
import gestionDeProjet.dao.TacheDao;
import gestionDeProjet.model.Avancement;
import gestionDeProjet.model.Classe;
import gestionDeProjet.model.Equipe;
import gestionDeProjet.model.Indicateur;
import gestionDeProjet.model.Phase;
import gestionDeProjet.model.Projet;
import gestionDeProjet.model.Tache;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AvancementProjet extends JInternalFrame {

	private static final long serialVersionUID = 1L;
    private JComboBox cmbClasse;
    private JComboBox cmbEquipe;
    private JComboBox cmbProjet;
    private JComboBox cmbPhase;
    private JComboBox cmbTache ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvancementProjet frame = new AvancementProjet(profId);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param profId 
	 */
	public AvancementProjet(int profId) {
		this.profId=profId;
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				fillClasseCombobox();
			}
		});
		setBounds(100, 100, 864, 603);
		getContentPane().setLayout(null);
		
		JLabel lblProjet = new JLabel("Classe");
		lblProjet.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblProjet.setBounds(69, 38, 174, 22);
		getContentPane().add(lblProjet);
		
		cmbClasse = new JComboBox();
		cmbClasse.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String selected = cmbClasse.getSelectedItem().toString();
			        if (!selected.equals("Choisir Equipe")) {
			            int equipeId = Integer.parseInt(selected.split(":")[0].trim());
			            fillEquipeCombobox(equipeId);
			        }
					
				} catch (Exception e2) {
				//	JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		cmbClasse.setBounds(67, 68, 280, 24);
		getContentPane().add(cmbClasse);
		
		Panel panel = new Panel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(67, 291, 711, 273);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblResponsable = new JLabel("Responsable :");
		lblResponsable.setBounds(28, 43, 119, 24);
		lblResponsable.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(lblResponsable);
		
		JLabel lblRespo = new JLabel("");
		lblRespo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblRespo.setBounds(157, 43, 235, 24);
		panel.add(lblRespo);
		
		JLabel lblDateDebut = new JLabel("Date Debut :");
		lblDateDebut.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDateDebut.setBounds(28, 102, 119, 24);
		panel.add(lblDateDebut);
		
		JLabel lblDd = new JLabel("");
		lblDd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDd.setBounds(157, 102, 106, 24);
		panel.add(lblDd);
		
		JLabel lblDateFin = new JLabel("Date Fin :");
		lblDateFin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDateFin.setBounds(28, 154, 119, 24);
		panel.add(lblDateFin);
		
		JLabel lblDf = new JLabel("");
		lblDf.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDf.setBounds(157, 154, 106, 24);
		panel.add(lblDf);
		
		JLabel lblTempsPasse = new JLabel("Temps Passe :");
		lblTempsPasse.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTempsPasse.setBounds(28, 215, 137, 24);
		panel.add(lblTempsPasse);
		
		JLabel lblTp = new JLabel("");
		lblTp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTp.setBounds(157, 215, 106, 24);
		panel.add(lblTp);
		
		JLabel lblResteAFaire = new JLabel("Avancement :");
		lblResteAFaire.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblResteAFaire.setBounds(402, 43, 119, 24);
		panel.add(lblResteAFaire);
		
		JLabel lblAvc = new JLabel("");
		lblAvc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAvc.setBounds(595, 43, 106, 24);
		panel.add(lblAvc);
		
		JLabel lblProductivite = new JLabel("productivite :");
		lblProductivite.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblProductivite.setBounds(402, 102, 119, 24);
		panel.add(lblProductivite);
		
		JLabel lblprod = new JLabel("");
		lblprod.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblprod.setBounds(595, 102, 106, 24);
		panel.add(lblprod);
		
		JLabel lblPerformance = new JLabel("Performance :\r\n");
		lblPerformance.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPerformance.setBounds(402, 154, 119, 24);
		panel.add(lblPerformance);
		
		JLabel lblPerfom = new JLabel("");
		lblPerfom.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPerfom.setBounds(595, 154, 106, 24);
		panel.add(lblPerfom);
		
		JLabel lblTauxDoccupation = new JLabel("Taux d'occupation :");
		lblTauxDoccupation.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTauxDoccupation.setBounds(402, 215, 171, 24);
		panel.add(lblTauxDoccupation);
		
		JLabel lblTd = new JLabel("");
		lblTd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTd.setBounds(595, 215, 106, 24);
		panel.add(lblTd);
		
		JLabel lblProjet_4 = new JLabel("Projet");
		lblProjet_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblProjet_4.setBounds(500, 38, 174, 22);
		getContentPane().add(lblProjet_4);
		

        cmbProjet = new JComboBox();
        cmbProjet.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		try {
					String selected = cmbProjet.getSelectedItem().toString();
			        if (!selected.equals("Choisir Equipe")) {
			            int projetId = Integer.parseInt(selected.split(":")[0].trim());
			            fillPhasesCombobox(projetId);
			        }
					
				} catch (Exception e2) {
				//	JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
        	
        });
		cmbProjet.setBounds(498, 68, 280, 24);
		getContentPane().add(cmbProjet);
		
		JLabel lblPhase = new JLabel("Equipe");
		lblPhase.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPhase.setBounds(71, 108, 174, 22);
		getContentPane().add(lblPhase);
		
	    cmbEquipe = new JComboBox();
	    cmbEquipe.addItemListener(new ItemListener() {
	    	public void itemStateChanged(ItemEvent e) {
	    		try {
					String selected = cmbEquipe.getSelectedItem().toString();
			        if (!selected.equals("Choisir Equipe")) {
			            int equipeId = Integer.parseInt(selected.split(":")[0].trim());
			            fillProjetCombobox(equipeId);
			        }
					
				} catch (Exception e2) {
					//JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
	
	    });
	
		cmbEquipe.setBounds(69, 138, 280, 24);
		getContentPane().add(cmbEquipe);
		
		JLabel lblTache = new JLabel("Phase");
		lblTache.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTache.setBounds(500, 108, 174, 22);
		getContentPane().add(lblTache);
		
		cmbPhase = new JComboBox();
		cmbPhase.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String selected = cmbPhase.getSelectedItem().toString();
			        if (!selected.equals("Choisir Equipe")) {
			            int phaseId = Integer.parseInt(selected.split(":")[0].trim());
			            fillTacheCombobox(phaseId);
			        }
					
				} catch (Exception e2) {
					//JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		cmbPhase.setBounds(498, 138, 280, 24);
		getContentPane().add(cmbPhase);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);
		panel_4.setBounds(66, 244, 712, 26);
		getContentPane().add(panel_4);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Details");
		lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1.setBounds(10, 0, 142, 20);
		panel_4.add(lblNewLabel_4_1_1_1);
		
		JLabel lblTache_2 = new JLabel("Tache");
		lblTache_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTache_2.setBounds(71, 179, 707, 22);
		getContentPane().add(lblTache_2);
		
		cmbTache = new JComboBox();
		cmbTache.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TacheDao tacheDao=new TacheDao();
				try {
					String selected = cmbTache.getSelectedItem().toString();
			        if (!selected.equals("Choisir Equipe")) {
			            int tacheId = Integer.parseInt(selected.split(":")[0].trim());
			            tache=tacheDao.getTacheById(tacheId);
			            lblRespo.setText(tache.getRespo().getEtudiant().getUser().getNom()+" "+tache.getRespo().getEtudiant().getUser().getNom());
			            lblDd.setText(tache.getDateDebut().toString());
			            lblDf.setText(tache.getDateFin().toString());
			            AvancementDao avcDao=new AvancementDao();
			            Avancement avancement =avcDao.getTotalDataForTache(tacheId);
			            lblTp.setText(avancement.getTempPasse_T()+"j");
			            lblAvc.setText(avancement.getAvancement_A()+"%");
			            IndicateurDao indiDao=new IndicateurDao();
			            Indicateur indicateur=indiDao.getIndicateurByAvcId(avancement.getId());
			            lblprod.setText(indicateur.getProductivite());
			            lblPerfom.setText(indicateur.getPerformance()+"%");
			            lblTd.setText(indicateur.getTauxDoccupation()+"%");
			            
			            
			            System.out.println(tache);
			        }
					
				} catch (Exception e2) {
					//JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		cmbTache.setBounds(69, 209, 709, 24);
		getContentPane().add(cmbTache);

	}
	public void fillClasseCombobox() {
		ClasseDao cdao=new ClasseDao();
		ArrayList<Classe> classes=cdao.getAllByProfId(profId);
		cmbClasse.removeAllItems();
		cmbClasse.addItem("Choisir Une Classe");
		for(Classe c:classes) {
			cmbClasse.addItem(c.getId()+" :"+c.getNom());
		}
		
	}
	public void fillEquipeCombobox(int classeId) {
		EquipeDao edao=new EquipeDao();
		ArrayList<Equipe> eqps=edao.getAllEquipesByClasseId(classeId);
		cmbEquipe.removeAllItems();
		if(!eqps.isEmpty()) {
			for(Equipe e:eqps) 
				cmbEquipe.addItem(e.getId()+" :"+e.getNom());
		}else {
			cmbProjet.removeAllItems();
			cmbPhase.removeAllItems();
			cmbTache.removeAllItems();
		}
	}
	
	public void fillProjetCombobox(int equipeId) {
		  pdao = new ProjetDao();
		    Projet projet = pdao.getByEquipeId(equipeId);
		    cmbProjet.removeAllItems();
		    if (projet != null) 
		        cmbProjet.addItem(projet.getId() + " :" + projet.getNom());
		    
		    else 
		    	cmbProjet.removeAllItems();
		    

		
	}
	public void fillPhasesCombobox(int projetId) {
		PhasesDao phaseDao=new PhasesDao();
		ArrayList<Phase> phases=phaseDao.getAllByProjetId(projetId);
		cmbPhase.removeAllItems();
		if(phases!=null)
		for(Phase phase:phases) {
			cmbPhase.addItem(phase.getId()+" :"+phase.getNom());
		}
		else
			cmbPhase.removeAllItems();
		
	}
	public void fillTacheCombobox(int phaseId) {
		TacheDao tacheDao=new TacheDao();
		ArrayList<Tache> taches=tacheDao.getAllTacheByPhaseId(phaseId);
		System.err.println("Tahces :"+taches);
		cmbTache.removeAllItems();
		if(taches!=null)
		for(Tache tache:taches) {
			cmbTache.addItem(tache.getId()+" :"+tache.getNom());
		}
		else
			cmbTache.removeAllItems();
		
	}
	    private Tache tache;
		private static int profId;
		private ProjetDao pdao;
}
