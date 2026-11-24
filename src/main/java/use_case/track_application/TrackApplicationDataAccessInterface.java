package use_case.track_application;

import entity.AdoptionApplication;
import java.util.List;

/**
 * DAO interface for Track Application Use Case.
 */
public interface TrackApplicationDataAccessInterface {

    /**
     * retrieve all applications belonging to the adopter with the given username.
     * @param username the username to look up
     * @return all applications belonging to the adopter with the given username
     */
    List<AdoptionApplication> getApplicationsByAdopter(String username);
}
