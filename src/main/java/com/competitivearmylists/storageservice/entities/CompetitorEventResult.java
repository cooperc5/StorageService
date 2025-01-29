package com.competitivearmylists.storageservice.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "competitor_event_result")
public class CompetitorEventResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_id", nullable = true)
    private String emailId;

    @Column(name = "result", nullable = false)
    private String result;

    @Column(name = "list", nullable = false)
    private String list;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "date", nullable = false)
    private String date;

    public CompetitorEventResult() {
    }

    public CompetitorEventResult(String firstName, String lastName, String emailId, String result, String list, String eventName, String date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.result = result;
        this.list = list;
        this.eventName = eventName;
        this.date = date;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public String getList() { return list; }
    public void setList(String list) { this.list = list; }
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return "CompetitorEventResult [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", result="
                + result + ", list=" + list + ", eventName=" + eventName + ", date=" + date + "]";
    }
}
