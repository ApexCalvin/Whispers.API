package com.spit.Spit.API.post;

import com.spit.Spit.API.account.Account;
import com.spit.Spit.API.account.AccountService;
import com.spit.Spit.API.comment.CommentService;
import com.spit.Spit.API.comment.GetCommentDTO;
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
    private final CommentService commentService;

    public PostController(PostService postService, AccountService accountService, CommentService commentService) {
        this.postService = postService;
        this.accountService = accountService;
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<String> createPost(@Valid @RequestBody CreatePostDTO postDTO) {
        Account account = accountService.getAccountById(postDTO.getAccountId());

        if(account == null) return new ResponseEntity<>("Associated account does not exist.", HttpStatus.NOT_FOUND);

        postService.createPost(postDTO);
        return new ResponseEntity<>("Post has been successfully saved.", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);

        if(post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/desc")
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
            return new ResponseEntity<>("Post has been successfully deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<GetCommentDTO>> getAllCommentsByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
