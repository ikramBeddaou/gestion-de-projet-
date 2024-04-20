package Testing;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import gestionDeProjet.dao.IndicateurDao;
import gestionDeProjet.model.Avancement;
import gestionDeProjet.model.Indicateur;
import gestionDeProjet.model.Responsable;

public class IndicateurDaoTest {

    private IndicateurDao indicateurDao;

    @Before
    public void setUp() {
        indicateurDao = new IndicateurDao();
    }

    @Test
    public void testAdd() {
        // Prepare test data
        Avancement avancement = new Avancement();
        avancement.setId(8); // Assuming existing avancement ID
        Responsable responsable = new Responsable();
        responsable.setId(14); // Assuming existing responsible ID
        String productivite = "Test Productivite";
        String performance = "Test Performance";
        String tauxDoccupation = "Test Taux D'occupation";

        Indicateur indicateurToAdd = new Indicateur(0, avancement, responsable, productivite, performance, tauxDoccupation);

        // Call the method
        int generatedKey = indicateurDao.add(indicateurToAdd);

        assertNotNull(generatedKey);
        // You can add more assertions based on your requirements
    }

    @Test
    public void testGetIndicateurByAvcId() {
        int avancementId = 8; // Assuming existing avancement ID

        Indicateur indicateur = indicateurDao.getIndicateurByAvcId(avancementId);

        assertNotNull(indicateur);
        // You can add more assertions based on your requirements
    }
}
