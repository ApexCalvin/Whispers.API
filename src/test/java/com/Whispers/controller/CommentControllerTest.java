package com.Whispers.controller;

import com.Whispers.service.CommentService;
import com.Whispers.dto.CreateCommentDTO;
import com.Whispers.model.Comment;
import com.Whispers.tool.DtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @InjectMocks
    private CommentController subject;
    @Mock
    private CommentService commentService;

    @Test
    void createComment_validRequestBody_createComment(){
        CreateCommentDTO createCommentDTO = DtoMapper.buildCreateCommentDTO("Testing", 1L, 1L);

        ResponseEntity<String> actual =  subject.createComment(createCommentDTO);

        verify(commentService).createComment(createCommentDTO);
        assertThat(actual.getBody()).isEqualTo("Comment has been successfully saved.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void deleteCommentById_whenCommentExists_deleteCommentSuccess(){
        when(commentService.getCommentById(1L)).thenReturn(new Comment());

        ResponseEntity<String> actual =  subject.deletedCommentById(1L);

        verify(commentService).deleteCommentById(1L);
        assertThat(actual.getBody()).isEqualTo("Comment has been successfully deleted.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteCommentById_whenCommentDoesNotExist_notFoundResponse(){
        ResponseEntity<String> actual =  subject.deletedCommentById(1L);

        verify(commentService, never()).deleteCommentById(1L);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
