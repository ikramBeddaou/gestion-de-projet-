package gestionDeProjet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Indicateur;

public class IndicateurDao {

	private Connection con;
	public IndicateurDao() {
		this.con=Connect.getInstance().getConnection();
	}
	public int add(Indicateur indicateur) {
        int indicateurId = -1; 
        String query = "INSERT INTO Indicateur (avancementId, responsableId, productivite, performance, taux_D_occupation) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, indicateur.getAvancement().getId());
            preparedStatement.setInt(2, indicateur.getResponsable().getId());
            preparedStatement.setString(3, indicateur.getProductivite());
            preparedStatement.setString(4, indicateur.getPerformance());
            preparedStatement.setString(5, indicateur.getTauxDoccupation());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    indicateurId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indicateurId;
    }
	 public Indicateur getIndicateurByAvcId(int avancementId) {
		 Indicateur indicateur = null;
	        try {
	            String query = "SELECT id, SUM(productivite) AS productivite, SUM(performance) AS performance, taux_D_occupation AS td FROM indicateur WHERE avancementId=?";
	            PreparedStatement pstmt = con.prepareStatement(query);
	            pstmt.setInt(1, avancementId);

	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	            	indicateur = new Indicateur();
	            	indicateur.setId(rs.getInt("id"));
	            	indicateur.setProductivite(rs.getString("productivite"));
	            	indicateur.setPerformance(rs.getString("performance"));
	            	indicateur.setTauxDoccupation(rs.getString("td"));
	            }

	            rs.close();
	            pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return indicateur;
	    }
	
}
