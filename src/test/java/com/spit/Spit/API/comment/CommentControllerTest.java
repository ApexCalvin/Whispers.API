package com.spit.Spit.API.comment;

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
    void deleteCommentById_whenCommentExists_deleteCommentSuccess(){
        when(commentService.getCommentById(1L)).thenReturn(new Comment());

        ResponseEntity<String> actual =  subject.deletedCommentById(1L);

        verify(commentService).deleteCommentById(1L);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteCommentById_whenCommentDoesNotExist_notFoundResponse(){

        ResponseEntity<String> actual =  subject.deletedCommentById(1L);

        verify(commentService, never()).deleteCommentById(1L);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void createComment_validRequestBody_createComment(){
        CreateCommentDTO createCommentDTO = buildCreateCommentDTO("Testing", 1L, 1L);

        ResponseEntity<String> actual =  subject.createComment(createCommentDTO);

        verify(commentService).createComment(createCommentDTO);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private static CreateCommentDTO buildCreateCommentDTO(String message, Long postId, Long accountId) {
        CreateCommentDTO createCommentDTO = new CreateCommentDTO();
        createCommentDTO.setMessage(message);
        createCommentDTO.setPostId(postId);
        createCommentDTO.setAccountId(accountId);
        return createCommentDTO;
    }
}
