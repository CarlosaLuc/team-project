package use_case.add_pet;

/**
 * Output boundary for the add pet use case.
 */
public interface AddPetOutputBoundary {
    void prepareSuccessView(AddPetOutputData outputData);

    void prepareFailView(String errorMessage);
}
