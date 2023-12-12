package com.spit.Spit.API.service;

import com.spit.Spit.API.dto.CreatePostDTO;
import com.spit.Spit.API.dto.GetPostDTO;
import com.spit.Spit.API.model.Account;
import com.spit.Spit.API.service.AccountService;
import com.spit.Spit.API.model.Hashtag;
import com.spit.Spit.API.service.HashtagService;
import com.spit.Spit.API.model.Post;
import com.spit.Spit.API.repository.PostRepository;
import com.spit.Spit.API.service.PostService;
import com.spit.Spit.API.tool.DtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

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
        CreatePostDTO createPostDTO = DtoMapper.buildCreatePostDTO(1L, "Test", new ArrayList<String>());
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
        ArrayList<String> hashtagNames = new ArrayList<>(Arrays.asList("Hash1", "Hash2"));
        CreatePostDTO createPostDTO = DtoMapper.buildCreatePostDTO(1L, "Test", hashtagNames);
        createPostDTO.setHashtags(hashtagNames);
        Account expectedAccount = new Account();
        when(accountService.getAccountById(1L)).thenReturn(expectedAccount);
        Set<Hashtag> hashtags = DtoMapper.buildHashtagSet("Hash1", "Hash2");
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
        Long id = 1L;
        List<GetPostDTO> posts = new ArrayList<>(Arrays.asList(new GetPostDTO(), new GetPostDTO()));
        when(postRepository.getLikedPostsByAccountId(id)).thenReturn(posts);

        List<GetPostDTO> actual = subject.getLikedPostsByAccountId(id);

        assertThat(actual).isEqualTo(posts);
    }

    @Test
    void getAllPostsByHashtagName() {
        String hashtag = "hero";
        List<Post> posts = new ArrayList<>(Arrays.asList(new Post(), new Post()));
        when(postRepository.findByHashtags_NameOrderByDateDesc(hashtag)).thenReturn(posts);

        List<Post> actual = subject.getAllPostsByHashtagName(hashtag);

        assertThat(actual).isEqualTo(posts);
    }
}
