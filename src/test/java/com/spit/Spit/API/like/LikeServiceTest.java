package com.spit.Spit.API.like;

import com.spit.Spit.API.Account.AccountService;
import com.spit.Spit.API.Like.LikeRepository;
import com.spit.Spit.API.Like.LikeService;
import com.spit.Spit.API.Post.PostService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LikeServiceTest {

    @InjectMocks
    private LikeService subject;

    @Mock
    private LikeRepository likeRepository;
    @Mock
    private PostService postService;
    @Mock
    private AccountService accountService;

}
