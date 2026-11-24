package entity;

/**
 * The shelter admin. There is a single admin account, identified only by its password.
 * The stored value is the hashed password.
 */
public class Admin {
    private final String passwordHash;

    public Admin(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    /** Same as getPasswordHash(); kept so existing code that calls getPassword() still works. */
    public String getPassword() {
        return passwordHash;
    }
}
