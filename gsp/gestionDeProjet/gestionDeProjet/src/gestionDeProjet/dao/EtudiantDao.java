package gestionDeProjet.dao;

import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Classe;
import gestionDeProjet.model.Etudiant;
import gestionDeProjet.model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDao {
    private Connection con;

    public EtudiantDao() {
        this.con = Connect.getInstance().getConnection();
    }

    public ArrayList<Etudiant> getAllEtudiantsByClasseId(int cid) {
    	ArrayList<Etudiant> etudiants = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT `id`, `userId`, `classeId` FROM `etudiant` WHERE `classeId`=?");
            preparedStatement.setInt(1, cid);
            ResultSet resultSet = preparedStatement.executeQuery();
            

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int classeId = resultSet.getInt("classeId");

                UtilisateurDao utilisateurDao = new UtilisateurDao();
                Utilisateur utilisateur = utilisateurDao.getById(userId);

                ClasseDao classeDao = new ClasseDao();
                Classe classe = classeDao.getById(classeId);

                Etudiant etudiant = new Etudiant(id, utilisateur, classe);
                etudiants.add(etudiant);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(etudiants);
        return etudiants;
    }
    public Etudiant getById(int id) {
        Etudiant etudiant = null;
        String sql = "SELECT `userId`, `classeId` FROM `etudiant` WHERE `id`=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("userId");
                    int classeId = resultSet.getInt("classeId");

                    UtilisateurDao utilisateurDao = new UtilisateurDao();
                    Utilisateur utilisateur = utilisateurDao.getById(userId);

                    ClasseDao classeDao = new ClasseDao();
                    Classe classe = classeDao.getById(classeId);

                    etudiant = new Etudiant(id, utilisateur, classe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }

}
