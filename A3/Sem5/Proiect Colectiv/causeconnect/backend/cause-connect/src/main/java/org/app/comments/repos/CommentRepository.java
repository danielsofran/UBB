package org.app.comments.repos;

import org.app.comments.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT CO FROM Comment CO " +
            "INNER JOIN Cause CA ON CA.id = CO.cause.id " +
            "INNER JOIN User U ON U.id = CO.user.id " +
            "LEFT JOIN CommentReaction CR ON CR.comment.id = CO.id " +
            "WHERE CA.id = :causeID " +
            "GROUP BY CO.id, CA.user.id " +
            "ORDER BY " +
            " CASE WHEN CO.user.id = CA.user.id THEN 1 ELSE 2 END, " +
            " ( " +
            "   SELECT COUNT(CR.id) " +
            "   FROM CommentReaction CR " +
            "   WHERE CR.comment.id = CO.id" +
            " ) DESC, " +
            " CO.date DESC"
            )
    List<Comment> findAllCommentsForCause(Long causeID);

}
