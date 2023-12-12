package com.spit.Spit.API.service;

import com.spit.Spit.API.dto.CreateCommentDTO;
import com.spit.Spit.API.dto.GetCommentDTO;
import com.spit.Spit.API.model.Account;
import com.spit.Spit.API.model.Comment;
import com.spit.Spit.API.model.Post;
import com.spit.Spit.API.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @InjectMocks
    private CommentService subject;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private AccountService accountService;
    @Mock
    private PostService postService;

    @Test
    void createComment() {
        CreateCommentDTO expected = new CreateCommentDTO();
        expected.setAccountId(1L);
        expected.setPostId(1L);
        expected.setMessage("Test");
        when(accountService.getAccountById(any(Long.class))).thenReturn(new Account());
        when(postService.getPostById(any(Long.class))).thenReturn(new Post());

        subject.createComment(expected);

        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void getCommentById() {
        Long id = 2L;
        Comment comment = new Comment();
        comment.setId(2L);
        when(commentRepository.findById(id)).thenReturn(Optional.of(comment));

        Comment actual = subject.getCommentById(id);

        assertThat(actual.getId()).isEqualTo(comment.getId());
    }

    @Test
    void getCommentById_returnNull() {
        Long id = 2L;
        when(commentRepository.findById(id)).thenReturn(Optional.empty());

        Comment actual = subject.getCommentById(id);

        assertThat(actual).isEqualTo(null);
    }

    @Test
    void getAllCommentsByPostId() {
        Long postId = 1L;
        List<GetCommentDTO> expected = new ArrayList<>(Arrays.asList(new GetCommentDTO(), new GetCommentDTO()));
        when(commentRepository.getAllCommentsByPostId(any(Long.class))).thenReturn(expected);

        List<GetCommentDTO> actual = subject.getAllCommentsByPostId(postId);

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void getAllCommentsByAccountId() {
        Long accountId = 1L;
        List<GetCommentDTO> expected = new ArrayList<>(Arrays.asList(new GetCommentDTO(), new GetCommentDTO()));
        when(commentRepository.getAllCommentsByAccountId(any(Long.class))).thenReturn(expected);

        List<GetCommentDTO> actual = subject.getAllCommentsByAccountId(accountId);

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void deleteCommentById() {
        subject.deleteCommentById(2L);
        verify(commentRepository).deleteById(any(Long.class));
    }
}
