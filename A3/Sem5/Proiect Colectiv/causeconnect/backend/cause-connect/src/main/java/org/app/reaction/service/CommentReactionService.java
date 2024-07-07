package org.app.reaction.service;

import org.app.comments.domain.Comment;
import org.app.comments.repos.CommentRepository;
import org.app.errors.exceptions.NotFoundException;
import org.app.reaction.domain.CommentReaction;
import org.app.reaction.domain.ReactionType;
import org.app.reaction.dtos.ReactionDTO;
import org.app.reaction.dtos.ReactionStatsDTO;
import org.app.reaction.repos.CommentReactionRepository;
import org.app.user.domain.User;
import org.app.user.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentReactionService {

    private final CommentReactionRepository commentReactionRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentReactionService(final CommentReactionRepository commentReactionRepository,
                                  final UserRepository userRepository,
                                  final CommentRepository commentRepository) {
        this.commentReactionRepository = commentReactionRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public CommentReaction get(final Long userID, final Long commentID) {
        final Optional<CommentReaction> commentReaction = commentReactionRepository.findAll().stream().filter(
                cr -> Objects.equals(cr.getUser().getId(), userID) && Objects.equals(cr.getComment().getId(), commentID)
        ).findFirst();
        return commentReaction.orElse(null);
    }

    public Long create(final ReactionDTO reactionDTO, final Long userID, final Long commentID) {
        if(get(userID, commentID)!=null) {
            return -1L;
        }
        else {
            final CommentReaction commentReaction = new CommentReaction();
            mapToEntity(reactionDTO, userID, commentID, commentReaction);
            return commentReactionRepository.save(commentReaction).getId();
        }
    }

    public void update(final ReactionDTO reactionDTO, final Long userID, final Long commentID) {
        CommentReaction commentReaction = get(userID, commentID);
        if(commentReaction == null)
            throw new NotFoundException("This user did not react to this cause");
        mapToEntity(reactionDTO, userID, commentID, commentReaction);
        commentReactionRepository.save(commentReaction);
    }

    public void delete(final Long userID, final Long commentID) {
        CommentReaction commentReaction = get(userID, commentID);
        if(commentReaction == null)
            throw new NotFoundException("This user did not react to this cause");
        commentReactionRepository.delete(commentReaction);
    }

    public Long getAmountOfType(final Long commentId, final ReactionType reactionType) {
        return commentReactionRepository.countReactionsByCommentIdAndType(commentId, reactionType);
    }

    public List<ReactionStatsDTO> getStatsForReaction(final Long commentID, final ReactionType type){
        List<ReactionStatsDTO> reactionStatsDTOS = new ArrayList<>();
        for (ReactionType reactionType : ReactionType.values()) {
            Long howMany = getAmountOfType(commentID, reactionType);
            ReactionStatsDTO reactionStatsDTO = new ReactionStatsDTO();
            reactionStatsDTO.setType(reactionType);
            reactionStatsDTO.setCount(howMany);
            reactionStatsDTO.setUserReacted(Objects.equals(type, reactionType));
            reactionStatsDTOS.add(reactionStatsDTO);
        }

        return reactionStatsDTOS;
    }

    private ReactionDTO mapToDto(final CommentReaction commentReaction, final ReactionDTO reactionDTO) {
        reactionDTO.setReactionType(commentReaction.getReactionType());
        return reactionDTO;
    }

    private CommentReaction mapToEntity(final ReactionDTO reactionDTO, final Long userID, final Long commentID, final CommentReaction commentReaction) {
        commentReaction.setReactionType(reactionDTO.getReactionType());

        final User user = userRepository.findById(userID)
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found!");
                });
        commentReaction.setUser(user);

        final Comment comment = commentRepository.findById(commentID)
                .orElseThrow(() -> {
                    throw new NotFoundException("Comment not found!");
                });
        commentReaction.setComment(comment);
        return commentReaction;
    }

}
