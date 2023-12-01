package com.spit.Spit.API.post;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.account.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    PostService subject;

    @Mock
    PostRepository postRepository;

    @Mock
    AccountService accountService;

    @Test
    void createPost() {
        CreatePostDTO createPostDTO = new CreatePostDTO();
        createPostDTO.setAccountId(1L);
        createPostDTO.setMessage("Test");
        Post post = new Post();
        post.setId(1L);
        post.setMessage("test");
        when(accountService.getAccountById(any(Long.class))).thenReturn(new Account());

        subject.createPost(createPostDTO);

        verify(postRepository).save(any(Post.class));
    }

    @Test
    void getAllPosts_returnPostList() {
        List<Post> expected = new ArrayList<>(Arrays.asList(new Post(), new Post()));
        when(postRepository.findAll()).thenReturn(expected);

        List<Post> actual = subject.getAllPosts();

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void getPostById_returnAccount() {
        Long id = 1L;
        Post post = new Post();
        post.setId(id);
        when(postRepository.findById(id)).thenReturn(Optional.of(post));

        Post actual = subject.getPostById(id);

        assertThat(actual.getId()).isEqualTo(post.getId());
    }

    @Test
    void getPostById_returnNull() {
        Long id = 1L;
        Post post = new Post();
        post.setId(id);
        when(postRepository.findById(id)).thenReturn(Optional.of(post));

        Post actual = subject.getPostById(id);

        assertThat(actual.getId()).isEqualTo(post.getId());
    }

    @Test
    void deletePostById() {
        subject.deletePostById(1L);
        verify(postRepository).deleteById(any(Long.class));
    }

    @Test
    void getAllPostDesc() {
        List<GetPostDTO> posts = new ArrayList<>(Arrays.asList(new GetPostDTO(), new GetPostDTO()));
        when(postRepository.getAllPostsDesc()).thenReturn(posts);

        List<GetPostDTO> actual = subject.getAllPostsDesc();

        assertThat(actual).isEqualTo(posts);
    }

    @Test
    void getPostByHandleDesc() {
        String handle = "SoldierBoy";
        List<GetPostDTO> posts = new ArrayList<>(Arrays.asList(new GetPostDTO(), new GetPostDTO()));
        when(postRepository.getPostsByHandleDesc(handle)).thenReturn(posts);

        List<GetPostDTO> actual = subject.getPostsByHandleDesc(handle);

        assertThat(actual).isEqualTo(posts);
    }
}
