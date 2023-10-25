package com.spit.Spit.API.Post;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostServices postServices;

    @Autowired
    AccountServices accountServices;

    @PostMapping("/add")
    public ResponseEntity<String> createPost(@RequestBody CreatePostDTO postDTO) {

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

    @GetMapping("/all")
    public List<Post> getAllPosts() { return postServices.getAllPost(); }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedPostById(@PathVariable Long id) {

        Optional<Post> exist = postServices.getPostById(id);

        if(exist.isPresent()) {
            postServices.deletePostById(id);
            String message = "Post with id " +id+ " has been deleted.";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
