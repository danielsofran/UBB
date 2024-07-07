package org.app.reaction.domain;

import jakarta.persistence.*;
import org.app.cause.domain.Cause;
import org.app.user.domain.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "causereactions")
public class CauseReaction {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name="causereaction_sequence",
            sequenceName ="causereaction_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "causereaction_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn (name =  "cause_id")
    private Cause cause;

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

    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

    public ReactionType getReactionType() {
        return reaction_type;
    }

    public void setReactionType(ReactionType reaction_type) {
        this.reaction_type = reaction_type;
    }
}
