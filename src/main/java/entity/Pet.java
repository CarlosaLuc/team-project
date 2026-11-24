package entity;

/**
 * A pet that can be adopted.
 * Has two constructors and both sets of getter names so that code written
 * by different team members (Add Pet and the existing code in main) both work.
 */
public class Pet {
    private final String petID;
    private final String name;
    private final String breed;
    private final String gender;
    private final String vaccine;
    private final int age;
    private final double weight;
    private final String imageURL;

    /** Constructor used by the existing code in main. */
    public Pet(String petID, String name, String breed, String gender, String vaccine,
               int age, int weight, String imageURL) {
        this(petID, name, breed, gender, vaccine, age, (double) weight, imageURL);
    }

    /** Constructor used by Add Pet: vaccinated is stored as "Yes" or "No". */
    public Pet(String petID, String name, String breed, String gender, boolean vaccinated,
               int age, double weight, String imageURL) {
        this(petID, name, breed, gender, vaccinated ? "Yes" : "No", age, weight, imageURL);
    }

    private Pet(String petID, String name, String breed, String gender, String vaccine,
                int age, double weight, String imageURL) {
        this.petID = petID;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.vaccine = vaccine == null ? "" : vaccine;
        this.age = age;
        this.weight = weight;
        this.imageURL = imageURL == null ? "" : imageURL;
    }

    // getters (names used in main)
    public String getPetID() {
        return petID;
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

    public String getVaccine() {
        return vaccine;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public String getImageURL() {
        return imageURL;
    }

    // same values under the names used by Add Pet
    public String getPetId() {
        return petID;
    }

    public String getImagePath() {
        return imageURL;
    }

    /** True unless the vaccine value is empty, "No", "None" or "false". */
    public boolean isVaccinated() {
        final String v = vaccine.trim();
        return !v.isEmpty() && !v.equalsIgnoreCase("No")
                && !v.equalsIgnoreCase("None") && !v.equalsIgnoreCase("false");
    }
}
