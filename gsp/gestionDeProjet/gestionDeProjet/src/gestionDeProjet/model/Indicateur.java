package gestionDeProjet.model;

public class Indicateur {
	
	private int id;
	private Avancement avancement;
	private Responsable responsable;
	private String productivite;
	private String performance ;
	private String tauxDoccupation;
	
	
	
	public Indicateur() {
		super();
	}



	public Indicateur(int id, Avancement avancement, Responsable responsable, String productivite, String performance,
			String tauxDoccupation) {
		super();
		this.id = id;
		this.avancement = avancement;
		this.responsable = responsable;
		this.productivite = productivite;
		this.performance = performance;
		this.tauxDoccupation = tauxDoccupation;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Avancement getAvancement() {
		return avancement;
	}



	public void setAvancement(Avancement avancement) {
		this.avancement = avancement;
	}



	public Responsable getResponsable() {
		return responsable;
	}



	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}



	public String getProductivite() {
		return productivite;
	}



	public void setProductivite(String productivite) {
		this.productivite = productivite;
	}



	public String getPerformance() {
		return performance;
	}



	public void setPerformance(String performance) {
		this.performance = performance;
	}



	public String getTauxDoccupation() {
		return tauxDoccupation;
	}



	public void setTauxDoccupation(String tauxDoccupation) {
		this.tauxDoccupation = tauxDoccupation;
	}



	@Override
	public String toString() {
		return "Indicateur [id=" + id + ", avancement=" + avancement + ", responsable=" + responsable
				+ ", productivite=" + productivite + ", performance=" + performance + ", tauxDoccupation="
				+ tauxDoccupation + "]";
	}
	
	
}
