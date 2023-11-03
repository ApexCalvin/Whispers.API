package com.spit.Spit.API.post;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountServices;
import com.spit.Spit.API.Post.CreatePostDTO;
import com.spit.Spit.API.Post.Post;
import com.spit.Spit.API.Post.PostController;
import com.spit.Spit.API.Post.PostServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @InjectMocks
    PostController postController;

    @Mock
    PostServices postServices;

    @Mock
    AccountServices accountServices;

    @Test
    void createPost_AccountNotFound(){
        CreatePostDTO postDTO = new CreatePostDTO();
        postDTO.setAccountId(1L);
        postDTO.setMessage("Hello");

        when(accountServices.getAccountById(postDTO.getAccountId()))
                .thenReturn(Optional.empty());

        ResponseEntity<String> re = postController.createPost(postDTO);

        assertThat(re.getBody()).isEqualTo("Associated account does not exist.");
        assertThat(re.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void createPost(){
        //created DTO --> Post Request
        CreatePostDTO postDTO = new CreatePostDTO();
        postDTO.setAccountId(1L);
        postDTO.setMessage("Hello");

        //HardCode: Assume return object
        Account account = new Account("John", "Doe");
        account.setAccount_id(1L);
        Optional<Account> optionalAcc = Optional.of(account);
        when(accountServices.getAccountById(postDTO.getAccountId())).thenReturn(optionalAcc);

        ResponseEntity<String> actual = postController.createPost(postDTO);

        verify(postServices).createPost(any(Post.class));
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void getPostById(){
        long id = 1L;
        Post post = new Post();
        post.setId(id);
        Optional<Post> optional = Optional.of(post);
        when(postServices.getPostById(id)).thenReturn(optional);

        ResponseEntity<Post> actual = postController.getPostById(id);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo(post);
    }

    @Test
    void getPostById_AccountNotFound(){
        Long id =3L ;
        when(postServices.getPostById(id)).thenReturn(Optional.empty());

        ResponseEntity<Post> actual = postController.getPostById(id);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    void getPostByHandle(){}

    void getPostByHandle_AccountNotFound(){}

    void getAllPost(){}

    void getAllPostDesc(){}

    void deletedPostById(){}

    void deleteByPostId_AccountNotFound() {}
}
