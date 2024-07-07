package org.app.comments.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CommentDTO {

    private Long id;

    @NotNull
    private Long userID;

    @NotNull
    private Long causeID;

    private LocalDateTime date;

    @NotNull
    @Size(max = 1000)
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getCauseID() {
        return causeID;
    }

    public void setCauseID(Long causeID) {
        this.causeID = causeID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
