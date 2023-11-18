package com.spit.Spit.API.Comment;

import com.spit.Spit.API.Account.AccountService;
import com.spit.Spit.API.Post.Post;
import com.spit.Spit.API.Post.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AccountService accountService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, AccountService accountService, PostService postService) {
        this.commentRepository = commentRepository;
        this.accountService = accountService;
        this.postService = postService;
    }

    public void createComment(CreateCommentDTO createCommentDTO) {
        Comment comment = new Comment();
        comment.setMessage(createCommentDTO.getMessage());
//        comment.setAccount(accountService.getAccountById(createCommentDTO.getAccountId()));
//        comment.setPost(postService.getPostById(createCommentDTO.getPostId()));
        commentRepository.save(comment);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<Post> getAllCommentsByPostDESC() { //TODO: Create method
        return null;
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }
}
