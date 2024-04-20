package gestionDeProjet.dao;

import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Professeur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDao {

    private Connection con;

    public ProfesseurDao() {
        // Get the connection from the Connect class
        this.con = Connect.getInstance().getConnection();
    }

    // Method to retrieve all professors
    public List<Professeur> getAll() {
        List<Professeur> professors = new ArrayList<>();
        String query = "SELECT p.`id`, `nom`, `prenom`, `telephone`, `role`, `email`, `mdps`, `specialisation`, `departement` FROM `professeur` p,`utilisateur` u WHERE u.id=p.id";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Professeur professor = new Professeur();
                professor.setId(rs.getInt("id"));
                professor.setNom(rs.getString("Nom"));
                professor.setPrenom(rs.getString("prenom"));
                professor.setTelephone(rs.getString("telephone"));
                professor.setRole(rs.getString("role"));
                professor.setMdps(rs.getString("mdps"));
                professor.setSpecialisation(rs.getString("specialisation"));
                professor.setDepartement(rs.getString("departement"));
                professors.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professors;
    }

    // Method to add a new professor
    public void add(Professeur professor) {
        String query = "INSERT INTO professeur (userId, specialisation, departement) VALUES (?, ?, ?)";
        UtilisateurDao dao=new UtilisateurDao();
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, professor.getUtilisateur().getId());
            pstmt.setString(2, professor.getSpecialisation());
            pstmt.setString(3, professor.getDepartement());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a professor
    public void delete(Professeur professor) {
        String query = "DELETE FROM professeur WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, professor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a professor
    public void update(Professeur professor) {
        String query = "UPDATE professeur SET userId = ?, specialisation = ?, departement = ? WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            // You need to set the userId according to your data model
            // pstmt.setInt(1, professor.getUtilisateur().getId());
            pstmt.setString(2, professor.getSpecialisation());
            pstmt.setString(3, professor.getDepartement());
            pstmt.setInt(4, professor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Professeur getById(int id) {
        String query = "SELECT p.`id`, `nom`, `prenom`, `telephone`, `role`, `email`, `mdps`, `specialisation`, `departement` FROM `professeur` p,`utilisateur` u WHERE u.id=p.id and p.id=?";
        Professeur prof = null;
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            
            if (rs.next()) {
            	 prof = new Professeur();
                prof.setId(rs.getInt("id"));
                prof.setNom(rs.getString("Nom"));
                prof.setPrenom(rs.getString("prenom"));
                prof.setTelephone(rs.getString("telephone"));
                prof.setRole(rs.getString("role"));
                prof.setMdps(rs.getString("mdps"));
                prof.setSpecialisation(rs.getString("specialisation"));
                prof.setDepartement(rs.getString("departement"));
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prof;
    }
}
