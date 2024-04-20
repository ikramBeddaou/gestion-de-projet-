package gestionDeProjet.dao;

import java.sql.*;
import java.util.ArrayList;

import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Appartient;
import gestionDeProjet.model.Classe;
import gestionDeProjet.model.Equipe;
import gestionDeProjet.model.Etudiant;
import gestionDeProjet.model.Utilisateur;

public class AppartientDao {

	private Connection con;
	public AppartientDao() { 
		this.con=Connect.getInstance().getConnection();
	}
	
	public void add(Appartient appartient) throws SQLException {
	    String sql = "INSERT INTO appartient (etudiantId, equipeId) VALUES (?, ?)";
	    try {
	    	PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, appartient.getEtudiant().getId());
	        statement.setInt(2, appartient.getEquipe().getId());
	        statement.executeUpdate();
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	}

	public boolean etudiantAvecEquipe(Etudiant etd) {
		 String sql = "SELECT * FROM `appartient` WHERE etudiantId=?";
		    try {
		    	PreparedStatement statement = con.prepareStatement(sql);
		        statement.setInt(1, etd.getId());
		        ResultSet rs=statement.executeQuery();
		        if(rs.next())
		        	return true;
		    }catch(Exception ex) {
		    	ex.printStackTrace();
		    }
		    return false;
	}
	public Equipe getEquipeByEtudiantId(int id) {
		 String sql = "SELECT `etudiantId`, `equipeId` FROM `appartient` WHERE `etudiantId`=? limit 1;";
		 Equipe equipe=null;
		 EquipeDao edao=new EquipeDao();
		 try {
		    	PreparedStatement statement = con.prepareStatement(sql);
		        statement.setInt(1, id);
		        ResultSet rs=statement.executeQuery();
		        
		        if(rs.next())
		        	equipe=edao.getEquipeById(rs.getInt("equipeId"));
		        
		    }catch(Exception ex) {
		    	ex.printStackTrace();
		    }
		    return equipe;
	}
	  public ArrayList<Etudiant> getAllEtudiantsByEquipeId(int equipeId) {
	    	ArrayList<Etudiant> etudiants = new ArrayList<>();
	        try {
	            PreparedStatement preparedStatement = con.prepareStatement("SELECT `etudiantId`, `equipeId` FROM `appartient` WHERE `equipeId`=?;");
	            preparedStatement.setInt(1, equipeId);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            EtudiantDao etDao=new EtudiantDao();
	            while (resultSet.next()) {
	                 int id= resultSet.getInt("etudiantId");
	                 System.out.println("ID :"+id);
                     etudiants.add(etDao.getById(id));               
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Liste Des Etudiant :"+ etudiants);
	        return etudiants;
	    }

	
}
