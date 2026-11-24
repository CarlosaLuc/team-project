package use_case.submit_application;

public class SubmitApplicationInputData {
    private final String adopterUsername;
    private final String petID;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String email;
    private final String phone;
    private final String reason;

    public SubmitApplicationInputData(String adopterUsername, String petID, String firstName, String lastName,
                                      String address, String email, String phone, String reason) {
        this.adopterUsername = adopterUsername;
        this.petID = petID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.reason = reason;
    }

    // getter

    public String getAdopterUsername() {return adopterUsername;}

    public String getPetID() {return petID;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getAddress() {return address;}

    public String getEmail() {return email;}

    public String getPhone() {return phone;}

    public String getReason() {return reason;}
}
