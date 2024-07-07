package org.app.comments.rest;

import org.app.comments.dtos.CommentDTO;
import org.app.comments.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentRestController {

    final private CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/causes/comments/{causeID}")
    public ResponseEntity<List<CommentDTO>> getCommentsForCauseByID(@PathVariable Long causeID) {
        return ResponseEntity.ok(commentService.findAllCommentsForCauseID(causeID));
    }

    @GetMapping("/comments/{commentID}")
    public ResponseEntity<CommentDTO> getCommentByID(@PathVariable Long commentID) {
        return ResponseEntity.ok(commentService.getCommentByID(commentID));
    }

    @PostMapping(value = "/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> saveComment(@RequestBody CommentDTO commentDTO) {
        Long commentID = commentService.create(commentDTO);
        return ResponseEntity.ok(commentService.getCommentByID(commentID));
    }

    @DeleteMapping("/comments/{commentID}")
    public ResponseEntity<CommentDTO> removeComment(@PathVariable Long commentID) {
        CommentDTO commentDTO = commentService.getCommentByID(commentID);
        commentService.removeComment(commentDTO);
        return ResponseEntity.ok(commentDTO);
    }

}
