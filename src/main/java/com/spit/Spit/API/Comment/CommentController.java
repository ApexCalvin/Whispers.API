package com.spit.Spit.API.Comment;

import com.spit.Spit.API.Account.Account;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createComment(@Valid @RequestBody CreateCommentDTO createCommentDTO) {
        commentService.createComment(createCommentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @GetMapping("/all")
//    public List<Comment> getAllComments() { return commentServices.getAllComments(); }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedCommentById(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);

        if(comment != null) {
            commentService.deleteCommentById(id);
            return new ResponseEntity<>("Comment has been successfully deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
