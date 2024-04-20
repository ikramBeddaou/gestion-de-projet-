package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.*;

import gestionDeProjet.dao.ProjetDao;
import gestionDeProjet.model.Professeur;
import gestionDeProjet.model.Projet;

public class ProjetDaoTest {


    private ProjetDao projetDao;

    @Before
    public void setUp() {
        projetDao = new ProjetDao();
    }

    @Test
    public void testGetAllByProfId() {
        // Assuming there are projects in the database with a certain professor ID
        int professorId = 0; // Change to an existing professor ID in your database
        ArrayList<Projet> projects = projetDao.getAllByProfId(professorId);
        assertNotNull(projects);
        assertFalse(projects.isEmpty());
    }

    @Test
    public void testAddDeleteAndGetById() {
        // Adding a project
        Projet projectToAdd = new Projet(0, "Test Project", "Description",
        		new Date(System.currentTimeMillis()),
        		new Date(System.currentTimeMillis()),
        		"Domain", new Professeur(), "Etat");
        projetDao.add(projectToAdd);

        // Getting the ID of the added project
        int projectId = projectToAdd.getId();

        // Retrieving the project by its ID
        Projet retrievedProject = projetDao.getById(projectId);
        assertNotNull(retrievedProject);
        assertEquals(projectToAdd.getNom(), retrievedProject.getNom());

        // Deleting the added project
        projetDao.delete(projectId);

        // Ensuring the project is deleted
        assertNull(projetDao.getById(projectId));
    }

    @Test
    public void testUpdate() {
        // Assuming there is a project in the database with a certain ID
        int projectIdToUpdate = 1; // Change to an existing project ID in your database
        Projet existingProject = projetDao.getById(projectIdToUpdate);
        assertNotNull(existingProject);

        // Updating the project
        existingProject.setDescription("Updated Description");
        projetDao.update(existingProject);

        // Retrieving the updated project
        Projet updatedProject = projetDao.getById(projectIdToUpdate);
        assertNotNull(updatedProject);
        assertEquals("Updated Description", updatedProject.getDescription());
    }

    @Test
    public void testGetByEquipeId() {
        // Assuming there is an existing team ID in your database
        int equipeId = 1; // Change to an existing equipe ID in your database
        Projet project = projetDao.getByEquipeId(equipeId);
        assertNotNull(project);
        // Assert other properties as needed
    }
}
