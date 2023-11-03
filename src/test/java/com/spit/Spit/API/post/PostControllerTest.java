package com.spit.Spit.API.post;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountRepository;
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

import java.util.Date;
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

        assertThat(re.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        verify(postServices).createPost(any(Post.class));
    }

    void createPost_AccountNotFound(){}

    void createPost_nullableField(){}

    void createPost_emptyField(){}

    void getPostById() throws Exception {
//        Post post = new Post(1, messge,)
//
//        when(userService.getUserById(1)).thenReturn(user)
//
//        mockMVC.perform(get("/users/1")).andExpect(status().isOK));
    }

    void getPostById_AccountNotFound(){}

    void getPostByHandle(){}

    void getPostByHandle_AccountNotFound(){}

    void getAllPost(){}

    void getAllPostDesc(){}

    void deletedPostById(){}

    void deleteByPostId_AccountNotFound() {}
}
