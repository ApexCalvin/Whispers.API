package com.spit.Spit.API.hashtag;

import com.spit.Spit.API.post.Post;
import com.spit.Spit.API.post.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HashtagControllerTest {

    @InjectMocks
    HashtagController subject;

    @Mock
    HashtagService hashtagService;

    @Mock
    PostService postService;

    @Test
    void getAllHashtags() {
        List<Hashtag> expected = new ArrayList<>();
        expected.add(new Hashtag());
        expected.add(new Hashtag());
        when(hashtagService.getAllHashtags()).thenReturn(expected);

        ResponseEntity<List<Hashtag>> actual = subject.getAllHashtags();

        assertThat(actual.getBody()).isEqualTo(expected);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getAllPostByHashtagNameDesc() {
        List<Post> expected = new ArrayList<>();
        expected.add(new Post());
        expected.add(new Post());
        when(postService.getAllPostsByHashtagName(any(String.class))).thenReturn(expected);

        ResponseEntity<List<Post>> actual = subject.getAllPostByHashtagNameDesc("Test");

        assertThat(actual.getBody()).isEqualTo(expected);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteHashtagById() {
        Hashtag ht = new Hashtag();
        when(hashtagService.getHashtagById(any(Long.class))).thenReturn(ht);

        ResponseEntity<String> actual = subject.deleteHashtagById(1L);

        verify(hashtagService).deleteHashtagById(any(Long.class));
        assertThat(actual.getBody()).isEqualTo("Hashtag has been successfully deleted.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteHashtagById_notFound() {
        when(hashtagService.getHashtagById(any(Long.class))).thenReturn(null);

        ResponseEntity<String> actual = subject.deleteHashtagById(1L);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}

