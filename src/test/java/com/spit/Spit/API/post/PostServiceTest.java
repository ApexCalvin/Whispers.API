package com.spit.Spit.API.post;

import com.spit.Spit.API.Post.Post;
import com.spit.Spit.API.Post.PostRepository;
import com.spit.Spit.API.Post.PostServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks //dependency injection for the mock beans
    PostServices postServices; //convention to the name the test object "subject"

    @Mock
    PostRepository postRepository;

    void createPost() {}

    void getPostById() {}

    void getAllPost() {}

    void deletePostById() {}

    void getAllPostDESC() {}

    void getPostByHandleDESC() {}
}
