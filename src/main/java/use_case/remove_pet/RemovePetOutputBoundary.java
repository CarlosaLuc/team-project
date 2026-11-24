package use_case.remove_pet;

public interface RemovePetOutputBoundary {
    void prepareSuccessView(String petID);
    void prepareFailureView(String message);
}
