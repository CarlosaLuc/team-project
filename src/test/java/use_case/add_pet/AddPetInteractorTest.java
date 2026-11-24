package use_case.add_pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import entity.Pet;

class AddPetInteractorTest {

    /** Keeps pets in memory so the tests don't touch real files. */
    private static class InMemoryPets implements AddPetDataAccessInterface {
        private final Map<String, Pet> pets = new HashMap<>();

        @Override
        public boolean existsById(String petId) {
            return pets.containsKey(petId);
        }

        @Override
        public void save(Pet pet) {
            pets.put(pet.getPetId(), pet);
        }
    }

    private final InMemoryPets dao = new InMemoryPets();

    @Test
    void successSavesPetWithAllFields() {
        final AddPetOutputBoundary presenter = new AddPetOutputBoundary() {
            @Override
            public void prepareSuccessView(AddPetOutputData outputData) {
                assertEquals("P001", outputData.getPetId());
                assertEquals("Buddy", outputData.getName());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected failure: " + errorMessage);
            }
        };
        new AddPetInteractor(dao, presenter).execute(new AddPetInputData(
                " P001 ", " Buddy ", "Golden Retriever", "Male", true, " 3 ", "25.5", "/pics/buddy.png"));

        final Pet pet = dao.pets.get("P001");
        assertEquals("Buddy", pet.getName());
        assertEquals("Golden Retriever", pet.getBreed());
        assertEquals("Male", pet.getGender());
        assertTrue(pet.isVaccinated());
        assertEquals(3, pet.getAge());
        assertEquals(25.5, pet.getWeight());
        assertEquals("/pics/buddy.png", pet.getImagePath());
    }

    @Test
    void imageIsOptional() {
        final boolean[] succeeded = {false};
        final AddPetOutputBoundary presenter = new AddPetOutputBoundary() {
            @Override
            public void prepareSuccessView(AddPetOutputData outputData) {
                succeeded[0] = true;
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected failure: " + errorMessage);
            }
        };
        new AddPetInteractor(dao, presenter).execute(
                new AddPetInputData("P002", "Mimi", "Siamese", "Female", false, "1", "4", null));
        assertTrue(succeeded[0]);
        assertEquals("", dao.pets.get("P002").getImagePath());
    }

    @Test
    void failsWhenIdTaken() {
        dao.save(new Pet("P001", "Old", "Pug", "Male", true, 5, 8, ""));
        expectFailure(input("P001", "Buddy", "Lab", "Male", "3", "20"),
                "A pet with ID \"P001\" already exists.");
        assertEquals("Old", dao.pets.get("P001").getName());
    }

    @Test
    void failsWhenRequiredFieldsMissing() {
        expectFailure(input("", "Buddy", "Lab", "Male", "3", "20"), "Pet ID is required.");
        expectFailure(input("P1", " ", "Lab", "Male", "3", "20"), "Name is required.");
        expectFailure(input("P1", "Buddy", "", "Male", "3", "20"), "Breed is required.");
        expectFailure(input("P1", "Buddy", "Lab", "", "3", "20"), "Please choose a gender.");
    }

    @Test
    void failsWhenIdHasBadCharacters() {
        expectFailure(input("P 1", "Buddy", "Lab", "Male", "3", "20"),
                "Pet ID can only use letters, digits, - or _ (max 20).");
    }

    @Test
    void failsWhenAgeInvalid() {
        final String error = "Age must be a whole number from 0 to 30.";
        expectFailure(input("P1", "Buddy", "Lab", "Male", "three", "20"), error);
        expectFailure(input("P1", "Buddy", "Lab", "Male", "2.5", "20"), error);
        expectFailure(input("P1", "Buddy", "Lab", "Male", "-1", "20"), error);
        expectFailure(input("P1", "Buddy", "Lab", "Male", "31", "20"), error);
    }

    @Test
    void failsWhenWeightInvalid() {
        final String error = "Weight must be a number above 0 and at most 150 (kg).";
        expectFailure(input("P1", "Buddy", "Lab", "Male", "3", ""), error);
        expectFailure(input("P1", "Buddy", "Lab", "Male", "3", "0"), error);
        expectFailure(input("P1", "Buddy", "Lab", "Male", "3", "abc"), error);
        expectFailure(input("P1", "Buddy", "Lab", "Male", "3", "NaN"), error);
        expectFailure(input("P1", "Buddy", "Lab", "Male", "3", "200"), error);
    }

    private static AddPetInputData input(String id, String name, String breed, String gender,
                                         String age, String weight) {
        return new AddPetInputData(id, name, breed, gender, false, age, weight, "");
    }

    private void expectFailure(AddPetInputData inputData, String expectedError) {
        final AddPetOutputBoundary presenter = new AddPetOutputBoundary() {
            @Override
            public void prepareSuccessView(AddPetOutputData outputData) {
                fail("Adding the pet should have failed");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals(expectedError, errorMessage);
            }
        };
        final int before = dao.pets.size();
        new AddPetInteractor(dao, presenter).execute(inputData);
        assertEquals(before, dao.pets.size());
        assertFalse(dao.pets.containsKey("P1"));
    }
}
