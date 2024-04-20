package Testing;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import gestionDeProjet.dao.AvancementDao;
import gestionDeProjet.model.Avancement;
import gestionDeProjet.model.Tache;

public class AvancementDaoTest {

    private AvancementDao avancementDao;

    @Before
    public void setUp() {
        avancementDao = new AvancementDao();
    }

    @Test
    public void testAdd() {
        // Prepare test data
        Tache tache = new Tache();
        tache.setId(25); // Assuming existing task ID
        Date dateAvanc = new Date();
        String tempPasse_T = "Test Temp Passe";
        String resteAfaire_R = "Test Reste A Faire";
        String avancement_A = "Test Avancement";

        Avancement avcToAdd = new Avancement(0, tache, dateAvanc, tempPasse_T, resteAfaire_R, avancement_A);

        // Call the method
        int generatedKey = avancementDao.add(avcToAdd);

        assertNotNull(generatedKey);
        // You can add more assertions based on your requirements
    }

    @Test
    public void testUpdate() {
        // Prepare test data
        Tache tache = new Tache();
        tache.setId(25); // Assuming existing task ID
        Date dateAvanc = new Date();
        String tempPasse_T = "3";
        String resteAfaire_R = "3";
        String avancement_A = "Test Avancement";
        Avancement avcToUpdate = new Avancement(1, tache, dateAvanc, tempPasse_T, resteAfaire_R, avancement_A);

        // Call the method
        boolean success = avancementDao.update(avcToUpdate);

        assertNotNull(success);
    }

    @Test
    public void testGetTotalDataForTache() {
        int tacheId = 25; // Assuming existing task ID

        Avancement avancement = avancementDao.getTotalDataForTache(tacheId);

        assertNotNull(avancement);
        // You can add more assertions based on your requirements
    }
}
