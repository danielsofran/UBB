package repository;

import bilete.domain.Show;

import java.time.LocalDate;
import java.util.Collection;

public interface ShowRepository extends Repository<Integer, Show>{
    Collection<Show> findByDay(LocalDate date);
}
