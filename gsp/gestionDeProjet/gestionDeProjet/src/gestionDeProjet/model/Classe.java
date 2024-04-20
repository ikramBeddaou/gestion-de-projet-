package gestionDeProjet.model;

public class Classe {

	private int id;
	private Professeur prof;
	private String nom;
	private String description;
	private String niveau;
	private String filliere;
	private String anneScolaire;
	
	public Classe() {
		super();
	}

	public Classe(int id, Professeur prof, String nom, String description, String niveau, String filliere,
			String anneScolaire) {
		super();
		this.id = id;
		this.prof = prof;
		this.nom = nom;
		this.description = description;
		this.niveau = niveau;
		this.filliere = filliere;
		this.anneScolaire = anneScolaire;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Professeur getProf() {
		return prof;
	}

	public void setProf(Professeur prof) {
		this.prof = prof;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getFilliere() {
		return filliere;
	}

	public void setFilliere(String filliere) {
		this.filliere = filliere;
	}

	public String getAnneScolaire() {
		return anneScolaire;
	}

	public void setAnneScolaire(String anneScolaire) {
		this.anneScolaire = anneScolaire;
	}

	@Override
	public String toString() {
		return "Classe [id=" + id + ", prof=" + prof + ", nom=" + nom + ", description=" + description + ", niveau="
				+ niveau + ", filliere=" + filliere + ", anneScolaire=" + anneScolaire + "]";
	}
	
	
}
