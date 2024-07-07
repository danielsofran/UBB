package org.app.cause.domain;

import jakarta.persistence.*;
import org.app.user.domain.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "causes")
public class Cause {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name="cause_sequence",
            sequenceName = "cause_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cause_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(length = 20)
    private String causeType;

    @Column
    private LocalDateTime date;

    @Column
    private LocalDateTime deadline;

    @Column(length = 300)
    private String location;

    @Column(nullable = false)
    private Long moneyTarget;

    @Column
    private Long moneyObtained;

    @Column
    private Integer score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCauseType() {
        return causeType;
    }

    public void setCauseType(String causeType) {
        this.causeType = causeType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getMoneyTarget() {
        return moneyTarget;
    }

    public void setMoneyTarget(Long moneyTarget) {
        this.moneyTarget = moneyTarget;
    }

    public Long getMoneyObtained() {
        return moneyObtained;
    }

    public void setMoneyObtained(Long moneyObtained) {
        this.moneyObtained = moneyObtained;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
