package org.app.reaction.dtos;

import jakarta.validation.constraints.NotNull;
import org.app.reaction.domain.ReactionType;

public class ReactionDTO {

    @NotNull
    private ReactionType reactionType;

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }
}
