package com.example.main.service;

import com.example.main.domain.Artist;
import com.example.main.repository.ArtistRepository;
import com.example.main.service.exceptions.BileteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Optional<Artist> findArtist(Integer id) {
        return artistRepository.findById(id);
    }

    public Iterable<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> addArtist(Artist artist) {
        try{
            Artist added = artistRepository.save(artist);
            return Optional.of(added);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void deleteArtist(Integer id) throws BileteException {
        if(artistRepository.findById(id).isEmpty())
            throw new BileteException("Artist could not be deleted");
        artistRepository.deleteById(id);
    }

    public Optional<Artist> updateArtist(Integer id, Artist artist) {
        Optional<Artist> updated = artistRepository.findById(id);
        if(updated.isEmpty())
            return Optional.empty();
        artist.setId(id);
        Artist saved = artistRepository.save(artist);
        return Optional.of(saved);
    }
}
