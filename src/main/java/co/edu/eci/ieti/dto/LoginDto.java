package co.edu.eci.ieti.dto;

public class LoginDto {

    String email;
    String password;

    public LoginDto(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
