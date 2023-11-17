package com.spit.Spit.API.Comment;

import com.spit.Spit.API.Post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServices {

    private final CommentRepository commentRepository;

    public CommentServices(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public String createComment(Comment comment) {
        //comment.setDate(new Date());
        commentRepository.save(comment);
        return "Comment with id " +comment.getId()+ " has been saved.";
    }

    public List<Post> getAllCommentsByPostDESC() { //TODO: Create method
        return null;
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }
}
