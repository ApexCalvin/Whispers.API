package com.spit.Spit.API.service;

import com.spit.Spit.API.dto.CreateCommentDTO;
import com.spit.Spit.API.dto.GetCommentDTO;
import com.spit.Spit.API.model.Comment;
import com.spit.Spit.API.repository.CommentRepository;
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
        comment.setAccount(accountService.getAccountById(createCommentDTO.getAccountId()));
        comment.setPost(postService.getPostById(createCommentDTO.getPostId()));
        commentRepository.save(comment);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<GetCommentDTO> getAllCommentsByPostId(Long postId) {
        return commentRepository.getAllCommentsByPostId(postId);
    }

    public List<GetCommentDTO> getAllCommentsByAccountId(Long accountId) {
        return commentRepository.getAllCommentsByAccountId(accountId);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }
}
