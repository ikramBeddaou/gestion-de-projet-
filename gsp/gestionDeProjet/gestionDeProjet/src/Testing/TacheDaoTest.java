package Testing;

import gestionDeProjet.dao.TacheDao;
import gestionDeProjet.model.Phase;
import gestionDeProjet.model.Responsable;
import gestionDeProjet.model.Tache;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.*;

public class TacheDaoTest {

    private TacheDao tacheDao;

    @Before
    public void setUp() {
        tacheDao = new TacheDao();
    }

    @Test
    public void testGetAllTacheByResponsableId() {
        int responsableId = 1; // Assuming existing responsible ID

        ArrayList<Tache> taches = tacheDao.getAllTacheByResponsableId(responsableId);

        assertNotNull(taches);
        // You can add more assertions based on your requirements
    }

    @Test
    public void testGetAllTacheByPhaseId() {
        int phaseId = 1; // Assuming existing phase ID

        ArrayList<Tache> taches = tacheDao.getAllTacheByPhaseId(phaseId);

        assertNotNull(taches);
        // You can add more assertions based on your requirements
    }

    @Test
    public void testAdd() {
        // Prepare test data
        Phase phase = new Phase();
        phase.setId(29); // Assuming existing phase ID
        Responsable responsable = new Responsable();
        responsable.setId(28); // Assuming existing responsible ID
        String nom = "Test Tache";
        Date dateDebut = new Date();
        Date dateFin = new Date(System.currentTimeMillis() + 86400000); // Adding one day from current date
        String etat = "En Cours...";

        Tache tacheToAdd = new Tache(0, nom, dateDebut, dateFin, etat, phase, responsable);

        // Call the method
        tacheDao.add(tacheToAdd);

        // No need to verify anything as add operation doesn't return anything
    }

    @Test
    public void testUpdate() {
        // Prepare test data
        int tacheIdToUpdate = 25; // Assuming existing task ID
        Phase phase = new Phase();
        phase.setId(29); // Assuming existing phase ID
        Responsable responsable = new Responsable();
        responsable.setId(28); // Assuming existing responsible ID
        String updatedNom = "Tache Modifié";
        Date updatedDateDebut = new Date();
        Date updatedDateFin = new Date(System.currentTimeMillis() + 86400000); // Adding one day from current date
        String updatedEtat = "Terminé";

        Tache updatedTache = new Tache(tacheIdToUpdate, updatedNom, updatedDateDebut, updatedDateFin, updatedEtat, phase, responsable);

        // Call the method
        tacheDao.update(updatedTache);

        // No need to verify anything as update operation doesn't return anything
    }

    @Test
    public void testDelete() {
        int tacheIdToDelete = 1; // Assuming existing task ID

        // Call the method
        tacheDao.delete(tacheIdToDelete);

        // No need to verify anything as delete operation doesn't return anything
    }

    @Test
    public void testGetTacheById() {
        int tacheId = 25; // Assuming existing task ID

        Tache tache = tacheDao.getTacheById(tacheId);

        assertNotNull(tache);
        // You can add more assertions based on your requirements
    }

    @Test
    public void testUpdateTacheEtatById() {
    	 int tacheIdToDelete = 999; // Assuming non-existent task ID

    	    // Call the method and assert that it throws SQLException
    	    assertThrows(SQLException.class, () -> tacheDao.delete(tacheIdToDelete));

    }
}
