package com.spit.Spit.API.post;

import com.spit.Spit.API.Post.Post;
import com.spit.Spit.API.Post.PostRepository;
import com.spit.Spit.API.Post.PostServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks //dependency injection for the mock beans
    PostServices postServices; //convention to the name the test object "subject"

    @Mock
    PostRepository postRepository;

    @Test
    void createPost() {
        Long id = 1L;
        Post post = new Post();
        post.setId(id);

        String expected = "Post with id 1 has been saved.";
        String actual = postServices.createPost(post);

        verify(postRepository).save(any(Post.class));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getPostById() {
        Long id = 1L;
        Post post = new Post();
        post.setId(id);
        Optional<Post> expected = Optional.of(post);
        when(postRepository.findById(id)).thenReturn(expected);

        Optional<Post> actual = postServices.getPostById(id);

        assertThat(actual.get().getId()).isEqualTo(expected.get().getId());
    }

    void getAllPost() {
        //when(postServices.getPostById(id)).thenReturn(optionalPost);
    }

    @Test
    void deletePostById() {
        postServices.deletePostById(1L);
        verify(postRepository).deleteById(any(Long.class));
    }

    void getAllPostDESC() {
        //when(postServices.getPostById(id)).thenReturn(optionalPost);
    }

    void getPostByHandleDESC() {
        //when(postServices.getPostById(id)).thenReturn(optionalPost);
    }
}
