package com.donatii.donatiiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.donatii.donatiiapi.model.Cauza;

@Repository
public interface CauzaRepository extends JpaRepository<Cauza, Long> {
}
