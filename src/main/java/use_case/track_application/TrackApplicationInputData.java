package use_case.track_application;

public class TrackApplicationInputData {
    private final String adopterUsername;

    public TrackApplicationInputData(String adopterUsername) {
        this.adopterUsername = adopterUsername;
    }

    public String getAdopterUsername() {return adopterUsername;}
}
