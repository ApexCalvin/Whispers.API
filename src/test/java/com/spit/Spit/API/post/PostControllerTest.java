package com.spit.Spit.API.post;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.account.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @InjectMocks
    PostController subject;

    @Mock
    PostService postService;

    @Mock
    AccountService accountService;

    @Test
    void createPost(){
        CreatePostDTO postDTO = new CreatePostDTO();
        postDTO.setAccountId(1L);
        postDTO.setMessage("Test");
        when(accountService.getAccountById(postDTO.getAccountId())).thenReturn(new Account());

        ResponseEntity<String> actual = subject.createPost(postDTO);

        verify(postService).createPost(any(CreatePostDTO.class));
        assertThat(actual.getBody()).isEqualTo("Post has been successfully saved.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void createPost_AccountNotFound(){
        CreatePostDTO postDTO = new CreatePostDTO();
        when(accountService.getAccountById(postDTO.getAccountId())).thenReturn(null);

        ResponseEntity<String> actual = subject.createPost(postDTO);

        assertThat(actual.getBody()).isEqualTo("Associated account does not exist.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getPostById(){
        long id = 1L;
        Post post = new Post();
        when(postService.getPostById(id)).thenReturn(post);

        ResponseEntity<Post> actual = subject.getPostById(id);

        assertThat(actual.getBody()).isEqualTo(post);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getPostById_notFound(){
        long id = 2L;
        when(postService.getPostById(id)).thenReturn(null);

        ResponseEntity<Post> actual = subject.getPostById(id);

        assertThat(actual.getBody()).isEqualTo(null);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getAllPost(){
        List<Post> postList = new ArrayList<>();
        postList.add(new Post());
        when(postService.getAllPosts()).thenReturn(postList);

        ResponseEntity<List<Post>> actual = subject.getAllPosts();

        assertThat(actual.getBody()).isEqualTo(postList);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getAllPostDesc(){
        List<GetPostDTO> postList = new ArrayList<>();
        postList.add(new GetPostDTO());
        when(postService.getAllPostsDesc()).thenReturn(postList);

        ResponseEntity<List<GetPostDTO>> actual = subject.getAllPostsDesc();

        assertThat(actual.getBody()).isEqualTo(postList);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getAllPostByHandleDesc(){
        String handle = "Starlight";
        Account account = new Account("Her", "Starlight");
        when(accountService.getAccountByHandle(handle)).thenReturn(account);

        List<GetPostDTO> posts = new ArrayList<>();
        posts.add(new GetPostDTO());
        posts.add(new GetPostDTO());
        when(postService.getPostsByHandleDesc(handle)).thenReturn(posts);

        ResponseEntity<List<GetPostDTO>> actual = subject.getAllPostsByHandleDesc(handle);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo(posts);
    }

    @Test
    void getAllPostByHandleDesc_AccountNotFound(){
        String handle = "Translucent";
        when(accountService.getAccountByHandle(handle)).thenReturn(null);

        ResponseEntity<List<GetPostDTO>> actual = subject.getAllPostsByHandleDesc(handle);

        verify(accountService).getAccountByHandle(any(String.class));
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deletedPostById(){
        long id = 1L;
        Post post = new Post();
        post.setId(id);
        when(postService.getPostById(id)).thenReturn(post);

        ResponseEntity<String> actual = subject.deletePostById(id);

        verify(postService).deletePostById(any(Long.class));
        assertThat(actual.getBody()).isEqualTo("Post has been successfully deleted.");
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteByPostId_notFound() {
        long id = 1L;
        when(postService.getPostById(id)).thenReturn(null);

        ResponseEntity<String> actual = subject.deletePostById(id);

        assertThat(actual.getBody()).isEqualTo(null);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    void putPostById(){}

    void patchPostById(){}
}
