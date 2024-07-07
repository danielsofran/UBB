package com.example.mobilebackend1.dto;

import com.example.mobilebackend1.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private String uuid;
    private String name;
    private String description;
    private Integer complexity;
    private Boolean isDone;
    private Double latitude;
    private Double longitude;
    private List<String> imagesUrls;
    private String created;
    private String updated;

    public TicketDto(Ticket ticket) {
        this.uuid = ticket.getUuid();
        this.name = ticket.getName();
        this.description = ticket.getDescription();
        this.complexity = ticket.getComplexity();
        this.isDone = ticket.isDone();
        this.latitude = ticket.getLatitude();
        this.longitude = ticket.getLongitude();
        this.imagesUrls = ticket.getImagesUrls();
        this.created = ticket.getCreated().toString();
        this.updated = ticket.getUpdated().toString();
    }
}
