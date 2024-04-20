package gestionDeProjet.model;

public class Professeur extends Utilisateur{
	private String specialisation;
	private String departement;
	private Utilisateur utilisateur;
	
	public Professeur() {
		super();
	}
	
	

	

	public Professeur(int id, String nom, String prenom, String telephone, String role, String email, String mdps,String specialisation,String departement) {
		super(id, nom, prenom, telephone, role, email, mdps);
		this.specialisation=specialisation;
		this.departement=departement;
	}
	public Professeur(String nom, String prenom, String telephone, String role, String email, String mdps,String specialisation,String departement) {
		super(nom, prenom, telephone, role, email, mdps);

		this.specialisation=specialisation;
		this.departement=departement;
	}
	public String getSpecialisation() {
		return specialisation;
	}
	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}



	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}



	@Override
	public String toString() {
		return "Professeur [specialisation=" + specialisation + ", departement=" + departement + ", utilisateur="
				+ utilisateur + "]";
	}
	
}
