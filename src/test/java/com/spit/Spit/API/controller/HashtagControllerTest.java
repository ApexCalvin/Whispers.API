package com.spit.Spit.API.controller;

import com.spit.Spit.API.model.Hashtag;
import com.spit.Spit.API.model.Post;
import com.spit.Spit.API.service.HashtagService;
import com.spit.Spit.API.service.PostService;
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
}

