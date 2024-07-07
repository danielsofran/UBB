package com.example.mobilebackend1.service;

import com.example.mobilebackend1.dto.PageDto;
import com.example.mobilebackend1.dto.TicketDto;
import com.example.mobilebackend1.model.Ticket;
import com.example.mobilebackend1.repository.TicketRepository;
import com.example.mobilebackend1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    private TicketRepository ticketRepository;
    private UserRepository userRepository;
    private JwtService jwtService;

    public PageDto<TicketDto> getTickets(Pageable pageable, String jwtToken, String content) {
        String userEmail = jwtService.getUserFromToken(jwtToken).getEmail();
        Long userId = userRepository.findByEmail(userEmail).orElseThrow().getId();
        Page<Ticket> tickets = ticketRepository.findByUserIdAndDescriptionContainingIgnoreCaseOrderByCreatedDescIdAsc(userId, content, pageable);
        return new PageDto<>(tickets.stream().map(TicketDto::new).toList(), content, tickets.getTotalPages(), tickets.getTotalElements(), tickets.getPageable());
    }

    public List<TicketDto> getAllTickets(String jwtToken) {
        String userEmail = jwtService.getUserFromToken(jwtToken).getEmail();
        Long userId = userRepository.findByEmail(userEmail).orElseThrow().getId();
        List<Ticket> tickets = ticketRepository.findByUserIdOrderByCreatedDescIdAsc(userId);
        return tickets.stream().map(TicketDto::new).toList();
    }

    public TicketDto createTicket(TicketDto ticketDto, String jwtToken) {
        String userEmail = jwtService.getUserFromToken(jwtToken).getEmail();
        Long userId = userRepository.findByEmail(userEmail).orElseThrow().getId();
        return new TicketDto(ticketRepository.save(new Ticket(ticketDto, userId)));
    }

    public TicketDto updateTicket(TicketDto ticketDto, String jwtToken) {
        String userEmail = jwtService.getUserFromToken(jwtToken).getEmail();
        Long userId = userRepository.findByEmail(userEmail).orElseThrow().getId();
        Ticket ticket = ticketRepository.findByUuid(ticketDto.getUuid()).orElseThrow();
        if (!ticket.getUserId().equals(userId)) {
            throw new RuntimeException("Ticket does not belong to user");
        }
        if (ticketDto.getName() != null)
            ticket.setName(ticketDto.getName());
        if (ticketDto.getDescription() != null)
            ticket.setDescription(ticketDto.getDescription());
        if (ticketDto.getComplexity() != null)
            ticket.setComplexity(ticketDto.getComplexity());
        if (ticketDto.getIsDone() != null)
            ticket.setDone(ticketDto.getIsDone());
        return new TicketDto(ticketRepository.save(ticket));
    }
}
