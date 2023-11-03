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

        ResponseEntity<String> re = postController.createPost(postDTO);

        verify(postServices).createPost(any(Post.class));
        assertThat(re.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    void createPost_AccountNotFound(){}

    void createPost_nullableField(){}

    void createPost_emptyField(){}
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

    void getPostById_AccountNotFound(){}

    void getPostByHandle(){}

    void getPostByHandle_AccountNotFound(){}

    void getAllPost(){}

    void getAllPostDesc(){}

    void deletedPostById(){}

    void deleteByPostId_AccountNotFound() {}
}
