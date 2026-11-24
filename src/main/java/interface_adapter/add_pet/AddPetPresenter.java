package interface_adapter.add_pet;

import use_case.add_pet.AddPetOutputBoundary;
import use_case.add_pet.AddPetOutputData;

/**
 * Shows the result of adding a pet on the same screen,
 * so the admin can add several pets in a row.
 */
public class AddPetPresenter implements AddPetOutputBoundary {
    private final AddPetViewModel viewModel;

    public AddPetPresenter(AddPetViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(AddPetOutputData outputData) {
        final AddPetState state = viewModel.getState();
        state.setError(null);
        state.setNotice("Added " + outputData.getName() + " (ID " + outputData.getPetId() + ").");
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final AddPetState state = viewModel.getState();
        state.setError(errorMessage);
        state.setNotice(null);
        viewModel.firePropertyChanged();
    }
}
