package entity;

public class Pet {
    private final String petID;
    private final String name;
    private final String breed;
    private final String gender;
    private final String vaccine;
    private final int age;
    private final int weight;
    private final String imageURL;

    public Pet(String petID, String name, String breed, String gender, String vaccine,
               int age, int weight, String imageURL){
        this.petID = petID;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.vaccine = vaccine;
        this.age = age;
        this.weight = weight;
        this.imageURL = imageURL;

    }

    // getter
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
}