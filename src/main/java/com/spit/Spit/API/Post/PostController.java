package com.spit.Spit.API.Post;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostServices postServices;
    private final AccountServices accountServices;

    public PostController(PostServices postServices, AccountServices accountServices) {
        this.postServices = postServices;
        this.accountServices = accountServices;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createPost(@Valid @RequestBody CreatePostDTO postDTO) {

        if (postDTO.getAccountId() == null) {
            String message = "Account id cannot be null. Please enter associated account id.";
            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
        }

        Optional<Account> exist = accountServices.getAccountById(postDTO.getAccountId());

        if(exist.isEmpty()) {
            String message = "Associated account does not exist.";
            return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
        }

        Post post = new Post();
        post.setAccount(exist.get());
        post.setMessage(postDTO.getMessage());

        String message = postServices.createPost(post);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> exist = postServices.getPostById(id);
        return exist.map(post -> new ResponseEntity<>(post, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all/handle/{handle}")
    public ResponseEntity<List<GetPostDTO>> getPostByHandle(@PathVariable String handle) {
        Optional<Account> exist = accountServices.getAccountByHandle(handle);

        if(exist.isPresent()) {
            List<GetPostDTO> posts = postServices.getPostByHandleDESC(handle);

            return new ResponseEntity<>(posts, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public List<Post> getAllPosts() { return postServices.getAllPost(); }

    @GetMapping("/desc/all")
    public List<GetPostDTO> getAllPostsDESC() { return postServices.getAllPostDESC(); }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id) {

        Optional<Post> exist = postServices.getPostById(id);

        if(exist.isPresent()) {
            postServices.deletePostById(id);
            String message = "Post with id " +id+ " has been deleted.";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
