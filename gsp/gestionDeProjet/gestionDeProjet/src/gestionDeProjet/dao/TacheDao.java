package gestionDeProjet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Avancement;
import gestionDeProjet.model.Indicateur;
import gestionDeProjet.model.Phase;
import gestionDeProjet.model.Responsable;
import gestionDeProjet.model.Tache;

public class TacheDao {
	 private Connection con;
	 
	 public TacheDao() {
		 this.con=Connect.getInstance().getConnection();
	 }
	 
	 
	    public ArrayList<Tache> getAllTacheByResponsableId(int id) {
	        ArrayList<Tache> taches = new ArrayList<>();
	        String query = "SELECT `id`, `phaseId`, `nom`, `dateDebut`, `dateFin`, `etat` FROM `tache` WHERE `responsableId` = ?";
	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            PhasesDao phaseDao=new PhasesDao();
	            while (resultSet.next()) {
	                int tacheId = resultSet.getInt("id");
	                String nom = resultSet.getString("nom");
	                Date dateDebut = resultSet.getDate("dateDebut");
	                Date dateFin = resultSet.getDate("dateFin");
	                String etat = resultSet.getString("etat");
	                
	                Phase phase = phaseDao.getPhaseById(resultSet.getInt("phaseId"));
	                
	                Responsable responsable = new ResponsableDao().getResponsableById(id);

	                Tache tache = new Tache(tacheId, nom, dateDebut, dateFin, etat, phase, responsable);
	                taches.add(tache);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return taches;
	    }
	    public ArrayList<Tache> getAllTacheByPhaseId(int id) {
	        ArrayList<Tache> taches = new ArrayList<>();
	        String query = "SELECT `id`, `phaseId`, `nom`, `dateDebut`, `dateFin`,`responsableId`, `etat` FROM `tache` WHERE `phaseId` = ?";
	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            PhasesDao phaseDao=new PhasesDao();
	            while (resultSet.next()) {
	                int tacheId = resultSet.getInt("id");
	                String nom = resultSet.getString("nom");
	                Date dateDebut = resultSet.getDate("dateDebut");
	                Date dateFin = resultSet.getDate("dateFin");
	                String etat = resultSet.getString("etat");
	                
	                Phase phase = phaseDao.getPhaseById(resultSet.getInt("phaseId"));
	                
	                Responsable responsable = new ResponsableDao().getResponsableById(resultSet.getInt("responsableId"));

	                Tache tache = new Tache(tacheId, nom, dateDebut, dateFin, etat, phase, responsable);
	                taches.add(tache);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return taches;
	    }
	   
	    
	    public void add(Tache tache) {
	        String query = "INSERT INTO `tache` (`phaseId`, `nom`, `dateDebut`, `dateFin`, `etat`, `responsableId`) VALUES (?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	            preparedStatement.setInt(1, tache.getPhase().getId());
	            preparedStatement.setString(2, tache.getNom());
	            preparedStatement.setDate(3, new java.sql.Date(tache.getDateDebut().getTime()));
	            preparedStatement.setDate(4, new java.sql.Date(tache.getDateFin().getTime()));
	            preparedStatement.setString(5, tache.getEtat());
	            preparedStatement.setInt(6, tache.getRespo().getId());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void update(Tache tache) {
	        String query = "UPDATE `tache` SET  `nom` = ?, `dateDebut` = ?, `dateFin` = ?,`responsableId` = ? WHERE `id` = ?";
	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	            preparedStatement.setString(1, tache.getNom());
	            preparedStatement.setDate(2, new java.sql.Date(tache.getDateDebut().getTime()));
	            preparedStatement.setDate(3, new java.sql.Date(tache.getDateFin().getTime()));
	            preparedStatement.setInt(4, tache.getRespo().getId());
	            preparedStatement.setInt(5, tache.getId());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	           System.out.println("TacheDao :\n"+e.getMessage());
	        }
	    }
	    public void delete(int tacheId) {
	        String query = "DELETE FROM `tache` WHERE `id` = ?";
	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	            preparedStatement.setInt(1, tacheId);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public ArrayList<Indicateur> getTacheByResponsableEtPhaseId(int responsibleId, int phaseId) {
	        ArrayList<Indicateur> inds = new ArrayList<>();

	        String query = "SELECT t.id, phaseId, nom, dateDebut, dateFin, etat, t.responsableId, " +
	                       "a.id AS avancementId, tacheId, dateAvancement, TempsPasse_T, ResteAfaire_R, avancement_A, " +
	                       "i.id AS indicateurId, i.avancementId, i.responsableId, productivite, performance, taux_D_occupation " +
	                       "FROM tache t " +
	                       "LEFT JOIN avancement a ON t.id = a.tacheId " +
	                       "LEFT JOIN indicateur i ON a.id = i.avancementId " +
	                       "WHERE t.responsableId = ? AND t.phaseId = ?";

	        try (PreparedStatement stmt = con.prepareStatement(query)) {
	            stmt.setInt(1, responsibleId);
	            stmt.setInt(2, phaseId);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                Tache tache = new Tache();
	                tache.setId(rs.getInt("t.id"));
	                tache.setNom(rs.getString("nom"));
	                tache.setDateDebut(rs.getDate("dateDebut"));
	                tache.setDateFin(rs.getDate("dateFin"));
	                tache.setEtat(rs.getString("etat"));

	                Avancement avancement = new Avancement();
	                avancement.setId(rs.getInt("avancementId"));
	                avancement.setDateAvanc(rs.getDate("dateAvancement"));
	                avancement.setTempPasse_T(rs.getString("TempsPasse_T"));
	                avancement.setResteAfaire_R(rs.getString("ResteAfaire_R"));
	                avancement.setAvancement_A(rs.getString("avancement_A"));

	                Indicateur indicateur = new Indicateur();
	                indicateur.setId(rs.getInt("indicateurId"));
	                indicateur.setProductivite(rs.getString("productivite"));
	                indicateur.setPerformance(rs.getString("performance"));
	                indicateur.setTauxDoccupation(rs.getString("taux_D_occupation"));

	                avancement.setTache(tache);
	                indicateur.setAvancement(avancement);
	                inds.add(indicateur);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return inds;
	    }


	    public Tache getTacheById(int tacheId) {
	        Tache tache = null;
	        String query = "SELECT id, phaseId, nom, dateDebut, dateFin, etat, responsableId FROM tache WHERE id = ?";
	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	            preparedStatement.setInt(1, tacheId);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            PhasesDao phaseDao = new PhasesDao();
	            if (resultSet.next()) {
	                String nom = resultSet.getString("nom");
	                Date dateDebut = resultSet.getDate("dateDebut");
	                Date dateFin = resultSet.getDate("dateFin");
	                String etat = resultSet.getString("etat");

	                Phase phase = phaseDao.getPhaseById(resultSet.getInt("phaseId"));

	                Responsable responsable = new ResponsableDao().getResponsableById(resultSet.getInt("responsableId"));

	                tache = new Tache(tacheId, nom, dateDebut, dateFin, etat, phase, responsable);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return tache;
	    }


		public void updateTacheEtatById(int id) {
			 String query = "UPDATE tache SET etat ='Terminé' WHERE id = ?";
		        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
		            preparedStatement.setInt(1, id);
		            preparedStatement.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			
		}
}
