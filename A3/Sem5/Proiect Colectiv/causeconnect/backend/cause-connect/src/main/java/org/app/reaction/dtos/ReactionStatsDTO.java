package org.app.reaction.dtos;

import jakarta.validation.constraints.NotNull;
import org.app.reaction.domain.ReactionType;

public class ReactionStatsDTO {
    @NotNull
    private ReactionType type;
    @NotNull
    private Long count;
    @NotNull
    private boolean userReacted;

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public boolean isUserReacted() {
        return userReacted;
    }

    public void setUserReacted(boolean userReacted) {
        this.userReacted = userReacted;
    }
}
