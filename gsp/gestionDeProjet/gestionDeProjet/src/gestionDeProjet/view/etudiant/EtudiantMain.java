package gestionDeProjet.view.etudiant;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gestionDeProjet.dao.AppartientDao;
import gestionDeProjet.dao.PhasesDao;
import gestionDeProjet.dao.ProjetDao;
import gestionDeProjet.dao.TacheDao;
import gestionDeProjet.model.Equipe;
import gestionDeProjet.model.Etudiant;
import gestionDeProjet.model.Indicateur;
import gestionDeProjet.model.Phase;
import gestionDeProjet.model.Projet;
import gestionDeProjet.model.Tache;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EtudiantMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel equipe;
	private JTable tablePhases;
	private JTable tableTache;
	private JComboBox cmbPhase;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EtudiantMain frame = new EtudiantMain(etdId);
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

	public EtudiantMain(int userId) {
		etdId=userId;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1107, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 219, 637);
		contentPane.add(panel);
		panel.setLayout(null);
		

		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(206, -29, 887, 666);
		contentPane.add(tabbedPane);
		JButton btnNewButton = new JButton("Projet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnNewButton.setBounds(64, 184, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Equipe");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNewButton_1.setBounds(64, 93, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Mes Taches");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnNewButton_2.setBounds(64, 298, 89, 23);
		panel.add(btnNewButton_2);
        tabbedPane.setBorder(null);
		equipe = new JPanel();
		equipe.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		equipe.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("New tab", null, equipe, null);
		equipe.setLayout(null);
		JLabel lblNewLabel = new JLabel("Equipe");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(367, 29, 124, 56);
		equipe.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Nom D'equipe :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(36, 122, 141, 42);
		equipe.add(lblNewLabel_3);
		
		JLabel eqpNom = new JLabel("Example1");
		eqpNom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eqpNom.setBounds(257, 123, 141, 43);
		equipe.add(eqpNom);
		JLabel eqpNbMembre = new JLabel("Example1");
		eqpNbMembre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eqpNbMembre.setBounds(257, 195, 141, 43);
		equipe.add(eqpNbMembre);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nombre de Membre :");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(36, 194, 187, 42);
		equipe.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Projet :");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1_1.setBounds(36, 263, 187, 42);
		equipe.add(lblNewLabel_3_1_1);
		
		JLabel prjNom = new JLabel("Example1");
		prjNom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prjNom.setBounds(257, 263, 141, 43);
		equipe.add(prjNom);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(36, 323, 778, 26);
		equipe.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Membres d'equipe");
		lblNewLabel_4_1_1_1.setBounds(10, 0, 142, 20);
		lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(lblNewLabel_4_1_1_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(36, 374, 778, 235);
		equipe.add(scrollPane_2);
		
		tableEquipe = new JTable();
		tableEquipe.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Nom Complet", "Classe", "Email", "Telephone"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableEquipe.getColumnModel().getColumn(1).setPreferredWidth(93);
		scrollPane_2.setViewportView(tableEquipe);
		
		JPanel projet = new JPanel();
		projet.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("New tab", null, projet, null);
		projet.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Ajouter");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhaseAdd phaseAdd=new PhaseAdd(projetId,equipeId);
				phaseAdd.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(762, 399, 89, 23);
		projet.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Modifier");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tablePhases.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(null,"No row selected");
					return;
				}
		        int value = Integer.parseInt(tablePhases.getValueAt(row, 0).toString());
				PhaseUpdate phaseUpdate=new PhaseUpdate(value);
				phaseUpdate.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(762, 465, 89, 23);
		projet.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Supprimer");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tablePhases.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(null,"No row selected");
					return;
				}
		        int value = Integer.parseInt(tablePhases.getValueAt(row, 0).toString());
		        phaseDao=new PhasesDao();
		        phaseDao.delete(value);
		        phasesListing(projetId);
			}
		});
		btnNewButton_5.setBounds(762, 527, 89, 23);
		projet.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Details");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tablePhases.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(null,"No row selected");
					return;
				}
				int value = Integer.parseInt(tablePhases.getValueAt(row, 0).toString());
				PhaseDetails phaseDetails=new PhaseDetails(value,equipeId);
				phaseDetails.setVisible(true);
			}
		});
		btnNewButton_6.setBounds(762, 581, 89, 23);
		projet.add(btnNewButton_6);
		
		JLabel lblProjet = new JLabel("Projet");
		lblProjet.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjet.setBounds(356, 31, 124, 56);
		projet.add(lblProjet);
		
		JLabel lblNewLabel_3_2 = new JLabel("Titre");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_2.setBounds(23, 75, 141, 42);
		projet.add(lblNewLabel_3_2);
		
		JLabel prjTitre = new JLabel("Example1");
		prjTitre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prjTitre.setBounds(244, 76, 141, 43);
		projet.add(prjTitre);
		
		JLabel prjDd = new JLabel("Example1");
		prjDd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prjDd.setBounds(244, 116, 141, 43);
		projet.add(prjDd);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("Date Debut");
		lblNewLabel_3_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1_2.setBounds(23, 115, 187, 42);
		projet.add(lblNewLabel_3_1_2);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Encadrant");
		lblNewLabel_3_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1_1_1.setBounds(23, 193, 187, 42);
		projet.add(lblNewLabel_3_1_1_1);
		
		JLabel prjEncadrant = new JLabel("Example1");
		prjEncadrant.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prjEncadrant.setBounds(244, 193, 141, 43);
		projet.add(prjEncadrant);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBounds(23, 341, 778, 26);
		projet.add(panel_4_1);
		
		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Phases");
		lblNewLabel_4_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_1.setBounds(10, 0, 142, 20);
		panel_4_1.add(lblNewLabel_4_1_1_1_1);
		
		JLabel lblNewLabel_3_1_2_1 = new JLabel("Date Fin");
		lblNewLabel_3_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1_2_1.setBounds(23, 158, 187, 42);
		projet.add(lblNewLabel_3_1_2_1);
		
		JLabel prjDf = new JLabel("Example1");
		prjDf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prjDf.setBounds(244, 158, 141, 43);
		projet.add(prjDf);
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Description");
		lblNewLabel_3_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1_1_1_1.setBounds(23, 251, 187, 42);
		projet.add(lblNewLabel_3_1_1_1_1);
		
		JLabel prjDesc = new JLabel("Example1");
		prjDesc.setVerticalAlignment(SwingConstants.TOP);
		prjDesc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prjDesc.setBounds(244, 251, 559, 88);
		projet.add(prjDesc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 378, 729, 226);
		projet.add(scrollPane);
		
		tablePhases = new JTable();
		tablePhases.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Id", "Nom", "Date Debut", "Date Fin", "Etat", "Nombre des Taches"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tablePhases);
		
		JPanel MesTaches = new JPanel();
		MesTaches.setBackground(new Color(105, 105, 105));
		tabbedPane.addTab("New tab", null, MesTaches, null);
		MesTaches.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Tab 3");
		lblNewLabel_2.setBounds(427, 5, 28, 14);
		MesTaches.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Titre");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_2_1.setBounds(21, 89, 141, 42);
		MesTaches.add(lblNewLabel_3_2_1);
		
		JLabel prjNomInTacheTab = new JLabel("Example1");
		prjNomInTacheTab.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prjNomInTacheTab.setBounds(242, 90, 141, 43);
		MesTaches.add(prjNomInTacheTab);
		
		JLabel lblNewLabel_3_1_2_2 = new JLabel("Date Debut");
		lblNewLabel_3_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1_2_2.setBounds(21, 143, 187, 42);
		MesTaches.add(lblNewLabel_3_1_2_2);
		
		JLabel prjDdInTacheTab = new JLabel("Example1");
		prjDdInTacheTab.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prjDdInTacheTab.setBounds(242, 144, 141, 43);
		MesTaches.add(prjDdInTacheTab);
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setLayout(null);
		panel_4_1_1.setBounds(23, 331, 778, 26);
		MesTaches.add(panel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("Mes Taches");
		lblNewLabel_4_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_1_1.setBounds(10, 0, 142, 20);
		panel_4_1_1.add(lblNewLabel_4_1_1_1_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 384, 729, 226);
		MesTaches.add(scrollPane_1);
		
		tableTache = new JTable();
		tableTache.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Id", "Nom", "Date Debut", "Date Fin", "Etat", "Temps Passe", "Reste A Faire", "Avancement"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(tableTache);
		
		JLabel lblNewLabel_3_1_2_2_1 = new JLabel("Date Fin");
		lblNewLabel_3_1_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1_2_2_1.setBounds(21, 187, 187, 42);
		MesTaches.add(lblNewLabel_3_1_2_2_1);
		
		JLabel prjDfInTacheTab = new JLabel("Example1");
		prjDfInTacheTab.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prjDfInTacheTab.setBounds(242, 188, 141, 43);
		MesTaches.add(prjDfInTacheTab);
		
		JLabel lblNewLabel_3_1_2_2_1_1 = new JLabel("Phases");
		lblNewLabel_3_1_2_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1_2_2_1_1.setBounds(21, 239, 187, 42);
		MesTaches.add(lblNewLabel_3_1_2_2_1_1);
		
	    cmbPhase = new JComboBox();
	    cmbPhase.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int phaseId=Integer.parseInt(cmbPhase.getSelectedItem().toString().split(":")[0]);	   
	    		phId=phaseId;
	    		tacheListing(phaseId);
	    	}
	    });
		cmbPhase.setBounds(21, 281, 322, 28);
		MesTaches.add(cmbPhase);
		
		JButton btnNewButton_7 = new JButton("Indiquer Avancement");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableTache.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(null,"No row selected");
					return;
				}
				int value =Integer.parseInt(tableTache.getValueAt(row, 0).toString());
				AvancementAdd avencementAdd=new AvancementAdd(value);
				avencementAdd.setVisible(true);
			}
		});
		btnNewButton_7.setBounds(760, 401, 112, 48);
		MesTaches.add(btnNewButton_7);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				appDao=new AppartientDao();
				//Fill Equipe Tab
					Equipe eqp=appDao.getEquipeByEtudiantId(etdId);
					equipeId=eqp.getId();
					Projet prj=eqp.getProjet();
					projetId=prj.getId();
					String equipeNom=eqp.getNom();
					
					if(equipeNom==null)
						equipeNom="Sans Nom";
					
					eqpNom.setText(equipeNom);
					eqpNbMembre.setText(String.valueOf(eqp.getNombreMembre()));
					prjNom.setText(prj.getNom());
					equipeListing(eqp.getId());
				//End equipe Tab
				
				//Fill Projet Tab
				prjNom.setText(prj.getNom());
				prjDd.setText(String.valueOf(prj.getDateDebut()));
				prjDf.setText(String.valueOf(prj.getDateFin()));
				prjEncadrant.setText(prj.getEncadrant().getNom()+" "+prj.getEncadrant().getPrenom());
				prjDesc.setText(prj.getDescription());
				phasesListing(prj.getId());
				//End Tab Projet
				
				// Fill Tache Tab
				prjNomInTacheTab.setText(prj.getNom());
				prjDdInTacheTab.setText(String.valueOf(prj.getDateDebut()));
				prjDfInTacheTab.setText(String.valueOf(prj.getDateFin()));
				fillPhaseCombobox(prj.getId());			
				//End Tache Tab
			}
			@Override
			public void windowActivated(WindowEvent e) {
				phasesListing(projetId);
				tacheListing(phId);
			}
		});
	}
	 public void equipeListing(int id){
	    	try {
	    		appDao=new AppartientDao();
	        	ArrayList<Etudiant> etds= appDao.getAllEtudiantsByEquipeId(id);
	        	DefaultTableModel m=(DefaultTableModel)tableEquipe.getModel();
	        	m.setNumRows(0);
	        	for(Etudiant e:etds)
	        	    m.addRow(new Object[] {e.getId(),e.getUser().getNom()+" "+e.getUser().getPrenom(),e.getClasse().getNom(),e.getUser().getEmail(),e.getUser().getTelephone()});	
	    	} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
	    	
	    	}
	 public void phasesListing(int id){
		 System.out.println("ProjetId :"+id);
	    	try {
	    		phaseDao=new PhasesDao();
	        	ArrayList<Phase> phases= phaseDao.getAllByProjetId(id);
	        	DefaultTableModel m=(DefaultTableModel)tablePhases.getModel();
	        	m.setNumRows(0);
	        	int nbTaches=0;
	        	for(Phase phase:phases) {
	        		nbTaches=phaseDao.getNombreDesTachesByPhase(phase.getId());
	        	    m.addRow(new Object[] {phase.getId(),phase.getNom(),phase.getDateDebut(),phase.getDateFin(),phase.getEtat(),nbTaches});	
	        	}
	    	} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
	    	
	    	}
	 public void fillPhaseCombobox(int id){
	    	try {
	    		phaseDao=new PhasesDao();
	        	ArrayList<Phase> phases= phaseDao.getAllByProjetId(id);	        	
	        	cmbPhase.removeAll();
	        	int nbTaches=0;
	        	for(Phase phase:phases) {
	        		cmbPhase.addItem(phase.getId()+": "+phase.getNom());
	        	    	
	        	}
	    	} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
	    	
	    	}
	 public void tacheListing(int phaseId){
	    	try {
	    		TacheDao tacheDao=new TacheDao();
	        	ArrayList<Indicateur> inds= tacheDao.getTacheByResponsableEtPhaseId(etdId, phaseId);
	        	DefaultTableModel m=(DefaultTableModel)tableTache.getModel();
	        	m.setNumRows(0);
	        	for(Indicateur i:inds)
	        	    m.addRow(new Object[] {i.getAvancement().getTache().getId(),i.getAvancement().getTache().getNom(),i.getAvancement().getTache().getDateDebut(),i.getAvancement().getTache().getDateFin(),
	        	    		i.getAvancement().getTache().getEtat(),i.getAvancement().getTempPasse_T(),i.getAvancement().getResteAfaire_R(),i.getAvancement().getAvancement_A()
	        	    		});	
	    	} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Hii"+e.getMessage());
			}
	    	
	    	}
	 private int phId;
	private int equipeId;
	private int projetId;
    private PhasesDao phaseDao;
	private AppartientDao appDao;
	private static int etdId;
	private JTable tableEquipe;
}
