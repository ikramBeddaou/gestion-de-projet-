package gestionDeProjet.view.prof;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import gestionDeProjet.dao.AppartientDao;
import gestionDeProjet.dao.ClasseDao;
import gestionDeProjet.dao.EquipeDao;
import gestionDeProjet.dao.EtudiantDao;
import gestionDeProjet.dao.ProjetDao;
import gestionDeProjet.model.Appartient;
import gestionDeProjet.model.Classe;
import gestionDeProjet.model.Equipe;
import gestionDeProjet.model.Etudiant;
import gestionDeProjet.model.Projet;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormerEquipe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox cmbClasse ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormerEquipe frame = new FormerEquipe(projetId,profId);
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
	public FormerEquipe(int id,int profId) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				fillCombobox();
			}
		});
		projetId=id;
		this.profId=profId;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 282, 688, 367);
		contentPane.add(scrollPane);
		
		table = new JTable();
		 table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		 table.setModel(new DefaultTableModel(
	                new Object[][]{},
	                new String[]{
	                		"Id", "Nom Complet", "Classe", "Avec Equipe"
	                }
	        ) {
	            boolean[] canEdit = new boolean[]{
	                false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }
	        });
		scrollPane.setViewportView(table);
		
	    cmbClasse = new JComboBox();
	    cmbClasse.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) { 
	    	 try {
	    		   
	    		    String value=cmbClasse.getSelectedItem().toString();
		    		ClasseDao cdao=new ClasseDao();
		    		int id=cdao.getClasseIdByNom(value);
		    		classeId=id;
		    		System.out.println(id);
		    		listing(id);
	         }
	         catch(Exception ex) {
	        	 JOptionPane.showMessageDialog(null,ex.getMessage());
	         }
	    		
	    	}
	    });
	    cmbClasse.addItemListener(new ItemListener() {
	    	public void itemStateChanged(ItemEvent e) {
	    	
	    	}
	    });
		cmbClasse.setBounds(10, 205, 538, 34);
		contentPane.add(cmbClasse);
		
		JLabel lblNewLabel = new JLabel("Classe :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 162, 658, 32);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Former L'equipe");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int columnIndex = 0; 
		        int[] selectedRows = table.getSelectedRows();
		        if (selectedRows.length == 0) {
		            JOptionPane.showMessageDialog(null, "No rows selected.");
		            return;
		        }
		        appDao=new AppartientDao();
		        Etudiant etd=new Etudiant();
		        int[] ids = new int[selectedRows.length];;
		        int i=0;
		        for (int row : selectedRows) {
		            int value = Integer.parseInt(table.getValueAt(row, columnIndex).toString());
		            ids[i]=value;
		            etd.setId(value);
		        	if(appDao.etudiantAvecEquipe(etd)) {
		        		JOptionPane.showMessageDialog(null, "Student N°"+ids[i]+" is already with a team");
		        		return;
		        	}
		            i++;
		        }
		        
		        Equipe equipe=new Equipe();
		        Classe classe =new Classe();
		        classe.setId(classeId);
		        Projet projet =new Projet();
		        projet.setId(projetId);
		        EquipeDao edao =new EquipeDao();
		        equipe.setClasse(classe);
		        equipe.setProjet(projet);
		        equipe.setNombreMembre(ids.length);
		        try {
		        	 equipe.setId(edao.addEquipe(equipe));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		        
		        Appartient apprt=new Appartient();
		        apprt.setEquipe(equipe);
		        for (int j=0;j<ids.length;j++) {
		        	etd.setId(ids[j]);
		        
		        	apprt.setEtudiant(etd);
		        	try {
						appDao.add(apprt);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		        }
		        	JOptionPane.showMessageDialog(null, "Equipe Ajouté Avec Succées");
			}
		});
		btnNewButton.setBounds(574, 205, 124, 34);
		contentPane.add(btnNewButton);
		
	}


	public void fillCombobox() {
		ClasseDao cdao=new ClasseDao();
		ArrayList<Classe> classes=cdao.getAllByProfId(profId);
		cmbClasse.removeAllItems();
		cmbClasse.addItem("Choisir Une Classe");
		for(Classe c:classes) {
			cmbClasse.addItem(c.getNom());
		}
		
	}
	 public void listing(int classeId){
	    	try {
	    		EtudiantDao edao=new EtudiantDao();
	        	ArrayList<Etudiant> etds= edao.getAllEtudiantsByClasseId(classeId);
	        	DefaultTableModel m=(DefaultTableModel)table.getModel();
	        	m.setNumRows(0);
	        	for(Etudiant e:etds)
	        	    m.addRow(new Object[] {e.getId(),e.getUser().getNom()+" "+e.getUser().getPrenom(),e.getClasse().getNom(),"Sans Equipe"});	
	    	} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
	    	
	    	}
	 private int classeId;
	private AppartientDao appDao;
	private static int projetId;
	private static int profId;
}
