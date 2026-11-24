package entity;

public class Admin {
    private final String password;

    public Admin(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
