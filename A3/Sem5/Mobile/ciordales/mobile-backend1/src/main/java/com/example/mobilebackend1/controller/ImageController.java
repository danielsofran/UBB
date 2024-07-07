package com.example.mobilebackend1.controller;

import com.example.mobilebackend1.exception.NotAuthorizedException;
import com.example.mobilebackend1.model.Image;
import com.example.mobilebackend1.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("ticketId") String ticketUuid,
                                              @RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                Image image = new Image();
                image.setName(LocalDateTime.now() + "_" + file.getOriginalFilename());
                image.setContent(file.getBytes());
                String jwtToken = authorizationHeader.substring(7);
                imageService.save(image, ticketUuid, jwtToken);
                return ResponseEntity.ok("Image uploaded successfully.");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save image.");
        } catch (NotAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("name") String uuid) {
        Optional<Image> imageOptional = imageService.findByUuid(uuid);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(image.getContent().length);

            return new ResponseEntity<>(image.getContent(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
