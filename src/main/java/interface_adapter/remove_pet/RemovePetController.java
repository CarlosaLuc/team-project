package interface_adapter.remove_pet;

import use_case.remove_pet.RemovePetInputBoundary;

public class RemovePetController {
    private final RemovePetInputBoundary removePetUseCase;

    public RemovePetController(RemovePetInputBoundary removePetUseCase) {
        this.removePetUseCase = removePetUseCase;
    }

    public void removePet(String petID) {
        removePetUseCase.execute(petID);
    }
}
