package com.example.mobilebackend1.service;

import com.example.mobilebackend1.dto.UserDto;
import com.example.mobilebackend1.exception.NotAuthorizedException;
import com.example.mobilebackend1.model.Image;
import com.example.mobilebackend1.model.Ticket;
import com.example.mobilebackend1.model.User;
import com.example.mobilebackend1.repository.ImageRepository;
import com.example.mobilebackend1.repository.TicketRepository;
import com.example.mobilebackend1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public void save(Image image, String ticketUuid, String jwtToken) {
        UserDto userDto = jwtService.getUserFromToken(jwtToken);
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow();
        Ticket ticket = ticketRepository.findByUuid(ticketUuid).orElseThrow();
        if (!ticket.getUserId().equals(user.getId())) {
            throw new NotAuthorizedException("Unauthorized.");
        }
        image.setTicketId(ticket.getId());
        ticket.getImagesUrls().add("http://localhost:8080" + "/images/" + imageRepository.save(image).getUuid());
        ticketRepository.save(ticket);
    }

    public Optional<Image> findByUuid(String uuid) {
        return imageRepository.findByUuid(uuid);
    }
}
