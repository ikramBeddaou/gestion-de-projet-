package gestionDeProjet.dao;

import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Phase;
import gestionDeProjet.model.Projet;
import gestionDeProjet.model.Responsable;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class PhasesDao {

    private Connection con;

    
    public PhasesDao() {
        this.con = Connect.getInstance().getConnection();
    }

   
    public ArrayList<Phase> getAllByProjetId(int id) {
        ArrayList<Phase> phases = new ArrayList<>();
        String query = "SELECT `id`, `projetId`, `nom`, `dateDebut`, `dateFin`, `etat` FROM `PHASES` WHERE `projetId` = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int phaseId = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String etat = resultSet.getString("etat");
                Date dateDebut = resultSet.getDate("dateDebut");
                Date dateFin = resultSet.getDate("dateFin");
                Projet projet = new ProjetDao().getById(resultSet.getInt("projetId"));

                Phase phase = new Phase(phaseId,nom, etat, dateDebut, dateFin, projet);
                phases.add(phase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phases;
    }
    
    public Phase getPhaseById(int id) {
        Phase phase = new Phase();
        String query = "SELECT `id`, `projetId`, `nom`, `dateDebut`, `dateFin`, `etat` FROM `PHASES` WHERE `id` = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int phaseId = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String etat = resultSet.getString("etat");
                Date dateDebut = resultSet.getDate("dateDebut");
                Date dateFin = resultSet.getDate("dateFin");
                Projet projet = new ProjetDao().getById(resultSet.getInt("projetId"));

                 phase = new Phase(id,nom, etat, dateDebut, dateFin, projet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phase;
    }
    
    public int getNombreDesTachesByPhase(int id) {
        int numberOfTasks = 0;
        String query = "SELECT COUNT(*) AS task_count FROM `tache` WHERE `phaseId` = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfTasks = resultSet.getInt("task_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfTasks;
    }
    
    public int add(Phase phase) {
        String query = "INSERT INTO `phases` (`projetId`, `nom`, `dateDebut`, `dateFin`, `etat`) VALUES (?, ?, ?, ?, ?)";
        int phaseId=-1;
        try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, phase.getProjet().getId());
            preparedStatement.setString(2, phase.getNom());
            preparedStatement.setDate(3, new java.sql.Date(phase.getDateDebut().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(phase.getDateFin().getTime()));
            preparedStatement.setString(5, phase.getEtat());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) 
                    phaseId= generatedKeys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phaseId;
    }
    
    public void update(Phase phase) {
        String query = "UPDATE `phases` SET `nom` = ?, `dateDebut` = ?, `dateFin` = ?, `etat` = ? WHERE `id` = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, phase.getNom());
            preparedStatement.setDate(2, new java.sql.Date(phase.getDateDebut().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(phase.getDateFin().getTime()));
            preparedStatement.setString(4, phase.getEtat());
            preparedStatement.setInt(5, phase.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int phaseId) {
        String query = "DELETE FROM `phases` WHERE `id` = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, phaseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

}
