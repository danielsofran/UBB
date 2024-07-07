package org.app.photo.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PhotoDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String path;

    @NotNull
    private Long causeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getCauseId() {
        return causeId;
    }

    public void setCauseId(Long causeId) {
        this.causeId = causeId;
    }
}
