package use_case.add_pet;

/**
 * Output data for the add pet use case.
 */
public class AddPetOutputData {
    private final String petId;
    private final String name;

    public AddPetOutputData(String petId, String name) {
        this.petId = petId;
        this.name = name;
    }

    public String getPetId() {
        return petId;
    }

    public String getName() {
        return name;
    }
}
