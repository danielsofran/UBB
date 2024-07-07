package org.app.reaction.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.app.reaction.domain.CauseReaction;
import org.app.reaction.dtos.ReactionStatsDTO;
import org.app.reaction.domain.ReactionType;
import org.app.reaction.dtos.ReactionDTO;
import org.app.reaction.service.CauseReactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CauseReactionRestController {
    final private CauseReactionService causeReactionService;

    public CauseReactionRestController(CauseReactionService causeReactionService) {
        this.causeReactionService = causeReactionService;
    }

    @PostMapping(value = "/causes/{causeID}/reaction")
    public ResponseEntity<?> saveReaction(@PathVariable final Long causeID, @RequestBody @Valid final ReactionDTO reactionDTO, HttpServletRequest request) {
        Long userID = (Long) request.getAttribute("userID");
        final Long createdID = causeReactionService.create(reactionDTO, userID, causeID);
        if(createdID == -1L)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdID, HttpStatus.CREATED);
    }

    @PutMapping(value = "/causes/{causeID}/reaction")
    public ResponseEntity<Void> updateReaction(@PathVariable final Long causeID, @RequestBody @Valid final ReactionDTO reactionDTO, HttpServletRequest request) {
        Long userID = (Long) request.getAttribute("userID");
        causeReactionService.update(reactionDTO, userID, causeID);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/causes/{causeID}/reaction")
    public ResponseEntity<Void> deleteReaction(@PathVariable final Long causeID, HttpServletRequest request) {
        Long userID = (Long) request.getAttribute("userID");
        causeReactionService.delete(userID, causeID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/causes/{causeID}/reactions")
    public ResponseEntity<List<ReactionStatsDTO>> getReactionInfo(@PathVariable final Long causeID, HttpServletRequest request) {
        Long userID = (Long) request.getAttribute("userID");
        ReactionType type = null;
        if(userID != null) {
            CauseReaction causeReaction = causeReactionService.get(userID, causeID);
            if (causeReaction != null)
                type = causeReaction.getReactionType();
        }
        List<ReactionStatsDTO> reactionStatsDTOS = causeReactionService.getStatsForReaction(causeID, type);

        return ResponseEntity.ok(reactionStatsDTOS);
    }
}
