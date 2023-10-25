package com.spit.Spit.API.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentServices commentServices;

    @PostMapping("/add")
    public ResponseEntity<String> createComment(@RequestBody Comment comment) {
        if (comment.getId() != null) {
            String message = "Remove comment id when creating.";
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }

        String message = commentServices.createComment(comment);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Comment> getAllComments() { return commentServices.getAllComments(); }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedCommentById(@PathVariable Long id) {

        Optional<Comment> exist = commentServices.getCommentById(id);

        if(exist.isPresent()) {
            commentServices.deleteCommentById(id);
            String message = "Comment with id " +id+ " has been deleted.";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
