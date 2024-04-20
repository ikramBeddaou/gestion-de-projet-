package gestionDeProjet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Classe;
import gestionDeProjet.model.Professeur;

public class ClasseDao {

		private Connection con;
		
		public ClasseDao() {
			this.con=Connect.getInstance().getConnection();
		}
		
		public ArrayList<Classe> getAllByProfId(int id) {
			ArrayList<Classe> classes = new ArrayList<>();
	        try {
	            PreparedStatement preparedStatement = con.prepareStatement("SELECT `id`, `professeurId`, `nom`, `description`, `niveau`, `filliere`, `anneeScolaire` FROM `Classe` WHERE `professeurId`=?");
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            

	            while (resultSet.next()) {
	                int idClasse = resultSet.getInt("id");
	                int professeurId = resultSet.getInt("professeurId");
	                String nom = resultSet.getString("nom");
	                String description = resultSet.getString("description");
	                String niveau = resultSet.getString("niveau");
	                String filliere = resultSet.getString("filliere");
	                String anneeScolaire = resultSet.getString("anneeScolaire");

	                ProfesseurDao professeurDao = new ProfesseurDao();
	                Professeur professeur = professeurDao.getById(professeurId);

	                Classe classe = new Classe(idClasse, professeur, nom, description, niveau, filliere, anneeScolaire);
	                classes.add(classe);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return classes;
	    }
		
		public Classe getById(int id) {
			Classe classe = new Classe();
	        try {
	            PreparedStatement preparedStatement = con.prepareStatement("SELECT `id`, `professeurId`, `nom`, `description`, `niveau`, `filliere`, `anneeScolaire` FROM `Classe` WHERE `id`=?");
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            

	            if (resultSet.next()) {
	                int idClasse = resultSet.getInt("id");
	                int professeurId = resultSet.getInt("professeurId");
	                String nom = resultSet.getString("nom");
	                String description = resultSet.getString("description");
	                String niveau = resultSet.getString("niveau");
	                String filliere = resultSet.getString("filliere");
	                String anneeScolaire = resultSet.getString("anneeScolaire");

	                ProfesseurDao professeurDao = new ProfesseurDao();
	                Professeur professeur = professeurDao.getById(professeurId);

	                classe = new Classe(idClasse, professeur, nom, description, niveau, filliere, anneeScolaire);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return classe;
	    }
		
		public int getClasseIdByNom(String nom) {
			int id=-1;
			try {
				PreparedStatement preparedStatement = con.prepareStatement("SELECT `id` FROM `classe` WHERE `Nom`=? limit 1");
	            preparedStatement.setString(1, nom);
	            ResultSet rs = preparedStatement.executeQuery();
	            if(rs.next())
	            	id=rs.getInt("id");
	            	
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return id;
		
		}
}
