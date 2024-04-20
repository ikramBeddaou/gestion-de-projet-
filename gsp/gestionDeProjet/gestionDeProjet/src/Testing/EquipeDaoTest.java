package Testing;

import gestionDeProjet.dao.EquipeDao;
import gestionDeProjet.model.Classe;
import gestionDeProjet.model.Equipe;
import gestionDeProjet.model.Projet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;


public class EquipeDaoTest {

    private EquipeDao equipeDao;

    @Before
    public void setUp() {
        equipeDao = new EquipeDao();
    }

    @Test
    public void testAddEquipe() throws SQLException {
        // Prepare test data
        String equipeName = "Test Equipe";
        int classeId = 1; // Assuming existing class ID
        int nombreMembre = 5; // Assuming a fixed number of members for the test
        int projetId = 1; // Assuming existing project ID
        Equipe equipeToAdd = new Equipe(0, equipeName, new Classe(classeId, null, null, null, null, null, null), nombreMembre, new Projet(projetId, null, null, null, null, null, null, null));

        // Call the method
        int result = equipeDao.addEquipe(equipeToAdd);

        // Verify the result
        assertNotNull(result);
        // You can add more assertions based on your requirements
    }

    @Test
    public void testGetEquipeById() throws SQLException {
        // Prepare test data
        int equipeId = 1;

        // Call the method
        Equipe result = equipeDao.getEquipeById(equipeId);

        // Verify the result
        assertNotNull(result);
        // You can add more assertions based on your requirements
    }

    @Test
    public void testGetAllEquipesByClasseId() throws SQLException {
        // Prepare test data
        int classeId = 1; // Assuming existing class ID

        // Call the method
        ArrayList<Equipe> result = equipeDao.getAllEquipesByClasseId(classeId);

        // Verify the result
        assertNotNull(result);
        // You can add more assertions based on your requirements
    }
}
