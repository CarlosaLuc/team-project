package use_case.cancel_application;

import entity.AdoptionApplication;

/**
 * DAO interface for the Cancel Application Use Case.
 */
public interface CancelApplicationDataAccessInterface {

    /**
     * Returns the application with the given applicationID.
     * @param applicationID the applicationID to look for
     * @return the application with the given applicationID
     */
    AdoptionApplication getApplication(String applicationID);

    /**
     * Deletes the application with the given application ID.
     * @param applicationID the applicationID to look for
     */
    void delete(String applicationID);
}
