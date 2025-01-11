package model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "internships")
public class Internship implements Serializable {

    /**
     * Unique identifier for the internship.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of the internship.
     */
    @Column(nullable = false)
    private String title;

    /**
     * Description of the internship.
     */
    @Column(nullable = false, length = 1000)
    private String description;

    /**
     * Location of the internship.
     */
    @Column(nullable = false)
    private String location;

    /**
     * Application deadline for the internship.
     */
    @Column(nullable = false)
    private LocalDate applicationDeadline;

    /**
     * Field of the internship.
     */
    @Column(nullable = false)
    private String field;

    /**
     * Skill requirements for the internship.
     */
    @Column(nullable = false)
    private String skillRequirement;

    /**
     * Recruiter associated with the internship.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "recruiter_id", nullable = false)
    private User recruiter;

    public Internship() {
    }
}
