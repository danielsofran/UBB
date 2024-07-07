package org.app.reaction.domain;

import jakarta.persistence.*;
import org.app.comments.domain.Comment;
import org.app.reaction.domain.ReactionType;
import org.app.user.domain.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "commentreactions")
public class CommentReaction {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name="commentreaction_sequence",
            sequenceName ="commentreaction_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "commentreaction_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn (name = "comment_id")
    private Comment comment;

    @Column(length = 1000)
    private ReactionType reaction_type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public ReactionType getReactionType() {
        return reaction_type;
    }

    public void setReactionType(ReactionType reaction_type) {
        this.reaction_type = reaction_type;
    }
}
