package com.example.mobilebackend1.controller;

import com.example.mobilebackend1.dto.PageDto;
import com.example.mobilebackend1.dto.TicketDto;
import com.example.mobilebackend1.service.JwtService;
import com.example.mobilebackend1.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public PageDto<TicketDto> getTickets(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "10") int size,
                                         @RequestParam(name = "content", defaultValue = "") String content,
                                         @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            return ticketService.getTickets(PageRequest.of(page, size), jwtToken, content);
        }
        return null;
    }

    @GetMapping("/all")
    public List<TicketDto> getAllTickets(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            return ticketService.getAllTickets(jwtToken);
        }
        return null;
    }

    @PatchMapping
    public TicketDto updateTicket(@RequestBody TicketDto ticketDto,
                                  @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            return ticketService.updateTicket(ticketDto, jwtToken);
        }
        return null;
    }

    @PostMapping
    public TicketDto createTicket(@RequestBody TicketDto ticketDto,
                                  @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            TicketDto createdTicket = ticketService.createTicket(ticketDto, jwtToken);

            if (createdTicket != null) {
                String userDestination = "/user/" + jwtToken + "/topic/tickets/created";
                messagingTemplate.convertAndSend(userDestination, createdTicket);
            }

            return createdTicket;
        }
        return null;
    }

    @MessageMapping("/tickets/create")
    public void handleTicketCreationMessage(TicketDto ticketDto) {
        // not needed
    }

    @SendTo("/topic/tickets/created")
    public TicketDto handleTicketCreation(TicketDto ticketDto) {
        return ticketDto;
    }
}
