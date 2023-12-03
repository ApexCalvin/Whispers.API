package com.spit.Spit.API.post;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.account.AccountService;
import com.spit.Spit.API.hashtag.Hashtag;
import com.spit.Spit.API.hashtag.HashtagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    PostService subject;

    @Mock
    PostRepository postRepository;

    @Mock
    AccountService accountService;

    @Mock
    HashtagService hashtagService;

    @Test
    void createPost_withNoHashtags_savesPostWithFields() {
        CreatePostDTO createPostDTO = new CreatePostDTO();
        createPostDTO.setAccountId(1L);
        createPostDTO.setMessage("Test");
        createPostDTO.setHashtags(new ArrayList<>());
        Account expectedAccount = new Account();
        when(accountService.getAccountById(1L)).thenReturn(expectedAccount);
        ArgumentCaptor<Post> postArgumentCaptor = ArgumentCaptor.forClass(Post.class);

        subject.createPost(createPostDTO);

        verify(hashtagService, never()).createHashtags(any());
        verify(postRepository).save(postArgumentCaptor.capture());
        assertThat(postArgumentCaptor.getValue().getAccount()).isEqualTo(expectedAccount);
        assertThat(postArgumentCaptor.getValue().getMessage()).isEqualTo("Test");
    }

    @Test
    void createPost_withHashtags_savesPostWithHashtags() {
        CreatePostDTO createPostDTO = new CreatePostDTO();
        createPostDTO.setAccountId(1L);
        createPostDTO.setMessage("Test");
        ArrayList<String> hashtagNames = new ArrayList<>(Arrays.asList("Hash1", "Hash2"));
        createPostDTO.setHashtags(hashtagNames);
        Hashtag hash1 = new Hashtag();
        hash1.setName("Hash1");
        Hashtag hash2 = new Hashtag();
        hash2.setName("Hash2");
        Set<Hashtag> hashtags = new HashSet<>(Set.of(hash1, hash2));
        createPostDTO.setHashtags(hashtagNames);
        Account expectedAccount = new Account();
        when(accountService.getAccountById(1L)).thenReturn(expectedAccount);
        when(hashtagService.createHashtags(hashtagNames)).thenReturn(hashtags);
        ArgumentCaptor<Post> postArgumentCaptor = ArgumentCaptor.forClass(Post.class);

        subject.createPost(createPostDTO);

        verify(hashtagService).createHashtags(hashtagNames);
        verify(postRepository).save(postArgumentCaptor.capture());
        assertThat(postArgumentCaptor.getValue().getAccount()).isEqualTo(expectedAccount);
        assertThat(postArgumentCaptor.getValue().getMessage()).isEqualTo("Test");
        assertThat(postArgumentCaptor.getValue().getHashtags()).isEqualTo(hashtags);
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
    void getAllPosts_returnPostList() {
        List<Post> expected = new ArrayList<>(Arrays.asList(new Post(), new Post()));
        when(postRepository.findAll()).thenReturn(expected);

        List<Post> actual = subject.getAllPosts();

        assertThat(actual.size()).isEqualTo(2);
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

    @Test
    void getLikedPostsByAccountId() {

    }

    @Test
    void getAllPostsByHashtagName() {

    }
}
