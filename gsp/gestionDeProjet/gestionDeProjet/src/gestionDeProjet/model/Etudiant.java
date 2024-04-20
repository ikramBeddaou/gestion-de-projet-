package gestionDeProjet.model;

public class Etudiant extends Utilisateur{
 
	private int id;
	private Utilisateur user;
	private Classe classe;
	
	
	public Etudiant() {
		super();
	}


	public Etudiant(int id, Utilisateur user, Classe classe) {
		super();
		this.id = id;
		this.user = user;
		this.classe = classe;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Utilisateur getUser() {
		return user;
	}


	public void setUser(Utilisateur user) {
		this.user = user;
	}


	public Classe getClasse() {
		return classe;
	}


	public void setClasse(Classe classe) {
		this.classe = classe;
	}


	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", user=" + user + ", classe=" + classe + "]";
	}
	
	
	
}
