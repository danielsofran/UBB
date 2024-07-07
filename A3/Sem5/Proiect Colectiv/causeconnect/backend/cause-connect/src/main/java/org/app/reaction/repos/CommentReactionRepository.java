package org.app.reaction.repos;

import org.app.reaction.domain.CommentReaction;
import org.app.reaction.domain.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {
    @Query("SELECT COUNT(cr) FROM CommentReaction cr WHERE cr.comment.id = :commentId AND cr.reaction_type = :reactionType")
    Long countReactionsByCommentIdAndType(@Param("commentId") Long commentId, @Param("reactionType") ReactionType reactionType);
}
