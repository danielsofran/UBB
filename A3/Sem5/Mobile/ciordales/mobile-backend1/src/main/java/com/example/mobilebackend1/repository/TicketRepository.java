package com.example.mobilebackend1.repository;

import com.example.mobilebackend1.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByUuid(String uuid);

    Page<Ticket> findByUserIdAndDescriptionContainingIgnoreCaseOrderByCreatedDescIdAsc(Long userId, String content, Pageable pageable);

    List<Ticket> findByUserIdOrderByCreatedDescIdAsc(Long userId);
}
