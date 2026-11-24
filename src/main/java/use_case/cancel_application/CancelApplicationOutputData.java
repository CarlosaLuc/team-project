package use_case.cancel_application;

import entity.ApplicationStatus;

public class CancelApplicationOutputData {
    private final String applicationID;

    public CancelApplicationOutputData(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getApplicationID() {return applicationID;}
}
