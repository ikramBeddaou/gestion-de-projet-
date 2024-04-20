package Testing;


import gestionDeProjet.dao.PhasesDao;
import gestionDeProjet.model.Phase;
import gestionDeProjet.model.Projet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class PhasesDaoTest {

    private PhasesDao phasesDao;

    @Before
    public void setUp() {
        phasesDao = new PhasesDao();
    }

    @Test
    public void testGetAllByProjetId() {
        int projetId = 1; // Assuming existing project ID

        ArrayList<Phase> phases = phasesDao.getAllByProjetId(projetId);

        assertNotNull(phases);
        // You can add more assertions based on your requirements
    }

    @Test
    public void testGetPhaseById() {
        int phaseId = 1; // Assuming existing phase ID

        Phase phase = phasesDao.getPhaseById(phaseId);

        assertNotNull(phase);
        // You can add more assertions based on your requirements
    }

    @Test
    public void testGetNombreDesTachesByPhase() {
        int phaseId = 1; // Assuming existing phase ID

        int numberOfTasks = phasesDao.getNombreDesTachesByPhase(phaseId);

        // Assuming the number of tasks should be greater than or equal to 0
        assert (numberOfTasks >= 0);
        // You can add more assertions based on your requirements
    }

    @Test
    public void testAdd() {
        // Prepare test data
        Projet projet = new Projet(1, null, null, null, null, null, null, null);
        String nom = "Test Phase";
        String etat = "In Progress";
        Date dateDebut = new Date();
        Date dateFin = new Date(System.currentTimeMillis() + 86400000); // Adding one day from current date

        Phase phaseToAdd = new Phase(0, nom, etat, dateDebut, dateFin, projet);

        // Call the method
        int phaseId = phasesDao.add(phaseToAdd);

        // Verify the result
        assert (phaseId >= 0);
        // You can add more assertions based on your requirements
    }

    @Test
    public void testUpdate() {
        // Prepare test data
        int phaseIdToUpdate = 1; // Assuming existing phase ID
        Projet projet = new Projet(1, null, null, null, null, null, null, null);
        String updatedNom = "Updated Phase";
        String updatedEtat = "Completed";
        Date updatedDateDebut = new Date();
        Date updatedDateFin = new Date(System.currentTimeMillis() + 86400000); // Adding one day from current date

        Phase updatedPhase = new Phase(phaseIdToUpdate, updatedNom, updatedEtat, updatedDateDebut, updatedDateFin, projet);

        // Call the method
        phasesDao.update(updatedPhase);

        // No need to verify anything as update operation doesn't return anything
    }

    @Test
    public void testDelete() {
        int phaseIdToDelete = 1; // Assuming existing phase ID

        // Call the method and assert that it throws SQLException
        assertThrows(SQLException.class, () -> phasesDao.delete(phaseIdToDelete));
    }
}
