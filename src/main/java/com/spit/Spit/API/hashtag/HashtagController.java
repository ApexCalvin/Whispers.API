package com.spit.Spit.API.hashtag;

import com.spit.Spit.API.post.GetPostDTO;
import com.spit.Spit.API.post.Post;
import com.spit.Spit.API.post.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/hashtag")
public class HashtagController {

    private final HashtagService hashtagService;

    private final PostService postService;

    public HashtagController(HashtagService hashtagService, PostService postService) {
        this.hashtagService = hashtagService;
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Hashtag>> getAllHashtags() {
        return ResponseEntity.ok(hashtagService.getAllHashtags());
    }

    @GetMapping("/{hashtag}/posts")
    public ResponseEntity<List<Post>> getAllPostByHashtagNameDesc(@PathVariable String hashtag) {
        return ResponseEntity.ok(postService.getAllPostsByHashtag(hashtag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHashtagById(@PathVariable Long id) {
        Hashtag hashtag = hashtagService.getHashtagById(id);

        if(hashtag != null) {
            hashtagService.deleteHashtagById(id);
            return new ResponseEntity<>("Hashtag has been successfully deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
