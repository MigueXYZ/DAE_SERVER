package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//queries
@NamedQueries({
        @NamedQuery(
                name = "getAllUsers",
                query = "SELECT u FROM User u ORDER BY u.name" // JPQL
        ),
        @NamedQuery(
                name = "getAllAdmins",
                query = "SELECT a FROM User u ORDER BY a.name where dtype = 'Administrator'" // JPQL
        ),
})
// Extra: try the other strategies… what happens to the database?
public class User {
    @Id
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    //constructor with no arguments
    public User() {
    }

    //constructor with arguments
    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    //getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}