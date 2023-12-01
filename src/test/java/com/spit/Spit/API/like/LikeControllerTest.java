package com.spit.Spit.API.like;

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
public class LikeControllerTest {
    @InjectMocks
    private LikeController subject;

    @Mock
    private LikeService likeService;


    @Test
    void createLike_HappyPath_CreatedResponse(){
        CreateLikeDTO likeDTO = new CreateLikeDTO();
        when(likeService.doesUserAlreadyLikePost(likeDTO)).thenReturn(false);

        ResponseEntity<Like_> actual = subject.createLike(likeDTO);

        verify(likeService).createLike(likeDTO);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void createLike_userAlreadyLikesPost_ConflictResponse(){
        CreateLikeDTO likeDTO = new CreateLikeDTO();
        when(likeService.doesUserAlreadyLikePost(likeDTO)).thenReturn(true);

        ResponseEntity<Like_> actual = subject.createLike(likeDTO);

        verify(likeService, never()).createLike(likeDTO);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(actual.getBody()).isNull();
    }

    @Test
    void getLikeById_likeExists_OkResponse(){
        Long id = 1L;
        when(likeService.getLikeById(id)).thenReturn(new Like_());

        ResponseEntity<Like_> actual = subject.getLikeById(id);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getLikeById_likeDoesNotExists_NotFoundResponse(){
        Long id = 1L;
        when(likeService.getLikeById(id)).thenReturn(null);

        ResponseEntity<Like_> actual = subject.getLikeById(id);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
