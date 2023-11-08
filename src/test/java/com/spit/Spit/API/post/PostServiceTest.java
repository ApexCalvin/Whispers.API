package com.spit.Spit.API.post;

import com.spit.Spit.API.Post.GetPostDTO;
import com.spit.Spit.API.Post.Post;
import com.spit.Spit.API.Post.PostRepository;
import com.spit.Spit.API.Post.PostServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    void getAllPost() {
        Post post = new Post();
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(postRepository.findAll()).thenReturn(posts);

        List<Post> actual = postServices.getAllPost();

        assertThat(actual).isEqualTo(posts);
    }

    @Test
    void deletePostById() {
        postServices.deletePostById(1L);
        verify(postRepository).deleteById(any(Long.class));
    }

    @Test
    void getAllPostDESC() {
        GetPostDTO post = new GetPostDTO();
        List<GetPostDTO> posts = new ArrayList<>();
        posts.add(post);
        when(postRepository.getAllPostsDESC()).thenReturn(posts);

        List<GetPostDTO> actual = postServices.getAllPostDESC();

        assertThat(actual).isEqualTo(posts);
    }

    @Test
    void getPostByHandleDESC() {
        String handle = "SoldierBoy";
        GetPostDTO post = new GetPostDTO();
        post.setHandle(handle);
        List<GetPostDTO> posts = new ArrayList<>();
        posts.add(post);
        when(postRepository.getPostsByHandleDESC(handle)).thenReturn(posts);

        List<GetPostDTO> actual = postServices.getPostsByHandleDESC(handle);

        assertThat(actual).isEqualTo(posts);
    }
}
