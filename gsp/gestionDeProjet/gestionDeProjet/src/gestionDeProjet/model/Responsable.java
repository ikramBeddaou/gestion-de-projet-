package gestionDeProjet.model;

public class Responsable extends Etudiant{
	
	private int id;
	private Etudiant etudiant;
	private Equipe equipe;



	public Responsable() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Responsable(int id, Utilisateur user, Classe classe) {
		super(id, user, classe);
		// TODO Auto-generated constructor stub
	}


	public Responsable(int id, Etudiant utilisateur, Equipe equipe) {
		super();
		this.id = id;
		this.etudiant = utilisateur;
		this.equipe = equipe;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etd) {
		this.etudiant = etd;
	}
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	@Override
	public String toString() {
		return "Responsable [id=" + id + ", etudiant=" + etudiant + ", equipe=" + equipe + "]";
	}
	
	
	
}
