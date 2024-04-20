package gestionDeProjet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Equipe;

public class EquipeDao {

    private Connection con;

    public EquipeDao() {
        this.con = Connect.getInstance().getConnection();
    }

    public int addEquipe(Equipe equipe) throws SQLException {
        String sql = "INSERT INTO equipe (nom, classeId, nombre_Membres, projetId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, equipe.getNom());
            statement.setInt(2, equipe.getClasse().getId());
            statement.setInt(3, equipe.getNombreMembre());
            statement.setInt(4, equipe.getProjet().getId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No generated keys returned.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex; 
        }
    }
    
    public Equipe getEquipeById(int id) throws SQLException {
        String sql = "SELECT * FROM equipe WHERE id = ?";
        ClasseDao cdao=new ClasseDao();
        ProjetDao pdao=new ProjetDao();
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Equipe equipe = new Equipe();
                    equipe.setId(resultSet.getInt("id"));
                    equipe.setNom(resultSet.getString("nom"));
                    equipe.setClasse(cdao.getById(resultSet.getInt("classeId")));
                    equipe.setNombreMembre(resultSet.getInt("nombre_Membres"));
                    equipe.setProjet(pdao.getById(resultSet.getInt("projetId")));
                    return equipe;
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    public ArrayList<Equipe> getAllEquipesByClasseId(int classeId){
    	ArrayList<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT `id`, `classeId`, `nom`, `nombre_Membres`, `projetId` FROM `equipe` WHERE `classeId`=?";
        ClasseDao cdao = new ClasseDao();
        ProjetDao pdao = new ProjetDao();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, classeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Equipe equipe = new Equipe();
                    equipe.setId(resultSet.getInt("id"));
                    equipe.setNom(resultSet.getString("nom"));
                    equipe.setClasse(cdao.getById(resultSet.getInt("classeId")));
                    equipe.setNombreMembre(resultSet.getInt("nombre_Membres"));
                    equipe.setProjet(pdao.getById(resultSet.getInt("projetId")));
                    equipes.add(equipe);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return equipes;
    }
}

