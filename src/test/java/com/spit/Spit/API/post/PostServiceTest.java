package com.spit.Spit.API.post;

import com.spit.Spit.API.Post.Post;
import com.spit.Spit.API.Post.PostRepository;
import com.spit.Spit.API.Post.PostServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks //dependency injection for the mock beans
    PostServices postServices; //convention to the name the test object "subject"

    @Mock
    PostRepository postRepository;

    @Test
    void createPostTest() {

        Post post = new Post();

        post.setMessage("Hello World!");

        String actual = postServices.createPost(post);

        verify(postRepository).save(any(Post.class)); //verify method was called

        System.out.println("[NOTICE] Message: " +actual);

        String expected = "Post with id " +post.getId()+ " has been saved.";

        assertThat(actual).isEqualTo(expected);
    }
}
