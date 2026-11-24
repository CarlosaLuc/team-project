package data_access;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

import entity.Pet;

class FilePetDataAccessObjectTest {

    @Test
    void petsSurviveRestart() throws IOException {
        final String csv = Files.createTempDirectory("pets").resolve("pets.csv").toString();

        final FilePetDataAccessObject dao = new FilePetDataAccessObject(csv);
        dao.save(new Pet("P001", "Max, Jr.", "Golden \"Goldie\" Retriever", "Male",
                true, 3, 25.5, "/pics/max, jr.png"));
        dao.save(new Pet("P002", "Mimi", "Siamese", "Female", false, 1, 4.0, ""));

        // A brand-new object reads the same file, like reopening the program.
        final FilePetDataAccessObject reloaded = new FilePetDataAccessObject(csv);
        assertTrue(reloaded.existsById("P001"));
        assertFalse(reloaded.existsById("P003"));
        assertEquals(2, reloaded.getAll().size());

        final Pet max = reloaded.get("P001");
        assertEquals("Max, Jr.", max.getName());
        assertEquals("Golden \"Goldie\" Retriever", max.getBreed());
        assertEquals("Male", max.getGender());
        assertTrue(max.isVaccinated());
        assertEquals(3, max.getAge());
        assertEquals(25.5, max.getWeight());
        assertEquals("/pics/max, jr.png", max.getImagePath());
        assertEquals("", reloaded.get("P002").getImagePath());
    }

    @Test
    void removeByIdDeletesFromFile() throws IOException {
        final String csv = Files.createTempDirectory("pets").resolve("pets.csv").toString();
        final FilePetDataAccessObject dao = new FilePetDataAccessObject(csv);
        dao.save(new Pet("P001", "Buddy", "Lab", "Male", true, 3, 25, ""));
        dao.save(new Pet("P002", "Mimi", "Siamese", "Female", false, 1, 4, ""));

        assertTrue(dao.removeById("P001"));
        assertFalse(dao.removeById("P001"));
        assertFalse(dao.removeById("NOPE"));

        // After a restart, the removed pet is still gone and the other one is still there.
        final FilePetDataAccessObject reloaded = new FilePetDataAccessObject(csv);
        assertFalse(reloaded.existsById("P001"));
        assertTrue(reloaded.existsById("P002"));
    }
}
