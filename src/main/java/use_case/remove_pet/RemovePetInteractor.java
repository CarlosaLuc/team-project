package use_case.remove_pet;

public class RemovePetInteractor implements RemovePetInputBoundary {
    private final RemovePetDataAccessInterface petDataAccess;
    private final RemovePetOutputBoundary presenter;

    public RemovePetInteractor(RemovePetDataAccessInterface petDataAccess, RemovePetOutputBoundary presenter) {
        this.petDataAccess = petDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(String petID) {
        boolean removed = petDataAccess.removeById(petID);

        if (removed) {
            presenter.prepareSuccessView(petID);
        } else  {
            presenter.prepareFailureView("Pet " + petID + " was not found.");
        }
    }
}
