package com.spit.Spit.API.Like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping
    public ResponseEntity<PostLike> createLike(@RequestBody CreateLikeDTO likeDTO) {
        PostLike createdPostLike = likeService.createLike(likeDTO);
        return new ResponseEntity<>(createdPostLike, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostLike> getLikeById(@PathVariable Long id) {
        PostLike postLike = likeService.getLikeById(id);
        if (postLike != null) {
            return new ResponseEntity<>(postLike, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/handle/{handle}/post/{postId}")
    public ResponseEntity<PostLike> getLikeByHandleAndPost(@PathVariable String handle, @PathVariable Long postId) {
        PostLike postLike = likeService.getLikeByHandleAndPost(handle, postId);
        if (postLike != null) {
            return new ResponseEntity<>(postLike, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PostLike>> getAllLikes() {
        List<PostLike> postLikes = likeService.getAllLikes();
        return new ResponseEntity<>(postLikes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
        boolean deleted = likeService.deleteLike(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

