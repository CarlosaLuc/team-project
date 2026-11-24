package entity;

public class Applicant {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String email;
    private final String phone;

    public Applicant(String firstName, String lastName, String address, String email, String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    //getter

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
}
