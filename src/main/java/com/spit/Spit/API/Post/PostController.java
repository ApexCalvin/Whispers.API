package com.spit.Spit.API.Post;

import com.spit.Spit.API.Account.Account;
import com.spit.Spit.API.Account.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final AccountService accountService;

    public PostController(PostService postService, AccountService accountService) {
        this.postService = postService;
        this.accountService = accountService;
    }

    @PostMapping("/add")
    //public ResponseEntity<String> createPost(@Valid @RequestBody CreatePostDTO postDTO) {
    public ResponseEntity<String> createPost(@RequestBody CreatePostDTO postDTO) {

        if(postDTO.getAccountId() == null || postDTO.getMessage() == null) return new ResponseEntity<>("All fields are required.", HttpStatus.BAD_REQUEST);

        Account account = accountService.getAccountById(postDTO.getAccountId());

        if(account == null) return new ResponseEntity<>("Associated account does not exist.", HttpStatus.NOT_FOUND);

        postService.createPost(postDTO);
        return new ResponseEntity<>("Post has been successfully saved.", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);

        if(post != null) {
            System.out.println("In if: " +post);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/desc/all")
    public ResponseEntity<List<GetPostDTO>> getAllPostsDesc() {
        return new ResponseEntity<>(postService.getAllPostsDesc(), HttpStatus.OK);
    }

    @GetMapping("/handle/{handle}")
    public ResponseEntity<List<GetPostDTO>> getAllPostsByHandleDesc(@PathVariable String handle) {
        Optional<Account> exist = Optional.ofNullable(accountService.getAccountByHandle(handle));

        if(exist.isPresent()) {
            List<GetPostDTO> posts = postService.getPostsByHandleDesc(handle);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);

        if(post != null) {
            postService.deletePostById(id);
            return new ResponseEntity<>("Account has been successfully deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> putPostById() {
        return null;
    }

    public ResponseEntity<String> patchPostById() {
        return null;
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
}
