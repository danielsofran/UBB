package org.app.cause.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CauseDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 1000)
    private String description;

    @Size(max = 20)
    private String causeType;

    private LocalDateTime date;

    private LocalDateTime deadline;

    @Size(max = 300)
    private String location;

    @NotNull
    private Long moneyTarget;

    private Long moneyObtained;

    private Integer score;

    @NotNull
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
