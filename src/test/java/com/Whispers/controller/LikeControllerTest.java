package com.Whispers.controller;

import com.Whispers.model.Like_;
import com.Whispers.service.LikeService;
import com.Whispers.dto.CreateLikeDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
        Like_ expected = new Like_();
        when(likeService.getLikeById(id)).thenReturn(expected);

        ResponseEntity<Like_> actual = subject.getLikeById(id);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo(expected);
    }

    @Test
    void getLikeById_likeDoesNotExists_NotFoundResponse(){
        Long id = 1L;
        when(likeService.getLikeById(id)).thenReturn(null);

        ResponseEntity<Like_> actual = subject.getLikeById(id);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getLikeByHandleAndPost_likeDoesNotExists_NotFoundResponse(){
        Long id = 1L;
        String handle = "MarvelLover";
        when(likeService.getLikeByHandleAndPost(handle, id)).thenReturn(null);

        ResponseEntity<Like_> actual = subject.getLikeByHandleAndPost(handle, id);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getLikeByHandleAndPost_likeExists_OkResponse(){
        Long id = 1L;
        String handle = "Butcher";
        Like_ expected = new Like_();
        when(likeService.getLikeByHandleAndPost(handle, id)).thenReturn(expected);

        ResponseEntity<Like_> actual = subject.getLikeByHandleAndPost(handle, id);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo(expected);
    }

    @Test
    void getAllLikes_OkResponse(){
        List<Like_> expected = List.of(new Like_());
        when(likeService.getAllLikes()).thenReturn(expected);

        ResponseEntity<List<Like_>> actual = subject.getAllLikes();

        verify(likeService).getAllLikes();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo(expected);
    }

    @Test
    void deleteLike_likeDeleted_NoContentResponse(){
        Long id = 1L;
        when(likeService.deleteLike(id)).thenReturn(true);

        ResponseEntity<Void> actual = subject.deleteLike(id);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteLike_likeNotDeleted_NotFoundResponse(){
        Long id = 1L;
        when(likeService.deleteLike(id)).thenReturn(false);

        ResponseEntity<Void> actual = subject.deleteLike(id);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
