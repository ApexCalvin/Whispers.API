package com.spit.Spit.API.post;

import com.spit.Spit.API.Account.AccountService;
import com.spit.Spit.API.Post.PostController;
import com.spit.Spit.API.Post.PostService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @InjectMocks
    PostController postController;

    @Mock
    PostService postServices;

    @Mock
    AccountService accountServices;

//    @Test
//    void createPost_AccountNotFound(){
//        CreatePostDTO postDTO = new CreatePostDTO();
//        postDTO.setAccountId(1L);
//        postDTO.setMessage("Hello");
//
//        when(accountServices.getAccountById(postDTO.getAccountId()))
//                .thenReturn(null);
//
//        ResponseEntity<String> re = postController.createPost(postDTO);
//
//        assertThat(re.getBody()).isEqualTo("Associated account does not exist.");
//        assertThat(re.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    void createPost(){
//        //created DTO --> Post Request
//        CreatePostDTO postDTO = new CreatePostDTO();
//        postDTO.setAccountId(1L);
//        postDTO.setMessage("Hello");
//
//        //HardCode: Assume return object
//        Account account = new Account("John", "Doe");
//        account.setAccount_id(1L);
//        Optional<Account> optionalAcc = Optional.of(account);
//        when(accountServices.getAccountById(postDTO.getAccountId())).thenReturn(account);
//
//        ResponseEntity<String> actual = postController.createPost(postDTO);
//
//        verify(postServices).createPost(any(Post.class));
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//    }
//
//    @Test
//    void getPostById(){
//        long id = 1L;
//        Post post = new Post();
//        post.setId(id);
//        Optional<Post> optional = Optional.of(post);
//        when(postServices.getPostById(id)).thenReturn(optional);
//
//        ResponseEntity<Post> actual = postController.getPostById(id);
//
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(actual.getBody()).isEqualTo(post);
//    }
//
//    @Test
//    void getPostById_AccountNotFound(){
//        Long id =3L ;
//        when(postServices.getPostById(id)).thenReturn(Optional.empty());
//
//        ResponseEntity<Post> actual = postController.getPostById(id);
//
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    void getPostByHandle(){
//        String handle = "Starlight";
//        Account account = new Account("Her", "Starlight");
//        Optional<Account> optionalAcc = Optional.of(account);
//        when(accountServices.getAccountByHandle(handle)).thenReturn(account);
//
//        List<GetPostDTO> posts = new ArrayList<>();
//        posts.add(new GetPostDTO());
//        posts.add(new GetPostDTO());
//        when(postServices.getPostsByHandleDesc(handle)).thenReturn(posts);
//
//        ResponseEntity<List<GetPostDTO>> actual = postController.getPostsByHandle(handle);
//
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(actual.getBody()).isEqualTo(posts);
//    }
//
//    @Test
//    void getPostByHandle_AccountNotFound(){
//        String handle = "Translucent";
//        when(accountServices.getAccountByHandle(handle)).thenReturn(null);
//
//        ResponseEntity<List<GetPostDTO>> actual = postController.getPostsByHandle(handle);
//
//        verify(accountServices).getAccountByHandle(any(String.class));
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    void getAllPost(){
//        ResponseEntity<List<Post>> actual = postController.getAllPosts();
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//
//    @Test
//    void getAllPostDesc(){
//        ResponseEntity<List<GetPostDTO>> actual = postController.getAllPostsDESC();
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//
//    @Test
//    void deleteByPostId_AccountNotFound() {
//        long id = 1L;
//
//        ResponseEntity<String> actual = postController.deletePostById(id);
//
//        verify(postServices).getPostById(any(Long.class));
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    void deletedPostById(){
//        long id = 1L;
//        Post post = new Post();
//        post.setId(id);
//        Optional<Post> optionalPost = Optional.of(post);
//        when(postServices.getPostById(id)).thenReturn(optionalPost);
//
//        ResponseEntity<String> actual = postController.deletePostById(id);
//
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }

    void editPostById_usingPut(){}

    void editPostById_usingPatch(){}

}
