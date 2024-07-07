package org.app.comments.service;

import org.app.cause.domain.Cause;
import org.app.cause.repos.CauseRepository;
import org.app.comments.domain.Comment;
import org.app.comments.dtos.CommentDTO;
import org.app.comments.repos.CommentRepository;
import org.app.errors.exceptions.NotFoundException;
import org.app.user.domain.User;
import org.app.user.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final Logger LOG = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final CauseRepository causeRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          CauseRepository causeRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.causeRepository = causeRepository;
        this.userRepository = userRepository;
    }

    public Long create(final CommentDTO commentDTO){
        Comment comment = mapToEntity(commentDTO);
        LOG.info("CommentDTO converted to Comment!");
        LOG.info("Trying to save comment ...");
        return commentRepository.save(comment).getId();
    }

    public void removeComment(final CommentDTO commentDTO){
        LOG.info("Removing comment...");
        Long commentID = commentDTO.getId();
        if(commentID == null){
            LOG.error("Comment does not contain info about the ID!!!!");
            throw new NotFoundException("Comment does not have an ID!");
        }
        Comment comment = commentRepository.findById(commentID)
                .orElseThrow(NotFoundException::new);
        LOG.info("Comment found in database!");

        commentRepository.delete(comment);
        LOG.info("Comment deleted!");
    }

    public CommentDTO getCommentByID(Long commentID){
        LOG.info("Retrieving comment by id: " + commentID);
        return commentRepository.findById(commentID)
                .map(this::mapToDTO)
                .orElseThrow(NotFoundException::new);
    }

    public List<CommentDTO> findAllCommentsForCauseID(Long causeID) {
        LOG.info("Retrieving comments for cause ID: " + causeID);
        List<Comment> comments = commentRepository.findAllCommentsForCause(causeID);
        LOG.info("Comments retrieved from repository!");
        LOG.info("Returning list of CommentDTO...");
        return comments.stream().map(this::mapToDTO).toList();
    }

    private CommentDTO mapToDTO(final Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setDate(comment.getDate());

        if (comment.getCause() == null) {
            throw new NotFoundException("Comment does not contain any info about the Cause!");
        }
        commentDTO.setCauseID(comment.getCause().getId());

        if (comment.getUser() == null) {
            throw new NotFoundException("Comment does not contain any info about the User!");
        }
        commentDTO.setUserID(comment.getUser().getId());

        if(comment.getMessage() == null){
            throw new NotFoundException("Comment does not contain any message!");
        }
        commentDTO.setMessage(comment.getMessage());

        return commentDTO;
    }

    private Comment mapToEntity(final CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setDate(commentDTO.getDate());

        final Long causeID = commentDTO.getCauseID();
        if (causeID == null) {
            throw new NotFoundException("Cause ID does not exist!");
        }

        final Cause cause = causeRepository.findById(causeID)
                .orElseThrow(() -> {
                    throw new NotFoundException("Cause not found!");
                });
        comment.setCause(cause);

        final Long userID = commentDTO.getUserID();
        if (userID == null) {
            throw new NotFoundException("User ID does not exist!");
        }

        final User user = userRepository.findById(userID)
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found!");
                });
        comment.setUser(user);

        if (commentDTO.getMessage() == null) {
            throw new NotFoundException("Message does not exist for comment!");
        }
        comment.setMessage(commentDTO.getMessage());

        return comment;
    }

}
