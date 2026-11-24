package entity;

/**
 * A pet that can be adopted.
 */
public class Pet {
    private final String petId;
    private final String name;
    private final String breed;
    private final String gender;
    private final boolean vaccinated;
    private final int age;
    private final double weight;
    private final String imagePath;

    /**
     * Creates a pet.
     *
     * @param petId      unique id of the pet
     * @param name       the pet's name
     * @param breed      the pet's breed
     * @param gender     "Male" or "Female"
     * @param vaccinated whether the pet is vaccinated
     * @param age        age in years
     * @param weight     weight in kg
     * @param imagePath  path to a picture of the pet, or "" if there is none
     */
    public Pet(String petId, String name, String breed, String gender,
               boolean vaccinated, int age, double weight, String imagePath) {
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

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public String getImagePath() {
        return imagePath;
    }
}
