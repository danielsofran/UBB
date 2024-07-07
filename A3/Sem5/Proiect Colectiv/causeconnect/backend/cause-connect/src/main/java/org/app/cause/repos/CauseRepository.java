package org.app.cause.repos;

import org.app.cause.domain.Cause;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CauseRepository extends JpaRepository<Cause, Long> {
}
