package gestionDeProjet.model;

public class Appartient {

	private Etudiant etudiant;
	private Equipe equipe;
	public Appartient() {
		super();
	}
	public Appartient(Etudiant etudiant, Equipe equipe) {
		super();
		this.etudiant = etudiant;
		this.equipe = equipe;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	@Override
	public String toString() {
		return "Appartient [etudiant=" + etudiant + ", equipe=" + equipe + "]";
	}
	
}
