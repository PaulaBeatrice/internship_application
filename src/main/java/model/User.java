package model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {
    /**
     * Unique identifier for the user.
     */
    @Id
    // Generated using the sequence strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User username.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * User password.
     */
    @Column(nullable = false)
    private String password;

    /**
     * User email.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * User type (STUDENT or RECRUITER).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    public User() {
    }

    // Enum for user type
    public enum UserType {
        STUDENT,
        RECRUITER
    }
}
