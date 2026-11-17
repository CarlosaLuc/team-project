package entity;

public class Pet {
    private final String petID;
    private final String name;
    private final String breed;
    private final String gender;
    private final String vaccine;
    private final int age;
    private final int weight;
    private final String ImageURL;

    public Pet(String petID, String name, String breed, String gender, String vaccine,
               int age, int weight, String ImageURL){
        this.petID = petID;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.vaccine = vaccine;
        this.age = age;
        this.weight = weight;
        this.ImageURL = ImageURL;

    }
}
