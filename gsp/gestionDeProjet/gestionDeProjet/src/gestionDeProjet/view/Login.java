package gestionDeProjet.view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestionDeProjet.dao.UtilisateurDao;
import gestionDeProjet.view.etudiant.EtudiantMain;
import gestionDeProjet.view.prof.ProfMain;
import gestionDeProjet.model.Utilisateur;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField mdps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1011, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton connect = new JButton("Connect");
		connect.setBounds(241, 306, 347, 23);
		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userEmail=email.getText();
				String userMdps=mdps.getText();
				userDao=new UtilisateurDao();
				Utilisateur user=userDao.compteExists(userEmail, userMdps);
				if(user==null)
					JOptionPane.showMessageDialog(null, "Email ou mot de passe n'existe pas");
				
				userId=user.getId();
				if(user.getRole().toUpperCase().equals("PROFESSEUR")) {
					
					ProfMain pmain=new ProfMain(userId);
					dispose();
					pmain.setVisible(true);
				}else if(user.getRole().toUpperCase().equals("ETUDIANT")){	
				     EtudiantMain etdMain=new EtudiantMain(userId);
				     dispose();
				     etdMain.setVisible(true);
				}
				System.out.println(user.getRole().toUpperCase());
			}
		});
		contentPane.setLayout(null);
		
		mdps = new JTextField();
		mdps.setBounds(242, 246, 477, 39);
		contentPane.add(mdps);
		mdps.setColumns(10);
		contentPane.add(connect);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(242, 197, 477, 39);
		contentPane.add(email);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReset.setBounds(599, 306, 120, 23);
		contentPane.add(btnReset);
	}
   private UtilisateurDao userDao;
   private JTextField email;
   private int userId;
}
