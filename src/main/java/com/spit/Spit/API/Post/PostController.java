package com.spit.Spit.API.Post;

import com.spit.Spit.API.Account.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final AccountService accountService;

    public PostController(PostService postService, AccountService accountService) {
        this.postService = postService;
        this.accountService = accountService;
    }

//    @PostMapping("/add")
//    public ResponseEntity<String> createPost(@Valid @RequestBody CreatePostDTO postDTO) {
//
//        Optional<Account> exist = Optional.ofNullable(accountServices.getAccountById(postDTO.getAccountId()));
//
//        if(exist.isEmpty()) {
//            String message = "Associated account does not exist.";
//            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
//        }
//
//        Post post = new Post();
//        post.setAccount(exist.get());
//        post.setMessage(postDTO.getMessage());
//
//        String message = postServices.createPost(post);
//        return new ResponseEntity<>(message, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
//        Optional<Post> exist = postServices.getPostById(id);
//        return exist.map(post -> new ResponseEntity<>(post, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @GetMapping("/handle/{handle}")
//    public ResponseEntity<List<GetPostDTO>> getPostsByHandle(@PathVariable String handle) {
//        Optional<Account> exist = Optional.ofNullable(accountServices.getAccountByHandle(handle));
//
//        if(exist.isPresent()) {
//            List<GetPostDTO> posts = postServices.getPostsByHandleDesc(handle);
//
//            return new ResponseEntity<>(posts, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Post>> getAllPosts() {
//        return new ResponseEntity<>(postServices.getAllPosts(), HttpStatus.OK);
//    }
//
//    @GetMapping("/desc/all")
//    public ResponseEntity<List<GetPostDTO>> getAllPostsDESC() {
//        return new ResponseEntity<>(postServices.getAllPostsDesc(), HttpStatus.OK); }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletePostById(@PathVariable Long id) {
//
//        Optional<Post> exist = postServices.getPostById(id);
//
//        if(exist.isPresent()) {
//            postServices.deletePostById(id);
//            String message = "Post with id " +id+ " has been deleted.";
//            return new ResponseEntity<>(message, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
