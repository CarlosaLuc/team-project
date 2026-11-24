package use_case.submit_application;

import entity.Adopter;
import entity.AdoptionApplication;

/**
 * DAO interface for Submit Application Use Case.
 */
public interface SubmitApplicationDataAccessInterface {

    /**
     * Returns the adopter with the given username.
     * @param username the username to look up
     * @return the adopter with the given username
     */
    Adopter getAdopter(String username);

    /**
     * checks if the pet with the given petID exists.
     * @param petID the petID to look for
     * @return true if a pet with the given petID exists; false otherwise
     */
    boolean petExists(String petID);

    /**
     * saves the application.
     * @param application the application to save
     */
    void save(AdoptionApplication application);
}
