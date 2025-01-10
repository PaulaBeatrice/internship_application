package model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "applications")
public class Application implements Serializable {

    /**
     * Unique identifier for the application.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User who applied (applicant).
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "applicant_id", nullable = false)
    private User applicant;

    /**
     * Internship the user applied to.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "internship_id", nullable = false)
    private Internship internship;

    /**
     * Date of application.
     */
    @Column(nullable = false)
    private LocalDate applicationDate;

    /**
     * Status of the application.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    public Application() {
    }

    // Enum for application status
    public enum ApplicationStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }
}
