package gestionDeProjet.model;

import java.util.Date;

public class Tache {

	
	private int id;
	private String nom;
	private Date dateDebut;
	private Date dateFin;
	private String etat;
	private Phase phase;
	private Responsable respo;
	
	public Tache() {
		super();
	}

	public Tache(int id, String nom, Date dateDebut, Date dateFin, String etat, Phase phase, Responsable respo) {
		super();
		this.id = id;
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etat = etat;
		this.phase = phase;
		this.respo = respo;
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

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Responsable getRespo() {
		return respo;
	}


	public void setRespo(Responsable respo) {
		this.respo = respo;
	}


	@Override
	public String toString() {
		return "Tache [id=" + id + ", nom=" + nom + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", etat="
				+ etat + ", phase=" + phase + ", respo=" + respo + "]";
	}




	
}
