package entity;

import java.time.LocalDate;

public class AdoptionApplication {
    private final String applicationID;
    private final String petID;
    private final Adopter adopter;
    private final Applicant applicant;
    private final LocalDate date;
    private final String reason;
    private ApplicationStatus status;

    public AdoptionApplication(String applicationID, String petID, Adopter adopter, Applicant applicant,
                               LocalDate date, String reason, ApplicationStatus status) {
        this.applicationID = applicationID;
        this.petID = petID;
        this.adopter = adopter;
        this.applicant = applicant;
        this.date = date;
        this.reason = reason;
        this.status = status;
    }

    //getter
    public String getApplicationID() {
        return applicationID;
    }

    public String getPetID() {
        return petID;
    }

    public Adopter getAdopter() {
        return adopter;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {this.status = status; }
}
