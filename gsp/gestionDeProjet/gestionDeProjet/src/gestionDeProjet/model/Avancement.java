package gestionDeProjet.model;

import java.util.Date;

public class Avancement {

	private int id;
	private Tache tache;
	private Date dateAvanc;
	private String tempPasse_T;
	private String resteAfaire_R;
	public Avancement(int id, Tache Tachee, Date dateAvanc, String tempPasse_T, String resteAfaire_R, String avancement_A) {
		super();
		this.id = id;
		this.tache = Tachee;
		this.dateAvanc = dateAvanc;
		this.tempPasse_T = tempPasse_T;
		this.resteAfaire_R = resteAfaire_R;
		this.avancement_A = avancement_A;
	}
	private String avancement_A;
	public Avancement() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Tache getTache() {
		return tache;
	}
	public void setTache(Tache Tachee) {
		this.tache = Tachee;
	}
	public Date getDateAvanc() {
		return dateAvanc;
	}
	public void setDateAvanc(Date dateAvanc) {
		this.dateAvanc = dateAvanc;
	}
	public String getTempPasse_T() {
		return tempPasse_T;
	}
	public void setTempPasse_T(String string) {
		this.tempPasse_T = string;
	}
	public String getResteAfaire_R() {
		return resteAfaire_R;
	}
	public void setResteAfaire_R(String resteAfaire_R) {
		this.resteAfaire_R = resteAfaire_R;
	}
	public String getAvancement_A() {
		return avancement_A;
	}
	public void setAvancement_A(String avancement_A) {
		this.avancement_A = avancement_A;
	}
	@Override
	public String toString() {
		return "Avancement [id=" + id + ", Tache=" + tache + ", dateAvanc=" + dateAvanc + ", tempPasse_T=" + tempPasse_T
				+ ", resteAfaire_R=" + resteAfaire_R + ", avancement_A=" + avancement_A + "]";
	}
	
}
