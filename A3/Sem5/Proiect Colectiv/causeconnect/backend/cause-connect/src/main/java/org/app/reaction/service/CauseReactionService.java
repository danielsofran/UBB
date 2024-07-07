package org.app.reaction.service;

import org.app.cause.domain.Cause;
import org.app.cause.repos.CauseRepository;
import org.app.errors.exceptions.NotFoundException;
import org.app.reaction.domain.CauseReaction;
import org.app.reaction.domain.ReactionType;
import org.app.reaction.dtos.ReactionDTO;
import org.app.reaction.dtos.ReactionStatsDTO;
import org.app.reaction.repos.CauseReactionRepository;
import org.app.user.domain.User;
import org.app.user.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CauseReactionService {
    private final CauseReactionRepository causeReactionRepository;
    private final UserRepository userRepository;
    private final CauseRepository causeRepository;

    public CauseReactionService(final CauseReactionRepository causeReactionRepository,
                                final UserRepository userRepository,
                                final CauseRepository causeRepository) {
        this.causeReactionRepository = causeReactionRepository;
        this.userRepository = userRepository;
        this.causeRepository = causeRepository;
    }

    public CauseReaction get(final Long userID, final Long causeID) {
        final Optional<CauseReaction> causeReaction = causeReactionRepository.findAll().stream().filter(
                cr -> Objects.equals(cr.getUser().getId(), userID) && Objects.equals(cr.getCause().getId(), causeID)
        ).findFirst();
        return causeReaction.orElse(null);
    }

    public Long create(final ReactionDTO reactionDTO, final Long userID, final Long causeID) {
        if(get(userID, causeID)!=null) {
            return -1L;
        }
        else {
            final CauseReaction causeReaction = new CauseReaction();
            mapToEntity(reactionDTO, userID, causeID, causeReaction);
            return causeReactionRepository.save(causeReaction).getId();
        }
    }

    public void update(final ReactionDTO reactionDTO, final Long userID, final Long causeID) {
        CauseReaction causeReaction = get(userID, causeID);
        if(causeReaction == null)
            throw new NotFoundException("This user did not react to this cause");
        mapToEntity(reactionDTO, userID, causeID, causeReaction);
        causeReactionRepository.save(causeReaction);
    }

    public void delete(final Long userID, final Long causeID) {
        CauseReaction causeReaction = get(userID, causeID);
        if(causeReaction == null)
            throw new NotFoundException("This user did not react to this cause");
        causeReactionRepository.delete(causeReaction);
    }

    public Long getAmountOfType(final Long causeID, final ReactionType reactionType) {
        return causeReactionRepository.countReactionsByCauseIdAndType(causeID, reactionType);
    }

    public List<ReactionStatsDTO> getStatsForReaction(final Long causeID, final ReactionType type){
        List<ReactionStatsDTO> reactionStatsDTOS = new ArrayList<>();
        for(ReactionType reactionType: ReactionType.values()) {
            Long howMany = getAmountOfType(causeID, reactionType);
            ReactionStatsDTO reactionStatsDTO = new ReactionStatsDTO();
            reactionStatsDTO.setType(reactionType);
            reactionStatsDTO.setCount(howMany);
            reactionStatsDTO.setUserReacted(Objects.equals(type, reactionType));
            reactionStatsDTOS.add(reactionStatsDTO);
        }

        return reactionStatsDTOS;
    }

    private ReactionDTO mapToDTO(final CauseReaction causeReaction, final ReactionDTO reactionDTO) {
        reactionDTO.setReactionType(causeReaction.getReactionType());
        return reactionDTO;
    }

    private CauseReaction mapToEntity(final ReactionDTO reactionDTO, final Long userID, final Long causeID, final CauseReaction causeReaction) {
        causeReaction.setReactionType(reactionDTO.getReactionType());

        final User user = userRepository.findById(userID)
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found!");
                });
        causeReaction.setUser(user);

        final Cause cause = causeRepository.findById(causeID)
                .orElseThrow(() -> {
                    throw new NotFoundException("Cause not found!");
                });
        causeReaction.setCause(cause);
        return causeReaction;
    }

}
