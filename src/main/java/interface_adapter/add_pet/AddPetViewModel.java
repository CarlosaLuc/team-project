package interface_adapter.add_pet;

import interface_adapter.ViewModel;

/**
 * View model for the "Add a pet" screen.
 */
public class AddPetViewModel extends ViewModel<AddPetState> {
    public static final String VIEW_NAME = "add pet";

    public AddPetViewModel() {
        super(VIEW_NAME);
        setState(new AddPetState());
    }
}
