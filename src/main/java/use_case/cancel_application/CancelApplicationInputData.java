package use_case.cancel_application;

public class CancelApplicationInputData {
    private final String applicationID;
    private final String adopterUsername;

    public CancelApplicationInputData(String applicationID, String adopterUsername) {
        this.applicationID = applicationID;
        this.adopterUsername = adopterUsername;
    }

    public String getAdopterUsername() {return adopterUsername;}

    public String getApplicationID() {return applicationID;}
}
