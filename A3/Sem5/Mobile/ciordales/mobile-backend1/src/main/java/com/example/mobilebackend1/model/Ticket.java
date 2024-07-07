package com.example.mobilebackend1.model;

import com.example.mobilebackend1.dto.TicketDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String uuid;
    private Long userId;
    private String name;
    private String description;
    private Integer complexity;
    private boolean isDone;
    private Double latitude;
    private Double longitude;
    @ElementCollection
    private List<String> imagesUrls = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;

    public Ticket(TicketDto ticketDto, Long userId) {
        this.uuid = ticketDto.getUuid();
        this.userId = userId;
        this.name = ticketDto.getName();
        this.description = ticketDto.getDescription();
        this.complexity = ticketDto.getComplexity();
        this.isDone = ticketDto.getIsDone();
        this.latitude = ticketDto.getLatitude();
        this.longitude = ticketDto.getLongitude();
        this.imagesUrls = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID().toString();
    }
}
