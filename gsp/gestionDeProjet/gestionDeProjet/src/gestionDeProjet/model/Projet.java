package gestionDeProjet.model;

import java.util.*;
public class Projet {
 
	private int id;
	private String nom;
	private String description;
	private Date dateDebut ;
	private Date dateFin ;
	private String domaine;
	private String etat;
	private Professeur encadrant;
	
	
	public Projet() {
		super();
	}


	public Projet(int id,String nom, String description, Date dateDebut, Date dateFin, String domaine, Professeur encadrant,String etat) {
		super();
		this.id=id;
		this.nom = nom;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.domaine = domaine;
		this.encadrant = encadrant;
		this.etat=etat;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}


	public String getDomaine() {
		return domaine;
	}


	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}


	public Professeur getEncadrant() {
		return encadrant;
	}


	public void setEncadrant(Professeur encadrant) {
		this.encadrant = encadrant;
	}


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}


	@Override
	public String toString() {
		return "Projet [id=" + id + ", nom=" + nom + ", description=" + description + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", domaine=" + domaine + ", etat=" + etat + ", encadrant=" + encadrant + "]";
	}


	
	
	
}
