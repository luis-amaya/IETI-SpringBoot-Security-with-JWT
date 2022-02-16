package co.edu.eci.ieti.dto;

public class UserDto {

    private String name;
    private String email;
    private String lastName;

    public UserDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Last Name:" + lastName;
    }
}
