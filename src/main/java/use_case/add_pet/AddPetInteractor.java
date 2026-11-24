package use_case.add_pet;

import entity.Pet;

/**
 * Lets the admin add a new pet after checking every field.
 */
public class AddPetInteractor implements AddPetInputBoundary {
    public static final String MALE = "Male";
    public static final String FEMALE = "Female";
    public static final int MAX_AGE = 30;
    public static final double MAX_WEIGHT = 150;
    private static final String PET_ID_PATTERN = "[A-Za-z0-9_-]{1,20}";

    private final AddPetDataAccessInterface dataAccess;
    private final AddPetOutputBoundary presenter;

    public AddPetInteractor(AddPetDataAccessInterface dataAccess, AddPetOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(AddPetInputData in) {
        final String petId = clean(in.getPetId());
        final String name = clean(in.getName());
        final String breed = clean(in.getBreed());
        final String gender = clean(in.getGender());
        final String ageText = clean(in.getAge());
        final String weightText = clean(in.getWeight());

        final String error = findError(petId, name, breed, gender, ageText, weightText);
        if (error != null) {
            presenter.prepareFailView(error);
            return;
        }

        final Pet pet = new Pet(petId, name, breed, gender, in.isVaccinated(),
                Integer.parseInt(ageText), Double.parseDouble(weightText), clean(in.getImagePath()));
        dataAccess.save(pet);
        presenter.prepareSuccessView(new AddPetOutputData(petId, name));
    }

    /** Returns the first problem with the input, or null if everything is valid. */
    private String findError(String petId, String name, String breed, String gender,
                             String ageText, String weightText) {
        if (petId.isEmpty()) {
            return "Pet ID is required.";
        }
        if (!petId.matches(PET_ID_PATTERN)) {
            return "Pet ID can only use letters, digits, - or _ (max 20).";
        }
        if (dataAccess.existsById(petId)) {
            return "A pet with ID \"" + petId + "\" already exists.";
        }
        if (name.isEmpty()) {
            return "Name is required.";
        }
        if (breed.isEmpty()) {
            return "Breed is required.";
        }
        if (!MALE.equals(gender) && !FEMALE.equals(gender)) {
            return "Please choose a gender.";
        }
        final Integer age = parseInt(ageText);
        if (age == null || age < 0 || age > MAX_AGE) {
            return "Age must be a whole number from 0 to " + MAX_AGE + ".";
        }
        final Double weight = parseDouble(weightText);
        if (weight == null || weight <= 0 || weight > MAX_WEIGHT) {
            return "Weight must be a number above 0 and at most " + (int) MAX_WEIGHT + " (kg).";
        }
        return null;
    }

    private static String clean(String s) {
        return s == null ? "" : s.trim();
    }

    private static Integer parseInt(String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    private static Double parseDouble(String s) {
        try {
            final double value = Double.parseDouble(s);
            return Double.isFinite(value) ? value : null;
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
}
