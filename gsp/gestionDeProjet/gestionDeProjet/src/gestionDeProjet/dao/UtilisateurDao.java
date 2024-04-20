package gestionDeProjet.dao;
import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDao {

    private Connection con;

    public UtilisateurDao() {
        // Get the connection from the Connect class
        this.con = Connect.getInstance().getConnection();
    }

    // Method to retrieve all users
    public List<Utilisateur> getAll() {
        List<Utilisateur> users = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setTelephone(rs.getString("telephone"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
                user.setMdps(rs.getString("mdps"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Method to retrieve a user by their ID
    public Utilisateur getById(int id) {
        Utilisateur user = null;
        String query = "SELECT * FROM utilisateur WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setTelephone(rs.getString("telephone"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
                user.setMdps(rs.getString("mdps"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // Method to add a new user
    public void add(Utilisateur user) {
        String query = "INSERT INTO utilisateur (nom, prenom, telephone, photo, role, email, mdps) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, user.getNom());
            pstmt.setString(2, user.getPrenom());
            pstmt.setString(3, user.getTelephone());
            pstmt.setString(5, user.getRole());
            pstmt.setString(6, user.getEmail());
            pstmt.setString(7, user.getMdps());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a user
    public void delete(Utilisateur user) {
        String query = "DELETE FROM utilisateur WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a user
    public void update(Utilisateur user) {
        String query = "UPDATE utilisateur SET nom = ?, prenom = ?, telephone = ?, photo = ?, role = ?, email = ?, mdps = ? WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, user.getNom());
            pstmt.setString(2, user.getPrenom());
            pstmt.setString(3, user.getTelephone());
            pstmt.setString(5, user.getRole());
            pstmt.setString(6, user.getEmail());
            pstmt.setString(7, user.getMdps());
            pstmt.setInt(8, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Utilisateur compteExists(String email,String mdps) {
    	String query="SELECT `id`, `nom`, `prenom`, `telephone`, `role`, `email`, `mdps` FROM `Utilisateur` WHERE"
    			+ " `email`=? AND `mdps`=?;";
    	 Utilisateur user = null;
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, mdps);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setTelephone(rs.getString("telephone"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
                user.setMdps(rs.getString("mdps"));
            }
           
    }catch(Exception e) {
    	System.out.println(e.getMessage());
    }
        return user;
    }
}
