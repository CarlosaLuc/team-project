package interface_adapter.add_pet;

/**
 * What the "Add a pet" screen should show.
 */
public class AddPetState {
    private String error;
    private String notice;

    /** Returns the error to show in red, or null if there is none. */
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    /** Returns a success message to show in green, or null. */
    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
