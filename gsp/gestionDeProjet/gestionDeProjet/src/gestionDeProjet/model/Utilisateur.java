package gestionDeProjet.model;

public class Utilisateur {

	private int id;
	private String nom;
	private String prenom;
	private String telephone;
	private String role;
	private String email;
	private String mdps;
	public Utilisateur() {
		
	}
	
	public Utilisateur(int id, String nom, String prenom, String telephone, String role, String email, String mdps) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.role = role;
		this.email = email;
		this.mdps = mdps;
	}
	public Utilisateur( String nom, String prenom, String telephone, String role, String email, String mdps) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.role = role;
		this.email = email;
		this.mdps = mdps;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdps() {
		return mdps;
	}
	public void setMdps(String mdps) {
		this.mdps = mdps;
	}
	
	@Override
	public String toString() {
		return "Utilisaeur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", role="
				+ role + ", email=" + email + ", mdps=" + mdps + "]";
	}
	
	
	
}
