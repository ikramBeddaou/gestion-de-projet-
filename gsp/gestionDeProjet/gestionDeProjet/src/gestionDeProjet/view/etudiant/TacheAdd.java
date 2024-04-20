package gestionDeProjet.view.etudiant;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import gestionDeProjet.dao.AppartientDao;
import gestionDeProjet.dao.ResponsableDao;
import gestionDeProjet.dao.TacheDao;
import gestionDeProjet.model.Equipe;
import gestionDeProjet.model.Etudiant;
import gestionDeProjet.model.Phase;
import gestionDeProjet.model.Projet;
import gestionDeProjet.model.Responsable;
import gestionDeProjet.model.Tache;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TacheAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tacheNom;
	private JTable tableTache;
    private JDateChooser tacheDd;
    private JDateChooser tacheDf;
    private JComboBox cmbResponsable;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TacheAdd frame = new TacheAdd(phaseId,equipeId);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param phaseId 
	 * @param equipeId 
	 */
	public TacheAdd(int phaseId, int equipeId) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				fillRespoCombobox();
				tacheListing();
			}
		});
		this.phaseId=phaseId;
		this.equipeId=equipeId;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 719, 737);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(52, 49, 268, 14);
		contentPane.add(lblNewLabel);
		
		tacheNom = new JTextField();
		tacheNom.setColumns(10);
		tacheNom.setBounds(52, 83, 526, 35);
		contentPane.add(tacheNom);
		
		JLabel lblDateDebut = new JLabel("Date Debut");
		lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateDebut.setBounds(52, 145, 268, 25);
		contentPane.add(lblDateDebut);
		
		tacheDd = new JDateChooser();
		tacheDd.setBounds(52, 184, 526, 35);
		contentPane.add(tacheDd);
		
		JLabel lblDateFin = new JLabel("Date Fin");
		lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateFin.setBounds(52, 241, 268, 25);
		contentPane.add(lblDateFin);
		
		tacheDf = new JDateChooser();
		tacheDf.setBounds(52, 280, 526, 35);
		contentPane.add(tacheDf);
		
		JButton cmdAdd = new JButton("Sauvegarder");
		cmdAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(!formValide()) {
						JOptionPane.showMessageDialog(null,"Forme Invalide!!!");
					}
					tache =new Tache();
					Phase phase=new Phase();
					phase.setId(phaseId);
					tacheDao=new TacheDao();
					ResponsableDao respoDao =new ResponsableDao();
					Responsable respo=new Responsable();
					Equipe equipe=new Equipe();
					Etudiant etd=new Etudiant();
					etd.setId(Integer.parseInt(cmbResponsable.getSelectedItem().toString().split(":")[0]));
					equipe.setId(equipeId);
					respo.setEtudiant(etd);
					respo.setEquipe(equipe);
					respo.setId(respoDao.add(respo));
					
					tache.setNom(tacheNom.getText());
					tache.setDateDebut(tacheDd.getDate());
					tache.setDateFin(tacheDf.getDate());
					tache.setPhase(phase);
					tache.setRespo(respo);
					tache.setEtat("En Cours...");
					tacheDao.add(tache);
					JOptionPane.showMessageDialog(null, "Ajouté Avec Succées");
					tacheListing();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		cmdAdd.setBounds(52, 427, 371, 34);
		contentPane.add(cmdAdd);
		
		JButton cmdAnnuler = new JButton("Annuler");
		cmdAnnuler.setBounds(434, 427, 144, 34);
		contentPane.add(cmdAnnuler);
		
		JLabel lblResponsable = new JLabel("Responsable");
		lblResponsable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblResponsable.setBounds(52, 339, 268, 25);
		contentPane.add(lblResponsable);
		
		cmbResponsable = new JComboBox();
		cmbResponsable.setBounds(52, 375, 526, 35);
		contentPane.add(cmbResponsable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 490, 526, 170);
		contentPane.add(scrollPane);
		
		tableTache = new JTable();
		tableTache.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 int row = tableTache.getSelectedRow();
			        tacheNom.setText(tableTache.getValueAt(row, 1).toString());

			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			        Date startDate=null;
			        Date endDate=null;
					try {
						startDate = dateFormat.parse(tableTache.getValueAt(row, 2).toString());
						endDate  = dateFormat.parse(tableTache.getValueAt(row, 3).toString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			       

			        tacheDd.setDate(startDate);
			        tacheDf.setDate(endDate);
			        cmbResponsable.setSelectedItem(tableTache.getValueAt(row, 0).toString()+": "+tableTache.getValueAt(row, 4).toString());
			}
		});
		tableTache.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Id", "Nom", "Date Debut", "Date Fin", "Responsable", "Etat"
			}
		));
		scrollPane.setViewportView(tableTache);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            int row = tableTache.getSelectedRow();
		            int value = Integer.parseInt(tableTache.getValueAt(row, 0).toString());
		            tache = new Tache();
		            tacheDao = new TacheDao();
		            tache.setId(value); 
		            tache.setNom(tacheNom.getText());
		            tache.setDateDebut(tacheDd.getDate());
		            tache.setDateFin(tacheDf.getDate());
		            Responsable respo = new Responsable();
		            respo.setId(Integer.parseInt(cmbResponsable.getSelectedItem().toString().split(":")[0]));
		            tache.setRespo(respo);
		            tacheDao.update(tache);
		            tacheListing();
		        } catch (Exception e2) {
		            e2.printStackTrace();
		        }
				
			}
		});
		btnNewButton_1.setBounds(588, 519, 107, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Supprimer");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableTache.getSelectedRow();
				if(row<=0) {
					JOptionPane.showMessageDialog(null,"No row selected");
					return;
				}
		         int value = Integer.parseInt(tableTache.getValueAt(row, 0).toString());
		         tacheDao=new TacheDao();
		         tacheDao.delete(value);
		         tacheListing();
			}
		});
		btnNewButton_1_1.setBounds(588, 567, 107, 25);
		contentPane.add(btnNewButton_1_1);
	}
	public boolean formValide() {
		if(tacheNom.getText().trim().equals(" ") || tacheDd.getDate()==null || tacheDf.getDate()==null || cmbResponsable.getSelectedItem()==null )
			return false;
		return true;
	}
	public void fillRespoCombobox() {
		AppartientDao appDao=new AppartientDao();
    	ArrayList<Etudiant> etds= appDao.getAllEtudiantsByEquipeId(equipeId);
    	cmbResponsable.removeAll();
    	for(Etudiant etd:etds) {
    	cmbResponsable.addItem(etd.getId()+": "+etd.getUser().getNom()+" "+etd.getUser().getPrenom());
    	}
	}
 	 public void tacheListing(){
		    	try {
		    		tacheDao=new TacheDao();
		        	ArrayList<Tache> taches= tacheDao.getAllTacheByPhaseId(phaseId);
		        	DefaultTableModel m=(DefaultTableModel)tableTache.getModel();
		        	m.setNumRows(0);
		        	for(Tache tache:taches) {
		        		System.out.println(tache.getRespo());
		        	    m.addRow(new Object[] {tache.getId(),tache.getNom(),tache.getDateDebut(),tache.getDateFin(),tache.getRespo().getEtudiant().getUser().getNom()+" "+tache.getRespo().getEtudiant().getUser().getPrenom(),tache.getEtat()});	
		        	}
		        	} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
	    	
	    	}
    	
    private Tache tache;
	private TacheDao tacheDao;
	private static int phaseId;
	private static int equipeId;
}
