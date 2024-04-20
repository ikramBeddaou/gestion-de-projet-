package gestionDeProjet.model;



public class Equipe {
    private int id;
    private String nom;
    private Classe classe;
    private Projet projet;
    private int nombreMembre;
    
    public Equipe() {
        super();
    }

    
    public Equipe(int id, String nom, Classe classe, int nombreMembre,Projet projet) {
		super();
		this.id = id;
		this.nom = nom;
		this.classe = classe;
		this.projet=projet;
		this.nombreMembre = nombreMembre;
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


    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    

    public int getNombreMembre() {
		return nombreMembre;
	}


	public void setNombreMembre(int nombreMembre) {
		this.nombreMembre = nombreMembre;
	}


	public Projet getProjet() {
		return projet;
	}


	public void setProjet(Projet projet) {
		this.projet = projet;
	}


	@Override
	public String toString() {
		return "Equipe [id=" + id + ", nom=" + nom + ", classe=" + classe + ", projet=" + projet + ", nombreMembre="
				+ nombreMembre + "]";
	}
}
