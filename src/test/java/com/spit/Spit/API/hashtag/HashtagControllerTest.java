package com.spit.Spit.API.hashtag;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HashtagControllerTest {

    @InjectMocks
    HashtagController subject;

    @Mock
    HashtagService hashtagService;

    @Test
    void getAllHashtags() {}

    @Test
    void getAllPostByHashtagNameDesc() {}

    @Test
    void deleteHashtagById() {}

    @Test
    void deleteHashtagById_notFound() {}
}

