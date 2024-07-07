package org.app.photo.repos;

import org.app.photo.domain.Photo;
import org.app.photo.dtos.PhotoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Query("SELECT p FROM Photo p WHERE p.cause.id = :causeID")
    List<Photo> findPhotosForCauseById(Long causeID);
}
