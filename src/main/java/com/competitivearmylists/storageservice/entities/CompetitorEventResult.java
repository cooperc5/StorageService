package com.competitivearmylists.storageservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Entity class representing a competitor's event result.
 * This class is mapped to the `competitor_event_result` table in the database.
 * It stores details about the competitor, event, and their result.
 */
@Getter // Generates getter methods for all fields at runtime
@Setter // Generates setter methods for all fields at runtime
@NoArgsConstructor // Generates a no-argument constructor (required by JPA)
@AllArgsConstructor // Generates a constructor with all arguments
@ToString // Generates a `toString()` method including all fields
@Entity // Marks this class as a JPA entity that is mapped to a database table
@Table(name = "competitor_event_result") // Maps this entity to the table named `competitor_event_result`
public class CompetitorEventResult {

    /**
     * Unique identifier for each result entry.
     *
     * - `@Id` marks this field as the primary key.
     * - `@GeneratedValue(strategy = GenerationType.IDENTITY)` ensures that the database
     *   automatically generates unique values for this field.
     * - `@Column(name = "id")` explicitly maps this field to the `id` column in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * First name of the competitor.
     *
     * - Mapped to the `first_name` column in the database.
     * - `nullable = false` ensures this field cannot be null.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Last name of the competitor.
     *
     * - Mapped to the `last_name` column in the database.
     * - `nullable = false` ensures this field cannot be null.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Email ID of the competitor (optional).
     *
     * - Mapped to the `email_id` column in the database.
     * - `nullable = true` allows this field to be null.
     */
    @Column(name = "email_id", nullable = true)
    private String emailId;

    /**
     * The competitor's result in the event.
     *
     * - Mapped to the `result` column in the database.
     * - `nullable = false` ensures this field cannot be null.
     */
    @Column(name = "result", nullable = false)
    private String result;

    /**
     * The list or configuration used by the competitor.
     *
     * - Mapped to the `list` column in the database.
     * - `nullable = false` ensures this field cannot be null.
     */
    @Column(name = "list", nullable = false)
    private String list;

    /**
     * Name of the event where the competitor participated.
     *
     * - Mapped to the `event_name` column in the database.
     * - `nullable = false` ensures this field cannot be null.
     */
    @Column(name = "event_name", nullable = false)
    private String eventName;

    /**
     * Date when the event took place.
     *
     * - Mapped to the `date` column in the database.
     * - Uses `LocalDate` for proper date handling.
     * - `nullable = false` ensures this field cannot be null.
     */
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "position", nullable = false)
    private int position;
}
