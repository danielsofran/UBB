package org.app.reaction.repos;

import org.app.reaction.domain.CauseReaction;
import org.app.reaction.domain.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CauseReactionRepository extends JpaRepository<CauseReaction, Long> {
    @Query("SELECT COUNT(cr) FROM CauseReaction cr WHERE cr.cause.id = :causeId AND cr.reaction_type = :reactionType")
    Long countReactionsByCauseIdAndType(@Param("causeId") Long causeId, @Param("reactionType") ReactionType reactionType);
}
