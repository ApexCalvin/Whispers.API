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

//    @PostMapping
//    public ResponseEntity<String> createHashtag(@Valid @RequestBody Hashtag hashtag){
//        boolean hashtagAvailability = hashtagService.isHashtagAvailable(hashtag.getName());
//
//        if(hashtagAvailability) {
//            Hashtag found = hashtagService.getHashtagByName(hashtag.getName());
//            hashtag.setId(found.getId());
//            hashtagService.createHashtag(hashtag); //saves
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
//
//        hashtagService.createHashtag(hashtag); //creates new
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    public ResponseEntity<String> createHashtags(@Valid @RequestBody Set<Hashtag> hashtags){
//        for (Hashtag h : hashtags) {
//            createHashtag(h);
//        }
//        return ResponseEntity.ok("");
//    }

    @GetMapping
    public ResponseEntity<List<Hashtag>> getAllHashtags() {
        return ResponseEntity.ok(hashtagService.getAllHashtags());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<List<Hashtag>> getAllHashtagsOfPost(@PathVariable Long id) {
//        return ResponseEntity.ok(hashtagService.getAllHashtagsByPostId(id));
//    }
    @GetMapping("/{hashtag}/posts")
    public ResponseEntity<List<Post>> getAllPostByHashtagNameDesc(@PathVariable String hashtag) {
        return ResponseEntity.ok(postService.getAllPostsByHashtag(hashtag));
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
