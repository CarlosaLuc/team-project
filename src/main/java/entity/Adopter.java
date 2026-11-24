package entity;

public class Adopter {
    private final String username;
    private final String password;

    public Adopter(String username, String password){
        this.username = username;
        this.password = password;
    }

    //getter
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
