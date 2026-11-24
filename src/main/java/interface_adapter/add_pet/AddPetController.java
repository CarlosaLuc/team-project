package interface_adapter.add_pet;

import use_case.add_pet.AddPetInputBoundary;
import use_case.add_pet.AddPetInputData;

/**
 * Turns the "Add a pet" form into a call to the add pet use case.
 */
public class AddPetController {
    private final AddPetInputBoundary interactor;

    public AddPetController(AddPetInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Adds a pet using what the admin entered.
     *
     * @param petId      pet id
     * @param name       name
     * @param breed      breed
     * @param gender     "Male" or "Female"
     * @param vaccinated whether the vaccinated box is ticked
     * @param age        age text
     * @param weight     weight text (kg)
     * @param imagePath  chosen image file, or "" if none
     */
    public void execute(String petId, String name, String breed, String gender,
                        boolean vaccinated, String age, String weight, String imagePath) {
        interactor.execute(new AddPetInputData(petId, name, breed, gender, vaccinated, age, weight, imagePath));
    }
}
