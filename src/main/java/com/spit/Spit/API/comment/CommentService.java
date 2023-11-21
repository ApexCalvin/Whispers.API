package com.spit.Spit.API.comment;

import com.spit.Spit.API.account.AccountService;
import com.spit.Spit.API.post.PostService;
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
        comment.setPost(postService.getPostById(createCommentDTO.getPostId()));
        commentRepository.save(comment);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<GetCommentDTO> getAllCommentsByPost(Long postId) {
        return commentRepository.getAllCommentsByPost(postId);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }
}
