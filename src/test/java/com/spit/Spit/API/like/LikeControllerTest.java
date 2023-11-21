package com.spit.Spit.API.like;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LikeControllerTest {
    @InjectMocks
    private LikeController subject;

    @Mock
    private LikeService likeService;


}
