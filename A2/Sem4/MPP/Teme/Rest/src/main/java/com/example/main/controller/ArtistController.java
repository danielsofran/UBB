package com.example.main.controller;

import com.example.main.domain.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.main.service.ArtistService;

import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("")
    public Iterable<Artist> getAllArtists() {
        System.err.println("Hello from getAllArtists");
        return artistService.findAllArtists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtist(@PathVariable("id") Integer id) {
        System.err.println("Hello from getArtist");
        Optional<Artist> artist = artistService.findArtist(id);
        return ResponseEntity.of(artist);
    }

    @PostMapping("")
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist) {
        System.err.println("Hello from addArtist");
        return ResponseEntity.of(artistService.addArtist(artist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable("id") Integer id) {
        System.err.println("Hello from deleteArtist");
        try{
            artistService.deleteArtist(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            System.err.println("Artist could not be deleted");
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable("id") Integer id, @RequestBody Artist artist) {
        System.err.println("Hello from updateArtist");
        try {
            Optional<Artist> updated = artistService.updateArtist(id, artist);
            return ResponseEntity.of(updated);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
