package gestionDeProjet.dao;

import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Projet;
import gestionDeProjet.model.Professeur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetDao {

    private Connection con;

    public ProjetDao() {
        // Get the connection from the Connect class
        this.con = Connect.getInstance().getConnection();
    }

    // Method to retrieve all projects
    public ArrayList<Projet> getAllByProfId(int id) {
    	ArrayList<Projet> projects = new ArrayList<>();
        String query = "SELECT * FROM projet WHERE professeurId=?";
        ProfesseurDao prof=new ProfesseurDao();
        try (PreparedStatement stmt = con.prepareStatement(query)){
        		stmt.setInt(1,id);
             ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Projet project = new Projet();
                project.setId(rs.getInt("id"));
                project.setNom(rs.getString("nom"));
                project.setDescription(rs.getString("description"));
                project.setDateDebut(rs.getDate("dateDebut"));
                project.setDateFin(rs.getDate("dateFin"));
                project.setDomaine(rs.getString("domaine"));
                project.setEncadrant(prof.getById(rs.getInt("professeurId")));
                project.setEtat(rs.getString("Etat"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    // Method to add a new project
    public void add(Projet project) {
        String query = "INSERT INTO projet (nom, description, dateDebut, dateFin, domaine, professeurId,etat) VALUES (?, ?, ?, ?, ?, ?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, project.getNom());
            pstmt.setString(2, project.getDescription());
            pstmt.setDate(3, new java.sql.Date(project.getDateDebut().getTime()));
            pstmt.setDate(4, new java.sql.Date(project.getDateFin().getTime()));
            pstmt.setString(5, project.getDomaine());
            pstmt.setInt(6, project.getEncadrant().getId());
            pstmt.setString(7, project.getEtat());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a project
    public void delete(int id) {
        String query = "DELETE FROM projet WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a project
    public void update(Projet project) {
        String query = "UPDATE projet SET Nom=?,description = ?, dateDebut = ?, dateFin = ?, domaine = ? WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
        	 pstmt.setString(1, project.getNom());
            pstmt.setString(2, project.getDescription());
            pstmt.setDate(3, new java.sql.Date(project.getDateDebut().getTime()));
            pstmt.setDate(4, new java.sql.Date(project.getDateFin().getTime()));
            pstmt.setString(5, project.getDomaine());
            pstmt.setInt(6, project.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Projet getById(int id) {
        String query = "SELECT * FROM projet WHERE id=?";
        ProfesseurDao prof=new ProfesseurDao();
        Projet project=null;
        try (PreparedStatement stmt = con.prepareStatement(query)){
        		stmt.setInt(1,id);
             ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                project = new Projet();
                project.setId(rs.getInt("id"));
                project.setNom(rs.getString("nom"));
                project.setDescription(rs.getString("description"));
                project.setDateDebut(rs.getDate("dateDebut"));
                project.setDateFin(rs.getDate("dateFin"));
                project.setDomaine(rs.getString("domaine"));
                project.setEncadrant(prof.getById(rs.getInt("professeurId")));
                project.setEtat(rs.getString("Etat"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return project;
    }
    public Projet getByEquipeId(int id) {
        String query = "SELECT `id`, `classeId`, `nom`, `nombre_Membres`, `projetId` FROM `equipe` WHERE `id`=?";
        ProfesseurDao prof=new ProfesseurDao();
        Projet project=null;
        try (PreparedStatement stmt = con.prepareStatement(query)){
        		stmt.setInt(1,id);
             ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                project = getById(rs.getInt("projetId"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return project;
    }
}
