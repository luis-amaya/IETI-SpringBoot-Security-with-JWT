package co.edu.eci.ieti.data;

import co.edu.eci.ieti.dto.UserDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

@Document
public class User {

    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String lastName;
    private String createdAt;
    private String passwordHash;
    private List<RoleEnum> roles;

    public User() {

    }

    public User(String id, String name, String email, String lastName, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }

    public User(UserDto userDto) {
        name = userDto.getName();
        email = userDto.getEmail();
        lastName = userDto.getLastName();
        passwordHash = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
    }

    public void update(UserDto userDto){
        name = userDto.getName();
        email = userDto.getEmail();
        lastName = userDto.getLastName();
        if (userDto.getPassword() != null) {
            passwordHash = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String toString() {
        return "Id: " + id + ", Name: " + name + ", Email: " + email + ", lastName: " + lastName + ", CreatedAt: "
                + createdAt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public boolean equals(User user) {
        boolean equals = false;
        if (this.getEmail().equals(user.getEmail())
                || (this.getName().equals(user.getName()) && this.getLastName().equals(user.getLastName()))) {
            equals = true;
        }
        return equals;
    }
}
