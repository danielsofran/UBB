package com.example.temalab3.repository;

import com.example.temalab3.domain.Show;

import java.time.LocalDate;
import java.util.Collection;

public interface ShowRepository extends Repository<Integer, Show>{
    Collection<Show> findByDay(LocalDate date);
}
