package use_case.add_pet;

/**
 * What the admin typed into the "Add a pet" form.
 * Age and weight are kept as text so the interactor can report a clear error
 * when they are not valid numbers.
 */
public class AddPetInputData {
    private final String petId;
    private final String name;
    private final String breed;
    private final String gender;
    private final boolean vaccinated;
    private final String age;
    private final String weight;
    private final String imagePath;

    public AddPetInputData(String petId, String name, String breed, String gender,
                           boolean vaccinated, String age, String weight, String imagePath) {
        this.petId = petId;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.vaccinated = vaccinated;
        this.age = age;
        this.weight = weight;
        this.imagePath = imagePath;
    }

    public String getPetId() {
        return petId;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getGender() {
        return gender;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public String getAge() {
        return age;
    }

    public String getWeight() {
        return weight;
    }

    public String getImagePath() {
        return imagePath;
    }
}
