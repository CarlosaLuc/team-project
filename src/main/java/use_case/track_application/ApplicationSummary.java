package use_case.track_application;

import java.time.LocalDate;
import entity.ApplicationStatus;

public class ApplicationSummary {
    private final String applicationID;
    private final String petID;
    private final LocalDate date;
    private final ApplicationStatus status;

    public ApplicationSummary(String applicationID, String petID, LocalDate date, ApplicationStatus status) {
        this.applicationID = applicationID;
        this.petID = petID;
        this.date = date;
        this.status = status;
    }

    public String getApplicationID() {return applicationID;}

    public String getPetID() {return petID;}

    public LocalDate getDate() {return date;}

    public ApplicationStatus getStatus() {return status;}
}
