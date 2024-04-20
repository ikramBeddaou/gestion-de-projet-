package gestionDeProjet.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gestionDeProjet.model.Responsable;
import gestionDeProjet.model.Utilisateur;
import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Equipe;
import gestionDeProjet.model.Etudiant;

public class ResponsableDao {

    private Connection con;

    public ResponsableDao() {
        this.con = Connect.getInstance().getConnection();
    }

    public Responsable getResponsableById(int id) {
        Responsable responsable = null;
        String query = "SELECT `id`, `etudiantId`, `equipeId` FROM `Responsable` WHERE `id` = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("etudiantId");
                int equipeId = rs.getInt("equipeId");

                Etudiant etd = new EtudiantDao().getById(userId);
                Equipe equipe = new EquipeDao().getEquipeById(equipeId);

                responsable = new Responsable(id, etd, equipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return responsable;
    }
    public Responsable getResponsableByEtudiantId(int id) {
        Responsable responsable = null;
        String query = "SELECT `id`, `etudiantId`, `equipeId` FROM `Responsable` WHERE `etudiantId` = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	int respoId=rs.getInt("id");
                int etdId = id;
                int equipeId = rs.getInt("equipeId");

                Etudiant etd = new EtudiantDao().getById(etdId);
                Equipe equipe = new EquipeDao().getEquipeById(equipeId);

                responsable = new Responsable(respoId, etd, equipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return responsable;
    }
    
    public int add(Responsable responsable) {
        String query = "INSERT INTO `Responsable` (`id`,`etudiantId`, `equipeId`) VALUES (?,?, ?)";
        int generatedKey = -1;

        try (PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        	stmt.setInt(1, responsable.getId());
        	stmt.setInt(2, responsable.getEtudiant().getId());
            stmt.setInt(3, responsable.getEquipe().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedKey;
    }
}
