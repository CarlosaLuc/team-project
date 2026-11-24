package use_case.track_application;

import java.util.List;

public class TrackApplicationOutputData {
    private final List<ApplicationSummary> applications;

    public TrackApplicationOutputData(List<ApplicationSummary> applications) {
        this.applications = List.copyOf(applications);
    }

    public List<ApplicationSummary> getApplications() {return applications;}

    public boolean hasApplication() {return !applications.isEmpty();}
}
