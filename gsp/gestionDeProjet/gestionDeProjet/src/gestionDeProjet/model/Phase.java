package gestionDeProjet.model;

import java.util.Date;

public class Phase {
  
	private int id;
	private String nom;
	private String etat;
	private Date dateDebut;
	private Date DateFin;
	private Projet projet;
	
	public Phase() {
		super();
	}
	public Phase(int id,String nom, String etat, Date dateDebut, Date fin, Projet projet) {
		super();
		this.id=id;
		this.nom = nom;
		this.etat = etat;
		this.dateDebut = dateDebut;
		DateFin = fin;
		this.projet = projet;
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
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return DateFin;
	}
	public void setDateFin(Date fin) {
		DateFin = fin;
	}
	public Projet getProjet() {
		return projet;
	}
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	@Override
	public String toString() {
		return "Phase [id=" + id + ", nom=" + nom + ", etat=" + etat + ", dateDebut=" + dateDebut + ", DateFin=" + DateFin
				+ ", projet=" + projet + "]";
	}
	
	
}

