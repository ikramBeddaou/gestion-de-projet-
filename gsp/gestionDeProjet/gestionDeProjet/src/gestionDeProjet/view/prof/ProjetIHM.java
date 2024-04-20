package gestionDeProjet.view.prof;

import java.awt.EventQueue;


import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;



import gestionDeProjet.dao.ProjetDao;
import gestionDeProjet.model.Professeur;
import gestionDeProjet.model.Projet;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class ProjetIHM extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField titre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjetIHM frame = new ProjetIHM(profId);
					
					((javax.swing.plaf.basic.BasicInternalFrameUI) frame.getUI()).setNorthPane(null);
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
	public ProjetIHM(int id) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				listing();
			}
		});
		profId=id;
		JDateChooser dd = new JDateChooser();
		JDateChooser df = new JDateChooser();
		setBackground(Color.WHITE);
		getContentPane().setBackground(SystemColor.control);
		getContentPane().setLayout(null);
		titre = new JTextField();
		titre.setBounds(10, 65, 394, 26);
		getContentPane().add(titre);
		titre.setColumns(10);
		
		JTextArea desc = new JTextArea();
		desc.setBackground(Color.WHITE);
		desc.setBounds(10, 116, 394, 113);
		getContentPane().add(desc);
		
		JComboBox cmbDomaine = new JComboBox();
		cmbDomaine.setModel(new DefaultComboBoxModel(new String[] {"Autre", "Mecanique", "Informatique", "Biologie", "Electronique", "Electrique"}));
		cmbDomaine.setEditable(true);
		cmbDomaine.setBounds(676, 70, 174, 22);
		getContentPane().add(cmbDomaine);
		
		JLabel lblNewLabel = new JLabel("Titre");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 40, 99, 22);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDes = new JLabel("Description");
		lblDes.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDes.setBounds(10, 95, 99, 22);
		getContentPane().add(lblDes);
		
		JLabel lblDomaine = new JLabel("Domaine");
		lblDomaine.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDomaine.setBounds(676, 40, 174, 22);
		getContentPane().add(lblDomaine);
		
		JButton cmdAdd = new JButton("Add");
		cmdAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Professeur prof=new Professeur();
				pdao=new ProjetDao();
				prof.setId(id);
				Projet projet=new Projet();
				projet.setNom(titre.getText());
				projet.setDescription(desc.getText());
				projet.setDateDebut(dd.getDate());
				projet.setDateFin(df.getDate());
				projet.setEncadrant(prof);
				projet.setDomaine(cmbDomaine.getSelectedItem().toString());
				projet.setEtat("No affecté");
				pdao.add(projet);
				listing();
				System.out.println(profId);
			}
		});
		cmdAdd.setBounds(676, 103, 174, 23);
		getContentPane().add(cmdAdd);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pdao=new ProjetDao();
					 int row = table.getSelectedRow();
					Projet projet=new Projet();
					projet.setId(Integer.parseInt(table.getValueAt(row, 0).toString()));
					projet.setNom(titre.getText());
					projet.setDescription(desc.getText());
					projet.setDateDebut(dd.getDate());
					projet.setDateFin(df.getDate());
					projet.setDomaine(cmbDomaine.getSelectedItem().toString());
					pdao.update(projet);
					listing();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
			}
		});
		btnUpdate.setBounds(676, 131, 174, 23);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pdao=new ProjetDao();
				int row=table.getSelectedRow();
				int value=Integer.parseInt(table.getValueAt(row, 0).toString());
				pdao.delete(value);
				listing();
				System.out.println(value);
			}
		});
		btnDelete.setBounds(676, 157, 174, 23);
		getContentPane().add(btnDelete);
		
		JButton btnAffecterLeProjet = new JButton("Affecter le Projet");
		btnAffecterLeProjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row =table.getSelectedRow();
				int value =Integer.parseInt(table.getValueAt(row, 0).toString());
				FormerEquipe equipeIhm=new FormerEquipe(value,profId);
				equipeIhm.setVisible(true);
			}
		});
		btnAffecterLeProjet.setBounds(676, 181, 174, 48);
		getContentPane().add(btnAffecterLeProjet);
		
		JLabel lblDateDebut = new JLabel("Date Debut");
		lblDateDebut.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDateDebut.setBounds(459, 40, 174, 22);
		getContentPane().add(lblDateDebut);
		
		
		dd.setBounds(459, 65, 174, 26);
		getContentPane().add(dd);
		
		JLabel lblDateFin = new JLabel("Date Fin");
		lblDateFin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDateFin.setBounds(459, 116, 174, 22);
		getContentPane().add(lblDateFin);
		
		
		df.setBounds(459, 141, 174, 26);
		getContentPane().add(df);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		
			}
		});
		scrollPane.setBounds(0, 257, 858, 340);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
			        int row = table.getSelectedRow();
			        titre.setText(table.getValueAt(row, 1).toString());
			        desc.setText(table.getValueAt(row, 3).toString());

			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			        Date startDate = dateFormat.parse(table.getValueAt(row, 4).toString());
			        Date endDate = dateFormat.parse(table.getValueAt(row, 5).toString());

			        dd.setDate(startDate);
			        df.setDate(endDate);
			        cmbDomaine.setSelectedItem(table.getValueAt(row, 6).toString());
					
			    } catch (Exception e2) {
			        System.out.println(e2.getMessage());
			    }
			}
		});
		
		 table.setModel(new DefaultTableModel(
	                new Object[][]{},
	                new String[]{
	                		"Id", "Titre", "Professeur", "Description", "Date Debut", "Date Fin", "Domaine", "Etat"
	                }
	        ) {
	            boolean[] canEdit = new boolean[]{
	                false, false, false, false, false, false, false,false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }
	        });
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		setBounds(100, 100, 864, 637);

	}
	 public void listing(){
	    	try {
	    		pdao=new ProjetDao();
	        	ArrayList<Projet> projets= pdao.getAllByProfId(profId);
	        	DefaultTableModel m=(DefaultTableModel)table.getModel();
	        	m.setNumRows(0);
	        	for(Projet p:projets)
	        	    m.addRow(new Object[] {p.getId(),p.getNom(),p.getEncadrant().getNom()+" "+p.getEncadrant().getPrenom(),p.getDescription(),p.getDateDebut(),p.getDateFin(),p.getDomaine(),p.getEtat()});	
	    	} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
	    	
	    	}
	 
	private static int profId;
	private ProjetDao pdao;
	private JTable table;
}
