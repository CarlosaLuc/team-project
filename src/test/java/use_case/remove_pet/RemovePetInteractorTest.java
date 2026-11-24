package use_case.remove_pet;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RemovePetInteractorTest {

    @Test
    void removingTheSamePetTwiceReportsSuccessThenFailure() {
        Set<String> petIDs = new HashSet<>(Arrays.asList("P001", "P002"));
        RemovePetDataAccessInterface petDataAccess = petIDs::remove;
        RecordingPresenter presenter = new RecordingPresenter();
        RemovePetInteractor interactor =
                new RemovePetInteractor(petDataAccess, presenter);

        interactor.execute("P001");

        assertFalse(petIDs.contains("P001"));
        assertTrue(petIDs.contains("P002"));
        assertEquals("P001", presenter.removedPetID);
        assertNull(presenter.errorMessage);

        presenter.removedPetID = null;
        interactor.execute("P001");

        assertNull(presenter.removedPetID);
        assertEquals("Pet P001 was not found.", presenter.errorMessage);
    }

    private static class RecordingPresenter implements RemovePetOutputBoundary {
        private String removedPetID;
        private String errorMessage;

        @Override
        public void prepareSuccessView(String petID) {
            removedPetID = petID;
        }

        @Override
        public void prepareFailureView(String message) {
            errorMessage = message;
        }
    }
}