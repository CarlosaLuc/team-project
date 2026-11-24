package use_case.add_pet;

import entity.Pet;

/**
 * What the add pet use case needs from storage.
 * Any pet storage class (including a teammate's) can implement this.
 */
public interface AddPetDataAccessInterface {
    boolean existsById(String petId);

    void save(Pet pet);
}
