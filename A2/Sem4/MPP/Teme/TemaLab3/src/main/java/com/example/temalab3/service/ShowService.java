package com.example.temalab3.service;

import com.example.temalab3.domain.Show;
import com.example.temalab3.repository.ShowRepository;

import java.time.LocalDate;
import java.util.Collection;

public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository)
    {
        this.showRepository = showRepository;
    }

    public Collection<Show> findAllArtisti()
    {
        return showRepository.findAll();
    }

    public Collection<Show> findShowsOnDate(LocalDate date)
    {
        if(date == null)
            return showRepository.findAll();
        return showRepository.findByDay(date);
    }
}
