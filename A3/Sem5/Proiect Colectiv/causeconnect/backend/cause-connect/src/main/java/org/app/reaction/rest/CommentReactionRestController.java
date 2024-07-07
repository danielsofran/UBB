package org.app.reaction.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.app.reaction.domain.CommentReaction;
import org.app.reaction.domain.ReactionType;
import org.app.reaction.dtos.ReactionDTO;
import org.app.reaction.dtos.ReactionStatsDTO;
import org.app.reaction.service.CommentReactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentReactionRestController {
    final private CommentReactionService commentReactionService;

    public CommentReactionRestController(CommentReactionService commentReactionService) {
        this.commentReactionService = commentReactionService;
    }

    @PostMapping(value = "/comments/{commentID}/reaction")
    public ResponseEntity<?> saveReaction(@PathVariable final Long commentID, @RequestBody @Valid final ReactionDTO reactionDTO, HttpServletRequest request) {
        Long userID = (Long) request.getAttribute("userID");
        final Long createdID = commentReactionService.create(reactionDTO, userID, commentID);
        if (createdID == -1L)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdID, HttpStatus.CREATED);
    }

    @PutMapping(value = "/comments/{commentID}/reaction")
    public ResponseEntity<Void> updateReaction(@PathVariable final Long commentID, @RequestBody @Valid final ReactionDTO reactionDTO, HttpServletRequest request) {
        Long userID = (Long) request.getAttribute("userID");
        commentReactionService.update(reactionDTO, userID, commentID);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/comments/{commentID}/reaction")
    public ResponseEntity<Void> deleteReaction(@PathVariable final Long commentID, HttpServletRequest request) {
        Long userID = (Long) request.getAttribute("userID");
        commentReactionService.delete(userID, commentID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/causes/comments/{commentID}/reactions")
    public ResponseEntity<List<ReactionStatsDTO>> getReactionInfo(@PathVariable final Long commentID, HttpServletRequest request) {
        Long userID = (Long) request.getAttribute("userID");
        ReactionType type = null;
        if (userID != null) {
            CommentReaction commentReaction = commentReactionService.get(userID, commentID);
            if (commentReaction != null)
                type = commentReaction.getReactionType();
        }
        List<ReactionStatsDTO> reactionStatsDTOS = commentReactionService.getStatsForReaction(commentID, type);

        return ResponseEntity.ok(reactionStatsDTOS);
    }
}
